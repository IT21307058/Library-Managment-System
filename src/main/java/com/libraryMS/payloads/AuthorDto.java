package com.libraryMS.payloads;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class AuthorDto {
    private Integer authorId;

    private Integer name;
    private Set<BookDto> books = new HashSet<>();

}
