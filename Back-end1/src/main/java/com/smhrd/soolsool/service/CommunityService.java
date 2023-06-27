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

	public List<Community> getList(){
		return mapper.getList();
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
	
	public Community content(int idx) {
		return mapper.content(idx);
	}

}
