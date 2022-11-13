package com.example.registrationlogindemo;

import com.example.registrationlogindemo.entity.Role;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.repository.RoleRepository;
import com.example.registrationlogindemo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

//Esta clase arranca la aplicación
@SpringBootApplication
public class RegistrationLoginDemoApplication {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private  RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(RegistrationLoginDemoApplication.class, args);
	}

	//Event listener quiere decir que apenas la aplicacion haya cargado se van a ejecutar los metodos
	@EventListener(ApplicationReadyEvent.class)
	public void saveAdmin(){
		//buscamos el ROLE_ADMIN si no existe lo creamos
		Role role = roleRepository.findByName("ROLE_ADMIN");
		if(role == null){
			role = createRoleAdmin();
		}

		// construimos un usuario si se quiere pueden cambiar
		User admin = new User(
				null,
				"admin",
				"admin@localhost.com",
				// la contraseña es alamo6402 cambienla pero debe estar encriptada
				"$2a$10$n.qDryUMLQrVfN3R3kgodOnnniZcnK/HLhfkxEcDxqtp2sHlll8Ty",
				Arrays.asList(role));

		userRepository.save(admin);
	}

	//creamos el role si no existe se hace por defecto no borrarlo porque no se puede crear desde la app
	public Role createRoleAdmin(){
		Role admin = new Role();
		admin.setName("ROLE_ADMIN");
		return roleRepository.save(admin);
	}

}
