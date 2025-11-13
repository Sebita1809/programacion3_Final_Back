package foodstore.foodStore.mapper;

import foodstore.foodStore.entity.*;

import foodstore.foodStore.entity.dto.DetallePedido.DetallePedidoCreate;
import foodstore.foodStore.entity.dto.Pedido.PedidoCreate;
import foodstore.foodStore.entity.dto.Pedido.PedidoDTO;
import foodstore.foodStore.repository.ProductoRepository;
import foodstore.foodStore.repository.UsuarioRepository;
import foodstore.foodStore.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;


@Component
public class PedidoMapper {

    @Autowired
    DetallePedidoMapper detallePedidoMapper;

    @Autowired
    UsuarioRepository usuarioRepository;

    public Pedido toEntity(PedidoCreate p){
        Pedido pedido = new Pedido();
        int total = 0;
        pedido.setFecha(LocalDate.now());
        pedido.setDireccion(p.direccion());
        pedido.setTelefono(p.telefono());
        pedido.setMetodoPago(p.metodoPago());
        pedido.setEstado(Estado.PENDIENTE);
        pedido.setIdUsuario(p.idUsuario());
        
        // Recuperar y guardar el nombre del usuario
        Usuario usuario = usuarioRepository.findById(p.idUsuario()).orElse(null);
        if (usuario != null) {
            pedido.setNombreUsuario(usuario.getNombre());
            pedido.setApellidoUsuario(usuario.getApellido());
        }
        
        pedido.setDetalles(p.detalles().stream().map(detallePedidoMapper::toEntity).toList());
        for (DetallePedido detallePedido : pedido.getDetalles()){
            total += detallePedido.getSubtotal();
        }
        pedido.setTotal(total);
        pedido.setEliminado(false);
        return pedido;
    }

    public PedidoDTO toDto(Pedido p){
        String nombreCompleto = "Usuario desconocido";
        
        // Usar los datos guardados en el pedido
        if (p.getNombreUsuario() != null) {
            nombreCompleto = p.getNombreUsuario();
            if (p.getApellidoUsuario() != null && !p.getApellidoUsuario().isEmpty()) {
                nombreCompleto += " " + p.getApellidoUsuario();
            }
        }
        
        return new PedidoDTO(
                p.getId(),
                p.getFecha(),
                p.getEstado(),
                p.getDireccion(),
                p.getTelefono(),
                p.getMetodoPago(),
                p.getTotal(),
                p.getIdUsuario(),
                nombreCompleto.trim(),
                p.getDetalles().stream().map(detallePedidoMapper::toDto).toList(),
                p.isEliminado()
        );
    }
}
