package foodstore.foodStore.service;

import foodstore.foodStore.entity.DetallePedido;
import foodstore.foodStore.entity.dto.DetallePedido.DetallePedidoCreate;
import foodstore.foodStore.entity.dto.Producto.ProductoCreateDTO;
import foodstore.foodStore.entity.dto.Producto.ProductoDTO;
import foodstore.foodStore.entity.dto.Producto.ProductoEdit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductoService {

    ProductoDTO save (ProductoCreateDTO p);
    ProductoDTO edit(Long id, ProductoEdit p);
    void delete(Long id);
    List<ProductoDTO> findAll();
    boolean productExist(String name);
    void reponerStock(List<DetallePedido> pedido);
    boolean checkStock(List<DetallePedidoCreate> detalles);
}
