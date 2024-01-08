package com.libraryMS.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="membercard")
@Getter
@Setter
@NoArgsConstructor
public class MemberCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardNo;
    private Date issuedDate;

    private Date expiryDate;

    @OneToOne
    @JoinColumn(name="member_id")
    private Member member;
}
