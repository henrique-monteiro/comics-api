package com.zup.comicsapi.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.zup.comicsapi.dto.UsuarioAtualizarDto;
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
		try{
			return usuarioRepository.save(usuario);
		} catch(DataIntegrityViolationException e){
			throw new UsuarioJaExisteException("Usuário já existe!");
		}
	}

	public Usuario buscaUsuarioPorId(Long idUsuario){
		try {
			return usuarioRepository.findById(idUsuario).get();
		} catch (NoSuchElementException e) {
			throw new UsuarioNaoExisteException("Usuário não existe!");
		}
	}


	public Usuario atualizarUsuario(Usuario usuario, UsuarioAtualizarDto usuarioAtualizar) {
		if (!usuarioAtualizar.getNome().isBlank()) {
			usuario.setNome(usuarioAtualizar.getNome());
		}
		if (!usuarioAtualizar.getEmail().isBlank()) {
			usuario.setEmail(usuarioAtualizar.getEmail());
		}
		return usuario;
	}

	public void delete(Long id) {
		try{
			usuarioRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e){
			throw new UsuarioNaoExisteException("Usuário não existe!");
		}
	}
}
