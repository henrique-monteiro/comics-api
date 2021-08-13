package com.zup.comicsapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.comicsapi.exceptions.UsuarioJaExistenteException;
import com.zup.comicsapi.model.Usuario;
import com.zup.comicsapi.reposiroty.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	public Usuario gravaUsuario(Usuario usuario) {
		//log: verificando se usuario existe
		try {
			if (usuarioRepository.findByEmail(usuario.getEmail()) != null
					|| usuarioRepository.findByCpf(usuario.getCpf()) != null) {
				throw new UsuarioJaExistenteException("Usuário já existe!");
			}
		} catch (UsuarioJaExistenteException e) {
			//log: usuário já existe
			System.out.println(e.getMessage());
			return null;
		}		
		
		//log: usuário não existe. Realizando gravação no banco!
		return usuarioRepository.save(usuario);
	}
}
