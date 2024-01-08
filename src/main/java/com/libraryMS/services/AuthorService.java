package com.libraryMS.services;

import com.libraryMS.payloads.AuthorDto;

import java.util.List;

public interface AuthorService {

    AuthorDto createAuthor(AuthorDto authorDto, Integer bookId);

    void deleteAuthor(Integer authorId);

    List<AuthorDto> getAllAuthor();
}
