package com.smhrd.soolsool.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.smhrd.soolsool.domain.Community;

@Mapper
public interface CommunityMapper {

	//comunity 전체 정보 불러오기
	public List<Community> getList();
	
	public Community communityOne(int idx); //Comunity.java에 있는 글순번 idx
	
	public int write(Community c);
	
	@Select("select * from tb_community where comm_idx=#{idx}")
	public Community content(int idx);
	
}
