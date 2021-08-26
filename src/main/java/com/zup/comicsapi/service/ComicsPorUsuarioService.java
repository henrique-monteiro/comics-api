package com.zup.comicsapi.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.comicsapi.model.Comics;
import com.zup.comicsapi.model.Usuario;

@Service
public class ComicsPorUsuarioService {
	
	@Autowired
	private UsuarioService usuarioService;	
	
	public Usuario buscaComicsPorUsuario(Long idUsuario) {	//feedback ZUP: nao trabalhar com id para busacs
			
		Usuario usuario = usuarioService.buscaUsuarioPorId(idUsuario);
		
		if (usuario == null) {
			return null;
		}
		
		List<Comics> listaAtualizada = atualizaValoresDaListaDeComics(usuario);
			
		usuario.setListaDeComics(listaAtualizada);
		return usuario;
	}

	private List<Comics> atualizaValoresDaListaDeComics(Usuario usuario) {
		char finalIsbn;	//para verificar o dia do desconto
		List<Comics> listaAtualizada = new ArrayList<>();
		for (Comics comics : usuario.getListaDeComics()) {
			
			//caso não tenha ISBN no comic consumido pela API da Marvel
			if (comics.getIsbn().isBlank()) {
				comics.setDiaDesconto("Não possui dia com desconto");
				comics.setDescontoAtivo(false);
			} else {
				finalIsbn = comics.getIsbn().charAt(comics.getIsbn().length()-1);

				comics.setDiaDesconto(diaDesconto(finalIsbn));  //verifica o dia do desconto
				
				comics.setDescontoAtivo(verificaDescontoAtivo(comics.getDiaDesconto())); //verifica se o desconto está ativo
			}			
			
			if (comics.isDescontoAtivo()) {
				comics.setPrice(comics.getPrice().subtract(comics.getPrice().multiply(new BigDecimal(0.1)))); //atualiza o preço do comics
			}			
			listaAtualizada.add(comics);
		}
		return listaAtualizada;
	}

	private boolean verificaDescontoAtivo(String diaDesconto) {
		Date data = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		
		if (day == 2 && diaDesconto.compareTo("segunda-feira") == 0 ) {			
			return true;
		}
		else if (day == 3 && diaDesconto.compareTo("terça-feira") == 0 ){
			return true;
		}
		else if (day == 4 && diaDesconto.compareTo("quarta-feira") == 0 ){
			return true;
		}
		else if (day == 5 && diaDesconto.compareTo("quinta-feira") == 0 ){
			return true;
		}
		else if (day == 6 && diaDesconto.compareTo("sexta-feira") == 0 ){
			return true;
		}
		else {
			return false;
		}		
	}

	private String diaDesconto(char finalIsbn) {
		if (finalIsbn=='0' || finalIsbn=='1') {
			return "segunda-feira";
		}
		else if (finalIsbn=='2' || finalIsbn=='3') {
			return "terça-feira";
		}
		else if (finalIsbn=='4' || finalIsbn=='5') {
			return "quarta-feira";
		}
		else if (finalIsbn=='6' || finalIsbn=='7') {
			return "quinta-feira";
		}
		else if (finalIsbn=='8' || finalIsbn=='9') {
			return "sexta-feira";
		}
		return null;
	}	
}

