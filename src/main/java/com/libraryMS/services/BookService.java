package com.libraryMS.services;

import com.libraryMS.payloads.ApiResponse;
import com.libraryMS.payloads.BookDto;

import java.util.List;

public interface BookService {
    BookDto createBook(BookDto bookDto);

    BookDto lendBook(BookDto bookDto, Integer memberId);

    void deleteBook(Integer bookId);

    List<BookDto> getAllBook();
}
