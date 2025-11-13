package foodstore.foodStore.service.impl;
import foodstore.foodStore.entity.*;
import foodstore.foodStore.entity.dto.DetallePedido.DetallePedidoCreate;
import foodstore.foodStore.entity.dto.Pedido.PedidoCreate;
import foodstore.foodStore.entity.dto.Pedido.PedidoDTO;
import foodstore.foodStore.entity.dto.Pedido.PedidoEditStatus;
import foodstore.foodStore.mapper.PedidoMapper;
import foodstore.foodStore.repository.PedidoRepository;
import foodstore.foodStore.repository.ProductoRepository;
import foodstore.foodStore.repository.UsuarioRepository;
import foodstore.foodStore.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImp implements PedidoService {

    @Autowired
    PedidoMapper pedidoMapper;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    PedidoService pedidoService;

    @Override
    public PedidoDTO editStatus(PedidoEditStatus p) {
        List<String> estados = List.of("pendiente", "confirmado", "cancelado", "terminado");
        Pedido pedido = pedidoRepository.findById(p.id())
                .orElseThrow(() -> new NullPointerException("Pedido no encontrado"));
        if (estados.contains(p.estado().toLowerCase())){
            if (p.estado().equalsIgnoreCase("cancelado")){
                pedidoService.reponerStock(pedido.getDetalles());
                pedido.setEstado(Estado.valueOf(p.estado().toUpperCase()));
            }
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
        if (!checkStock(p.detalles())) {
            throw new IllegalArgumentException("Stock no disponible para alguno de los productos solicitados");

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
    public void deleteOrder(Long id){
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Pedido no encontrado"));
        pedido.setEliminado(true);
        pedidoRepository.save(pedido);
    }

    @Override
    public boolean checkPayment(String cp){
        List<String> metodos = List.of("efectivo", "tarjeta", "transferencia");

        return metodos.contains(cp.toLowerCase());
    }

    @Override
    public boolean checkStock(List<DetallePedidoCreate> detalles){
        for (DetallePedidoCreate detalle : detalles){
            Producto producto = productoRepository.findById(detalle.idProducto())
                    .orElseThrow(() -> new NullPointerException("Produto no encontrado"));
            if (detalle.cantidad() > producto.getStock()){
                return false;
            }
        }
        return true;
    }

    @Override
    public void reponerStock(List<DetallePedido> pedido){
        for (DetallePedido detalle : pedido) {
            Producto producto = productoRepository.findById(detalle.getIdProducto())
                    .orElseThrow(() -> new NullPointerException());
            producto.setStock(detalle.getCantidad());
        }
    }
}
