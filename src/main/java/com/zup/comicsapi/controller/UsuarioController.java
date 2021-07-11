package com.zup.comicsapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zup.comicsapi.UsuarioReposiroty.UsuarioRepository;
import com.zup.comicsapi.model.Usuario;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@RequestMapping(value="/listaUsuarios")
	@ResponseBody
	public List<Usuario> lista() { 
		List<Usuario> usuarios = usuarioRepository.findAll();
		System.out.println(usuarios);
		return usuarios;
	}
	
	
	
	
	
	
}
