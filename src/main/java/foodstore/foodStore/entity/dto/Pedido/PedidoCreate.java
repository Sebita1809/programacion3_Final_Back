package foodstore.foodStore.entity.dto.Pedido;


import foodstore.foodStore.entity.dto.DetallePedido.DetallePedidoCreate;

import java.util.List;

public record PedidoCreate(
        Long idUsuario,
        Long telefono,
        String direccion,
        String metodoPago,
        List<DetallePedidoCreate> detalles
) {
}

