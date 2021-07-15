package com.zup.comicsapi.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;
import javax.validation.constraints.NotBlank;


@Entity
public class Usuario {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
	
	@NotNull @NotBlank
	private String nome;
	
	@Column(unique=true)
	private String email;
	
	@Column(unique=true)
	private String cpf;
	
	@NotNull @NotBlank
	private String dataDeNascimento;
	
	@OneToMany(mappedBy = "usuario")
	private List<Comics> listaDeComics = new ArrayList<>();	
	
	public Usuario() {	} //construtor vazio necessário para JPA
	
	
	public Usuario(@NotBlank String nome, String email, String cpf, @NotBlank String dataDeNascimento) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	
	@Override
	public String toString() {
		return "Nome: " + this.nome + ", CPF: " + this.cpf;
	}
	
	public List<Comics> getListaDeComics() {
		return listaDeComics;
	}


	public void setListaDeComics(List<Comics> listaDeComics) {
		this.listaDeComics = listaDeComics;
	}
	
}
