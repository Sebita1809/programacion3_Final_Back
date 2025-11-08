package foodstore.foodStore.service;

import foodstore.foodStore.entity.DetallePedido;
import foodstore.foodStore.entity.dto.DetallePedido.DetallePedidoDTO;

public interface DetallePedidoService {

    DetallePedidoDTO save(DetallePedido d);
}
