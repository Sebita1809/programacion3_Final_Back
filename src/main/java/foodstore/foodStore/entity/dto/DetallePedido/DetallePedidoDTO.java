package foodstore.foodStore.entity.dto.DetallePedido;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedidoDTO {

    private String producto;
    private int cantidad;
    private Double subtotal;
}
