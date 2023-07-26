package com.smhrd.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smhrd.boot.model.AndMember;
import com.smhrd.boot.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	// localhost:8089/join
	@PostMapping("/join")
	public String join(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		String andMember = request.getParameter("AndMember"); 
		
		ObjectMapper om = new ObjectMapper(); // jackson-databind에서 제공 (라이브러리)
		AndMember am = om.readValue(andMember, AndMember.class);
		
		AndMember result = service.join(am);
		if(result != null) {
			return "Success";
		} else {
			return "Fail";
		}

	}
	
	@PostMapping("/login")
	public String login(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		
		String andMember = request.getParameter("AndMember"); // JSON 형식의 문자열
		
		ObjectMapper om = new ObjectMapper();
		AndMember am = om.readValue(andMember, AndMember.class);
		
		// 요청이 들어오면 -> Controller(요청받고, 응답하고) -> Service (요청과 응답사이의 로직 처리)
		// MyBatis : 		-> Mapper(interface)
		// jPA 	   :		-> Repository(interface)
		AndMember result = service.login(am); 
		
		System.out.println(result);
		
		if(result != null) {
			return "Success";
		} else {
			return "Fail";
		}
	}

}
