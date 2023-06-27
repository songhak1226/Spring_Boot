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

import com.smhrd.soolsool.domain.Archive;
import com.smhrd.soolsool.service.ArchiveService;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class ArchiveRestController {

	@Autowired
	private ArchiveService service;
	
	@GetMapping("/archive")
	public JSONArray ArchiveList() {
		JSONArray array = service.ArchiveList();
		return array;
	}
	
	//하나만 가져오는거 (ex: 게시판 클릭시 글 상세)
//	@GetMapping("/{idx}")
//	public JSONObject CommunityOne(@PathVariable("idx") int idx) {
//		return service.CommunityOne(idx);
//	}
	

	@PostMapping("/archive")
	public String write(@RequestBody Archive a) {

		int cnt = service.write(a);
		
		if(cnt>0) {
			return "redirect:/archive";
		}
		else {
			return "redirect:/archive";
		}
		
	}
	
	
}
