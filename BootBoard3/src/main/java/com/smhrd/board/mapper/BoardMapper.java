package com.smhrd.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.smhrd.board.domain.Board;

@Mapper
public interface BoardMapper {
	
	public int write(Board b);
	
	public List<Board> getList();
	
	@Select("select * from board where idx=#{idx}") 
	public Board content(int idx);
	
	@Delete("delete from board where idx=#{idx}")
	public void delete(int idx);

}
