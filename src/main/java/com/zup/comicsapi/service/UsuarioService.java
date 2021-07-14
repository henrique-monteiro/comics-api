package com.zup.comicsapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.comicsapi.model.Usuario;
import com.zup.comicsapi.reposiroty.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> findAll(){
		return usuarioRepository.findAll();
	}
	
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
}
