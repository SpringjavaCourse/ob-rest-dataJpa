package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

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


    @DisplayName("Comprobar Hola mundo desde controladores Spring REST") // Asignamos nombre al test para visualizacion
    @Test
    void hello() {
        ResponseEntity<String> response = // Respuesta Http
                testRestTemplate.getForEntity("/hola",String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode()); // compara el codigo del estado de la peticion este en OK
        assertEquals(200, response.getStatusCodeValue()); // verifica que el valor de codigo http sea 200
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

        ResponseEntity<Book> response = // Respuesta Http
                testRestTemplate.getForEntity("/api/books/1",Book.class);

        //assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()); // verifica la respuesta sea OK
    }

    @Test
    void create() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "title": "Libro creado desde Spring Test",
                    "author": "Yuval Noah",
                    "pages": 650,
                    "price": 19.99,
                    "releaseDate": "2019-12-01",
                    "online": false
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json,headers); // Simboliza la peticion
        ResponseEntity<Book> response =
                testRestTemplate.exchange("/api/books", HttpMethod.POST, request, Book.class);

        Book result =  response.getBody();

        assertEquals(1L,result.getId()); // verifica que contenga la ID 1
        assertEquals("Libro creado desde Spring Test",result.getTitle()); // verifica que el titulo

    }
}