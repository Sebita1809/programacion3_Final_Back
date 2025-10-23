package foodstore.foodStore.mapper;

import foodstore.foodStore.entity.Rol;
import foodstore.foodStore.entity.Usuario;
import foodstore.foodStore.entity.dto.Usuario.UsuarioCreate;
import foodstore.foodStore.entity.dto.Usuario.UsuarioDTO;
import foodstore.foodStore.utils.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import static foodstore.foodStore.utils.PasswordHash.hashPassword;

@Component
public class UsuarioMapper {


    public Usuario toEntity(UsuarioCreate userC) {
        Usuario user = new Usuario();
        user.setNombre(userC.nombre());
        user.setApellido(userC.apellido());
        user.setEmail(userC.email());
        user.setCelular(userC.celular());
        user.setContrasena(PasswordHash.hashPassword(userC.contrasena()));
        user.setRol(Rol.USUARIO);

        return user;
    }

    public UsuarioDTO toDto (Usuario user) {
        return new UsuarioDTO(
                user.getId(),
                user.getNombre(),
                user.getApellido(),
                user.getEmail(),
                user.getCelular(),
                user.getRol()
        );
    }
}
