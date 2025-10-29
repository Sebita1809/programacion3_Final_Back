package foodstore.foodStore.repository;

import foodstore.foodStore.entity.Categoria;
import foodstore.foodStore.entity.dto.Categoria.CategoriaDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    boolean existsByNombre(String name);
    Optional<CategoriaDTO> findByNombre(String name);
}
