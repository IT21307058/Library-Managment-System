package com.libraryMS.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@NoArgsConstructor
@Getter
@Setter
public class MemberDto {
    @NotEmpty
    @Size(min=4, message="Username must be min of 4 characters!!")
    private String name;

    @Email(message = "Email address is not valid !!")
    private String email;

    private Date DOB;

    @NotEmpty
    private String address;

    @NotEmpty
    @Size(min = 3, max = 10, message = "Password must be min of 3 chars and max 10 characters")
    private String password;

//    private MemberCardDto memberCardDto;

//    private Set<BookDto> books = new HashSet<>();
//    private Set<BookDto> books = new HashSet<>();



}
