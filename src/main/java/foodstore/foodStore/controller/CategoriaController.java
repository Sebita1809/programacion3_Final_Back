package foodstore.foodStore.controller;

import foodstore.foodStore.entity.dto.Categoria.CategoriaDTO;
import foodstore.foodStore.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/")
    public ResponseEntity<?> findAll(){
        try {
            return ResponseEntity.ok(categoriaService.findAll());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody CategoriaDTO categoria){

        if (categoriaService.categoryExist(categoria.getNombre())){
            return ResponseEntity.badRequest().body("Esta categoria ya se encuentra registrada");
        }

        categoriaService.save(categoria);
        return ResponseEntity.status(201).body(("Categoria creada exitosamente"));
    }

}
