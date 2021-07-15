package com.zup.comicsapi.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.comicsapi.dto.ComicsDto;
import com.zup.comicsapi.model.Comics;

import com.zup.comicsapi.service.ComicsPorUsuarioService;

@RestController
@RequestMapping(value="/comicsPorUsuario")
public class ComicsPorUsuarioController {
		
	@Autowired
	private ComicsPorUsuarioService comicsPorUsuarioService;

	@GetMapping()
	public List<ComicsDto> comicsPorUsuario(Long id) {
		List<Comics> listaComicsPorUsuario = comicsPorUsuarioService.buscaComicsPorUsuario(id);
		
		return ComicsDto.converter(listaComicsPorUsuario);
	}
}
