package foodstore.foodStore.entity.dto.Producto;

public record ProductoCreateDTO(
        String nombre,
        double precio,
        String categoria,
        int stock
) {}
