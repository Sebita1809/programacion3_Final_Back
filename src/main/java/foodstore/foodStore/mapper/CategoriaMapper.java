package foodstore.foodStore.mapper;

import foodstore.foodStore.entity.Categoria;
import foodstore.foodStore.entity.Producto;
import foodstore.foodStore.entity.dto.Categoria.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoriaMapper {

    @Autowired
    ProductoMapper productoMapper;

    public Categoria toEntity(CategoriaDTO c){
        Categoria categoria = new Categoria();
        categoria.setId(c.getId());
        categoria.setNombre(c.getNombre());
        categoria.setDescripcion(c.getDescripcion());
        categoria.setUrl(c.getUrl());
        List<Producto> productos = c.getProductos() == null ?
                Collections.emptyList() :
                c.getProductos()
                .stream()
                .map(productoMapper::toEntity).collect(Collectors.toList());
        categoria.setProductos(productos);

        return categoria;
    }

    public CategoriaDTO toDto(Categoria c){

        return new CategoriaDTO(
                c.getId(),
                c.getNombre(),
                c.getDescripcion(),
                c.getUrl(),
                c.getProductos().stream().map(productoMapper::toDto).collect(Collectors.toList())
        );
    }

}
