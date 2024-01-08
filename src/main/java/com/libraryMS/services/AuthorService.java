package com.libraryMS.services;

import com.libraryMS.payloads.AuthorDto;

import java.util.List;

public interface AuthorService {

    AuthorDto createAuthor(AuthorDto authorDto);

    void deleteAuthor(Integer authorId);

    List<AuthorDto> getAllAuthor();
}
