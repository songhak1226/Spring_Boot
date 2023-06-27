package com.smhrd.soolsool.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.smhrd.soolsool.converter.ImageConverter;
import com.smhrd.soolsool.converter.ImageToBase64;
import com.smhrd.soolsool.domain.Archive;
import com.smhrd.soolsool.mapper.ArchiveMapper;


@Service
public class ArchiveService {
	
	@Autowired
	private ArchiveMapper mapper;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	public JSONArray ArchiveList() {
		List<Archive> list = mapper.archiveList();
		
		JSONArray jsonArray = new JSONArray();
		ImageConverter<File, String> converter = new ImageToBase64();
		//Community -> JsonObject
		for(Archive a: list) {
			//1. img 필드값 수정( 파일이름 -> 이미지 바이트 문자열 형태)
			//-1. 변환할 파일 실제 경로 정의
			String filepath = "classpath:/static/img"+a.getArc_file();
			Resource resource = resourceLoader.getResource(filepath);
			String fileStringValue = null;
			try {
			fileStringValue= converter.convert(resource.getFile());
			}catch(IOException e) {
				e.printStackTrace();
			}
			
			a.setArc_file(fileStringValue);
			//2. Community -> JsonObject(key:value) 변환
			JSONObject obj = new JSONObject(); //비어있는 json object 생성
			obj.put("archive", a); //비어있는 object에 값을 추가한 것 
			
			jsonArray.add(a); 
		}
		return jsonArray;
	}
	
	public JSONObject ArchiveOne(int idx) {
		Archive archive = mapper.archiveOne(idx);
		JSONObject obj = new JSONObject();
		ImageConverter<File, String> converter = new ImageToBase64();
		String filepath = "classpath:/static/img"+archive.getArc_file();
		Resource resource = resourceLoader.getResource(filepath);
		String fileStringValue = null;
		try {
		fileStringValue= converter.convert(resource.getFile());
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		archive.setArc_file(fileStringValue);
		//2. Community -> JsonObject(key:value)변환
		obj.put("archive", archive);
		
		return obj;
		
	}
	
	public int write(Archive a) {
		return mapper.write(a);
	}
	
	

}
