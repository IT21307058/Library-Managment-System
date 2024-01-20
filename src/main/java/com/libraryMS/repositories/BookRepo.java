package com.libraryMS.repositories;

import com.libraryMS.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Integer> {

    @Query("select b from Book b where b.title like :key")
    List<Book> searchByTitle(@Param("key") String title);
}
