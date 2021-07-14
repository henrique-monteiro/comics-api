package com.zup.comicsapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.comicsapi.model.Comics;
import com.zup.comicsapi.model.Usuario;
import com.zup.comicsapi.reposiroty.ComicsRepository;
import com.zup.comicsapi.reposiroty.UsuarioRepository;

@RestController
@RequestMapping(value="/comicsPorUsuario")
public class ComicsPorUsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ComicsRepository comicsRepository;

	
//	@GetMapping()
//	public List<Comics> comicsPorUsuario(Long id) {
//		Optional<Usuario> usuario = usuarioRepository.findById(id);
//		//List<Comics> listaComicsPorUsuario = comicsRepository.findbyUsuarioId(usuario);
//		return null;
//	}
	@GetMapping()
	public List<Comics> comicsPorUsuario(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		List<Comics> listaComicsPorUsuario = comicsRepository.findByUsuario(usuario);
		return listaComicsPorUsuario;
	}
}
