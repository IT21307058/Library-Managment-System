package com.libraryMS.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class MemberCardResponse {

    private List<MemberCardDto> memberCardDto;

}
