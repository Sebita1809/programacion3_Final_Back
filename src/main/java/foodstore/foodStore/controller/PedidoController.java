package foodstore.foodStore.controller;

import foodstore.foodStore.entity.Pedido;
import foodstore.foodStore.entity.dto.Pedido.PedidoCreate;
import foodstore.foodStore.entity.dto.Pedido.PedidoDTO;
import foodstore.foodStore.entity.dto.Pedido.PedidoEditStatus;
import foodstore.foodStore.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/order")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.ok(pedidoService.findAll());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/")
    public ResponseEntity<?> userOrders(@PathVariable Long id){
        try {
            return ResponseEntity.ok(pedidoService.userOrders(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody PedidoCreate p){
        try {
            return ResponseEntity.ok(pedidoService.save(p));
        } catch (NullPointerException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error al crear el pedido: " + e.getMessage());
        }
    }

    @PatchMapping("/status")
    public ResponseEntity<?> editStatus(@RequestBody PedidoEditStatus p){
        try {
            return ResponseEntity.ok(pedidoService.editStatus(p));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
