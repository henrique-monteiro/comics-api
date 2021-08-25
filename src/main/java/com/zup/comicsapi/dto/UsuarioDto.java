package com.zup.comicsapi.dto;

import java.util.ArrayList;
import java.util.List;

import com.zup.comicsapi.model.Usuario;

public class UsuarioDto {
	
	private Long id;
	private String nome;
	
	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static List<UsuarioDto> converterLista(List<Usuario> usuarios) {
		List<UsuarioDto> usuariosDto = new ArrayList<UsuarioDto>();
		for (Usuario usuario : usuarios) {
			usuariosDto.add(new UsuarioDto(usuario));
		}
		return usuariosDto;
	}
	
	
	
}
