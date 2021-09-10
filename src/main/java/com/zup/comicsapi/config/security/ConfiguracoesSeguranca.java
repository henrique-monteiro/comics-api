package com.zup.comicsapi.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.zup.comicsapi.reposiroty.UsuarioRepository;

@EnableWebSecurity
@Configuration
public class ConfiguracoesSeguranca extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override //configuracoes de autenticacao (controle de login)
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override //configuracoes de autorizacao (URL's publicas e bloqueadas)
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/comics").permitAll()
								.antMatchers(HttpMethod.GET, "/usuarios").permitAll()
								.antMatchers(HttpMethod.POST, "/auth").permitAll()
								.antMatchers(HttpMethod.GET, "/actuator/**").hasRole("MODERADOR")
								.antMatchers("/usuarios/*").hasRole("MODERADOR") //apenas o moderador pode deletar um usuario
								.anyRequest().authenticated()
//								.and().formLogin() //cria session (JSESSION, que não é stateless)
								.and().csrf().disable()//desabilita a proteção contra o ataque hacker csrf (JSESSION)
								.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //politica de criacao de sessao STATELESS (nao criar session)
								.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
								;
	}
	
	@Override //configuracoes de recursos estaticos de frontend (js, css, imagens, etc..)
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
	}
	
//	public static void main(String[] args) {
//		System.out.println(new BCryptPasswordEncoder().encode("teste123"));
//	}
}
