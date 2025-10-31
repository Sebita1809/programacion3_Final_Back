package foodstore.foodStore.service.impl;

import foodstore.foodStore.entity.Categoria;
import foodstore.foodStore.entity.Producto;
import foodstore.foodStore.entity.dto.Categoria.CategoriaCreate;
import foodstore.foodStore.entity.dto.Categoria.CategoriaDTO;
import foodstore.foodStore.entity.dto.Categoria.CategoriaEditDTO;
import foodstore.foodStore.mapper.CategoriaMapper;
import foodstore.foodStore.repository.CategoriaRepository;
import foodstore.foodStore.repository.ProductoRepository;
import foodstore.foodStore.service.CategoriaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImp implements CategoriaService {

    @Autowired
    CategoriaMapper categoriaMapper;

    @Autowired
    CategoriaRepository categoriaRespository;

    @Autowired
    ProductoRepository productoRepository;


    @Override
    public CategoriaDTO save(CategoriaCreate c){

        Categoria categoria = categoriaMapper.toEntity(c);

        categoriaRespository.save(categoria);

        return categoriaMapper.toDto(categoria);
    }

    @Override
    public boolean categoryExist(String name){
        for(Categoria categoria : categoriaRespository.findAll()){
            if (categoria.getNombre().equals(name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<CategoriaDTO> findAll(){
        List<CategoriaDTO> categorias = categoriaRespository.findAllByOrderByIdAsc().
                stream().
                map(categoriaMapper::toDto).
                collect(Collectors.toList());
        return categorias;
    }

    @Override
    public void delete(Long id){

        categoriaRespository.deleteById(id);
    }

    @Override
    public void edit(Long id, CategoriaEditDTO c) {

        for (Categoria categoria : categoriaRespository.findAll()){
            if (categoria.getId().equals(id)){
                categoria.setNombre(c.nombre());
                categoria.setDescripcion(c.descripcion());
                categoria.setUrl(c.url());
                categoriaRespository.save(categoria);
                categoriaMapper.toDto(categoria);
            }
        }
    }
}
