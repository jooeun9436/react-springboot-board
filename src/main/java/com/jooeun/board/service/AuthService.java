package com.jooeun.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jooeun.board.dto.ResponseDto;
import com.jooeun.board.dto.SignInDto;
import com.jooeun.board.dto.SignInResponseDto;
import com.jooeun.board.dto.SignUpDto;
import com.jooeun.board.entity.UserEntity;
import com.jooeun.board.repository.UserRepository;
import com.jooeun.board.security.TokenProvider;

@Service
public class AuthService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TokenProvider tokenProvider;
	
	public ResponseDto<?> signUp(SignUpDto dto){
		String userEmail = dto.getUserEmail();
		String userPassword = dto.getUserPassword();
		String userPasswordCheck = dto.getUserPasswordCheck();
		
		//이메일 중복확인
		try {
			if(userRepository.existsById(userEmail))
				return ResponseDto.setFailed("이미 존재하는 이메일 계정입니다.");
		} catch (Exception error) {
			return ResponseDto.setFailed("데이터를 조회할 수 없습니다.");
		}
		
		//비밀번호가 서로 다르면 failed response 반환
		if(!userPassword.equals(userPasswordCheck)) 
			return ResponseDto.setFailed("비밀번호가 일치하지 않습니다.");
		
		//UserEntity 생성
		UserEntity userEntity = new UserEntity(dto);
		//UserRepository 이용해 데이터베이스에 Entity 저장
		try {
			userRepository.save(userEntity);
		} catch (Exception error) {
			return ResponseDto.setFailed("데이터를 등록할 수 없습니다.");
		}

		//성공 시 success response 반환
		return ResponseDto.setSuccess("회원가입에 성공하였습니다", null);
		
		
	}
	
	public ResponseDto<SignInResponseDto> signIn(SignInDto dto){
		String userEmail = dto.getUserEmail();
		String userPassword = dto.getUserPassword();
		
		
		try {
			if(!userRepository.existsByUserEmailAndUserPassword(userEmail, userPassword)) {
				return ResponseDto.setFailed("로그인에 실패하였습니다.");
			}
		} catch (Exception error) {
			return ResponseDto.setFailed("데이터를 조회할 수 없습니다.");
		}
		
		UserEntity userEntity = null;
		try {
			userEntity = userRepository.findById(userEmail).get();
		} catch (Exception error) {
			return ResponseDto.setFailed("데이터를 조회할 수 없습니다.");
		}
		
		userEntity.setUserPassword("");
		String token = tokenProvider.create(userEmail);
		int exprTime = 3600000;
		SignInResponseDto signInResponseDto = new SignInResponseDto(token, exprTime, userEntity);
		
		return ResponseDto.setSuccess("로그인에 성공하였습니다.", signInResponseDto);
		
	}

}
