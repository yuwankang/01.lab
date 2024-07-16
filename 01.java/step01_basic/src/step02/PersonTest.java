package step02;

import org.junit.Test;
import model.domain.Person;

public class PersonTest {

	@Test
	public void m1() {
		//? Person 기본 생성자로 
		// 객체 생성 new Peson();
		Person p1 = Person.builder().build();
	}
}
