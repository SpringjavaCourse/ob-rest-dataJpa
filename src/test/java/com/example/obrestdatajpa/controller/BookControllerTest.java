package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)// Asigna un puerto aleatorio para hacer el test
class BookControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder); // este objeto nos permite utilizar los methodos http
    }


    @Test
    void hello() {
        ResponseEntity<String> response = // Respuesta Http
                testRestTemplate.getForEntity("/hola",String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode()); // compara el codigo del estado de la peticion este en OK
        assertEquals("Hola Mundo que tal vamos mundo, como va todo? Hasta Luego", response.getBody()); // compara el string recibido
    }
    @Test
    void findAll() {

        ResponseEntity<Book[]> response =   // Respuesta Http
                testRestTemplate.getForEntity("/api/books", Book[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode()); // verifica la respuesta sea OK
        assertEquals(200, response.getStatusCodeValue()); // verifica que el valor de codigo http sea 200

        List<Book> books = Arrays.asList(response.getBody()); // Nos convierte un array normal a arrayList
        System.out.println(books.size());

    }

    @Test
    void findOneById() {
    }

    @Test
    void create() {
    }
}