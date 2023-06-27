package com.smhrd.soolsool.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.soolsool.domain.Community;
import com.smhrd.soolsool.service.CommunityService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
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

	@PostMapping("/community/write")
	public ResponseEntity<String> write(@RequestPart("title") String title, @RequestPart("content") String content,
			@RequestPart("img") MultipartFile img) {
		try {
			title = URLDecoder.decode(title, "UTF-8");
			content = URLDecoder.decode(content, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ResponseEntity<String> entity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");

		try {
			Community c = new Community();
			c.setComm_content(content);
			c.setComm_title(title);

			byte[] fileData = img.getBytes();

			String fileName = null;
			if (fileData != null) {
			    String base64Image = saveImage(img);
			    c.setComm_file(base64Image);
			}

			int cnt = service.write(c);

			if (cnt == 1)
				entity = new ResponseEntity<String>("SUCCESS", responseHeaders, HttpStatus.CREATED);
			else
				entity = new ResponseEntity<String>("FAIL", responseHeaders, HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

	private String saveImage(MultipartFile img) {
		String base64Image = "";
		if (img != null && !img.isEmpty()) {
			try {
				byte[] imgBytes = img.getBytes();
				base64Image = Base64.getEncoder().encodeToString(imgBytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return base64Image;
	}
}
