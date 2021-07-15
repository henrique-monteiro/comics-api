package com.zup.comicsapi.dto;

import java.util.ArrayList;
import java.util.List;

import com.zup.comicsapi.model.Comics;
import com.zup.comicsapi.model.Usuario;

public class UsuarioDto {
	
	private String nome;
	private List<Comics> listaAtualizada = new ArrayList<>();
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Comics> getListaAtualizada() {
		return listaAtualizada;
	}

	public void setListaAtualizada(List<Comics> listaAtualizada) {
		this.listaAtualizada = listaAtualizada;
	}

	public static UsuarioDto convert(Usuario usuarioComListaAtualizada) {
		UsuarioDto usuario = new UsuarioDto();
		usuario.setNome(usuarioComListaAtualizada.getNome());
		usuario.setListaAtualizada(usuarioComListaAtualizada.getListaDeComics());
		return usuario;
	}
	
}
