package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.junit.Test;

import model.domain.Customer;

public class LevelUp6Optional {
	/* 
	 * 프로그램 실행 중지를 방지하기 위한 실습 개발 코드
	 */
	@Test
	public void step08() {
		HashMap<String, Customer> map = null;
		//null값이 있는 map을 Optional을 사용한다.
		Optional<HashMap<String, Customer>> opt = Optional.ofNullable(map);
		
		System.out.println("1-------");
		System.out.println("2-------");
		//ofNullable은 null과 null이 아닌 모든 값을 허용함으로
		//ifPresent를 사용하여 null인지 검증한다.
		opt.ifPresent(map2 -> {
								System.out.println(map2.values().stream()
												   .filter(customer ->customer.getAge()<20)
												   .mapToInt(Customer::getAge)
												   .sum()				
											);
			 							});
		System.out.println("3-------");
	}
	//@Test
	/* null값인 문제를 이상없이 실행 되도록 수정하시오
	 * public void step08() { HashMap<String, Customer> map = null;
	 * System.out.println("1------------"); 
	 * int sum = map.values() .stream()
	 * .filter(t ->t.getAge()<20) .mapToInt(Customer::getAge) .sum();
	 * System.out.println("2------------"); 
	 * System.out.println(sum);
	 * System.out.println("3------------"); }
	 */
	
	//@Test
	public void step07() {
		String v = "fisa";
		//String v = null;
		Optional<String> opt = Optional.ofNullable(v);
		System.out.println("1-------------");
		System.out.println(opt.isEmpty()); //null인 경우 true
		System.out.println(opt.isPresent());
		System.out.println(opt.orElse("null인경우 출력 메세지"));
		
		opt.ifPresent(v2 -> System.out.println(v2));
		System.out.println("2--------정상 실행 유지");
	}
	
//	@Test
	public void step06() {
		
		/* of() : null은 불허, 실행시 예외 발생
		 * 		ifPresent() 불필요
		 * 
		 */
		//String v = null;
		String v = "fisa";
		Optional<String> opt = Optional.of(v);//of는 철저하게 null값을 불허한다.
		System.out.println("1-------------");
		//opt.ifPresent(v2 -> System.out.println(v2));
		System.out.println(opt.get());
		System.out.println("2-------------");
	}
	/* ofNullable() : null 또는 실제객체로 생성
	 * 단, null 필터링은 ifPresent() 활용도 가능
	 * ifPresent() -> null인지 아닌지 검증한다.
	 * 	- Optional 객체 내부에 null이면 실행 skip
	 * 	- null이 아닌 경우 실행
	 */
	//@Test
	public void step05() {
		//String v = null;
		String v = "fisa";//Null일경우 실행 안하고 데이터가 있을경우 실행한다.
		Optional<String> opt = Optional.ofNullable(v);
		System.out.println("1-------------");
		opt.ifPresent(v2 -> System.out.println(v2));
		System.out.println("2-------------");
	}
	
	/* ofNullable() -null과 객체 다 수용
	 * get() - 실제 Optional 객체에 저장된 데이터값 반환 메소드
	 * 		- empty인 경우 실행 예외 발생
	 */
//	@Test
	public void step04() {
		String v = null;//Optional.empty
//		String v = "fisa"; //Optional[fisa]
		Optional<String> opt = Optional.ofNullable(v);//null도 허용
		System.out.println(opt.get()); //get은 데이터가 있을경우만 정상실행
	}
	
//	@Test //step02 실행 문제 해결
	//굉장히 올드한 기본 처리 방식
	public void step03() {
		String v = null;
		if(v != null) {
			System.out.println(v.length());
		}
	}
	
//	@Test
	public void step02() {
		String v = null;
		//실행시 NullPointerE~발생
		System.out.println(v.length());
	}
	public void step01() {
		//고유한 index로 데이터 관리 
		ArrayList<Customer> al1 = new ArrayList<>(); 
		al1.add(new Customer("id1", "pw1",10,"주소","vip","남"));
		al1.add(new Customer("id2", "pw2",15,"마포","gold","여"));
		al1.add(new Customer("id3", "pw3",50,"상암","silver","여"));
		al1.add(new Customer("id4", "pw4",32,"논현","vip","남"));
		al1.add(new Customer("id5", "pw5",20,"강남","gold","남"));
		
		//고유한 구분값(key)값으로 데이터(value) 구분해서 사용-map
		//String타입의 key값으로 Customer 객체들 구분
		//key는 중복 불허(id or email or 휴대폰번호..)
		/* 고려사항
		 * 1. 실데이터가 없을수도?
		 * 2. 실데이터 저장 공간인 list 또는 map 메모리가 없을수도?
		 * 		- 변수만 선언된 상태
		 */
		HashMap<String, Customer> map = new HashMap<>();
/*		map.put("id1", new Customer("id1", "pw1",10,"상암","vip","M"));
		map.put("id2", new Customer("id2", "pw2",15,"마포","gold","F"));
		map.put("id3", new Customer("id3", "pw3",50,"상암","gold","F"));*/
		
		map.forEach((k,v)->System.out.println(k+ " "+v));
		
		//map에 저장된 모든 고객의 나이값의 합 출력
		
		map.forEach((k, v)->{
							  System.out.println(v.getAge());
							});
		
		//map에 저장된 모든 고객의 나이값 20미만된 고객의 나이값만 합 
		//Customer -> age 착출 -> 조건비교 -> 20미만 데이터
		//현 데이터 수이외에 다수의 데이터 존재할 경우라 가정
		//map.forEach((k, v)->System.out.println(v.getAge()>20));
		System.out.println("-------------------------");
		int i = map.values().stream()
				.filter(c-> (c.getAge() < 20))
				.mapToInt(Customer::getAge).sum();
		System.out.println(i);
	}

}
