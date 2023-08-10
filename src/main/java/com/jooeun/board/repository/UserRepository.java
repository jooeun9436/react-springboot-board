package com.jooeun.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jooeun.board.dto.SignInDto;
import com.jooeun.board.entity.UserEntity;

@Repository //@Component 포함. 의존성 주입
public interface UserRepository extends JpaRepository<UserEntity, String> { // UserEntity를 매개체로 데이터베이스와 통신. pk의 타입 지정. service 레이어가 데이터베이스에 접근해 쿼리문을 실행하도록 도와줌

	public boolean existsByUserEmailAndUserPassword(String userEmail, String userPassword);
}
