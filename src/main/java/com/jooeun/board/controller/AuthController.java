package com.jooeun.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jooeun.board.dto.ResponseDto;
import com.jooeun.board.dto.SignInDto;
import com.jooeun.board.dto.SignInResponseDto;
import com.jooeun.board.dto.SignUpDto;
import com.jooeun.board.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthService authService;
	
	@PostMapping("/signUp")
	public ResponseDto<?> signUp(@RequestBody SignUpDto requestBody){
		ResponseDto<?> result = authService.signUp(requestBody);
		return result;
	}
	
	@PostMapping("/signIn")
	public ResponseDto<SignInResponseDto> signIn(@RequestBody SignInDto requestBody){
		ResponseDto<SignInResponseDto> result =authService.signIn(requestBody);
		return result;
	}
	
}
