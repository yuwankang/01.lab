package model.domain;

/* JPA 필수 적용 사항
 * - DTO는 view에서도 사용 가능한 데이터 표현용 클래스
 * - model에서 생성해서 View에 전송
 * - JPA 특화된 클래스 : Entitiy 클래스
 * 	- RDBMS 자체의 table에 특화된 종속성 있는 클래스
 * 	- 구조 : table과 동일
 * 	- Entity는 객체 생성 등 db와 완전 밀착 
 * 		- setXxx() - update sql 문장과 동일하게 수행
 * 	- Model단의 DB 연동 로직에섬나 사용해야 함
 * 
 * - DTO와 Entity가 같다. 하나로 사용하면 안되나? 불가능
 * 
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Dept01DTO {
	private int deptno;
	private String dname;
	private String loc;

}
