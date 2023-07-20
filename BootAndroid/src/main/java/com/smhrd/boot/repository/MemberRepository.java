package com.smhrd.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.boot.model.AndMember;

@Repository
public interface MemberRepository extends JpaRepository<AndMember, Integer>{
	
}
