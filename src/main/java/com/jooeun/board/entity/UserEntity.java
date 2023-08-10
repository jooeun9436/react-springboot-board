package com.jooeun.board.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jooeun.board.dto.SignUpDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="User") // 현재 클래스를 Entity 클래스로 지정 후 name 이름으로 사용
@Table(name="User") // 데이터배이스 테이블명과 일치시켜 현재 클래스를 매핑
public class UserEntity {

	@Id // primary key 지정
	private String userEmail;
	private String userPassword;
	private String userNickname;
	private String userPhoneNumber;
	private String userAddress;
	private String userProfile;
	
	public UserEntity(SignUpDto dto) {
		this.userEmail = dto.getUserEmail();
		this.userPassword = dto.getUserPassword();
		this.userNickname = dto.getUserNickname();
		this.userPhoneNumber = dto.getUserPhoneNumber();
		this.userAddress = dto.getUserAddress() + " " + dto.getUserAddressDetail();
	}
}
