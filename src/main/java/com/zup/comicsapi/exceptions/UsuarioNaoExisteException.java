package com.zup.comicsapi.exceptions;

public class UsuarioNaoExisteException extends RuntimeException {

	public UsuarioNaoExisteException(String mensagem) {
		super(mensagem);
	}
}
