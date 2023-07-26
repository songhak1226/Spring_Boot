package com.smhrd.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smhrd.boot.model.AndMember;

@Repository
public interface MemberRepository extends JpaRepository<AndMember, Integer>{
	
	// select * from andmember2 where id=? and pw=?
	// findby = where이랑 비슷함
	AndMember findByIdAndPw(@Param("id")String id, @Param("pw")String pw); // 실제넘어오는 값이랑 컬럼이름
}
