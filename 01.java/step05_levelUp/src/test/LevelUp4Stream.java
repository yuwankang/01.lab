package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.domain.Customer;


public class LevelUp4Stream {

	public static void main(String[] args) {
		
		List<String> datas  = Arrays.asList("a","b","c","d","e");
		List<String> datas2  = Arrays.asList("a3","b","casdasd","dw","eeee");
		
		//step01
		//더블연산자 + forEach()문을 활용해서 출력하기
		
		datas.forEach(v -> System.out.println(v));
		System.out.println("----------------");
		datas.forEach(System.out::println);
		System.out.println("----------------");
		
		//step02 - b 문자열 제외하고 출력
		datas.forEach((v) -> {System.out.println(v);});
		
		System.out.println("-------step02-------");
		datas.forEach(v -> {
			if(!v.equals("b")) {//객체들의 내용값 비교 메소드
				System.out.println(v);
			}
		});
		//step03 -
		System.out.println("-------step03 - Stream API활용한 조건 표현-------");
		datas.stream().filter(v -> !v.equals("b"))
			.forEach(System.out::println); 
		
		//step04 - String data 문자열 길이 1인 경우 제외하고 데이터 출력
		System.out.println("-------step04 문자열 길이-------");
		datas2.stream().filter(v -> v.length()!=1 )
					.forEach(System.out::println);
		
		//길이가 1인 데이터 제외하고 문자열 길이 출력
		datas2.stream().filter(v -> !(v.length()==1))
		.forEach(v-> System.out.println(v.length()));
		
		System.out.println("-------step05 참조타입 활용-------");
		//step05 - 사용자 정의 객체 타입(참조타입, 클래스타입) 활용
		ArrayList<Customer> al1 = new ArrayList<>(); 
		al1.add(new Customer("id1", "pw1"));
		al1.add(new Customer("id2", "pw2"));
		al1.add(new Customer("id3", "pw3"));
		System.out.println(al1);
		//id2만 제외한 다른 고객의 id값만 출력
		al1.stream().filter(v -> !(v.getId().equals("id2")))
				.forEach(v-> System.out.println(v.getId()));
		
		//step06 - 연산
		//int v = Integer.parsInt("1"); 
		List<String> datas3 = Arrays.asList("1","2","3","4","5","6","7");
		int result = datas3.stream().mapToInt(Integer::parseInt).sum();//mapToInt 문자열을 숫자열로 변환
		System.out.println(result);
		
		
		
		double result2 
		= datas3.stream().mapToDouble(Double::parseDouble).sum();//mapToDouble 문자열을 Double형으로 변환
		System.out.println(result2);
		
		System.out.println(datas3.stream()
				.mapToInt(Integer::parseInt).max());//OptionalInt
		
		System.out.println(datas3.stream()
				.mapToInt(Integer::parseInt).count());//7
		
		System.out.println(datas3.stream()
				.mapToInt(Integer::parseInt).sorted().min().getAsInt());//getAsInt를 사용해서 값을 도출한다.
	}

}
