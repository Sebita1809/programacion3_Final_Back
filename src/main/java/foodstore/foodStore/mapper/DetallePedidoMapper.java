package foodstore.foodStore.mapper;

import foodstore.foodStore.entity.DetallePedido;
import foodstore.foodStore.entity.Producto;
import foodstore.foodStore.entity.dto.DetallePedido.DetallePedidoCreate;
import foodstore.foodStore.entity.dto.DetallePedido.DetallePedidoDTO;
import foodstore.foodStore.repository.DetallePedidoRepository;
import foodstore.foodStore.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DetallePedidoMapper {

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    DetallePedidoRepository detallePedidoRepository;


    public DetallePedido toEntity(DetallePedidoCreate detalle){
        DetallePedido detallePedido = new DetallePedido();
        Producto producto = productoRepository.findById(detalle.idProducto())
                .orElseThrow(() -> new NullPointerException("Producto no encontrado"));
        detallePedido.setIdProducto(detalle.idProducto());
        producto.setStock(producto.getStock() - detalle.cantidad());
        detallePedido.setCantidad(detalle.cantidad());
        productoRepository.save(producto);
        detallePedido.setSubtotal(producto.getPrecio() * detalle.cantidad());
        detallePedidoRepository.save(detallePedido);
        return detallePedido;
    }

    public DetallePedidoDTO toDto(DetallePedido detalle){
        Producto producto = productoRepository.findById(detalle.getIdProducto())
                .orElseThrow(() -> new NullPointerException("Producto no encontrado"));
        return new DetallePedidoDTO(
                producto.getNombre(),
                detalle.getCantidad(),
                detalle.getSubtotal()
        );

    }
}
