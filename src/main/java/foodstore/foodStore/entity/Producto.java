package foodstore.foodStore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private double precio;
    private int stock;
    private String url;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = true)
    @ToString.Exclude
    private Categoria categoria;
}
