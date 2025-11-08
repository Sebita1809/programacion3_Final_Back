package foodstore.foodStore.controller;

import foodstore.foodStore.entity.dto.Categoria.CategoriaCreate;
import foodstore.foodStore.entity.dto.Categoria.CategoriaEditDTO;
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
    public ResponseEntity<?> save(@RequestBody CategoriaCreate categoria){

        if (categoriaService.categoryExist(categoria.nombre())){
            return ResponseEntity.badRequest().body("Esta categoria ya se encuentra registrada");
        }

        categoriaService.save(categoria);
        return ResponseEntity.status(201).body(("Categoria creada exitosamente"));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id){

        if (categoriaService.delete(id)){
            return ResponseEntity.ok("Categoria borrada con exito");
        }
        return ResponseEntity.badRequest().body("Error: esta categoria contiene productos");
    }

    @PatchMapping("/{id}/edit")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody CategoriaEditDTO c){

        try{
            categoriaService.edit(id, c);

            return ResponseEntity.ok("Categoria editada correctamente");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
