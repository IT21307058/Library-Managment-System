package com.libraryMS.services.impl;

import com.libraryMS.entities.Author;
import com.libraryMS.entities.Book;
import com.libraryMS.exception.ResourceNotFoundException;
import com.libraryMS.payloads.AuthorDto;
import com.libraryMS.repositories.AuthorRepo;
import com.libraryMS.repositories.BookRepo;
import com.libraryMS.services.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
//        Book book = this.bookRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "Book id", bookId));
        Author author = this.modelMapper.map(authorDto, Author.class);

//        author.setName(authorDto.getName());

        Author savedAuthor = this.authorRepo.save(author);

        return this.modelMapper.map(savedAuthor, AuthorDto.class);
    }

    @Override
    public void deleteAuthor(Integer authorId) {
        Author author = this.authorRepo.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author", "Author id", authorId));

        this.authorRepo.delete(author);
    }

    @Override
    public List<AuthorDto> getAllAuthor() {
        List<Author> authors = this.authorRepo.findAll();

        List<AuthorDto> authorDtoList = authors.stream().map((author) -> this.modelMapper.map(author, AuthorDto.class)).collect(Collectors.toList());

        return authorDtoList;
    }
}
