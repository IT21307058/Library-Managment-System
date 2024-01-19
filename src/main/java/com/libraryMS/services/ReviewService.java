package com.libraryMS.services;

import com.libraryMS.payloads.ReviewDto;

import java.util.List;

public interface ReviewService {

    ReviewDto createReview(ReviewDto reviewDto, Integer bookId);

    List<ReviewDto> allReview();
}
