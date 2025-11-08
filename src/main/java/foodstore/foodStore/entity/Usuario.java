package foodstore.foodStore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    private int celular;
    private String contrasena;
    private Rol rol;

    @OneToMany
    @JoinTable(
            name = "usuario_pedido",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "pedido_id")
    )
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList<>();
}
