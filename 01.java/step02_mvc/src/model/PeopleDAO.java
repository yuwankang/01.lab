/*
*
*/
package model;
import java.util.ArrayList;
import model.domain.PeopleDTO;
public class PeopleDAO {
	//객체 생성후에만 사용 가능한 일반 변수
	static ArrayList<PeopleDTO> p = new ArrayList();
	//db처럼 멤버 변수를 구성
	
	static {
		p.add(new PeopleDTO("재석", 10));
		p.add(new PeopleDTO("연아", 40));
	}
	
	//select : 모든 사람 정보 반환
	//select * from people where id = 'test'
	public static ArrayList<PeopleDTO> getAllPeople(){
		return p;
		
	}
	
	//이름으로 그 사람의 나이만 수정
	//필요한 데이터 : 이름, 나이
	/*
	 * public int updatePeople(String name, int newAge) { //update people set age=?
	 * where name=?
	 * 
	 * }
	 */
}




