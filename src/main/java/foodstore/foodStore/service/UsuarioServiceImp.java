package foodstore.foodStore.service;

import foodstore.foodStore.entity.Usuario;
import foodstore.foodStore.entity.dto.Usuario.UsuarioCreate;
import foodstore.foodStore.entity.dto.Usuario.UsuarioDTO;
import foodstore.foodStore.mapper.UsuarioMapper;
import foodstore.foodStore.repository.UsuarioRepository;
import foodstore.foodStore.utils.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioService{

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

}
