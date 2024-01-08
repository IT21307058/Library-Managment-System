package com.libraryMS.services.impl;

import com.libraryMS.entities.Member;
import com.libraryMS.entities.MemberCard;
import com.libraryMS.exception.ResourceNotFoundException;
import com.libraryMS.payloads.MemberCardDto;
import com.libraryMS.payloads.MemberCardResponse;
import com.libraryMS.payloads.MemberDto;
import com.libraryMS.repositories.MemberCardRepo;
import com.libraryMS.repositories.MemberRepo;
import com.libraryMS.services.MemberCardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberCardServiceImpl implements MemberCardService {
    @Autowired
    private MemberCardRepo memberCardRepo;

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MemberCardDto createCard(MemberCardDto memberCardDto, Integer memberId) {
        Member member = this.memberRepo.findById(memberId).orElseThrow(() -> new ResourceNotFoundException("Member", "Member id", memberId));
        System.out.println(member.getName());
//        MemberDto memberDto1 = this.modelMapper.map(member, MemberDto.class);

        MemberCard memberCard = this.modelMapper.map(memberCardDto, MemberCard.class);

        memberCard.setMember(member);
        memberCard.setIssuedDate(new Date());
        memberCard.setExpiryDate(Date.from(LocalDate.now().plusYears(2).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        MemberCard memberCard1 = this.memberCardRepo.save(memberCard);


        return this.modelMapper.map(memberCard1, MemberCardDto.class);
    }

    @Override
    public List<MemberCardDto> getAllCard() {
        List<MemberCard> mcList = this.memberCardRepo.findAll();

        List<MemberCardDto> mcdtolist = mcList.stream().map((card) -> this.modelMapper.map(card, MemberCardDto.class)).collect(Collectors.toList());

        return mcdtolist;
    }

    @Override
    public void deleteCard(Integer cardId) {
        MemberCard membercard = this.memberCardRepo.findById(cardId).orElseThrow(() -> new ResourceNotFoundException("MemberCard", "card id", cardId));
        this.memberCardRepo.delete(membercard);

    }
}
