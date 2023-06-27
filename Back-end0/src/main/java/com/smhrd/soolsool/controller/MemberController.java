package com.smhrd.soolsool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.soolsool.domain.Member;
import com.smhrd.soolsool.service.MemberService;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/member")
    public String home() {
        return "member";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Member member) {
        int result = memberService.save(member);

        if(result == 1) {
            System.out.println("회원가입 실패");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원가입 실패");
        }

        System.out.println("회원가입 성공");
        return ResponseEntity.status(HttpStatus.OK).body("회원가입 성공");
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Member member) {
        Member loggedInMember = memberService.login(member);

        if (loggedInMember == null) {
            System.out.println("로그인 실패");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }

        System.out.println("로그인 성공");
        return ResponseEntity.status(HttpStatus.OK).body(loggedInMember);
    }
}
