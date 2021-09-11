package com.zup.comicsapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class Perfil implements GrantedAuthority{ //perfil de acesso ao usuario

	private static final long serialVersionUID = 1L;
	
//	@Id 
//	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private long id;
	
	@NotBlank(message = "Este campo não pode estar em branco.")
	private String nome; //pode ser aluno, moderador, admin, etc..
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	

	@Override
	public String getAuthority() {
		return this.nome;
	}

}
