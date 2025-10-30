package foodstore.foodStore.entity.dto.Producto;

import foodstore.foodStore.entity.dto.Categoria.CategoriaView;

public record ProductoEdit(
        String nombre,
        double precio,
        int stock,
        String categoria
) {
}
