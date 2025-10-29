package foodstore.foodStore.mapper;

import foodstore.foodStore.entity.Producto;
import foodstore.foodStore.entity.dto.Producto.ProductoDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public Producto toEntity(ProductoDTO p){
        Producto producto = new Producto();
        producto.setId(p.getId());
        producto.setNombre(p.getNombre());
        producto.setPrecio(p.getPrecio());

        return producto;
    }

    public ProductoDTO toDto(Producto p){
        return new ProductoDTO(
                p.getId(),
                p.getNombre(),
                p.getPrecio()
        );
    }
}
