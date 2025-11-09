package foodstore.foodStore.entity.dto.Pedido;

import foodstore.foodStore.entity.Estado;
import foodstore.foodStore.entity.dto.DetallePedido.DetallePedidoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private Long id;
    private LocalDate fecha;
    private Estado estado;
    private String direccion;
    private Long telefono;
    private String metodoPago;
    private double total;
    private Long idUsuario;
    private String nombreUsuario;
    private List<DetallePedidoDTO> detalles = new ArrayList<>();
}
