package com.zup.comicsapi.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.zup.comicsapi.dto.ErroDeFormularioDto;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
	
	@Autowired
	private MessageSource messageSource; //trata mensagem de erro de acordo com o idioma que o cliente faz a requisição
	
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST) //codigo de retorno
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDeFormularioDto> trataErro(MethodArgumentNotValidException exception){ //parametro exception contem todos os erros encontrados
		List<ErroDeFormularioDto> listaErroDeFormularioDto = new ArrayList<>();
		
		List<FieldError> camposComErro = exception.getBindingResult().getFieldErrors(); //obtem os campos com erros
		
		for (FieldError campoComErro : camposComErro) {
			String mensagem = messageSource.getMessage(campoComErro, LocaleContextHolder.getLocale());
			ErroDeFormularioDto erro = new ErroDeFormularioDto(campoComErro.getField(), mensagem);
			listaErroDeFormularioDto.add(erro);
		}
		
		return listaErroDeFormularioDto;
		
	}

}
