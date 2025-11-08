package foodstore.foodStore.entity.dto.Usuario;

import foodstore.foodStore.entity.Rol;

public record UsuarioCreate(
        String nombre,
        String apellido,
        String email,
        int celular,
        String contrasena,
        Rol rol
) {
}
