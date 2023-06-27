package com.smhrd.soolsool.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.soolsool.domain.Archive;
import com.smhrd.soolsool.domain.Community;

@Mapper
public interface ArchiveMapper {

	// comunity 전체 정보 불러오기
	public List<Archive> archiveList();

	public Archive archiveOne(int idx); // Comunity.java에 있는 글순번 idx

	public int write(Archive a);

	public int getTotalArchives();

	public List<Archive> archiveListPage(int offset, int size);

}
