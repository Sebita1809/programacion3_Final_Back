package foodstore.foodStore.controller;

import foodstore.foodStore.entity.dto.Producto.ProductoCreateDTO;
import foodstore.foodStore.entity.dto.Producto.ProductoEdit;
import foodstore.foodStore.service.CategoriaService;
import foodstore.foodStore.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/")
    public ResponseEntity<?> findAll(){
        try{
            return ResponseEntity.ok(productoService.findAll());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody ProductoCreateDTO p){

        if (productoService.productExist(p.nombre())){
            return ResponseEntity.badRequest().body("Este producto ya existente");
        }
        if (!categoriaService.categoryExist(p.categoria())){
            return ResponseEntity.badRequest().body("Categoria asignada no valida");
        }
        productoService.save(p);
        return ResponseEntity.status(201).body("Producto creado con exito");

    }

    @PatchMapping("/{id}/edit")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody ProductoEdit p){
        if (!productoService.productExist(p.nombre())){
            return ResponseEntity.badRequest().body("Producto no existente");
        }
        if (!categoriaService.categoryExist(p.categoria())){
            return ResponseEntity.badRequest().body("Categoria no existente");
        }
        productoService.edit(id, p);
        return ResponseEntity.status(201).body("Producto editado con exito");
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> delete (@PathVariable Long id){
        try{
            productoService.delete(id);
            return ResponseEntity.ok("Producto eliminado con exito");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
