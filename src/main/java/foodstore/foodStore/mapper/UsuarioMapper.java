package foodstore.foodStore.mapper;

import foodstore.foodStore.entity.Pedido;
import foodstore.foodStore.entity.Rol;
import foodstore.foodStore.entity.Usuario;
import foodstore.foodStore.entity.dto.Pedido.PedidoDTO;
import foodstore.foodStore.entity.dto.Usuario.UsuarioCreate;
import foodstore.foodStore.entity.dto.Usuario.UsuarioDTO;
import foodstore.foodStore.utils.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class UsuarioMapper {

    @Autowired
    PedidoMapper pedidoMapper;

    public Usuario toEntity(UsuarioCreate userC) {
        List<Pedido> pedidos = new ArrayList<>();
        Usuario user = new Usuario();
        user.setNombre(userC.nombre());
        user.setApellido(userC.apellido());
        user.setEmail(userC.email());
        user.setCelular(userC.celular());
        user.setContrasena(PasswordHash.hashPassword(userC.contrasena()));
        user.setRol(Rol.USUARIO);
        user.setPedidos(pedidos);

        return user;
    }

    public UsuarioDTO toDto (Usuario user) {
        List<PedidoDTO> pedidos = user.getPedidos()
                .stream()
                .map(pedidoMapper::toDto) // Usar el mapper inyectado
                .toList();
        return new UsuarioDTO(
                user.getId(),
                user.getNombre(),
                user.getApellido(),
                user.getEmail(),
                user.getCelular(),
                user.getRol(),
                pedidos
        );
    }
}
