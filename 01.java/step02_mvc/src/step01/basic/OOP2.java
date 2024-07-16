/* 학습 내용
 * 	- 상속 다형성 리뷰
 * 
 */
package step01.basic;

import model.domain.PeopleDTO;

public class OOP2 {
	
	//Object o = new Parent();
	static Object m1() {//반환시 Object인 상위 타입으로 자동형변환 되어 반환
		return new Parent();//Object의 자식 객체 생성
	}
	
	static Object[] m2() {
		Object[] o = new Object[3];// 모든 클래스의 최상위 root
		o[0] = "str";//문자열 생성-> Object로 저장
		o[1] = 3;
		o[2] = new PeopleDTO("id",11);
		
		return o;
	}
	
	/* 50분 까지 2~3문제
	 * 
	 */

	public static void main(String[] args) {
		//m2() 메소드 호출
		Object[] o = m2();
		
		//m2() 반환 값에서 index 0 번째의 문자열 길이 출력
		//String/length()/형변환이 필요
		//String data = (String)o[0];
		//- o-> [0] -> (String)
		System.out.println(((String)o[0]).length());
		
		//index 2의 name 값만 출력-name는 상속받지 않은 PeopleDTO 만의 멤버 변수
		//PeopleDTO p = (PeopleDTO)o[2];
		//System.out.println(p.getName());
		System.out.println(((PeopleDTO)o[2]).getName());
		
		//index 2의 name 값을 다른 name값으로 수정
		//PeopleDTO p2 = (PeopleDTO)o[2];
		//p2.setName("22");
		((PeopleDTO)o[2]).setName("22");
		//index 2의 name 값만 출력
		
		//step01
		//typecasting 형변환
		Parent p = (Parent) m1();//Object 타입으로 반환, 자식타입의 변수엔 명시적인 형변환
		p.id = "fisa man";
		//p.printAll();

	}

}
