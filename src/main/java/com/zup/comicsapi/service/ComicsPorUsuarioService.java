package com.zup.comicsapi.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.comicsapi.model.Comics;
import com.zup.comicsapi.model.Usuario;
import com.zup.comicsapi.reposiroty.ComicsRepository;
import com.zup.comicsapi.reposiroty.UsuarioRepository;

@Service
public class ComicsPorUsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ComicsRepository comicsRepository;
	
	public List<Comics> buscaComicsPorUsuario(Long id) {
		
		char finalIsbn;		
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		List<Comics> listaComicsPorUsuario = comicsRepository.findByUsuario(usuario);
		
		for (Comics comics : listaComicsPorUsuario) {
			finalIsbn = comics.getIsbn().charAt(comics.getIsbn().length()-1);
//			System.out.println(finalIsbn);
//			System.out.println(diaDesconto(finalIsbn));
			comics.setDiaDesconto(diaDesconto(finalIsbn));  
			comics.setDescontoAtivo(verificaDescontoAtivo(comics.getDiaDesconto()));
		}		
		
		return listaComicsPorUsuario;
	}

	private boolean verificaDescontoAtivo(String diaDesconto) {
		Date data = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		System.out.println(day);
		
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
