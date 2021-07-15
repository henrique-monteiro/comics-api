package com.zup.comicsapi.reposiroty;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zup.comicsapi.model.Comics;
import com.zup.comicsapi.model.Usuario;

public interface ComicsRepository extends JpaRepository<Comics, Long>{
	List<Comics> findByUsuario(Usuario usuario);
}
