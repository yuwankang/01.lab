package test;
import java.util.ArrayList;
import java.util.Arrays;
import model.domain.Customer;
public class LevelUp1 {
	public static void main(String[] args) {
		//step01 - 기본 문법
		// ArrayList에 3개의 Customer 객체 생성해서 저장
		ArrayList<Customer> al1 = new ArrayList<>(); 
		al1.add(new Customer("id1", "pw1"));
		al1.add(new Customer("id2", "pw2"));
		al1.add(new Customer("id3", "pw3"));
		
		int cnt = al1.size();
		for(int i=0; i<cnt; i++) {
			System.out.println(al1.get(i));//al1.get(i).toString() size 메소드 호출이 너무 많다.
		}
		System.out.println(" ****** ");
		for(Customer c : al1) {
			System.out.println(c);
		}
		System.out.println(" ****** ");
		//step02 - 생성과 저장 및 출력 간소화
		//jdk1.5 
		Arrays.asList(new Customer("id1", "pw1"),
					  new Customer("id2", "pw2"),
					  new Customer("id3", "pw3")).forEach(c -> System.out.println(c));	
		System.out.println(" ****** ");
		//step03 - 생성과 저장 및 출력 간소화 & -> 람다식 적용
		//jdk1.5 
		Arrays.asList(new Customer("id1", "pw1"),
					  new Customer("id2", "pw2"),
					  new Customer("id3", "pw3")).forEach(System.out::println);		
		System.out.println(" ****** ");
		//step04 - 생성과 저장 및 출력 간소화 & -> 람다식 적용
				//jdk1.5 id만 출력하는 방법
				Arrays.asList(new Customer("id1", "pw1"),
							  new Customer("id2", "pw2"),
							  new Customer("id3", "pw3")).forEach(c -> System.out.println(c.getId()));		
	}	

}
