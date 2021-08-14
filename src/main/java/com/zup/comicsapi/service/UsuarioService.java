package com.zup.comicsapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.comicsapi.exceptions.UsuarioJaExisteException;
import com.zup.comicsapi.exceptions.UsuarioNaoExisteException;
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
				throw new UsuarioJaExisteException("Usuário já existe!");
			}
		} catch (UsuarioJaExisteException e) {
			//log: usuário já existe
			System.out.println(e.getMessage());
			return null;
		}		
		
		//log: usuário não existe. Realizando gravação no banco!
		return usuarioRepository.save(usuario);
	}
	
	public Usuario buscaUsuarioPorId(Long idUsuario){
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);	
		try {
			if (usuarioOptional.isEmpty()) {
				throw new UsuarioNaoExisteException("Usuário não existe!");				
			} else {
				return usuarioOptional.get();
			}			
		} catch (UsuarioNaoExisteException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
}
