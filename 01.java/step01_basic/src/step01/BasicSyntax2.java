/* 
 * 
 */

package step01;

public class BasicSyntax2 {
	//멤버 변수 - 실제화(instance)로 메모리에 생성은 객체 생성
	String name = "상민";
	int age = 21;
	
	//생성자 : 필수, 개발자가 생략시에 자동 추가
	BasicSyntax2(){}
	
	//메소드 : 로컬 변수 선언, 멤버 변수값과 로컬 변수값 출력 
	//반환타입 메소드명([parameter]){}
	void info() {
		String name2 = "동길";
		int age2 = 22;
		System.out.println(name + " " + name2);
	}
	
	public static void main(String[] args) {
		
		BasicSyntax2 c = new BasicSyntax2();  //name 과 age 저장
		System.out.println(c);//step01.BasicSyntax2@5ca881b5
		
		//멤버 변수 호출 후 출력, 멤버 변수값 수정 후 출력
		System.out.println(c.name);//name 변수가 실제 메모리에 사용 가능하게 존재해야함 호출 가능
		
		//메소드 호출 	
		c.info();
		
		
	}

}
