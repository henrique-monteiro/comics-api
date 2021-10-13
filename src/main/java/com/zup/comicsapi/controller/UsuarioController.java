package com.zup.comicsapi.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.zup.comicsapi.exceptions.UsuarioJaExisteException;
import com.zup.comicsapi.exceptions.UsuarioNaoExisteException;
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

	@GetMapping
	public List<UsuarioDto> lista() {
		List<Usuario> usuarios = usuarioService.findAll();
		return UsuarioDto.converterLista(usuarios);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid Usuario usuarioForm, UriComponentsBuilder uriBuilder) {
		Usuario usuario = usuarioForm;
		try{
			usuarioService.gravaUsuario(usuario);
			URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri(); //retornar 201
			return ResponseEntity.created(uri).body(usuario);
		} catch(UsuarioJaExisteException e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> detalhar(@PathVariable Long id) {
		try{
			return ResponseEntity.ok(new UsuarioDto(usuarioService.buscaUsuarioPorId(id)));
		}catch(UsuarioNaoExisteException e){
			return ResponseEntity.notFound().build(); //404 não encontrado
		}
	}
	
	@PutMapping("/{id}")
	@Transactional //informa para a JPA que deve fazer o commit
	public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioAtualizarDto usuarioAtualizar){
		try{
			Usuario usuario = usuarioService.atualizarUsuario(usuarioService.buscaUsuarioPorId(id), usuarioAtualizar);
			return ResponseEntity.ok(new UsuarioDto(usuario));
		}catch(UsuarioNaoExisteException e){
			return ResponseEntity.notFound().build(); //404 não encontrado
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity remover(@PathVariable Long id) {
		try{
			usuarioService.delete(id);
			return ResponseEntity.ok().build();
		}catch(UsuarioNaoExisteException e) {
			return ResponseEntity.notFound().build(); //404 não encontrado
		}
	}
}
