package com.zup.comicsapi.service;

import com.zup.comicsapi.exceptions.UsuarioJaExisteException;
import com.zup.comicsapi.model.Usuario;
import com.zup.comicsapi.reposiroty.UsuarioRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UsuarioServiceTest {

//    private Usuario usuario;
//    @Autowired
//    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;


//    @BeforeAll
//    public void inicializar(){
//        JA EXISTE ESTE USUARIO CADASTRADO NO BANCO DE DADOS
//        this.usuario = new Usuario("henrique", "henrique@email.com", "333.333.333-44", "11/01/2001");
//        usuarioRepository.save(this.usuario);
//    }

    @Test
    public void deveriaRetornarNullPoisOCPFJaExiste() {
        assertEquals(null, usuarioService.gravaUsuario(new Usuario("henrique", "henrique@outromail.com", "333.333.333-44", "11/01/2001")));
    }

    @Test
    public void deveriaRetornarNullPoisOEmailJaExiste() {
        assertEquals(null, usuarioService.gravaUsuario(new Usuario("henrique", "henrique@email.com", "333.333.333-45", "11/01/2001")));
    }




}