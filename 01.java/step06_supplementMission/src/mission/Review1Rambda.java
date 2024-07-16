/* 미션
 * 1. rambda식을 위한 interface 및 사용법 익숙해지기
 * 2. 제시된 미완성 로직 완성하기 
 * 3. inerface를 lambda에 최적화된 마킹
 */

package mission;

//?
interface MessageService {
	//반드시 이름을 포함한 메세지값 이어야 함
	void sayMessage(String message);
}

@FunctionalInterface
interface Calculation {
	//4칙 연산 수행후 반환하는 추상 메소드 선언
	int operation(int v1, int v2);
}



public class Review1Rambda {
	
	public static void main(String args[]) {
		// 괄호가 표현된 람다식
		MessageService message1 = message -> System.out.println(message);
		message1.sayMessage("람다식 활용하기");  //람다식 활용하기
		
		
		// 괄호 없는 람다식
		
		  MessageService message2 = message -> System.out.println(message);
		  message2.sayMessage("람다식 이해하기");
		  
		  
		  //??? 미션 - 사칙연산 하기
		  
			
			// 타입 선언한 람다식으로 더하기
			Calculation addition =  (a,b)->a+b;
			System.out.println("5 + 5 = " + addition.operation(5, 5));
			
			//System.out.println(addition);
			
			//? 타입 선언없는 람다식으로 빼기
			addition = (a,b)->a-b;
			System.out.println("5 - 5 = " + addition.operation(5, 5));
		
	}
}






