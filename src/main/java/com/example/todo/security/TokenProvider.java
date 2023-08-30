package com.example.todo.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.todo.model.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenProvider {
	private static final String SECRET_KEY = "AKAJD25BNJ4nc43k52";
	
	//JWT 라이브러리를 이용, JWT 토큰 생성
	public String create(UserEntity userEntity) {
		Date expiryDate = Date.from(Instant
									.now()
									.plus(1, ChronoUnit.DAYS)); //지금으로부터 1일로 기한 설정
		//JWT Token 생성
		return Jwts.builder()
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY) //header에 들어갈 내용 및 서명을 하기 위한 secret_key
				.setSubject(userEntity.getId()) //payload에 들어갈 내용 - sub
				.setIssuer("demo app") //iss
				.setIssuedAt(new Date()) //iat
				.setExpiration(expiryDate) //exp -
				.compact(); 
	}

	//토큰을 디코딩 및 파싱 후 토큰의 위조 여부 확인
	public String validateAndGetUserId(String token) {
		//parseClainsJws메서드가 Base64로 디코딩 및 파싱
		//헤더와 페이로드를 setSigningKey로 넘어온 시크릿을 이용해 서명한 후, token의 서명과 비교
			//위조x -> 페이로드(Claims)리턴, 
			//위조 -> 예외 리턴
		//userId가 필요하므로 getBody를 부름
		
		Claims claims = Jwts.parser()
							.setSigningKey(SECRET_KEY)
							.parseClaimsJws(token)
							.getBody();
		
		return claims.getSubject();
	}
	
	
}
