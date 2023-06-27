package com.smhrd.soolsool.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.soolsool.converter.ImageConverter;
import com.smhrd.soolsool.converter.ImageToBase64;
import com.smhrd.soolsool.domain.Community;
import com.smhrd.soolsool.service.CommunityService;

@CrossOrigin("http://localhost:3000")
@RestController
public class CommunityRestController {

	@Autowired
	private CommunityService service;

	@GetMapping("/community")
	public JSONArray CommunityList(@RequestParam(required = false, defaultValue = "1") int page) {
	  return service.CommunityList(page);
	}

	@GetMapping("/{idx}")
	public JSONObject CommunityOne(@PathVariable("idx") int idx) {
		System.out.println("요청2");
		return service.CommunityOne(idx);
	}

	@PostMapping("/community/write")
	public ResponseEntity<?> write(@RequestParam("comm_title") String comm_title,
			@RequestParam("comm_content") String comm_content,
			@RequestPart(name = "comm_file") List<MultipartFile> file)

	{
		System.out.println("요청3");
		// boardInsert 메서드에 매개변수들 전달
		Community write = service.write(comm_title, comm_content, file);
		
		JSONObject jsonResponse = new JSONObject();

	    if (write != null) {
	        jsonResponse.put("result", "success");
	        jsonResponse.put("writeResult", write);
	    } else {
	        jsonResponse.put("result", "fail");
	    }

		return ResponseEntity.ok(jsonResponse); // 예시로 간단히 응답만 반환하도록 설정

	}
}
