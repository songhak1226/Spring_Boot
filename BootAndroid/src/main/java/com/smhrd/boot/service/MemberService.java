package com.smhrd.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.boot.model.AndMember;
import com.smhrd.boot.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository repository;
	
	public AndMember join(AndMember am) {
		// save = insert랑 비슷함
		return repository.save(am);
	}

}
