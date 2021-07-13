package com.zup.comicsapi.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.zup.comicsapi.model.Comics;
import com.zup.comicsapi.reposiroty.ComicsRepository;
import com.zup.comicsapi.service.ComicsService;

@RestController
@RequestMapping(value="/comics")
public class ComicsController {
	
	@Autowired
	private ComicsRepository comicsRepostory;
	
	@Autowired
	private ComicsService comicsService;
	
	@GetMapping
	public ResponseEntity<Comics> searchAndSave(String id, UriComponentsBuilder uriBuilder) {
		Comics comics = comicsService.searchAndSave(id); 
		comicsRepostory.save(comics);
		
		
		//boas práticas do modelo REST (retornar 201 e nao 200)
		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(comics.getId()).toUri();
		return ResponseEntity.created(uri).body(comics);
	}
}
