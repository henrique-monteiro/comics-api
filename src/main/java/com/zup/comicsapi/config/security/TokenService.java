package com.zup.comicsapi.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.zup.comicsapi.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${forum.jwt.expiration}") //injeta a propriedade que esta no aplication propoertie
	private String expiration;
	
	@Value("${forum.jwt.secret}") //injeta a propriedade que esta no aplication propoertie
	private String secret;

	public String gerarToken(Authentication authentication) {
		Usuario logado = (Usuario) authentication.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
	
		//classe Jwts para criar um token
		return Jwts.builder()
				.setIssuer("Comics-API")
				.setSubject(logado.getId().toString()) //string que identifica o usuario
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao) //tempo de expiração do token
				.signWith(SignatureAlgorithm.HS256, secret) //criptografia (hmac sha-256) do token
				.compact();
	}
	
	public boolean ehTokenValido(String token) {
		try {
			//classe Jwts para descriptografar e verificar se está OK (parser)
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
