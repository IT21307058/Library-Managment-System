package com.libraryMS.payloads;

import com.libraryMS.entities.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class BookDto {
    private Integer bookid;
//    private Integer authorId;
    private String title;
    private String description;

    private AuthorDto author;
    private String imageName;
//    private Date lend_date;

    private Set<MemberDto> members = new HashSet<>();
//
//    private Set<ReviewDto> reviews = new HashSet<>();
}
