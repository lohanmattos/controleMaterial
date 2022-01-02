package com.material.controleMaterial.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.material.controleMaterial.model.Role;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSeguranca extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private CodificadorSenha pass;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/conferirURL/**", "/listarURL/**", "/error").permitAll()
		.antMatchers("/css/**", "/js/**", "/img/**", "/resources/**").permitAll()
		.antMatchers("/adm/**").hasAuthority(Role.ADMIN.getNome())
			.anyRequest().authenticated()
			.and()
		.formLogin()
		.loginPage("/login")
			.permitAll()			
			.and()
		.logout()
			.permitAll();
	}

	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(pass.passwordEncoder());
	}
	
}
