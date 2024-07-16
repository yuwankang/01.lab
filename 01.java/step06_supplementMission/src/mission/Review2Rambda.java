/* 미션 : 제시된 출력 결과 기반으로 미완성 로직 완성하기

 실행 결과

--- 1. 기본 반복문 ---
사과 - 배 - 오렌지 - 포도 - 파인애플 - 토마토 - 

--- 2. 람다식 및 함수 연산 사용 ---
사과
배
오렌지
포도
파인애플
토마토

--- 3. Java 8에서 이중 콜론 연산자 사용 ---
사과
배
오렌지
포도
파인애플
토마토

 */

package mission;

import java.util.Arrays;
import java.util.List;

public class Review2Rambda {

	public static void main(String[] args) {
		
		String[] fruits = {"사과", "배", "오렌지", "포도", "파인애플", "토마토"};
		List<String> fruits2 =  Arrays.asList(fruits);
		       
		System.out.println("--- 1. 기본 반복문 ---");
		for (String fruit : fruits2) {
		     System.out.print(fruit + " - ");
		}
		  
		System.out.println();  
		
		//? forEach() 메소드와 lambda식을 활용하여 출력해 보기
		System.out.println("\n--- 2. 람다식 및 함수 연산 사용 ---");
		fruits2.forEach(v -> System.out.println(v));
		
		
		//? 더블콜론 연산자 활용해서 출력해 보기
		System.out.println("\n--- 3. Java 8의 더블 콜론 연산자 사용 ---");
		fruits2.forEach(System.out::println);
		
	}

}