package foodstore.foodStore.repository;

import foodstore.foodStore.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Producto existsByNombre(String name);
    List<Producto> findAllByOrderByIdAsc();

}
