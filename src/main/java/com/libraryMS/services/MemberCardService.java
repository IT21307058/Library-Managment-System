package com.libraryMS.services;

import com.libraryMS.payloads.MemberCardDto;
import com.libraryMS.payloads.MemberCardResponse;

import java.util.Date;
import java.util.List;

public interface MemberCardService {

    MemberCardDto createCard(MemberCardDto memberCardDto, Integer memberId);
    List<MemberCardDto> getAllCard();
    void deleteCard(Integer cardId);
}
