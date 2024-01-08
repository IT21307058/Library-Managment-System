package com.libraryMS.services;

import com.libraryMS.payloads.ApiResponse;
import com.libraryMS.payloads.BookDto;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface BookService {
    BookDto createBook(BookDto bookDto, Integer authorId);

    BookDto lendBook(Integer memberId, Integer bookId);

    void deleteBook(Integer bookId);

    List<BookDto> getAllBook();
}
