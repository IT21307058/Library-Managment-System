package com.libraryMS.controllers;

import com.libraryMS.entities.Book;
import com.libraryMS.entities.Member;
import com.libraryMS.payloads.ApiResponse;
import com.libraryMS.payloads.BookDto;
import com.libraryMS.payloads.MemberDto;
import com.libraryMS.services.BookService;
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

    @PostMapping("/")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto){
        BookDto createdBook = this.bookService.createBook(bookDto);

        return new ResponseEntity<BookDto>(createdBook, HttpStatus.OK);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<BookDto> lendBook(@RequestBody BookDto bookDto, @PathVariable("id") Integer memberId){
        BookDto selectMember = this.bookService.lendBook(bookDto, memberId);

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
