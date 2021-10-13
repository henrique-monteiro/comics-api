package com.zup.comicsapi.service;

import java.math.BigDecimal;
import java.util.ArrayList;
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

@Service
public class ComicsService {
	
	@Autowired
	private ComicsRepository comicsRepository;
	
	@Autowired
	private UsuarioService usuarioService;

	public Comics buscaEGrava(String idComics, Usuario usuario) {

		JSONObject json = obtemJsonComicMarvel(idComics);

		Comics comics = new Comics();
		comics.setComicId(json.getInt("id"));
		comics.setTitle(json.getString("title"));
		comics.setIsbn(json.getString("isbn"));
		comics.setUsuario(usuario);
		comics.setDescription(obtemDescricao(json));
		comics.setPrice(obtemPreco(json));
		comics.setCreators(obtemAutores(json));

		return comics;
	}

	private String obtemAutores(JSONObject json) {
		JSONObject creatorsJSON = json.getJSONObject("creators");
		JSONArray items = creatorsJSON.getJSONArray("items");
		List<String> autores = new ArrayList<>();

		if (items.isEmpty()) {
			return "nao especificado";
		}
		else {
			for(int i=0; i<items.length(); i++) {
				JSONObject joi = items.getJSONObject(i);
				if(joi.has("name")) {
					autores.add(joi.getString("name"));
				}
			}
			String creators = null;
			for (String string : autores) {
				creators += string + ", ";
			}
			return creators.substring(0, creators.length()-2);
		}
	}

	private BigDecimal obtemPreco(JSONObject json) {
		JSONArray prices = json.getJSONArray( "prices" );
		for(int i=0; i<prices.length(); i++) {
			JSONObject joi = prices.getJSONObject(i);
			if(joi.has("price")) {
				return joi.getBigDecimal("price");
			}
		}
		return null;
	}

	private JSONObject obtemJsonComicMarvel(String idComics) {
		//ts = 1625840271
		//private key = 21ed559bcd08b974ca2c8b73d654a2bc2980dd70
		//public key (apikey) = 1292f518b5874baf11a56f55e28bf010

		/*
		 * Para obter o hash, acessar http://andti.com.br/tool/hash e, no campo "Mensagem",
		 * inserir ts+privateKey+publicKey, como mostrado abaixo:
		 * 162584027121ed559bcd08b974ca2c8b73d654a2bc2980dd701292f518b5874baf11a56f55e28bf010
		*/

		//http://gateway.marvel.com/v1/public/comics/2?ts=1625840271&apikey=1292f518b5874baf11a56f55e28bf010&hash=5fe40325ef66ac6be88b70095c6d00b6
		UriComponents url = UriComponentsBuilder.newInstance()
				.scheme("http")
				.host("gateway.marvel.com")
				.path("v1/public/comics/" + idComics)
				.queryParam("ts", "1625840271")
				.queryParam("apikey", "1292f518b5874baf11a56f55e28bf010")
				.queryParam("hash", "5fe40325ef66ac6be88b70095c6d00b6")
				.build();

		System.out.println("\n\n");
		System.out.println(url.toString());
		System.out.println("\n\n");

		RestTemplate restTemplate = new RestTemplate();
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		restTemplate = restTemplateBuilder.build();

		String body = restTemplate.getForEntity(url.toString(), String.class).getBody();
		System.out.println("\n\n");
		System.out.println(body);
		System.out.println("\n\n");

		String bodyFormatada = body.substring(317, body.length()-3); //-3 para retirar os colchetes e chaves que sobravam

		JSONObject json = new JSONObject(bodyFormatada);
		return json;
	}

	private String obtemDescricao(JSONObject json) {
		int maxLength = json.getString("description").length();
		if (maxLength < 250) {
			return json.getString("description");
		} else {
			return json.getString("description").substring(0, 250) + " ...";
		}
	}

	public void save(Comics comics) {
		comicsRepository.save(comics);
	}

	public List<Comics> findAll() {		
		return comicsRepository.findAll();
	}
	
}
