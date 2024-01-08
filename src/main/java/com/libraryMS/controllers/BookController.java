package com.libraryMS.controllers;

import com.libraryMS.entities.Book;
import com.libraryMS.entities.Member;
import com.libraryMS.payloads.ApiResponse;
import com.libraryMS.payloads.BookDto;
import com.libraryMS.payloads.MemberDto;
import com.libraryMS.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/{authorid}/")
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto, @PathVariable("authorid") Integer authorId){
        BookDto createdBook = this.bookService.createBook(bookDto, authorId);

        return new ResponseEntity<BookDto>(createdBook, HttpStatus.OK);
    }

    @PutMapping ("/{bookid}/{id}")
    public ResponseEntity<BookDto> lendBook(@PathVariable("id") Integer memberId, @PathVariable("bookid") Integer bookid){
        BookDto selectMember = this.bookService.lendBook(memberId, bookid);

        return new ResponseEntity<BookDto>(selectMember, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable("id") Integer bookid){
        this.bookService.deleteBook(bookid);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Book deleted sucessfully", true), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<BookDto>> getAllBooks(){
        List<BookDto> bookDtoList = this.bookService.getAllBook();

        return new ResponseEntity<List<BookDto>>(bookDtoList, HttpStatus.OK);
    }
}
