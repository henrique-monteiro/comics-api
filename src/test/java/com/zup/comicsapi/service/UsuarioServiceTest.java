package com.zup.comicsapi.service;

import com.zup.comicsapi.exceptions.UsuarioJaExisteException;
import com.zup.comicsapi.model.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.TransactionSystemException;

import javax.persistence.RollbackException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UsuarioServiceTest {

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
        System.out.println("\n\n");
        System.out.println(usuarioService.listaUsuarios());
        System.out.println("\n\n");

        assertThrows(UsuarioJaExisteException.class,
                () -> usuarioService.gravaUsuario(new Usuario(
                "henrique", "henrique@outromail.com",
                "333.333.333-44", "11/01/2001",
                "$2a$10$MC4Q/dGI940PglYZJm0gQ.8mR0l0ydrKHRVwgwQRt5FGOCx9S8p62")));
    }

    @Test
    public void deveriaRetornarNullPoisOEmailJaExiste() {
        assertThrows(UsuarioJaExisteException.class,
                () -> usuarioService.gravaUsuario(new Usuario(
                "henrique", "henrique@email.com",
                "333.333.333-45", "11/01/2001",
                "$2a$10$MC4Q/dGI940PglYZJm0gQ.8mR0l0ydrKHRVwgwQRt5FGOCx9S8p62")));
    }
}