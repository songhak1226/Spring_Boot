package com.smhrd.soolsool.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.soolsool.domain.Liquid;
import com.smhrd.soolsool.service.LiquidService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LiquidController {

	@Autowired
	private LiquidService service;
	
	@GetMapping("/liquid")
	public JSONArray LiquidList() {
		JSONArray array = service.LiquidList();
		return array;
	}
	
//	@GetMapping("/{idx}")
//	public JSONObject LiquidOne(@PathVariable("idx") int idx) {
//		return service.LiquidOne(idx);
//	}
	

	@PostMapping("/liquid")
	public String write(@RequestBody Liquid c) {

		int cnt = service.write(c);
		
		if(cnt>0) {
			return "redirect:/liquid";
		}
		else {
			return "redirect:/liquid";
		}
		
	}
	
	
}
