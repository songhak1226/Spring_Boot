package com.smhrd.soolsool.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.soolsool.domain.Community;

@Mapper
public interface CommunityMapper {

	//comunity 전체 정보 불러오기
	public List<Community> communityList();
	
	public Community communityOne(int idx); //Community.java에 있는 글순번 idx
	
	public int write(Community c);
	
}
