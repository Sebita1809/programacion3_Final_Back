package foodstore.foodStore.entity.dto.Categoria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public record CategoriaView(
        Long id,
        String nombre
) {
}
