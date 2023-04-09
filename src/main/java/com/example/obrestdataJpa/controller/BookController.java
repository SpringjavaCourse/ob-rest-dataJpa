package com.example.obrestdataJpa.controller;

import com.example.obrestdataJpa.entities.Book;
import com.example.obrestdataJpa.repositories.BookRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    // Atributos
    private BookRepository bookRepository;

    // Constructores
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    // Crud sobre la entidad Book

    // Buscar todos los libros

    /**
     * http://localhost:8080/api/books
     * @return List<Book> Lista de libros
     */
    @GetMapping("/api/books")
    public List<Book> findAll(){
        // recuperar y devolver los libros de BD
        return bookRepository.findAll();
    }


    // Buscar un solo libro en BD segun su ID
    /**
     * obtiene un libro especifico
     * @param id id del libro deseado
     * @return book libro deseado
     */
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findOneById(@PathVariable Long id){

        Optional<Book> bookOpt =  bookRepository.findById(id);
        // comprueba si el libro esta presente.

        // Option 1
        if(bookOpt.isPresent()) // comprueba si el libro esta presente.
            return ResponseEntity.ok(bookOpt.get()); // envia el codigo OK 202
        else
            return ResponseEntity.notFound().build();// envia un error 404 Not found

        // Opcion 2
        //return bookOpt.orElse(null);
        //return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo libro en BD
    @PostMapping( "/api/books")
    public Book create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));// obtiene el header UserAgent, quien nos envia la peticion.
        // guardar el libro recibido por parámetro en la base de datos
        return bookRepository.save(book);
    }

    // Actualziar un libro existente en BD

    // Borrar un libro en BD

}
