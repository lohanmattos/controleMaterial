package com.material.controleMaterial.controler;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.material.controleMaterial.model.Produto;
import com.material.controleMaterial.repositorio.ProdutoRepositorio;

@RestController
@Transactional
public class ProdutoControlerRest {
	
	
	@Autowired
	private ProdutoRepositorio produtoRepositorio;
	
	//conferir
	@GetMapping("/conferir/{numeroPatrimonial}/{setor}")
	public ResponseEntity<String> conferirMaterial(@PathVariable String numeroPatrimonial, @PathVariable String setor,  Model model) {
		
		Produto produto = produtoRepositorio.getById(numeroPatrimonial);
					
			produto.setSetor(setor);
			produto.setStatus("Conferido");
			produto.setDataConferido(LocalDate.now());
			
			produtoRepositorio.save(produto);
			String mgs = "Produto Conferido Com Sucesso " + produto.getNumeroPatrimonial(); 
			
			return new ResponseEntity<>(mgs, HttpStatus.OK);
	
	}
	
	
	//conferir
	@GetMapping(path = "/conferirURL")
	public ResponseEntity<String> conferirMaterialURL(@RequestParam("numeroPatrimonial") String numeroPatrimonial, @RequestParam ("setor") String setor) {

		
		try {
			Produto produto = produtoRepositorio.getById(numeroPatrimonial);
			
			produto.setStatus("Conferido");
			produto.setSetor(setor);
			produto.setDataConferido(LocalDate.now());
			
			produtoRepositorio.save(produto);
			String mgsSucesso = "Nº Patrimonial: " + produto.getNumeroPatrimonial() + ""
					+ " Conferido Com Sucesso"
			;
						
			return new ResponseEntity<>(mgsSucesso, HttpStatus.OK);	
		} catch (Exception EntityNotFoundException) {
			
			String mgsErro = "Nº Patrimonial: " + numeroPatrimonial + ""
					+ " não foi encontrado na base de dados."
			;
			
			return new ResponseEntity<>(mgsErro, HttpStatus.OK);	
		}
	}	
	
	
	//conferir
		@GetMapping(path = "/listarURL/{numeroPatrimonial}")
		public ResponseEntity<String> listarMaterialURL(@PathVariable String numeroPatrimonial) {
			
			try {
				Produto produto = produtoRepositorio.getById(numeroPatrimonial);
				
				
				String mgsSucesso = "Nº Patrimonial: " + produto.getNumeroPatrimonial() + "\n"
						+ " Setor: " + produto.getSetor() + "\n" + 
						" Status: " + produto.getStatus() + "\n" + 
						"Conferido em: " + produto.getDataConferido(); 
				;
							
				return new ResponseEntity<>(mgsSucesso, HttpStatus.OK);	
			} catch (Exception EntityNotFoundException) {
				
				String mgsErro = "Nº Patrimonial: " + numeroPatrimonial + ""
						+ " não foi encontrado na base de dados."
				;
				
				return new ResponseEntity<>(mgsErro, HttpStatus.OK);	
			}
		}	


}
