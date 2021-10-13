package com.zup.comicsapi.controller;

import java.net.URI;
import java.util.List;

import com.zup.comicsapi.exceptions.UsuarioNaoExisteException;
import com.zup.comicsapi.model.Usuario;
import com.zup.comicsapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/{idUsuario}")
	public ResponseEntity<Comics> buscaEGrava(@PathVariable long idUsuario, String idComic, UriComponentsBuilder uriBuilder) {
		Usuario usuario;
		Comics comics;

		try{
			usuario = usuarioService.buscaUsuarioPorId(idUsuario);
			comics = comicsService.buscaEGrava(idComic, usuario);
			comicsService.save(comics);
			URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(comics.getId()).toUri(); //retornar 201
			return ResponseEntity.created(uri).body(comics);
		}catch(UsuarioNaoExisteException e){
			return ResponseEntity.notFound().build(); //404 não encontrado
		}catch(Exception e){ //	qualquer outro erro que possa acontecer
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping
	public List<Comics> lista() { 
		List<Comics> comics = comicsService.findAll();
		return comics;
	}
}
