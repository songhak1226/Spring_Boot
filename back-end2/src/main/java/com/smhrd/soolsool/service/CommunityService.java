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
import com.smhrd.soolsool.domain.Community;
import com.smhrd.soolsool.mapper.CommunityMapper;


@Service
public class CommunityService {
	
	@Autowired
	private CommunityMapper mapper;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	public JSONArray CommunityList() {
		List<Community> list = mapper.communityList();
		
		JSONArray jsonArray = new JSONArray();
		ImageConverter<File, String> converter = new ImageToBase64();
		//Community -> JsonObject
		for(Community c: list) {
			//1. img 필드값 수정( 파일이름 -> 이미지 바이트 문자열 형태)
			//-1. 변환할 파일 실제 경로 정의
			String filepath = "classpath:/static/img"+c.getComm_file();
			Resource resource = resourceLoader.getResource(filepath);
			String fileStringValue = null;
			try {
			fileStringValue= converter.convert(resource.getFile());
			}catch(IOException e) {
				e.printStackTrace();
			}
			
			c.setComm_file(fileStringValue);
			//2. Community -> JsonObject(key:value) 변환
			JSONObject obj = new JSONObject(); //비어있는 json object 생성
			obj.put("comunity", c); //비어있는 object에 값을 추가한 것 
			
			jsonArray.add(c); 
		}
		return jsonArray;
	}
	
	public JSONObject CommunityOne(int idx) {
		Community community = mapper.communityOne(idx);
		JSONObject obj = new JSONObject();
		ImageConverter<File, String> converter = new ImageToBase64();
		String filepath = "classpath:/static/img"+community.getComm_file();
		Resource resource = resourceLoader.getResource(filepath);
		String fileStringValue = null;
		try {
		fileStringValue= converter.convert(resource.getFile());
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		community.setComm_file(fileStringValue);
		//2. Community -> JsonObject(key:value)변환
		obj.put("community", community);
		
		return obj;
		
	}
	
	public int write(Community c) {
		return mapper.write(c);
	}
	
	

}
