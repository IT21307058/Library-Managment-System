package com.libraryMS.controllers;

import com.libraryMS.payloads.ApiResponse;
import com.libraryMS.payloads.MemberCardDto;
import com.libraryMS.payloads.MemberCardResponse;
import com.libraryMS.services.MemberCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/card")
public class MemberCardController {

    @Autowired
    private MemberCardService memberCardService;

    @PostMapping("/{memberid}")
    public ResponseEntity<MemberCardDto> createCard(@RequestBody MemberCardDto memberCardDto, @PathVariable("memberid") Integer memberid){
        MemberCardDto memberCardDto1 = this.memberCardService.createCard(memberCardDto, memberid);

        return new ResponseEntity<MemberCardDto>(memberCardDto1, HttpStatus.OK);
    }

    @DeleteMapping("/{memberid}")
    public ResponseEntity<ApiResponse> deleteCard(@PathVariable("memberid") Integer memberid){
         this.memberCardService.deleteCard(memberid);
         return new ResponseEntity<ApiResponse>(new ApiResponse("Member card deleted", true), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<MemberCardDto>> getCards(){
        List<MemberCardDto> list = this.memberCardService.getAllCard();

        return new ResponseEntity<List<MemberCardDto>>(list, HttpStatus.OK);
    }

}
