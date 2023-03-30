package com.example.obrestdataJpa;

import com.example.obrestdataJpa.entities.Book;
import com.example.obrestdataJpa.repositories.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ObRestDataJpaApplication {

	public static void main(String[] args) {

		ApplicationContext context =  SpringApplication.run(ObRestDataJpaApplication.class, args);
		BookRepository repository = context.getBean(BookRepository.class);

		// CRUD
		// crear un libro
		Book book1 = new Book(null, "Homo Deus", "Yuval Noah", 450, 29.99, LocalDate.of(2018,12,1), true);
		Book book2 = new Book(null, "Homo Sapiens", "Yuval Noah", 450, 19.99, LocalDate.of(2013,12,1), true);
		// Almacenar un libro
		System.out.println("Num libros en base de datos: " + repository.findAll().size());
		repository.save(book1);// Guardamos los libros
		repository.save(book2);// Guardamos los libros
		// Recuperar todos los libros
		System.out.println("Num libros en base de datos: " + repository.findAll().size());
		// Borrar un libro
		repository.deleteById(1L);

		System.out.println("Num libros en base de datos: " + repository.findAll().size());
	}

}
