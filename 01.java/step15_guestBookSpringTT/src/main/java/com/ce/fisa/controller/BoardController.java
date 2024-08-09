package com.ce.fisa.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ce.fisa.model.guestbook.GuestBookDAO;

@Controller
public class BoardController{

	@GetMapping("list")
	public ModelAndView execute() throws SQLException {
		System.out.println("++++++++++++++++++");
		if(true) {
			throw new SQLException("db연동 이슈");
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", GuestBookDAO.getAllContents());
		mv.setViewName("list");
		return mv;
	}
	
	/* 예외 전담 메소드
	 * 1. 장점 
	 * 	- 코드에 예외 처리 코드의 필수이나 중복 및 지저분
	 *  - 예외 처리 전담 메소드 or 예외 전담 클래스 별도로 개발 
	 *  - @ExceptionHandler */
	@ExceptionHandler
	public String exceptionProcess(SQLException e, Model message) {
		System.out.println("*************");
		e.printStackTrace();
		message.addAttribute("errorMsg", e.getMessage());
		return "forward:/error.jsp";
	}
}