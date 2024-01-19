package com.libraryMS.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="review")
@Setter
@Getter
@NoArgsConstructor
public class Review {

    //The GenerationType.IDENTITY strategy in JPA specifies that the database
    // generates the primary key values for newly inserted entities automatically
    // using an identity column.

    //databases that support auto-incremented columns, like MySQL, PostgreSQL, etc.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;

    private String reviewDescription;

    @ManyToOne
    @JoinColumn(name="bookid")
    private Book book;


}
