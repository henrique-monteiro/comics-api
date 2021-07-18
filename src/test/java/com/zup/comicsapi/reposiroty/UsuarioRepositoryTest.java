package com.zup.comicsapi.reposiroty;


import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zup.comicsapi.model.Usuario;

@RunWith(SpringRunner.class)
@DataJpaTest //anotação específica para classes do tipo repository
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //quando não estamos utilizando BD em memória
//@ActiveProfiles("test")
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository repositoy;
	@Test
	public void deveriaCarregarUmUsuarioAoBuscarPeloSeuId() {
		long idUsuario = 1;
		
		Optional<Usuario> usuarioOptional = repositoy.findById(idUsuario);		
		Assert.assertNotNull(usuarioOptional);
		
		Usuario usuario = repositoy.findById(idUsuario).get();
		Assert.assertEquals("henrique", usuario.getNome());
	}
	
	@Test
	public void nãoDeveriaCarregarUmUsuarioQueNaoEstejaCadastrado() {
		long idUsuario = 3;
		
		Optional<Usuario> usuario = repositoy.findById(idUsuario);		
		Assert.assertEquals(Optional.empty(), usuario);
	}

}
