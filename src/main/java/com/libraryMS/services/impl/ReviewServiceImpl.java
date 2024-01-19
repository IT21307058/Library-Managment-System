package com.libraryMS.services.impl;

import com.libraryMS.entities.Book;
import com.libraryMS.entities.Review;
import com.libraryMS.exception.ResourceNotFoundException;
import com.libraryMS.payloads.BookDto;
import com.libraryMS.payloads.ReviewDto;
import com.libraryMS.repositories.BookRepo;
import com.libraryMS.repositories.ReviewRepo;
import com.libraryMS.services.ReviewService;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ReviewDto createReview(ReviewDto reviewDto, Integer bookId) {
        Book book = this.bookRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "bookid", bookId));

        Review review = this.modelMapper.map(reviewDto, Review.class);

        review.setBook(book);

        Review savedReview = this.reviewRepo.save(review);
        return this.modelMapper.map(savedReview, ReviewDto.class);
    }

    @Override
    public List<ReviewDto> allReview() {
        List<Review> reviewList = this.reviewRepo.findAll();

        List<ReviewDto> reviewDtoList = reviewList.stream().map((review) -> this.modelMapper.map(review, ReviewDto.class)).collect(Collectors.toList());

        return reviewDtoList;
    }
}
