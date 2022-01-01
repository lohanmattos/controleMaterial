package com.material.controleMaterial.controler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		  List<Produto> produto = produtoRepositorio.findAllProdutoListado();
		  model.addAttribute("listarProduto", produto);
		  return "home";
	
	}
	
	/*
	@GetMapping("/produtos")
	public String pessoas(Model model, @RequestParam("page") Optional<Integer> pagina, @RequestParam("size") Optional<Integer> tamanho) {
		int paginaAtual = pagina.orElse(1) - 1;
		int tamanhoPagina = tamanho.orElse(5);
		
		PageRequest requisicao = PageRequest.of(paginaAtual, tamanhoPagina);
		Page<Produto> listaPaginada = produtoRepositorio.findAllProdutoListadoPaginado(requisicao);
		
		model.addAttribute("listarProduto", listaPaginada);

		int totalPaginas = listaPaginada.getTotalPages();
		if (totalPaginas > 0) {
			List<Integer> numerosPaginas = IntStream.rangeClosed(1, totalPaginas)
						.boxed()
						.collect(Collectors.toList());
			model.addAttribute("numerosPaginas", numerosPaginas);
		}
		
		return "home";
	}
	*/
						
	//Novo
	@GetMapping("adm/novo")
	public String novoProduto(Model model, Produto produto) {
		model.addAttribute("produto", produto);
		return "cadastrarProduto";
	}
	
	//Salvar
	@PostMapping("adm/salvar")
	public String salvarProduto(Model model, Produto produto, RedirectAttributes attributes) {
		model.addAttribute("produto", produtoRepositorio.save(produto));
	
		attributes.addFlashAttribute("mensagem", "Produto Cadastrado com Sucesso.");
		return "redirect:/produtos";	
	}
	
	//Editar Produto
	@GetMapping("adm/{numeroPatrimonial}")
	public String alterarProduto(@PathVariable("numeroPatrimonial") String numeroPatrimonial, Model model) {
		Optional<Produto> produto = produtoRepositorio.findById(numeroPatrimonial);
					
		model.addAttribute("produto", produto);	
		return "editarProduto";
	}		
		
	//Deletar Produto
	@RequestMapping(path = "adm/deletar/{id}", method = RequestMethod.GET)
	public String excluirProduto(@PathVariable String id, RedirectAttributes attributes) {
		produtoRepositorio.deleteById(id);
		attributes.addFlashAttribute("mensagemErro", "Produto Excluído com Sucesso.");
		return "redirect:/produtos";
	}
	
	
	@GetMapping(path ="/listar")
	public String findId(@RequestParam String numeroPatrimonial, Model model, RedirectAttributes attributes) {
		
		Optional<Produto> produto = produtoRepositorio.findById(numeroPatrimonial);
		
		if (produto.isPresent()) {
			
			List<Produto> produtoListado =  produto.stream().
					filter(p -> p.getNumeroPatrimonial() == numeroPatrimonial)
					.toList();
			model.addAttribute("listarProduto", produtoListado);
			
			return "home"; 
		}else {
			
			attributes.addFlashAttribute("mensagemNaoAchado", "Número Patrimonial não existe.");
			return "redirect:/produtos" ;
		}
		
	}
				
}
