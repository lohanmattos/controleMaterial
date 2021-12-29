package com.material.controleMaterial;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.material.controleMaterial.seguranca.model.Role;
import com.material.controleMaterial.seguranca.model.Usuario;
import com.material.controleMaterial.seguranca.model.UsuarioRepositorio;

//@Component
//@Transactional
public class PopulacaoInicialBanco implements CommandLineRunner {

	@Autowired
	public UsuarioRepositorio usuariorepositorio; 
	
	@Autowired
	private PasswordEncoder passwordEncoder;
		
	
	@Override
	public void run(String... args) throws Exception {
		
		Usuario user1 = new Usuario();
		user1.setUsername("admin");
		user1.setPassword(passwordEncoder.encode("admin"));
		user1.setRole(Role.ADMIN.getNome());
		usuariorepositorio.save(user1);
		
		Usuario user = new Usuario();
		user.setUsername("user");
		user.setPassword(passwordEncoder.encode("user"));
		user.setRole(Role.USER.getNome());
		usuariorepositorio.save(user);
		
		
	}

}
