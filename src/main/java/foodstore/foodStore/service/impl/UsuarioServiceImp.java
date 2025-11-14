package foodstore.foodStore.service.impl;

import foodstore.foodStore.entity.Usuario;
import foodstore.foodStore.entity.dto.Usuario.UsuarioCreate;
import foodstore.foodStore.entity.dto.Usuario.UsuarioDTO;
import foodstore.foodStore.mapper.UsuarioMapper;
import foodstore.foodStore.repository.UsuarioRepository;
import foodstore.foodStore.service.UsuarioService;
import foodstore.foodStore.utils.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioMapper usuarioMapper;


    @Override
    public UsuarioDTO save(UsuarioCreate userC){

        Usuario user = usuarioMapper.toEntity(userC);

        user = usuarioRepository.save(user);

        return usuarioMapper.toDto(user);
    }


    @Override
    public Optional<UsuarioDTO> login(String email, String password){

        String hashPassword = PasswordHash.hashPassword(password);

        return usuarioRepository.findByEmail(email)
                .filter(user -> hashPassword.equals(user.getContrasena()))
                .map(usuarioMapper::toDto);
    }

    @Override
    public boolean usuarioExists(String email){

        if (usuarioRepository.existsByEmail(email)){
            return true;
        }
        return false;
    }

    @Override
    public List<UsuarioDTO> findAll(){
        List<UsuarioDTO> usuarios = usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDto)
                .toList();

        return usuarios;
    }

    @Override
    public void deleteUser(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Usuario no encontrado"));
        usuario.setEliminado(true);
        usuarioRepository.save(usuario);
    }
}
