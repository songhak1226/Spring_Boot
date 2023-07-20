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

public class MemberController {
	
	@Autowired
	private MemberService service;
	
	// localhost:8089/join
	@PostMapping("/join")
	public String join(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		String andMember = request.getParameter("AndMember");
		
		ObjectMapper om = new ObjectMapper(); // jackson-databind에서 제공
		AndMember am = om.readValue(andMember, AndMember.class);
		
		AndMember result = service.join(am);
		if(result != null) {
			return "Success";
		} else {
			return "Fail";
		}

	}

}
