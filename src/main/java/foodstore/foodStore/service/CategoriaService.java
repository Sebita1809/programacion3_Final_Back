package foodstore.foodStore.service;

import foodstore.foodStore.entity.Categoria;
import foodstore.foodStore.entity.dto.Categoria.CategoriaCreate;
import foodstore.foodStore.entity.dto.Categoria.CategoriaDTO;
import foodstore.foodStore.entity.dto.Categoria.CategoriaEditDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoriaService {

    CategoriaDTO save (CategoriaCreate c);
    void edit(Long id, CategoriaEditDTO c);
    List<CategoriaDTO> findAll();
    boolean delete(Long id);
    boolean categoryExist (String name);
}
