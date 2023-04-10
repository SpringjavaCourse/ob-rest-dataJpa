package com.example.obrestdataJpa.controller;

import com.example.obrestdataJpa.entities.Book;
import com.example.obrestdataJpa.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private final Logger log = LoggerFactory.getLogger(BookController.class); // Nos permite mostrar mensaje con colres, logs etc

    // Atributos
    private BookRepository bookRepository;

    // Constructores
    public BookController(BookRepository bookRepository) {

        this.bookRepository = bookRepository;
    }


    /**
     * Buscar todos los libros que hay en base de datos (ArrayList de libros)
     * http://localhost:8080/api/books
     * @return List<Book> Lista de libros
     */
    @GetMapping("/api/books")
    public List<Book> findAll(){
        // recuperar y devolver los libros de BD
        return bookRepository.findAll();
    }


    /**
     * http://localhost:8080/api/books/1...
     * Buscar un solo libro en BD segun su ID
     * @param id id del libro deseado
     * @return book libro deseado
     */
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findOneById(@PathVariable Long id){

        Optional<Book> bookOpt =  bookRepository.findById(id); // Optional, para no trabajar con el null
        // comprueba si el libro esta presente.

        // Option 1
        if(bookOpt.isPresent()) // comprueba si el libro esta presente.
            return ResponseEntity.ok(bookOpt.get()); // envia el codigo OK 202
        else
            return ResponseEntity.notFound().build();// envia un error 404 Not found

        // Opcion 2 programacion funcional
        //return bookOpt.orElse(null);
        //return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Crear un nuevo libro en BD
     * Metodo POST, no colisiona con findAll porque son diferentes metodos HTTP: GET vs POST
     * @param book
     * @param headers
     * @return
     */
    @PostMapping( "/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));// obtiene el header UserAgent, quien nos envia la peticion.
        // guardar el libro recibido por parámetro en la base de datos
        if(book.getId() != null){ // quiere decir que existe el id y por tanto no es una creación
            log.warn("trying to create a book with id");//TODO: Nos porporciona mayor informacion
            System.out.println("trying to create a book with id");
            return ResponseEntity.badRequest().build();
        }
        Book result = bookRepository.save(book);// Se genera el libro devuelto con clave primaria
        return ResponseEntity.ok(result);
    }

    /**
     * Actualziar un libro existente en BD
     * @param book
     * @return
     */
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book){

        if(book.getId() == null){// si no tienen ID no es el metodo adecuado
            log.warn("trying to update a non existent book");
            return ResponseEntity.badRequest().build();// SE ESTA ENVIANDO MAL LA PETICION
        }
        if(!bookRepository.existsById(book.getId())){// NO ENCONTRADO
            log.warn("trying to update a non existent book");
            return ResponseEntity.notFound().build();
        }

        Book result = bookRepository.save(book);// Se genera el libro devuelto con clave primaria
        return ResponseEntity.ok(result);
    }


    // Borrar un libro en BD
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){

        if(!bookRepository.existsById(id)){// NO ENCONTRADO
            log.warn("trying to update a non existent book");
            return ResponseEntity.notFound().build();
        }

        bookRepository.deleteById(id);
        return  ResponseEntity.noContent().build();
    }

}
