package com.material.controleMaterial.controler;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.material.controleMaterial.model.Produto;
import com.material.controleMaterial.repositorio.ProdutoRepositorio;


@Controller
public class ProdutoControle {
	
	@Autowired
	private ProdutoRepositorio produtoRepositorio;
	
	
	//Listar
	@GetMapping("/")
	public String listarProduto(Model model) {
		model.addAttribute("listarProduto", produtoRepositorio.findAll());
		return "home";
		
	}
	//Novo
	@GetMapping("/novo")
	public String novoProduto(Model model, Produto produto) {
		model.addAttribute("produto", produto);
		return "cadastrarProduto";
	}
	
	//Salvar
	@PostMapping("/salvar")
	public String salvarProduto(Model model, Produto produto) {
		model.addAttribute("produto", produtoRepositorio.save(produto));

		return "redirect:/";	
	}
	
	//Editar Produto
		@GetMapping("/{numeroPatrimonial}")
		public String alterarProduto(@PathVariable("numeroPatrimonial") String numeroPatrimonial, Model model) {
			Optional<Produto> produto = produtoRepositorio.findById(numeroPatrimonial);
			
			model.addAttribute("produto", produto);
			
			return "editarProduto";
		}
	
	//Deletar Produto
	@RequestMapping(path = "/deletar/{id}", method = RequestMethod.GET)
	public String excluirProduto(@PathVariable String id) {
		produtoRepositorio.deleteById(id);
		return "redirect:/";
	}
	

}
