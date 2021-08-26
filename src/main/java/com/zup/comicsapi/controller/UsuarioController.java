package com.zup.comicsapi.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.zup.comicsapi.dto.UsuarioAtualizarDto;
import com.zup.comicsapi.dto.UsuarioDto;
import com.zup.comicsapi.model.Usuario;
import com.zup.comicsapi.service.UsuarioService;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid Usuario usuarioForm, UriComponentsBuilder uriBuilder) {
		Usuario usuario = usuarioForm;		
		
		if(usuarioService.gravaUsuario(usuario) == null) { //null se usuário já exite
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		}
		
		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri(); //retornar 201
		return ResponseEntity.created(uri).body(usuario);			
	}
	
	@GetMapping
	public List<UsuarioDto> lista() { 
		List<Usuario> usuarios = usuarioService.findAll();
		return UsuarioDto.converterLista(usuarios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> detalhar(@PathVariable Long id) {
		Usuario usuario = usuarioService.buscaUsuarioPorId(id);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}			
		return ResponseEntity.ok(new UsuarioDto(usuario));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long id, @RequestBody UsuarioAtualizarDto usuarioAtualizar){
		Usuario usuario = usuarioService.buscaUsuarioPorId(id);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		} else {
			usuario = usuarioService.atualizarUsuario(usuario, usuarioAtualizar);
			return ResponseEntity.ok(new UsuarioDto(usuario));
		}		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		if (usuarioService.buscaUsuarioPorId(id) == null) {
			return ResponseEntity.notFound().build();
		} else {
			usuarioService.delete(id);
			return ResponseEntity.ok().build();
		}		
	}
	
	
	
}
