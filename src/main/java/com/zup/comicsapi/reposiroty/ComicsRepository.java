package com.zup.comicsapi.reposiroty;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zup.comicsapi.model.Comics;

public interface ComicsRepository extends JpaRepository<Comics, Long>{
}
