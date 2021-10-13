package com.zup.comicsapi.reposiroty;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.zup.comicsapi.model.Usuario;

@DataJpaTest //anotação específica para classes do tipo repository
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //quando não estamos utilizando BD em memória
public class UsuarioRepositoryTest {
	@Autowired
	private UsuarioRepository repository;

	@Test
	public void deveriaCarregarUmUsuarioAoBuscarPeloSeuId() {
		long idUsuario = 1;
		
		Optional<Usuario> usuarioOptional = repository.findById(idUsuario);		
		Assertions.assertNotNull(usuarioOptional);
		
		Usuario usuario = repository.findById(idUsuario).get();
		Assertions.assertEquals("henrique", usuario.getNome());
	}
	
	@Test
	public void nãoDeveriaCarregarUmUsuarioQueNaoEstejaCadastrado() {
		long idUsuario = 3;
		
		Optional<Usuario> usuario = repository.findById(idUsuario);		
		Assertions.assertEquals(Optional.empty(), usuario);
	}

	
}
