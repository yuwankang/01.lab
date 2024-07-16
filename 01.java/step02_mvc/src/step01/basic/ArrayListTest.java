/* 학습 내용
 * 1. java.util package내에 배열과 흡사한 구조로 데이터를 관리하는 타입
 * 	- list
 * 	- Object[]이 아닌 list 사용 사유?
 * 		- 실시간 동적 메모리 크기 가능
 * 		- 배열은 한번 생성한 크기는 절대 불변
 * 		- 실행 속도 측면에선 배열보단 떨어짐
 * 			: 고정 크기의 데이터들 개수 관리 - 배열 
 * 2. 데이터를 어떤 형식으로 관리할 것인가? 자료 구조
 * 	- 데이터 수가 다수, 하나의 변수 - 고유한 index or 고유한 key 관리
 * 3. 형식
 * 	- java.util package 클래스로 제공
 * 	- 객체생성 후 데이터 저장, 삭제, 수정
 * 	- 관리하는 객체타입에 대한 제약 없음, 참조타입 모두다 관리 가능
 * 
 * 4. java.util.ArrayList 설계자 관점
 * 	1. 데이터 저장을 위해 메모리 확보 : 객체 생성(new ArrayList())
 * 	2. 생성된 메모리에 데이터 저장 : 
 * 		public boolean add(Object v){parameter로 유입된 데이터 저장}
 * 		저장시 자동 index를 순서에 맞게 부여
 * 
 * 	3. 저장된 데이터를 활용하기 위해 반환 :
 * 		public Object get(int index){특정 위치의 데이터 반환}
 * 
 * 	4. 저장된 데이터 삭제 :
 * 		public boolean remove(int index){해당 index 데이터 삭제}
 * 
 * 	5. 저장되어 있는 데이터 개수 :
 * 		public int size(){} 
 * 
 * 5. 용어
 * 	- element(요소)
 * 		html, xml - <tag>~~</tag> 의미
 * 		java에선 java.util 자료구조 클래스들인 collection api에 의해 관리되는 
 * 		데이터를 element라 표현
 * 		jdk1.4까지
 * 			public boolean add(Object v){parameter로 유입된 데이터 저장}
 * 		jdk1.5부터 표현법이 달라짐
 * 			public boolean add(E v){parameter로 유입된 데이터 저장}
 * 
 *  	K, V, T, E - Object 타입
 *  
 *  6. 제너릭
 *   1. jdk1.5 부터 제시
 *   2. 주 목적 : 데이터 형의 제약으로 코드의 견고함과 간결성
 *   		- 타입 제약으로 인해 잘못된 데이터 저장시 컴파일 불가
 *   		- 형변환 코드 불필요
 *   3. 표기 <제약타입> 
 */

package step01.basic;

import java.util.ArrayList;

import org.junit.Test;

public class ArrayListTest {
	
	//@Test
	public void m1() {
		//String 값만 저장
			ArrayList al = new ArrayList();//10개 생성 0~9까지
			al.add("a");//index 0번째 저장
			al.add("ab");//index 1번째 저장
			al.add("abc");//index 2번째 저장
			al.add("abcd");//index 3번째 저장
			System.out.println(al.size());
			System.out.println(al);
			
			String v = (String)al.get(1);//형변환 생략시 문법 오류 방생
			System.out.println(v+" "+v.length());
			
			al.remove(1);
			System.out.println(al.get(1));//지워지면 뒤에 있는 데이터를 가지고 온다.
	}
	public void m2() {
		//String 값만 저장
			ArrayList<String> al = new ArrayList<String>();//String배열로 바뀐다.
			al.add("1");//index 0번째 저장
			al.add("ab");//index 1번째 저장
			al.add("abc");//index 2번째 저장
			
			System.out.println(al.size());
			System.out.println(al);
			
			String v = (String)al.get(1);
			System.out.println(v+" "+v.length());
			
			al.remove(1);
			System.out.println(al.get(1));
	}


}
