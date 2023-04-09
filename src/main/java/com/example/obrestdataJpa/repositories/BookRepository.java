package com.example.obrestdataJpa.repositories;

import com.example.obrestdataJpa.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> { // JpaRepository, mos proporcionara los emtodos para realizar el crud.



}
