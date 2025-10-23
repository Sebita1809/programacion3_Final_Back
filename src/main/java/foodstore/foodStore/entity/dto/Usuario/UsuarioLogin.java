package foodstore.foodStore.entity.dto.Usuario;

import foodstore.foodStore.entity.Rol;

public record UsuarioLogin(
    String email,
    String contrasena
) {
}
