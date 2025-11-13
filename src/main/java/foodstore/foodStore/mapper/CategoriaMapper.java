package foodstore.foodStore.mapper;

import foodstore.foodStore.entity.Categoria;
import foodstore.foodStore.entity.Producto;
import foodstore.foodStore.entity.dto.Categoria.CategoriaCreate;
import foodstore.foodStore.entity.dto.Categoria.CategoriaDTO;
import foodstore.foodStore.entity.dto.Producto.ProductoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoriaMapper {

    @Autowired
    ProductoMapper productoMapper;

    public Categoria toEntity(CategoriaCreate c){
        List<Producto> productos = new ArrayList<>();
        Categoria categoria = new Categoria();
        categoria.setNombre(c.nombre());
        categoria.setDescripcion(c.descripcion());
        categoria.setUrl(c.url());
        categoria.setProductos(productos);
        categoria.setEliminado(false);
        return categoria;
    }

    public CategoriaDTO toDto(Categoria c){
        List<ProductoView> productos = new ArrayList<>();
        for (Producto producto : c.getProductos()){
            ProductoView productoView = new ProductoView(producto.getId(), producto.getNombre());
            productos.add(productoView);
        }
        return new CategoriaDTO(
                c.getId(),
                c.getNombre(),
                c.getDescripcion(),
                c.getUrl(),
                productos,
                c.isEliminado()
        );
    }

}
