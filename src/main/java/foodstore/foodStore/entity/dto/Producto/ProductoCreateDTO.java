package foodstore.foodStore.entity.dto.Producto;

public record ProductoCreateDTO(
        String nombre,
        double precio,
        int stock,
        String url,
        String categoria
) {}
