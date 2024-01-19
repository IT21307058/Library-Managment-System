package com.libraryMS.payloads;

import com.libraryMS.entities.Book;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
    private int reviewId;
//    private Integer bookid;
    private String reviewDescription;

    private BookDto book;
}
