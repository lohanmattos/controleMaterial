package com.material.controleMaterial.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioContoler {
	
	@GetMapping("/adm/novoUsuario")
	public String novo() {
		return "cadastroUsuario";
	}

}
