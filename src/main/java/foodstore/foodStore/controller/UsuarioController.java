package foodstore.foodStore.controller;

import foodstore.foodStore.entity.Usuario;
import foodstore.foodStore.entity.dto.Usuario.UsuarioCreate;
import foodstore.foodStore.entity.dto.Usuario.UsuarioDTO;
import foodstore.foodStore.entity.dto.Usuario.UsuarioLogin;
import foodstore.foodStore.mapper.UsuarioMapper;
import foodstore.foodStore.repository.UsuarioRepository;
import foodstore.foodStore.service.UsuarioService;
import foodstore.foodStore.utils.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioMapper usuarioMapper;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLogin userL){

            return usuarioService.login(userL.email(), userL.contrasena())
                    .<ResponseEntity<?>>map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales invalidas"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsuarioCreate userC){

        if (usuarioRepository.existsByEmail(userC.email())){
            return ResponseEntity.badRequest().body("Este correo ya se encuentra registrado");
        }

        UsuarioDTO user = usuarioService.save(userC);
        return ResponseEntity.status(201).body("Usuario registrado correctamente");

    }

}
