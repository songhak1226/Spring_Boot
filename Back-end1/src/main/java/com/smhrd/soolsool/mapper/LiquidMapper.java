package com.smhrd.soolsool.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.soolsool.domain.Liquid;

@Mapper
public interface LiquidMapper {

	//comunity 전체 정보 불러오기
	public List<Liquid> liquidList();
	
	public Liquid liquidOne(int idx); //Comunity.java에 있는 글순번 idx
	
	public int write(Liquid a);
	
}
