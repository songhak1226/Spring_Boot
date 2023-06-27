package com.smhrd.soolsool.service;

import com.smhrd.soolsool.domain.Member;
import com.smhrd.soolsool.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;

    public int save(Member member) {
        Member existingMember = memberMapper.getMemberByMb_id(member.getMb_id());
        System.out.println(member.getMb_id());
        if (existingMember != null) {
            return 1;  // ID already exists
        }

        memberMapper.insertMember(member);
        return 0;  // Registration successful
    }
    public Member login(Member member) {
        Member fetchedMember = memberMapper.login(member.getMb_id(), member.getMb_pw());
        return fetchedMember;
    }



}
