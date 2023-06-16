package com.smhrd.soolsool.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.soolsool.domain.Community;
import com.smhrd.soolsool.service.CommunityService;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class CommunityRestController {

	@Autowired
	private CommunityService service;
	
	@GetMapping("/community")
	public JSONArray CommunityList() {
		JSONArray array = service.CommunityList();
		return array;
	}
	
	@GetMapping("/{idx}")
	public JSONObject CommunityOne(@PathVariable("idx") int idx) {
		return service.CommunityOne(idx);
	}
	

	@PostMapping("/community")
	public String write(@RequestBody Community c) {

		int cnt = service.write(c);
		
		if(cnt>0) {
			return "redirect:/community";
		}
		else {
			return "redirect:/community";
		}
		
	}
	
	
}
