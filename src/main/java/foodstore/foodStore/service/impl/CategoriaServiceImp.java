package foodstore.foodStore.service.impl;

import foodstore.foodStore.entity.Categoria;
import foodstore.foodStore.entity.dto.Categoria.CategoriaDTO;
import foodstore.foodStore.entity.dto.Categoria.CategoriaEditDTO;
import foodstore.foodStore.mapper.CategoriaMapper;
import foodstore.foodStore.repository.CategoriaRepository;
import foodstore.foodStore.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImp implements CategoriaService {

    @Autowired
    CategoriaMapper categoriaMapper;

    @Autowired
    CategoriaRepository categoriaRespository;


    @Override
    public CategoriaDTO save(CategoriaDTO c){

        Categoria categoria = categoriaMapper.toEntity(c);

        categoria = categoriaRespository.save(categoria);

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
        List<CategoriaDTO> categorias = categoriaRespository.findAll().
                stream().
                map(categoriaMapper::toDto).
                collect(Collectors.toList());
        return categorias;
    }

    @Override
    public CategoriaEditDTO edit(CategoriaEditDTO c) {
        return null;
    }
}
