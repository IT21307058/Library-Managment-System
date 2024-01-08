package com.libraryMS.controllers;

import com.libraryMS.entities.Member;
import com.libraryMS.payloads.ApiResponse;
import com.libraryMS.payloads.MemberDto;
import com.libraryMS.services.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/")
    public ResponseEntity<MemberDto> createMember(@Valid @RequestBody MemberDto memberDto){

        MemberDto member = this.memberService.createMember(memberDto);

        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDto> updateMember(@Valid @RequestBody MemberDto memberDto, @PathVariable("id") Integer memberid){
        MemberDto updatedMemberDto = this.memberService.updateMember(memberDto, memberid);

        return ResponseEntity.ok(updatedMemberDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> getSingleMember(@PathVariable("id") Integer memberid){

        return ResponseEntity.ok(this.memberService.getMemberById(memberid));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") Integer uid){
        this.memberService.deleteUser(uid);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Member Deleted Successfully", true), HttpStatus.OK);
    }
}
