package com.libraryMS.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class MemberCardDto {

    private Integer cardNo;
    private Date issuedDate;

    private Date expiryDate;

}
