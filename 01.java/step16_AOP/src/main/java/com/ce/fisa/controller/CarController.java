package com.ce.fisa.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ce.fisa.biz.Car;

@RestController
public class CarController {
	Logger logger = Logger.getLogger(getClass());

	/*
	 *  @Component   
		public class Car {
	 */
	@Autowired  //타입과 일치되는 스프링빈을 자동 주입
	private Car car;
	
	//rest get
	@GetMapping("aop1")
	public String m1() throws Exception {
		logger.info("m1() ---");
		car.buy();
		System.out.println("***********");
		
		String data = car.buyReturn();
		logger.info("controller에서 리턴받은 데이터 " + data);
		
		return "m1() 응답";
	}
	
	@ExceptionHandler
	public String exception(Exception e) {
		return e.getMessage();
	}
}