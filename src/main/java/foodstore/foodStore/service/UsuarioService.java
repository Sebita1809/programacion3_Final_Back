package foodstore.foodStore.service;

import foodstore.foodStore.entity.Usuario;
import foodstore.foodStore.entity.dto.Usuario.UsuarioCreate;
import foodstore.foodStore.entity.dto.Usuario.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    public UsuarioDTO save (UsuarioCreate u);
    Optional<UsuarioDTO> login (String email, String password);
    List<UsuarioDTO> findAll();
    boolean usuarioExists(String email);
}
