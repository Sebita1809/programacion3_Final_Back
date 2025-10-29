package foodstore.foodStore.service;

import foodstore.foodStore.entity.Categoria;
import foodstore.foodStore.entity.dto.Categoria.CategoriaDTO;
import foodstore.foodStore.entity.dto.Categoria.CategoriaEditDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoriaService {

    public CategoriaDTO save (CategoriaDTO c);
    public CategoriaEditDTO edit (CategoriaEditDTO c);
    public List<CategoriaDTO> findAll();
    boolean categoryExist (String name);
}
