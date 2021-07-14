package com.zup.comicsapi.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;



@Entity
public class Comics {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long comicId;	
	private String title;
	private BigDecimal price;
	private String creators;
	private String isbn;
	private String description;
	private String diaDesconto;
	private boolean descontoAtivo;
	
	@ManyToOne
	private Usuario usuario;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getComicId() {
		return comicId;
	}
	public void setComicId(long comicId) {
		this.comicId = comicId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getCreators() {
		return creators;
	}
	public void setCreators(String creators) {
		this.creators = creators;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getDiaDesconto() {
		return diaDesconto;
	}
	public void setDiaDesconto(String diaDesconto) {
		this.diaDesconto = diaDesconto;
	}
	public boolean isDescontoAtivo() {
		return descontoAtivo;
	}
	public void setDescontoAtivo(boolean descontoAtivo) {
		this.descontoAtivo = descontoAtivo;
	}

	
	
	
	
}
	
	
