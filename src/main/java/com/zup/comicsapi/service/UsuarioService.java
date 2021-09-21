package com.zup.comicsapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
		//log: verificando se usuario existe
		try {
			verificaSeUsuarioJaExiste(usuario);
		} catch (UsuarioJaExisteException e) {
			//log: usuário já existe
			return null;
		}		
		
		//log: usuário não existe. Realizando gravação no banco!
		return usuarioRepository.save(usuario);
	}

	private void verificaSeUsuarioJaExiste(Usuario usuario) {
		if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()
				|| usuarioRepository.findByCpf(usuario.getCpf()).isPresent()) {
			throw new UsuarioJaExisteException("Usuário já existe!");
		}
	}

	public Usuario buscaUsuarioPorId(Long idUsuario){
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);	
		try {
			return verificaSeUsuarioNaoExiste(usuarioOptional);
		} catch (UsuarioNaoExisteException e) {
			return null;
		}

	}

	private Usuario verificaSeUsuarioNaoExiste(Optional<Usuario> usuarioOptional) {
		if (usuarioOptional.isEmpty()) {
			throw new UsuarioNaoExisteException("Usuário não existe!");
		}
		return usuarioOptional.get();

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
		usuarioRepository.deleteById(id);
		
	}
}
