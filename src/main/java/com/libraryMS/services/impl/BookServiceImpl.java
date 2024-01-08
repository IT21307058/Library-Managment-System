package com.libraryMS.services.impl;

import com.libraryMS.entities.Book;
import com.libraryMS.entities.Member;
import com.libraryMS.exception.ResourceNotFoundException;
import com.libraryMS.payloads.ApiResponse;
import com.libraryMS.payloads.BookDto;
import com.libraryMS.repositories.BookRepo;
import com.libraryMS.repositories.MemberRepo;
import com.libraryMS.services.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookDto createBook(BookDto bookDto) {

        Book createBook = this.bookRepo.save(this.modelMapper.map(bookDto, Book.class));

        BookDto createdBook = this.modelMapper.map(createBook, BookDto.class);
        return createdBook;
    }

    @Override
    public BookDto lendBook(BookDto bookDto, Integer memberId) {
        Member member = this.memberRepo.findById(memberId).orElseThrow(() -> new ResourceNotFoundException("Member", "member id", memberId));

        Book book = this.modelMapper.map(bookDto, Book.class);

        book.setMember(member);
        book.setLend_date(new Date());

        Book lendbook = this.bookRepo.save(book);
        return this.modelMapper.map(lendbook, BookDto.class);
    }

    @Override
    public void deleteBook(Integer bookId) {
        this.bookRepo.delete(this.bookRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "Book id", bookId)));
    }

    @Override
    public List<BookDto> getAllBook() {
        List<Book> allbooks = this.bookRepo.findAll();

        List<BookDto> bookDtos = allbooks.stream().map((book) -> this.modelMapper.map(book, BookDto.class)).collect(Collectors.toList());

        return bookDtos;
    }
}
