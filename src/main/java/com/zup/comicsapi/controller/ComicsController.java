package com.zup.comicsapi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		
		if (comics == null) { //usuario nao encontrado
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			comicsService.save(comics);			
			URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(comics.getId()).toUri(); //retornar 201
			return ResponseEntity.created(uri).body(comics);
		}		
	}
	@GetMapping("lista")
	public List<Comics> lista() { 
		List<Comics> comics = comicsService.findAll();
		return comics;
	}
}
