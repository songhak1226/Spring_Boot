package com.smhrd.camping.controller;


import java.util.List;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


import org.springframework.http.converter.HttpMessageNotReadableException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.smhrd.camping.domain.Comment;
import com.smhrd.camping.domain.Comunity;
import com.smhrd.camping.service.ComunityService;

@RestController
@CrossOrigin("http://localhost:3000")
public class ComunityRestController {

	@Autowired
	private ComunityService service;
	
	@GetMapping("/comunity")
	public JSONArray ComunityList() {
		JSONArray array = service.ComunityList();
		
		return array;
	}
	
	@GetMapping("/{idx}")
	public JSONObject ComunityOne(@PathVariable("idx") int idx) {
		return service.ComunityOne(idx);
	}
	
	
	
	 //게시판 글 작성 데이터 삽입하기
	   @PostMapping("/comunity/write")
	   public ResponseEntity<?> write(@RequestParam("story_title") String story_title,
			 @RequestParam("user_email") String user_email,
			 @RequestParam("story_category") String story_category,
	         @RequestParam("story_content") String story_content ,
	         @RequestPart(name="story_img") List<MultipartFile> file) 
	      
	   {
	      
	      // boardInsert 메서드에 매개변수들 전달
	      Comunity write = service.write(story_title, story_content, file, user_email, story_category); 
	       
	       return ResponseEntity.ok(write); // 예시로 간단히 응답만 반환하도록 설정
	      
	   }


	
	@GetMapping("/comunity/a")
	public String category() { //carray : 카테고리 스텝 array
		String carray = service.CategoryStep();
		return carray;
		
	}
	@PostMapping("/comunity/comment")
	public int comment(Comment m) {
		int cmt = service.comment(m);
		return cmt;
	}
	
	@GetMapping("/comunity/b")
	public String CommentList() {
		System.out.println("성공");
		String comment_array = service.CommentList();
		return comment_array;
		
	}
	
	@GetMapping("/comunity/search")
	public ResponseEntity<List<Comunity>> searchComunity(@RequestParam("search") String search){
		List<Comunity> comunity = service.searchComunity(search);
		return ResponseEntity.ok(comunity);
	}

	
	
	
}
