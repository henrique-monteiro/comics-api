package com.zup.comicsapi.exceptions;

public class UsuarioJaExisteException extends RuntimeException {
	
	public UsuarioJaExisteException(String mensagem) {
		super(mensagem);
	}
}
