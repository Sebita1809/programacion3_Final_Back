package foodstore.foodStore.service.impl;
import foodstore.foodStore.entity.Estado;
import foodstore.foodStore.entity.Pedido;
import foodstore.foodStore.entity.Producto;
import foodstore.foodStore.entity.Usuario;
import foodstore.foodStore.entity.dto.Pedido.PedidoCreate;
import foodstore.foodStore.entity.dto.Pedido.PedidoDTO;
import foodstore.foodStore.entity.dto.Pedido.PedidoEditStatus;
import foodstore.foodStore.mapper.PedidoMapper;
import foodstore.foodStore.repository.PedidoRepository;
import foodstore.foodStore.repository.ProductoRepository;
import foodstore.foodStore.repository.UsuarioRepository;
import foodstore.foodStore.service.PedidoService;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImp implements PedidoService {

    @Autowired
    PedidoMapper pedidoMapper;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public PedidoDTO editStatus(PedidoEditStatus p) {
        List<String> estados = List.of("pendiente", "confirmado", "cancelado", "terminado");
        Pedido pedido = pedidoRepository.findById(p.id())
                .orElseThrow(() -> new NullPointerException("Pedido no encontrado"));
        if (estados.contains(p.estado().toLowerCase())){
            pedido.setEstado(Estado.valueOf(p.estado().toUpperCase()));
        }else{
            throw new IllegalArgumentException("Estado ingresado no valido");
        }
        pedidoRepository.save(pedido);
        return pedidoMapper.toDto(pedido);
    }

    @Override
    public PedidoDTO save(PedidoCreate p) {
        Usuario usuario = usuarioRepository.findById(p.idUsuario())
                .orElseThrow(() -> new NullPointerException("Usuario no encontrado"));
        if (!checkPayment(p.metodoPago())){
            throw new IllegalArgumentException("Metodo de pago no valido");
        }
        Pedido pedido = pedidoMapper.toEntity(p);
        pedido = pedidoRepository.save(pedido);
        usuario.getPedidos().add(pedido);
        usuarioRepository.save(usuario);
        return pedidoMapper.toDto(pedido);
    }

    @Override
    public List<PedidoDTO> findAll() {
        List<PedidoDTO> pedidos = pedidoRepository.findAllByOrderByIdAsc()
                .stream()
                .map(pedidoMapper::toDto)
                .toList();

        return pedidos;
    }

    @Override
    public List<PedidoDTO> userOrders(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Usuario no encontrado"));
        List<Pedido> pedidosDeUsuario = usuario.getPedidos();

        List<PedidoDTO> pedidosDTO = pedidosDeUsuario.stream()
                .map(pedidoMapper::toDto)
                .toList();
        return pedidosDTO;
    }

    @Override
    public boolean checkPayment(String cp){
        List<String> metodos = List.of("efectivo", "tarjeta", "transferencia");

        return metodos.contains(cp.toLowerCase());
    }
}
