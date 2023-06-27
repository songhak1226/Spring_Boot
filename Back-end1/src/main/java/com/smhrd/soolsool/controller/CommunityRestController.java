package com.smhrd.soolsool.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.soolsool.converter.ImageConverter;
import com.smhrd.soolsool.converter.ImageToBase64;
import com.smhrd.soolsool.domain.Community;
import com.smhrd.soolsool.service.CommunityService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CommunityRestController {

	@Autowired
	private CommunityService service;

	@GetMapping("/community")
	public String communityList(Model model) {
		model.addAttribute("list", service.getList());
		return "communitylist";
	}

	@GetMapping("/community/writeform")
	public String writeForm() {
		return "communityform";
	}

	@PostMapping("/community/write")
	public String write(Community c, @RequestPart("comm_file") MultipartFile file) {
		
		String newFilename = UUID.randomUUID().toString() + file.getOriginalFilename();
		
		try {
			file.transferTo(new File(newFilename));
		}catch(IllegalStateException e ) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		c.setComm_file(newFilename);
		
		int cnt = service.write(c);
		
		if(cnt > 0) {
			return "redirect:/community";
		} else {
			return "redirect:/community/write";
		}
		
	}
	
	@GetMapping("/community/content/{idx}")
	public String content(@PathVariable("idx") int idx, Model model) {
		Community c = service.content(idx);
		File file = new File("C:\\uploadImage\\"+c.getComm_file());
		
		ImageConverter<File, String> converter = new ImageToBase64();
		
		try {
			String fileStringValue = converter.convert(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("community", c);
		return "communitycontent";
		
	}
}
