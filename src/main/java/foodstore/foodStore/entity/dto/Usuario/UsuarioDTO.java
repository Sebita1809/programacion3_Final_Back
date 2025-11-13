package foodstore.foodStore.entity.dto.Usuario;

import foodstore.foodStore.entity.Rol;
import foodstore.foodStore.entity.dto.Pedido.PedidoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioDTO {

    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    private int celular;
    private Rol rol;
    private List<PedidoDTO> pedidos;
    private boolean eliminado;


}
