package com.libraryMS.controllers;

import com.libraryMS.payloads.ApiResponse;
import com.libraryMS.payloads.AuthorDto;
import com.libraryMS.services.AuthorService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto){
        AuthorDto authorDto1 = this.authorService.createAuthor(authorDto);

        return new ResponseEntity<AuthorDto>(authorDto1, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAuthor(@PathVariable("id") Integer authorid){
        this.authorService.deleteAuthor(authorid);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Successsful", true), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<AuthorDto>> allAuthor(){
        List<AuthorDto> authorDtoList = this.authorService.getAllAuthor();

        return new ResponseEntity<List<AuthorDto>>(authorDtoList, HttpStatus.OK);
    }
}
