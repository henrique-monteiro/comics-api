package com.zup.comicsapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.zup.comicsapi.dto.UsuarioDto;
import com.zup.comicsapi.model.Usuario;
import com.zup.comicsapi.service.ComicsPorUsuarioService;

@RestController
@RequestMapping(value="/comicsPorUsuario")
public class ComicsPorUsuarioController {
		
	@Autowired
	private ComicsPorUsuarioService comicsPorUsuarioService;

	@GetMapping()
	public ResponseEntity<UsuarioDto> comicsPorUsuario(Long idUsuario) {
		Usuario usuarioComListaAtualizada = comicsPorUsuarioService.buscaComicsPorUsuario(idUsuario);
		
		if(usuarioComListaAtualizada == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(UsuarioDto.convert(usuarioComListaAtualizada));
	}
}
