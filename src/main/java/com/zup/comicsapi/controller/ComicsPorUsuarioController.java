package com.zup.comicsapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
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
	public UsuarioDto comicsPorUsuario(Long idUsuario) {
		Usuario usuarioComListaAtualizada = comicsPorUsuarioService.buscaComicsPorUsuario(idUsuario);
		return UsuarioDto.convert(usuarioComListaAtualizada);
	}
}
