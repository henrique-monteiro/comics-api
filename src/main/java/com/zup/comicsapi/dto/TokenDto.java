package com.zup.comicsapi.dto;

public class TokenDto {
	
	private String token;
	private String tipo; //bearer

	public TokenDto(String token, String tipo) {
		this.token = token;
		this.tipo = tipo; //bearer
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}

}
