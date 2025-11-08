package foodstore.foodStore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private Estado estado;
    private String direccion;
    private Integer telefono;
    private String metodoPago;
    private double total;

    @OneToMany
    @JoinColumn(name = "detalle_id")
    @Builder.Default
    private List<DetallePedido> detalles = new ArrayList<>();
}
