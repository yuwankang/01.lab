package step02.pagemove;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class Two extends HttpServlet {
	
	//get방식, post방식 모두 다 처리하는 특화된 메소드
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Two의 service()");
		
		String id = request.getParameter("id");
		System.out.println("id : " + id);
		System.out.println("요청객체에 저장한 새로운 데이터 : " + request.getAttribute("heart"));
		
		
	}

}
