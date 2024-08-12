//EMPNO|ENAME |SAL |DEPTNO|
package com.ce.fisa.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

//@Entity
public class EmpCopy {
	@Id 
	private int empNo;
	
	private String eName;
	private double sal;
	private int deptno;
}
