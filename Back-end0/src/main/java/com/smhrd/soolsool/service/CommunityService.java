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
import org.springframework.web.bind.annotation.GetMapping;

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

	@GetMapping("/community")
	public JSONArray CommunityList() {
		List<Community> list = mapper.communityList();
		JSONArray jsonArray = new JSONArray();

		// ImageConverter 인터페이스의 구현체 생성
		ImageConverter<File, String> converter = new ImageToBase64();

		// Community -> JsonObject
		for (Community c : list) {
			// 1. 이미지 파일을 읽어 문자열로 변환
			String filepath = "classpath:/static/img" + c.getComm_file();
			Resource resource = resourceLoader.getResource(filepath);
			String fileStringValue = null;

			try {
				fileStringValue = converter.convert(resource.getFile());
			} catch (IOException e) {
				e.printStackTrace();
			}

			// 2. Community 객체를 JsonObject로 변환
			JSONObject obj = new JSONObject();
			obj.put("comm_idx", c.getComm_idx());
			obj.put("comm_title", c.getComm_title());
			obj.put("comm_content", c.getComm_content());
			obj.put("comm_at", c.getComm_at());
			obj.put("comm_file", fileStringValue);
			obj.put("mb_id", c.getMb_id());
			obj.put("comm_likes", c.getComm_likes());
			jsonArray.add(obj);
		}

		return jsonArray;
	}

	public JSONObject CommunityOne(int idx) {
		Community community = mapper.communityOne(idx);
		ImageConverter<File, String> converter = new ImageToBase64();
		String filepath = "classpath:/static/img" + community.getComm_file();
		Resource resource = resourceLoader.getResource(filepath);
		String fileStringValue = null;
		try {
			fileStringValue = converter.convert(resource.getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		obj.put("community", community);

		return obj;
	}

	public int write(Community c) {
		return mapper.write(c);
	}

}
