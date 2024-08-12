package com.ce.fisa.biz;

import org.springframework.stereotype.Component;

//개발자가 코드로 생성하지 않고 스프링 빈으로 등록 및 활용 예정
//스프링 빈으로 등록 : car변수명으로 생성
@Component   
public class Car {
	//biz메소드들로 구성이라 가정
	public void buy() {
		// 환영합니다 어서 오세요~~
		System.out.println("biz buy()");
		// 마무리 인사
	}
	
	public String buyReturn() throws Exception {
		System.out.println("biz buyReturn()");
//		if(true) { //예외 test 시에만 연관
//			throw new Exception("예외 발생");
//		}
		return "리턴 데이터🎉🎉🎉";  //예외 발생시 절대 실행 불가 따라서 공통의 리턴값 처리 메소드 실행 안 함
	}
}
