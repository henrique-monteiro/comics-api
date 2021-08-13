package com.zup.comicsapi.exceptions;

public class UsuarioJaExistenteException extends RuntimeException {
	
	public UsuarioJaExistenteException(String mensagem) {
		super(mensagem);
	}
}
