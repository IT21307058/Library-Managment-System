package com.libraryMS.entities;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="member")
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer member_id;

    @Column(name="member_name", nullable = false, length = 100)
    private String name;
    private String email;
    private Date DOB;
    private String address;
    private String password;

    @OneToOne
    private MemberCard memberCard;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private Set<Book> books = new HashSet<>();


}
