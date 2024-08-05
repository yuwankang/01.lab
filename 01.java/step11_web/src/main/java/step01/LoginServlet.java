package step01;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


//http://127.0.0.1/step11_web/login
public class LoginServlet extends HttpServlet {
	//메소드 재정의
	//http://localhost:8080/step11_web/login?id=test&pw=1234
	protected void doGet(HttpServletRequest request,
							HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		process(request, response);
	}

	//메소드 재정의
	//http://localhost:8080/step11_web/login
	protected void doPost(HttpServletRequest request, 
							HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		process(request, response);
	}
	
	//사용자 정의 메소드 - get / post 방식 모두다 처리하는 메소드
	protected void process(HttpServletRequest request, 
							HttpServletResponse response) throws ServletException, IOException {
		//id 값 착출 및 출력
		String id = request.getParameter("id"); //<input type="text" name="id">
		String pw = request.getParameter("pw"); //<input type="password" name="pw">
		System.out.println(id+" "+pw);
		
	}

}
