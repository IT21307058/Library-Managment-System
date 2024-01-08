package com.libraryMS.services;

import com.libraryMS.payloads.MemberDto;

public interface MemberService {
    MemberDto createMember(MemberDto memberDto);
    MemberDto updateMember(MemberDto memberDto, Integer id);
    MemberDto getMemberById(Integer id);
    void deleteUser(Integer id);
}
