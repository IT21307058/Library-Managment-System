package com.libraryMS.services;

import com.libraryMS.entities.Book;
import com.libraryMS.payloads.ApiResponse;
import com.libraryMS.payloads.BookDto;
import com.libraryMS.payloads.BookResponse;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;
import java.util.Set;

public interface BookService {
    BookDto createBook(BookDto bookDto, Integer authorId);

    BookDto updateBook(BookDto bookDto, Integer bookId);

    BookDto lendBook(Integer memberId, Integer bookId);

    void deleteBook(Integer bookId);

    BookDto getBookById(Integer bookId);

    BookResponse getAllBook(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    BookResponse searchBook(String keyword);
}
