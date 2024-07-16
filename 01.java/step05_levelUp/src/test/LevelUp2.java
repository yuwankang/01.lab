package test;

import java.util.ArrayList;
import java.util.Arrays;

public class LevelUp2 {
	
	public static void main(String[] args) {
		//Integer.parsInt("3") -> 3 int로 변환...
		//? 1, 2, 3 으로 forEach()/ :: / -> 조합해서 간단한 예제
		Arrays.asList(1,2,3).forEach(System.out::println);
		Arrays.asList("1","2","3").forEach(System.out::println);
		System.out.println("--------------");
		Arrays.asList(1,2,3).forEach(v -> System.out.println(v+1));
			
	 
	}
}
