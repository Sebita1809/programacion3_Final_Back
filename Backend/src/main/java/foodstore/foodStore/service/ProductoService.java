package foodstore.foodStore.service;

import foodstore.foodStore.entity.Producto;
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
    boolean checkStock(Long idProduct, int cantidad);
}
