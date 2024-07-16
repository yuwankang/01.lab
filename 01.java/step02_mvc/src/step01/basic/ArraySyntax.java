/* 배열
 * - 하나의 변수로 다수의 데이터 관리
 * - 데이터 구분은 고유한 index
 * - index의 시작은 0부터
 * - 모든 배열은 객체(heap 메모리에 저장)
 * - python - list len() 데이터 개수 파악
 * - js - length 변수 자동 생성
 * - java - length 변수가 자동 생성(메모리 크기값)
 * - java의 배열 생성 문법 - {값1, 값2, ..}
 * - int i; / int[] i; / int i[];
 * - 선언 위치
 * 1. class{} - 멤버변수, 객체 생성시 heap에 객체 내부에 생성
 * 			  - 기본값으로 자동초기화(heap 메모리에 생성) *heap에서만
 * 2. 생성자 parameter - 생성자 실행 중에만 사용 가능한 로컬 변수
 * 3. 생성자 {} - 생성자 실행 중에만 사용 가능한 로컬 변수
 * 4. 메소드 parameter - 메소드 실행 중에만 사용 가능한 로컬 변수
 * 5. 메소드 {} - 메소드 실행 중에만 사용 가능한 로컬 변수
 * * 멤버 변수 : heap 메모리에 생성, 기본값으로 자동 초기화
 * 			  정수 0/실수 0.0/boolean false/char 'u0000' / 참조 null
 * * 로컬 변수 : 자동 초기화 안됨
 * 			  로컬 변수는 선언시 기본값으로라도 초기화 권장(중요)
 * 		* 참고 로컬 변수이나 데이터 타입이 배열인 경우 값을 각 index 별로 초기화 
 * 				하지 않아도 자동으로 초기화 진행
 */
package step01.basic;

import org.junit.Test;

import model.domain.PeopleDTO;

public class ArraySyntax {
	//1차원 배열 정석 문법
	public void m1() {
		//int 1, 2, 3
		int[] i = new int[3];// 메모리 생성
		i[0] = 0;
		int i2 = 0;//로컬 변수 선언시 자동초기화 불가 따라서 사용전 값 대입 필수
		System.out.println(i[0]);
		System.out.println(i2);
		
		System.out.println("***");
		//String 참조 타입으로 배열 생성/값 대입/ 활용
		String[] s = new String[2];
		System.out.println(s[0]);
		s[0] = "test";
		System.out.println(s[0]);
				
		//PeopleDTO 참조 타입으로 생성/값 대입/활용
		//PeopleDTO 0번째 저장 후 이름값 출력
		PeopleDTO[] p = new PeopleDTO[2];
		p[0] = new PeopleDTO("성호", 10);
		System.out.println(p[0].getName());
		/* p[0].getName()
		 * p : PeopleDTO 배열
		 * P[0] : PeopleDTO
		 * getName() : String		  
		 */
		//String " " or new String(" "); String만 ""따옴표로 생성 가능
		
	}
	//1차원 배열 함축 문법
	public void m2() {
		int[] i = {1, 2, 3};
		String[] s = {"a",null}; //2개의 메모리+length
		PeopleDTO[] p = {null, new PeopleDTO("재석",3)};
	}
	public PeopleDTO getPeople() {
		PeopleDTO[] p = {null, new PeopleDTO("재석",3)};
		return p[1];  //? 1번째 데이터 변환
	}
	@Test
	public void running() {
//		m1();
		/*System.out.println(getPeople());
		 *System 클래스
		 * out : static out
		 * println() : out 변수 타입인 PrintStream 클래스에서 제공
		 * 
		 * getPeople()
		 *  - PeopleDTO 객체
		 */
		System.out.println(getPeople()); //model.domain.PeopleDTO@5700d6b1
		System.out.println(getPeople().getName()); //재석
	}
}
