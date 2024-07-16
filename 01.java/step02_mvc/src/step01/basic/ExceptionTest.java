//byte code(자바 실행파일 *.class)를 메모리에 로딩
/* 이름이 없다 = 사용자가 직접 호출 불가, 코드로 호출 불가
 * 실행 시점 - byte code가 메모리에 로딩시 단 한번만 실행
 * 24시간 365일 구동되는 웹서버의 웹 app 개발이라 가정
 * 	- 모든 user가 공유하는 단일 자원 초기화시에 권장
 * 
 
static {
	System.out.println("실행시점?"); //메인보다 우선권이 있다.
}
*/
package step01.basic;

class A{
	static {
		System.out.println("A.class가 메모리에 로딩시 실행");
	}
}


public class ExceptionTest {
	//배열의 index에 어긋나는 action
	
	
	public static void main(String[] args) {
		/* ArrayIndexOutOfBoundsException
		 *  실행시에 발생
		 *  	- 조치를 값만 변경해서 정상 실행
		 */
		//java.lang.ArrayIndexOutOfBoundsException
		int[] i = {1, 2};
		System.out.println(i[1]);
		
		System.out.println("************");

		/* forName() 제공자 관점
		 * - 호출해서 사용하는 user에게 경우의 수 고려하게 강요
		 * 
		 */
		//byte code 로딩
		try {
			//로직 구현부 단 예외가 발생될 수 있는 코드 영역
			Class.forName("step01.basic.A");
			System.out.println("난?");
			
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			System.out.println("로딩하려는 클래스가 없을때 실행되는 영역");
		}
		
		System.out.println(111);
	}

}




