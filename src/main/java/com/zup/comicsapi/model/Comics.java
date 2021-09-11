package com.zup.comicsapi.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Comics {
	
//	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
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
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
		return price.divide(new BigDecimal("1.2"),2,RoundingMode.UP);
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
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
//	public Usuario getUsuario() {
//		return usuario;
//	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getDiaDesconto() {
		return diaDesconto;
	}
	public void setDiaDesconto(String diaDesconto) {
		this.diaDesconto = diaDesconto;
	}
	public String getCreators() {
		return creators;
	}
	public void setCreators(String creators) {
		this.creators = creators;
	}
	public boolean isDescontoAtivo() {
		return descontoAtivo;
	}
	public void setDescontoAtivo(boolean descontoAtivo) {
		this.descontoAtivo = descontoAtivo;
	}	
	
}
	
	
