package com.ce.fisa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ce.fisa.biz.Car;

@RestController
public class CarController {
	
	/*
	 *  @Component
	 *  public calss Car{}
	 * 
	 */
	@Autowired //타입과 일치되는 스프링빈을 자동 주입
	private Car car;
	
	@GetMapping("aop1")
	public String m1() throws Exception {
		System.out.println("m1() ---");
		car.buy();
		System.out.println("****************");
		
		String data = car.buyReturn();
		System.out.println("controler에서 리턴받은 데이터 "+data);
		return "m1() 응답";
	}
	
	@ExceptionHandler
	public String exception(Exception e) {
		return e.getMessage();
	}
}
