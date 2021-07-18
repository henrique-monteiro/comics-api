package com.zup.comicsapi.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import com.zup.comicsapi.model.Comics;
import com.zup.comicsapi.model.Usuario;

public class ComicsDto {
	
	private long comicId;	
	private String title;
	private BigDecimal price;
	private String creators;
	private String isbn;
	private String description;
	private String diaDesconto;
	private boolean descontoAtivo;
	
	private String nomeDoUsuario;	

	public ComicsDto(long comicId, String title, BigDecimal price, String creators, String isbn, String description,
			String diaDesconto, boolean descontoAtivo, Usuario usuario) {

		this.comicId = comicId;
		this.title = title;
		this.price = price;
		this.creators = creators;
		this.isbn = isbn;
		this.description = description;
		this.diaDesconto = diaDesconto;
		this.descontoAtivo = descontoAtivo;
		this.nomeDoUsuario = usuario.getNome();
	}

	public long getComicId() {
		return comicId;
	}

	public String getTitle() {
		return title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getCreators() {
		return creators;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getDescription() {
		return description;
	}

	public String getDiaDesconto() {
		return diaDesconto;
	}

	public boolean isDescontoAtivo() {
		return descontoAtivo;
	}

	public String getNomeDoUsuario() {
		return nomeDoUsuario;
	}

	public static List<ComicsDto> converter(List<Comics> listaComicsPorUsuario) {
		List<ComicsDto> listaComicsPorUsuarioDto= new ArrayList<>();	
		return listaComicsPorUsuarioDto;
	}
	
	
}
