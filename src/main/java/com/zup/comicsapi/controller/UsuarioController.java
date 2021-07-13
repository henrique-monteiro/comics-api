package com.zup.comicsapi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.zup.comicsapi.model.Usuario;
import com.zup.comicsapi.reposiroty.UsuarioRepository;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

//	@RequestMapping(value="/listaUsuarios")
	@GetMapping
	public List<Usuario> lista() { 
		List<Usuario> usuarios = usuarioRepository.findAll();
		System.out.println(usuarios);
		return usuarios;
	}
	
	@PostMapping
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuarioForm, UriComponentsBuilder uriBuilder) {
		Usuario usuario = usuarioForm;
		usuarioRepository.save(usuario);
		
		//boas práticas do modelo REST (retornar 201 e nao 200)
		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(usuario);
	}
	
	
	
	
	
	
}
