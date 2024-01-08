package com.libraryMS.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class BookDto {
    private Integer bookid;
    private String title;
    private String description;
    private Date lend_date;
}
