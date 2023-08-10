package com.jooeun.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "set") // 고정된 이름으로 사용
public class ResponseDto<D> {
	
	private boolean result;
	private String message;
	private D data;

	//메소드에서 제네릭 D 사용을 선언(매개변수로 제네릭을 사용할 경우 반환타입 앞에 제네릭 명시)
	//성공한 경우 성공한 인스턴스 만들어주는 메소드
	public static <D> ResponseDto<D> setSuccess(String message, D data){
//		return new ResponseDto<D>(true, message, data);
		return ResponseDto.set(true, message, data);
	}
	
	//실패한 경우 실패한 인스턴스 만들어주는 메소드
	public static <D> ResponseDto<D> setFailed(String message){
		return ResponseDto.set(false, message, null);
	}
}
