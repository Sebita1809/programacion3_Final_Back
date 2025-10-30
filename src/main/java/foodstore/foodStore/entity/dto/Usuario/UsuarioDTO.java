package foodstore.foodStore.entity.dto.Usuario;

import foodstore.foodStore.entity.Rol;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

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


}
