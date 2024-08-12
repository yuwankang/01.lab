/* View에서도 사용 가능
 * entity는 절대 view단에서 사용 금지 
 * 	- table구조 자체가 entity
 * client : 데이터 입력 -> controller : DTO자동생성 
 * -> Service -> DAO -> RDBMS
 * 
 * 	client : 데이터 입력 -> controller : DTO자동생성 
 * -> Service -> DAO : entity로 변경해야만 db insert -> RDBMS 
 */

package com.ce.fisa.model.dto;

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
public class Emp2DTO {
	private int empno;
	private String ename;
	private double sal;
	private int deptno;
}
