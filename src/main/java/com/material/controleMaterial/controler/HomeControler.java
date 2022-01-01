package com.material.controleMaterial.controler;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeControler {
	
	@RequestMapping("/")
	public String Home() {
		return "homePrincipal";
		
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
    }
	
	
	@GetMapping("/logout")
	public String logout() {
		return "login";
    }
	
	@GetMapping("/testePdf")
	public String gerarPdf() {
		return "testePdf2";
    }
	
	
}
