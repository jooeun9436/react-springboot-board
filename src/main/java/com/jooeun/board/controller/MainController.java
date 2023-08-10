package com.jooeun.board.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody 합쳐진 형태. 데이터를 직접 보냄
@RequestMapping("/")
public class MainController {
	
	@GetMapping("")
	public String hello() {
		return "Connection Successful";
	}

}
