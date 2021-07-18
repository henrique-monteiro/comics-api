package com.zup.comicsapi.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.zup.comicsapi.model.Comics;
import com.zup.comicsapi.service.ComicsService;

@RestController
@RequestMapping(value="/comics")
public class ComicsController {
	
	@Autowired
	private ComicsService comicsService;
	
	@GetMapping
	public ResponseEntity<Comics> buscaEGrava(String idComic, long idUsuario, UriComponentsBuilder uriBuilder) {
		Comics comics = comicsService.buscaEGrava(idComic, idUsuario); 
		comicsService.save(comics);
		
		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(comics.getId()).toUri(); //retornar 201
		return ResponseEntity.created(uri).body(comics);
	}
		
}
