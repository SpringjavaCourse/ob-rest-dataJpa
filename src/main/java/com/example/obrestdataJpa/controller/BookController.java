package com.example.obrestdataJpa.controller;

import com.example.obrestdataJpa.entities.Book;
import com.example.obrestdataJpa.repositories.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    public Book findOneById(@PathVariable Long id){

        Optional<Book> bookOpt =  bookRepository.findById(id);
        // comprueba si el libro esta presente.

        // Option 1
//        if(bookOpt.isPresent()) // comprueba si el libro esta presente.
//            return bookOpt.get();
//        else
//            return  null;

        // Opcion 2
        return bookOpt.orElse(null);


    }

    // Crear un nuevo libro en BD

    // Actualziar un libro existente en BD

    // Borrar un libro en BD

}
