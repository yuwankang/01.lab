/* 미션
 * 
 * 1. 소스 분석하기
 * 2. 다양한 사용법 익히기
 */

package mission;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class Review3StreamAPI {
	
	//step01 - printf() / %s / %d 학습
	//하나의 문자열을 %s 표기에는 String 데이터, %d 표기는 숫자 타입의 변수 데이터 적용하는 기술
@Test
	public void step01() {
		String data = "fisa";
		int age = 2;
		System.out.println("2024년 " + data + " " + age);
		System.out.printf("2024년 %s %d", data, age);
	}
	
	
@Test
	//step02 - 소스 보고 분석하세요
	public void step02(){
		// 문자열 리스트 생성
		List<String> strList = Arrays.asList("abc", "", "bcd", "", "defg", "jk");
		
		//1. 빈 문자열의 개수 계산
		System.out.println("-- 1 --");
		long count = strList.stream().filter(x -> x.isEmpty()).count();
		System.out.printf("리스트의 모든 문자열 :  %s, 빈문자열 개수 : %d개", strList, count);
		// 2. 길이가 3 이상인 문자열의 개수 계산
		System.out.println("\n-- 2 --");
		long num = strList.stream().filter(x -> x.length() > 3).count();
		System.out.printf("리스트의 문자열 길이가 3이상인 문자열 개수 :  %s, %d개", strList, num);
		 // 3. 'a'로 시작하는 문자열의 개수 계산
		System.out.println("\n-- 3 --");
		count = strList.stream().filter(x -> x.startsWith("a")).count();
		System.out.printf("리스트에서 a로 시작하는 문자들 :  %s,  %d개", strList, count);
			
		
		// 4. 빈 문자열을 제외한 리스트 생성
		System.out.println("\n-- 4 --");
		List<String> filtered = strList.stream().filter(x -> !x.isEmpty()).collect(Collectors.toList());
		System.out.printf("모든 리스트 데이터 : %s, 빈문자열이 없는 리스트 : %s", strList, filtered);
		
		// 5. 길이가 2를 초과한 문자열 리스트 생성
		System.out.println("\n-- 5 --");
		filtered = strList.stream().filter(x -> x.length() > 2).collect(Collectors.toList());
		System.out.printf("모든 리스트 데이터 : %s, 문자열 길이가 2를 초과한 문자열 : %s", strList, filtered);
		
		// 나라 이름 리스트 생성
		List<String> countryName = Arrays.asList("Korea", "USA", "France", "Germany", "Italy", "U.K.", "Canada");
		
		// 6. 모든 나라 이름을 대문자로 변환 후 출력
		System.out.println("\n-- 6 --");
		String country = countryName.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
		System.out.println("모든 나라 이름이 대문자로 변환된 데이터 " + country);
		
		countryName = countryName.stream().map(x -> x.toUpperCase()).collect(Collectors.toList());
		System.out.println("모든 나라 이름이 대문자로 변환된 데이터 " + countryName);

		// 숫자 리스트 생성
		List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
		
		// 7. 각 숫자의 제곱값 중복 제거 후 리스트 생성
		System.out.println("\n-- 7 --");
		List<Integer> distinct = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
		System.out.printf("모든 데이터 : %s, 곱한 연산 결과값들 중복 제거된 데이터 : %s", numbers, distinct);
		
		// 제곱값 리스트 생성 중복값 제거없이
		List<Integer> data = numbers.stream().map(i -> i*i).collect(Collectors.toList());
		System.out.printf("\n--- distince() 없이 %s - %s %n", numbers, data);
		
		
		System.out.println("\n-- 8 : 기초 통계 --");
		// 소수 리스트 생성
		List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
		IntSummaryStatistics stats = primes.stream().mapToInt(x -> x).summaryStatistics();
		// 소수 리스트의 통계 정보 출력
		System.out.println(stats); 
		System.out.println("목록에서 가장 높은 소수 : " + stats.getMax());
		System.out.println("목록에서 가장 작은 소수 : " + stats.getMin());
		System.out.println("모든 수의 합 : " + stats.getSum());
		System.out.println("모든 수의 평균 : " + stats.getAverage());
		
	}

}
