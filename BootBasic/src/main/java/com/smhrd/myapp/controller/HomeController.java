package com.smhrd.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// view 반환 컨트롤러 지정
@Controller
public class HomeController {
	
	// @RequestMapping(value="/", method=RequestMethod.GET)
	@GetMapping(value="/") //하나밖에 없으니 value 생략 가능
	public String main() {
		return "Main";
	}

}
