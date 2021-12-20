package com.material.controleMaterial.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.material.controleMaterial.model.Produto;
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
	
	@GetMapping("/novo")
	public String novoProduto(Model model, Produto produto) {
		model.addAttribute("produto", produto);
		return "cadastrarProduto";
	}
	
	//Deletar Produto
	@RequestMapping(path = "/deletar/{id}", method = RequestMethod.GET)
	public String excluirProduto(@PathVariable String id) {
		produtoRepositorio.deleteById(id);
		return "redirect:/";
	}
	

}
