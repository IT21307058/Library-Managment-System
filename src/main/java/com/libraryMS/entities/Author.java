package com.libraryMS.entities;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="author")
@Getter
@Setter
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;

    @Column(nullable = false, length = 50)
    private String name;

    //cascade = CascadeType.ALL: Defines the cascade behavior for this association.
    // In this scenario, when operations
    // (like persist, remove, merge, etc.) are performed on an Author entity,
    // the same operations will be cascaded to its associated Book entities.
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Book> books = new HashSet<>();

}
