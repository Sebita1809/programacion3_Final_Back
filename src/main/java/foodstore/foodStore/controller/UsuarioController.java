package foodstore.foodStore.controller;

import foodstore.foodStore.entity.dto.Usuario.UsuarioCreate;
import foodstore.foodStore.entity.dto.Usuario.UsuarioDTO;
import foodstore.foodStore.entity.dto.Usuario.UsuarioLogin;
import foodstore.foodStore.mapper.UsuarioMapper;
import foodstore.foodStore.repository.UsuarioRepository;
import foodstore.foodStore.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/")
    public ResponseEntity<?> findAll(){
        try{
            return ResponseEntity.ok(usuarioService.findAll());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLogin userL){

            return usuarioService.login(userL.email(), userL.contrasena())
                    .<ResponseEntity<?>>map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales invalidas"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsuarioCreate userC){

        if (usuarioService.usuarioExists(userC.email())){
            return ResponseEntity.badRequest().body("Este correo ya se encuentra registrado");
        }

        usuarioService.save(userC);
        return ResponseEntity.status(201).body("Usuario registrado correctamente");
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try {
            usuarioService.deleteUser(id);
            return ResponseEntity.ok("Usuario eliminado exitosamente");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
