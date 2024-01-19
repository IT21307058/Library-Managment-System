package com.libraryMS.controllers;

import com.libraryMS.entities.Review;
import com.libraryMS.payloads.BookDto;
import com.libraryMS.payloads.ReviewDto;
import com.libraryMS.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/{bookid}/review")
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto, @PathVariable("bookid") Integer bookid){
        ReviewDto reviewDto1 = this.reviewService.createReview(reviewDto, bookid);

        return new ResponseEntity<ReviewDto>(reviewDto1, HttpStatus.CREATED);
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewDto>> getAllReviews(){
        List<ReviewDto> reviewDtoList = this.reviewService.allReview();

        return new ResponseEntity<List<ReviewDto>>(reviewDtoList, HttpStatus.OK);
    }
}
