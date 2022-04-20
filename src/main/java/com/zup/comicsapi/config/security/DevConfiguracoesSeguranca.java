package com.zup.comicsapi.config.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
@Profile("dev")
public class DevConfiguracoesSeguranca extends WebSecurityConfigurerAdapter {
	
	@Override //configuracoes de autorizacao (URL's publicas e bloqueadas)
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/**").permitAll()
								.and().csrf().disable();//desabilita a proteção contra o ataque hacker csrf (JSESSION)
	}
}
