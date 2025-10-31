package foodstore.foodStore.service.impl;

import foodstore.foodStore.entity.Categoria;
import foodstore.foodStore.entity.Producto;
import foodstore.foodStore.entity.dto.Producto.ProductoCreateDTO;
import foodstore.foodStore.entity.dto.Producto.ProductoDTO;
import foodstore.foodStore.entity.dto.Producto.ProductoEdit;
import foodstore.foodStore.mapper.CategoriaMapper;
import foodstore.foodStore.mapper.ProductoMapper;
import foodstore.foodStore.repository.CategoriaRepository;
import foodstore.foodStore.repository.ProductoRepository;
import foodstore.foodStore.service.ProductoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImp implements ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    CategoriaMapper categoriaMapper;

    @Autowired
    ProductoMapper productoMapper;

    @Override
    public ProductoDTO save(ProductoCreateDTO p){

        Categoria categoria = categoriaRepository.findByNombre(p.categoria());

        Producto producto = productoMapper.toEntity(p);

        producto.setCategoria(categoria);
        categoria.getProductos().add(producto);
        productoRepository.save(producto);

        return productoMapper.toDto(producto);
    }

    @Override
    public List<ProductoDTO> findAll(){
        List<ProductoDTO> productos = productoRepository.findAllByOrderByIdAsc()
                .stream()
                .map(productoMapper::toDto)
                .toList();
        return productos;
    }

    @Override
    public boolean productExist(String name){
        for (Producto producto : productoRepository.findAll()){
            if (producto.getNombre().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public ProductoDTO edit(Long id, ProductoEdit p){
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no existente"));
        producto.setNombre(p.nombre());
        producto.setPrecio(p.precio());
        producto.setStock(p.stock());
        producto.setUrl(p.url());
        producto.setDescripcion(p.descripcion());
        Categoria categoriaNueva = categoriaRepository.findByNombre(p.categoria());
        Categoria categoriaVieja = producto.getCategoria();
        if (categoriaVieja != null && !categoriaVieja.getId().equals(categoriaNueva.getId())){
            categoriaVieja.getProductos().remove(producto);
        }
        producto.setCategoria(categoriaNueva);
        if (!categoriaNueva.getProductos().contains(producto)){
            categoriaNueva.getProductos().add(producto);
        }
        productoRepository.save(producto);
        return productoMapper.toDto(producto);

    }

    @Override
    public void delete(Long id){
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        producto.getCategoria().getProductos().remove(producto);
        productoRepository.deleteById(id);
    }
}
