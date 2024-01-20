package com.libraryMS.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@Getter
@Setter
public class BookResponse {
    //content of post
    private List<BookDto> content;
    // page number in pagination
    private int pageNumber;

    //page size in one page
    private int pageSize;

    //total element in all the post
    private long totalElements;
    //how many pages in the post in application
    private int totalPages;

    //last page true or false
    private boolean lastPage;
}
