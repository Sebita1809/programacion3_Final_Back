package foodstore.foodStore;

import foodstore.foodStore.entity.Rol;
import foodstore.foodStore.entity.Usuario;
import foodstore.foodStore.repository.UsuarioRepository;
import foodstore.foodStore.utils.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class FoodStoreApplication {

	@Autowired
	UsuarioRepository usuarioRepository;

	public static void main(String[] args) {

		SpringApplication.run(FoodStoreApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void iniciarAdmin(){

		String adminEmail = "admin@gmail.com";

		if (usuarioRepository.findByEmail(adminEmail).isEmpty()){
			Usuario admin = new Usuario();
			admin.setNombre("admin");
			admin.setEmail(adminEmail);
			admin.setContrasena(PasswordHash.hashPassword("admin123"));
			admin.setRol(Rol.ADMIN);

			usuarioRepository.save(admin);
		}
	}
}
