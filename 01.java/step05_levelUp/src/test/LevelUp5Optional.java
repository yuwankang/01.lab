package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.domain.Customer;

public class LevelUp5Optional {

	public static void main(String[] args) {
//		String s = "null";
//		System.out.println(s.length());//실행 예외 발생
		
		//step01 
		List<String> datas1
		= Arrays.asList("1","2","3","4","5");
		List<String> datas2 = null;
		
		System.out.println(datas1.stream()
				.mapToInt(Integer::parseInt).count());//5 출력
	

		System.out.println(datas1.stream()
				.mapToInt(v -> {return Integer.parseInt(v);}).count());
	
		System.out.println(datas1.stream()
				.mapToInt(Integer::parseInt)
					// System.out.println("------"); 내부적으로 코드를 먹어버림
					// return은 가능하지만 다른 기능은 없다. filter를 통해 데이터를 정제해야함
					.filter(v -> v==3).max());
		
		System.out.println(datas1.stream()
				.filter(v -> v.equals("3"))//filter를 통해 3을 문자열로 먼저 찾는다.
				.mapToInt(Integer::parseInt)
				.count());
		
		//step02
		//3명의 고객 정보 생성
		//나이가 20 미만 고객들의 나이값 합하기 + 출력
		ArrayList<Customer> al1 = new ArrayList<>(); 
		al1.add(new Customer("id1", "pw1",10,"주소","vip","남"));
		al1.add(new Customer("id2", "pw2",15,"마포","gold","여"));
		al1.add(new Customer("id3", "pw3",50,"상암","silver","여"));
		al1.add(new Customer("id4", "pw4",32,"논현","vip","남"));
		al1.add(new Customer("id5", "pw5",20,"강남","gold","남"));
		
		System.out.println(al1.stream()
							.filter(c -> (c.getAge() < 20))
							.mapToInt(Customer::getAge)
							.sum());

	}
}
