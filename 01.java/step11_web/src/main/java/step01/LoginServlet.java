package step01;

import java.io.IOException;
import java.io.PrintWriter;

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
		System.out.println(id +" "+ pw);
		
		//접속한 client에게 응답하는 servlet 개발 기술
		/* 응답 포멧과 인코딩 설정
		 * 응답 데이터를 접속한 client 브라우저 출력
		 */
		//html 포멧의 다국어 text응답 의미
		response.setContentType("text/html;charset=utf-8"); 
		//2byte 단위로 출력
		PrintWriter out = response.getWriter();
		out.println("네 id "+ id);//client 브라우저에 출력
	}

}
