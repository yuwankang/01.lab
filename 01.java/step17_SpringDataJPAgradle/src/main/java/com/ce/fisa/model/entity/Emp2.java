//EMPNO|ENAME |SAL |DEPTNO|
package com.ce.fisa.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
public class Emp2 {
	@Id 
	private int empno;
	
	private String ename;
	private double sal;
	private int deptno;
}
