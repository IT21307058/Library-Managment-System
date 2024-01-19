package com.libraryMS.controllers;

import com.libraryMS.entities.Book;
import com.libraryMS.entities.Member;
import com.libraryMS.payloads.ApiResponse;
import com.libraryMS.payloads.BookDto;
import com.libraryMS.payloads.MemberDto;
import com.libraryMS.services.BookService;
import com.libraryMS.services.FileService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private FileService fileService;

    @Value("images/")
    private String path;

    @PostMapping("/{authorid}/")
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto, @PathVariable("authorid") Integer authorId){
        BookDto createdBook = this.bookService.createBook(bookDto, authorId);

        return new ResponseEntity<BookDto>(createdBook, HttpStatus.OK);
    }

    @PutMapping("/{bookid}")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto, @PathVariable("bookid") Integer bookid){
        BookDto bookDto1 = this.bookService.updateBook(bookDto, bookid);

        return new ResponseEntity<BookDto>(bookDto1, HttpStatus.OK);
    }

    @PostMapping  ("/{bookid}/{id}")
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

    @PostMapping("/image/upload/{bookid}")
    public ResponseEntity<BookDto> uploadBookImage(@RequestParam("image")MultipartFile image, @PathVariable Integer bookid) throws IOException {
        BookDto bookDto = this.bookService.getBookById(bookid);

        String fileName = this.fileService.uploadImage(path, image);

        bookDto.setImageName(fileName);

        BookDto updatedBook = this.bookService.updateBook(bookDto, bookid);

        return new ResponseEntity<BookDto>(updatedBook, HttpStatus.OK);
    }

    @GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    )throws IOException{
        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}
