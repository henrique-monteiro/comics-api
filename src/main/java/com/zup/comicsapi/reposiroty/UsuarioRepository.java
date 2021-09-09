package com.zup.comicsapi.reposiroty;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zup.comicsapi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findById(Long Id);
	Optional<Usuario> findByEmail(String email);
	Optional<Usuario> findByCpf(String cpf);
	
}
