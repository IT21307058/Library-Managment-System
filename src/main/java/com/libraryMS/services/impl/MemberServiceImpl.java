package com.libraryMS.services.impl;

import com.libraryMS.entities.Member;
import com.libraryMS.exception.ResourceNotFoundException;
import com.libraryMS.payloads.MemberDto;
import com.libraryMS.repositories.MemberRepo;
import com.libraryMS.services.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MemberDto createMember(MemberDto memberDto) {
        Member member = this.modelMapper.map(memberDto, Member.class);
        Member savedMember = this.memberRepo.save(member);

        MemberDto memberDto1 = this.modelMapper.map(savedMember, MemberDto.class);
        return memberDto1;
    }

    @Override
    public MemberDto updateMember(MemberDto memberDto, Integer id) {
        Member updatemember = this.memberRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member", "member id", id));

        updatemember.setName(memberDto.getName());
        updatemember.setEmail(memberDto.getEmail());
        updatemember.setDOB(new Date(String.valueOf(memberDto.getDOB())));
        updatemember.setAddress(memberDto.getAddress());
        updatemember.setPassword(memberDto.getPassword());

        Member updatedMember = this.memberRepo.save(updatemember);
        MemberDto updatedDto = this.modelMapper.map(updatedMember, MemberDto.class);
        return updatedDto;
    }

    @Override
    public MemberDto getMemberById(Integer id) {
        Member member = this.memberRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member", "member id", id));

        MemberDto memberDto = this.modelMapper.map(member, MemberDto.class);
        return memberDto;
    }

    @Override
    public void deleteUser(Integer id) {
        Member member = this.memberRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member", "member id", id));

        this.memberRepo.delete(member);

    }
}
