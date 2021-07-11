package com.zup.comicsapi.UsuarioReposiroty;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zup.comicsapi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
