package com.zup.comicsapi.controller;


import com.zup.comicsapi.exceptions.UsuarioNaoExisteException;
import com.zup.comicsapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.zup.comicsapi.dto.UsuarioComComicsDto;
import com.zup.comicsapi.model.Usuario;
import com.zup.comicsapi.service.ComicsPorUsuarioService;

@RestController
@RequestMapping(value="/comicsPorUsuario")
public class ComicsPorUsuarioController {
		
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ComicsPorUsuarioService comicsPorUsuarioService;

	@GetMapping("/{idUsuario}")
	public ResponseEntity<UsuarioComComicsDto> comicsPorUsuario(@PathVariable Long idUsuario) {
		Usuario usuario;
		try{
			usuario = usuarioService.buscaUsuarioPorId(idUsuario);
			return ResponseEntity.ok(UsuarioComComicsDto.convert(comicsPorUsuarioService.atualizaValoresListaComics(usuario)));
		}catch(UsuarioNaoExisteException e){
			return ResponseEntity.notFound().build();
		}
	}
}
