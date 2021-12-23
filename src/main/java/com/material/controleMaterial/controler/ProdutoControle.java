package com.material.controleMaterial.controler;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.material.controleMaterial.model.Produto;
import com.material.controleMaterial.repositorio.ProdutoRepositorio;


@Controller
@Transactional 
public class ProdutoControle {
	
	@Autowired
	private ProdutoRepositorio produtoRepositorio;
		
	
	//Listar
	@RequestMapping("/produtos")
	public String listarProduto(Model model) {		
		List<Produto> produto = produtoRepositorio.findAll();		
		model.addAttribute("listarProduto", produto);
		
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

		return "redirect:/produtos";	
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
		return "redirect:/produtos";
	}
	
	
	//conferir
	@GetMapping("/conferir/{numeroPatrimonial}/{status}")
	public String conferirMaterial(@PathVariable String numeroPatrimonial, @PathVariable String status,  Model model) {
		
		Produto produto = produtoRepositorio.getById(numeroPatrimonial);
		
		try {
			produto.setStatus(status);
			produto.setDataConferido(LocalDate.now());
			
			produtoRepositorio.save(produto);

			model.addAttribute("listarProduto", produto);
			
			return "redirect:/produtos";
			
		} catch (Exception e) {
			 return "redirect:/produtos";
		}
	
	}
	
	
	@GetMapping(path ="/listar")
	public String findId(@RequestParam String numeroPatrimonial, String nomeclatura, Model model) {
		
		Optional<Produto> produto = produtoRepositorio.findById(numeroPatrimonial);
		
		if (produto.isPresent()) {
			
			List<Produto> produtoListado =  produto.stream().
					filter(p -> p.getNumeroPatrimonial() == numeroPatrimonial || p.getNomeclatura() == nomeclatura)
					.toList();
			model.addAttribute("listarProduto", produtoListado);
			
			return "home"; 
		}else {
			
			return "redirect:/produtos" ;
		}
		
	}
	
}
