package com.example.foodstore;

import com.example.foodstore.entity.Rol;
import com.example.foodstore.entity.Usuario;
import com.example.foodstore.repository.UsuarioRepository;
import com.example.foodstore.service.UsuarioService;
import com.example.foodstore.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class FoodstoreApplication {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	PasswordUtil hash;

	public static void main(String[] args) {
		SpringApplication.run(FoodstoreApplication.class, args);
		System.out.println("Funcionando .....");
	}

	@Bean
	CommandLineRunner initUsuarios(UsuarioRepository usuarioRepository) {
		return args -> {
			if (usuarioRepository.findByEmail("admin@admin.com").isEmpty()) {
				Usuario admin = new Usuario(
						"admin",
						"apellido",
						"admin@admin.com",
						123,
						hash.encode("1234"),
						Rol.ADMIN,
						new ArrayList<>()
				);
				usuarioRepository.save(admin);
				System.out.println("✅ Usuario admin creado");
			} else {
				System.out.println("ℹ️ admin ya existe");
			}
		};
	}

}
