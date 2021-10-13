package com.zup.comicsapi.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void deveriaDevolver400CasoEmailJaEstejaCadastradoParaOutroUsuario() throws Exception {
		URI uri = new URI("/usuarios");
		String usuarioJson = "{\"nome\":\"teste\",\"email\":\"henrique@email.com\",\"cpf:111.2222.333-14\"\"dataDeNascimento:11/11/1111\"}";
		mockMvc
				.perform(MockMvcRequestBuilders.post(uri).content(usuarioJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}
	
	@Test
	public void deveriaDevolver400CasoCPFJaEstejaCadastradoParaOutroUsuario() throws Exception {
		URI uri = new URI("/usuarios");
		String usuarioJson = "{\"nome\":\"teste\",\"email\":\"henriquemonteiro091@gmail.com\",\"cpf:333.333.333-44\"\"dataDeNascimento:11/11/1111\"}";
		mockMvc
		.perform(MockMvcRequestBuilders.post(uri).content(usuarioJson).contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(400));
	}
	
}
