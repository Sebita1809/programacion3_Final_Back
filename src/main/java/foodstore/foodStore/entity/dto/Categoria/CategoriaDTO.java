package foodstore.foodStore.entity.dto.Categoria;

import foodstore.foodStore.entity.dto.Producto.ProductoView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private String url;
    private List<ProductoView> productos;
}
