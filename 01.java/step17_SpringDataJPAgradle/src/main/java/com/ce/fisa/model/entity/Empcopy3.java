//EMPNO|ENAME |SAL |DEPTNO|
package com.ce.fisa.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@Table(name = "emp_copy3")
//@Entity
public class Empcopy3 {
	@Id 
	private int empno;
	
	private String ename;
	private double sal;
	private int deptno;
}
