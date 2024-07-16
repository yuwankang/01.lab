/* 학습내용
 * 
 * 1. lombok 설치
 * 	- 다운로드 -> maven 설정 파일에 설정 후 저장 
 *   -> library가 maven Dependenices 폴더에 자동 다운로드 
 *   -> 자바코드에 애노테이션 설정으로 코드 자동생성
 * 
 * 2. 애노테이션
 * 	- 1. 표현 : @(엣)
 * 	- 2. 기능 : 고유한 이름별 고유한 기능	
 * 			  jdk1.5 버전 부터 활성화 & framework들 주도적인 설정 
 *  - 3. lombok
 *  	1. setXxx() 메소드 자동 생성
 *  	2. getXxx() 메소드 자동 생성
 *  	3. 생성자들도 생성
 *  	4. builder pattern 지원
 * 
 */

package model.domain;

import org.junit.Test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//step01
//@NoArgsConstructor
//@AllArgsConstructor
//@Setter  //각 변수별 데이터 수정 메소드 자동 생성하는 표현
//@Getter

//step02 - builder 코드
@Builder
public class Customer {
	public String pw;
	public int age;
	public String grade;
	
	@Test
	public void test() {
		// pw값만 명시적인 초기화 해서 Customer 객체 요청
		Customer c = Customer.builder().pw("비번").build();
		Customer c2 = Customer.builder().grade("등급").build();
		
		//? pw, age 초기화 해서 Customer 객체 생성 요청
		/* Customer.builder().pw("pw").age(10).build()
		 * 
		 * Customer.builder()
		 * 	- Customer class에서 static 메소드
		 * 	- CustomerBuilder를 반환하는 메소드 
		 * 
		 * Customer.builder().pw("pw")
		 * 	- CustomerBuilder의 pw 값 setPw() 동일한 구조의 메소드
		 *  - pw()의 반환 타입은 CustomerBuilder
		 *  
		 *  
		 * Customer.builder().pw("pw").age(10).build() 
		 * - CustomerBuilder 의 메소드
		 * - Customer 반환 메소드 
		 */
		Customer c3 = Customer.builder().pw("pw").age(10).build();
	}
	
	
//	@Test
//	public void m() {
//		//String concat(String data){}
//		String v = "fisa";
//		v = v.concat(" 3기");
//		
//		String v2 = "fisa".concat(" 3기").concat("tt");
//	
//		
//	}
}
/* 
 * public static void a(){}
 * class명.a();
 * a();
 * 
 * String concat(String data){}
 * String v = "fisa";
 * v = v.concat(" 3기"); fisa 3기
 * 
 */







