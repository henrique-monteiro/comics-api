package com.zup.comicsapi.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(uniqueConstraints= {@UniqueConstraint(columnNames = {"cpf"}), @UniqueConstraint(columnNames = {"email"})})
public class Usuario implements UserDetails { 

	private static final long serialVersionUID = 1L;

//	@Id 
//	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;

	@NotNull
//	@NotBlank(message = "Este campo não pode estar em branco.")
	private String nome;

	@NotNull
	@NotBlank(message = "Este campo não pode estar em branco.")
	private String senha;

	@NotNull
	@NotBlank(message = "Este campo não pode estar em branco.")
	private String email;

	@NotNull
	@NotBlank(message = "Este campo não pode estar em branco.")
	@Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}", message = "O campo CPF deve seguir o formato XXX.XXX.XXX-XX")
	private String cpf;

	@NotNull
	@NotBlank(message = "Este campo não pode estar em branco.")
	@Pattern(regexp = "\\d{2}\\/\\d{2}\\/\\d{4}", message = "O campo Data de Nascimento deve seguir o formato XX/XX/XXXX")
	private String dataDeNascimento;
	
	@OneToMany(mappedBy = "usuario")
	private List<Comics> listaDeComics = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER) //dessa forma ao carregar o usuario, carrega-se a lista de perfis dele
	private List<Perfil> perfis = new ArrayList<>();	
	
	public Usuario() {	} //construtor vazio necessário para JPA
	
	
	public Usuario(String nome, String email, String cpf, String dataDeNascimento, String senha) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento;
		this.senha = senha;
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
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
		return "Nome: " + this.nome + ", CPF: " + this.cpf + ", Email: " + this.email;
	}
	
	public List<Comics> getListaDeComics() {
		return listaDeComics;
	}


	public void setListaDeComics(List<Comics> listaDeComics) {
		this.listaDeComics = listaDeComics;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
