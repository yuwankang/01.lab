/* 사용자 정의 예외 클래스 
 * 1. 필요성 - 클래스명으로 상황 판단용으로 주로 사용
 * 2. 구조
 * 	- 상위클래스 / 생성자 
 */
package com.ce.fisa.exception;

public class NotExistEmp2Exception extends Exception {
	
	public NotExistEmp2Exception() {}
	public NotExistEmp2Exception(String message) {
		super(message);//상위클래스 멤버 변수값 초기화
	}
	
}
