package com.zup.comicsapi.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.zup.comicsapi.model.Comics;
import com.zup.comicsapi.model.Usuario;
import com.zup.comicsapi.reposiroty.ComicsRepository;
import com.zup.comicsapi.reposiroty.UsuarioRepository;

@Service
public class ComicsService {
	
	@Autowired
	private ComicsRepository comicsRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Comics buscaEGrava(String idComics, long idUsuario) {
		
		//http://gateway.marvel.com/v1/public/comics/2?ts=1625840271&apikey=1292f518b5874baf11a56f55e28bf010&hash=5fe40325ef66ac6be88b70095c6d00b6
		UriComponents url = UriComponentsBuilder.newInstance()
				.scheme("http")
				.host("gateway.marvel.com")
				.path("v1/public/comics/" + idComics)
				.queryParam("ts", "1625840271")
				.queryParam("apikey", "1292f518b5874baf11a56f55e28bf010")
				.queryParam("hash", "5fe40325ef66ac6be88b70095c6d00b6")
				.build();
		
		RestTemplate restTemplate = new RestTemplate();
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		restTemplate = restTemplateBuilder.build();

		String body = restTemplate.getForEntity(url.toString(), String.class).getBody();
		String bodyFormatada = body.substring(317, body.length()-3); //-3 para retirar os colchetes e chaves que sobravam		
		
		JSONObject json = new JSONObject(bodyFormatada);
		
		Comics comics = new Comics();
		comics.setComicId(json.getInt("id"));
		comics.setTitle(json.getString("title"));
		comics.setIsbn(json.getString("isbn"));	
		Usuario usuario = usuarioRepository.findById(idUsuario).get();
		comics.setUsuario(usuario);
		
		int maxLength = json.getString("description").length();
		if (maxLength < 250) {
			comics.setDescription(json.getString("description"));
		} else {
			comics.setDescription(json.getString("description").substring(0, 250) + " ...");
		}	
		
		JSONArray prices = json.getJSONArray( "prices" );
		
		for(int i=0; i<prices.length(); i++) {
			JSONObject joi = prices.getJSONObject(i);
			if(joi.has("price")) {
				//System.out.println(joi.get("price"));
				comics.setPrice(joi.getBigDecimal("price"));
			}
		}		
		return comics;
	}

	public void save(Comics comics) {
		comicsRepository.save(comics);
	}

	public List<Comics> findAll() {		
		return comicsRepository.findAll();
	}
	
}
