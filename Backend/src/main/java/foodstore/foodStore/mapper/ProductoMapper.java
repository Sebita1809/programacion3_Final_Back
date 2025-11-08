package foodstore.foodStore.mapper;

import foodstore.foodStore.entity.Producto;
import foodstore.foodStore.entity.dto.Categoria.CategoriaView;
import foodstore.foodStore.entity.dto.Producto.ProductoCreateDTO;
import foodstore.foodStore.entity.dto.Producto.ProductoDTO;
import foodstore.foodStore.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    @Autowired
    CategoriaRepository categoriaRepository;

    public Producto toEntity(ProductoCreateDTO p){
        Producto producto = new Producto();
        producto.setNombre(p.nombre());
        producto.setPrecio(p.precio());
        producto.setStock(p.stock());
        producto.setUrl(p.url());
        producto.setDescripcion(p.descripcion());

        return producto;
    }

    public ProductoDTO toDto(Producto p){
        if (p == null) return null;

        CategoriaView categoriaView = null;
        if (p.getCategoria() != null){
            categoriaView = new CategoriaView(p.getCategoria().getId(), p.getCategoria().getNombre());
        }

        return new ProductoDTO(
                p.getId(),
                p.getNombre(),
                p.getPrecio(),
                p.getStock(),
                p.getUrl(),
                p.getDescripcion(),
                categoriaView
        );
    }
}
