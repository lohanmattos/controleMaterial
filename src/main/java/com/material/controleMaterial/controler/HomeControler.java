package com.material.controleMaterial.controler;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeControler {
	
	@RequestMapping("/")
	public String Home() {
		return "homePrincipal";
		
	}
	
	@GetMapping("/login1")
	public String login() {
		return "login";
    }
}
