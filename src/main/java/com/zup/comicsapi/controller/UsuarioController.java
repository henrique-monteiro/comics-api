package com.zup.comicsapi.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.zup.comicsapi.model.Usuario;
import com.zup.comicsapi.service.UsuarioService;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuarioForm, UriComponentsBuilder uriBuilder) {
		Usuario usuario = usuarioForm;		
		
		if (usuarioService.gravaUsuario(usuario) != null) { //usuario registrado
			URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri(); //retornar 201
			return ResponseEntity.created(uri).body(usuario);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //usuario ja existente		
		
	}
	
}
