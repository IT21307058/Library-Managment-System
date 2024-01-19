package com.libraryMS.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name="member")
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int member_id;

    @Column(name="member_name", nullable = false, length = 100)
    private String name;
    private String email;
    private Date DOB;
    private String address;
    private String password;

    @OneToOne(mappedBy = "member")
    private MemberCard memberCard;

//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private Set<Book> books = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "member_book",
            joinColumns = @JoinColumn(name = "member_id", referencedColumnName = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "bookid"))
    @JsonManagedReference
    private Set<Book> bookList;


}
