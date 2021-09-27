package com.zup.comicsapi.service;

import com.zup.comicsapi.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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