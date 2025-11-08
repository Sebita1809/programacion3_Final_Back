package foodstore.foodStore.service;


import foodstore.foodStore.entity.dto.Pedido.PedidoCreate;
import foodstore.foodStore.entity.dto.Pedido.PedidoDTO;
import foodstore.foodStore.entity.dto.Pedido.PedidoEditStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PedidoService {

    PedidoDTO editStatus(PedidoEditStatus p);
    PedidoDTO save(PedidoCreate pedidoCreate);
    List<PedidoDTO> findAll();
    List<PedidoDTO> userOrders(Long id);
    boolean checkPayment(String cp);
}
