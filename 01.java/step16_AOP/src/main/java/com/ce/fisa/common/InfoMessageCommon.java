package com.ce.fisa.common;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//step01 : before -> after -> after return -> after throws 
//공통 로직으로만 구성된 클래스
//@Component
//@Aspect
public class InfoMessageCommon {
	
	//biz 실행 직전에 실행 
	@Before("execution(* com.ce.fisa.biz.Car.*(..))")
	public void before() {
		System.out.println("어서오세요 ~~~~~");
	}
	
	//biz 실행 후 실행
	//예외 발생시에도 예외 처리 후 실행 
	@After("execution(* com.ce.fisa.biz.Car.*(..))")
	public void after() {
		System.out.println("~~~~~ 안녕히가세요");
	}
	
	//예외 발생시 리턴 실행 불가인 경우만 제외하고 리턴값이 있는 메소드 정상 실행시 실행
	//biz 메소드에 리턴값이 있다면 "리턴값 & 공통"등으로 가공해서 출력
	//biz 메소드의 반환값 받고 처리
	@AfterReturning(pointcut = "execution(* com.ce.fisa.biz.Car.*(..))",
					returning = "bizReturnValue")
	public void afterReturn(String bizReturnValue) {
		System.out.println("공통2 " + bizReturnValue + " & 공통");
	}
	
	//예외 발생시에만 실행 
	//biz 로직의 예외 처리 전담하는 공통 메소드
	@AfterThrowing(pointcut = "execution(* com.ce.fisa.biz.Car.*(..))",
				   throwing = "e")
	public void throwsAfter(Exception e) {
		System.out.println("biz의 예외 발생시 공통처리 로직 *******");
		e.printStackTrace();
	}
	
	
}
