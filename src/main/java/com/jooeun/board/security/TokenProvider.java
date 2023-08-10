package com.jooeun.board.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenProvider {

	//jwt 생성 및 검증을 위한 키
	private static final String SECURITY_KEY = "jwtseckey!@";
	
	//jwt 생성하는 메서드
	public String create(String userEmail) {
		
		//만료날짜를 현재 날짜 + 1시간으로 설정
		Date exprTime = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
		
		//jwt를 생성 
		return Jwts.builder()
				.signWith(SignatureAlgorithm.HS512, SECURITY_KEY) //암호화에 사용될 알고리즘, 키 지정
				.setSubject(userEmail).setIssuedAt(new Date()).setExpiration(exprTime) //jwt 제목, 생성일, 만료일 지정
				.compact(); //생성
		
	}
	
	//jwt 검증
	public String validate(String token) {
		
		//매개변수로 받은 토큰을 키를 사용해 복호화(디코딩)
		//claims에 파싱한 데이터 저장. 시크릿키와 토큰 지정해 getBody로 받아옴
		Claims claims = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody();
		
		//복호화 된 토큰의 payload에서 제목을 가져옴
		return claims.getSubject();
	}
}
