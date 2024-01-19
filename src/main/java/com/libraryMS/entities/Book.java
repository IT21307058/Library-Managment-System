package com.libraryMS.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name="book")
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookid;

    @Column(nullable = false, length = 50)
    private String title;
    @Column(nullable = false, length = 100)
    private String description;
    private String imageName;
    private Date lend_date;

//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;

    @ManyToMany(mappedBy = "bookList", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Member> memberList;

    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviewSet = new ArrayList<>();
}
