package com.material.controleMaterial.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.material.controleMaterial.repositorio.ProdutoRepositorio;


@Controller
public class ProdutoControle {
	
	@Autowired
	private ProdutoRepositorio produtoRepositorio;
	
	@GetMapping("/")
	public String listarProduto(Model model) {
		model.addAttribute("listarProduto", produtoRepositorio.findAll());
		return "home";
		
	}

}
