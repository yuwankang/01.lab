package step03.pagemove.test;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class Logout extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Logout doGet()");
//		process(request, response); //쿠키 정리
		process2(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Logut process()");
//		process(request, response);  //쿠키 정리
		process2(request, response);
	}
	//step02 - HttpSession 정리하는 메소드
	protected void process2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션 삭제
		HttpSession session = request.getSession();
		System.out.println("logout "+ session.getId()); 
		session.invalidate();//세션 객체 무효화, 기능 상실 session.getAttribute() 사용 불가
		session =  null;
		
		response.sendRedirect("html/login.html");
	}
	
	//step01 - Cookie 정리하는 메소드 
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//client 쿠키 정보 삭제 -> login.html
		Cookie[] all = request.getCookies();
		
		for(Cookie c:all) {
			if(c.getName().equals("msg1")) { //쿠키 key값 뽑고 비교
				c.setValue("");
				response.addCookie(c);
			}
		}		
		response.sendRedirect("html/login.html");
	}

}
