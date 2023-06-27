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
import com.smhrd.soolsool.domain.Liquid;
import com.smhrd.soolsool.mapper.LiquidMapper;


@Service
public class LiquidService {
	
	@Autowired
	private LiquidMapper mapper;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	public JSONArray LiquidList() {
		List<Liquid> list = mapper.liquidList();
		
		JSONArray jsonArray = new JSONArray();
		ImageConverter<File, String> converter = new ImageToBase64();
		//Community -> JsonObject
		for(Liquid l: list) {
			//1. img 필드값 수정( 파일이름 -> 이미지 바이트 문자열 형태)
			//-1. 변환할 파일 실제 경로 정의
			String filepath = "classpath:/static/img"+l.getLiq_img();
			Resource resource = resourceLoader.getResource(filepath);
			String fileStringValue = null;
			try {
			fileStringValue= converter.convert(resource.getFile());
			}catch(IOException e) {
				e.printStackTrace();
			}
			
			l.setLiq_img(fileStringValue);
			//2. Community -> JsonObject(key:value) 변환
			JSONObject obj = new JSONObject(); //비어있는 json object 생성
			obj.put("liquid", l); //비어있는 object에 값을 추가한 것 
			
			jsonArray.add(l); 
		}
		return jsonArray;
	}
	
	public JSONObject LiquidOne(int idx) {
		Liquid liquid = mapper.liquidOne(idx);
		JSONObject obj = new JSONObject();
		ImageConverter<File, String> converter = new ImageToBase64();
		String filepath = "classpath:/static/img"+liquid.getLiq_img();
		Resource resource = resourceLoader.getResource(filepath);
		String fileStringValue = null;
		try {
		fileStringValue= converter.convert(resource.getFile());
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		liquid.setLiq_img(fileStringValue);
		//2. Community -> JsonObject(key:value)변환
		obj.put("liquid", liquid);
		
		return obj;
		
	}
	
	public int write(Liquid a) {
		return mapper.write(a);
	}
	
	

}
