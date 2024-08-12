//EMPNO|ENAME |SAL |DEPTNO|
package com.ce.fisa.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

//@Entity
public class Empcopy2 {
	@Id 
	private int empno;
	
	private String ename;
	private double sal;
	private int deptno;
}
