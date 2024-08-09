package com.ce.fisa.controller.action;

import java.io.IOException;
import java.sql.SQLException;

import com.ce.fisa.model.guestbook.GuestBookDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteBoardAction implements Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strNum = request.getParameter("num");
		String password = request.getParameter("password");
		
		try{
			if(strNum == null || strNum.trim().length() == 0 || password == null || password.trim().length() == 0){
				throw new Exception("입력값이 충분하지 않습니다.");
			}
			boolean result = GuestBookDAO.deleteContent(Integer.parseInt(strNum), password);
			
			if(result){//삭제 성공
				response.sendRedirect("board?command=list");
				return;
			}else{
				throw new Exception("이미 삭제된 게시물이거나, 비밀번호가 올바르지 않습니다.");
			}
		}catch (SQLException e) {
			request.setAttribute("errorMsg", "시스템 문제가 발생했습니다.");
		}catch (Exception e){
			request.setAttribute("errorMsg", e.getMessage());
		}
		request.getRequestDispatcher("error.jsp").forward(request, response);
	}
	
}
