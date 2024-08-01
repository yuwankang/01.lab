/* 일반 순수 자바 아님
 * http통신이 가능한 특화된 스펙의 웹 클래스
 * 메소드명 직접 호출 불가 - client 접속으로 자동 실행
 * 
 * http://localhost:8080/step11_webBasic/index.html
 * http://localhost:8080/step11_webBasic/index.jsp
 * http://localhost:8080/step11_webBasic/fisa
 */
package step01;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//http://localhost:8080/step11_Servlet/fisa
public class FirstServlet extends HttpServlet {
       
    public FirstServlet() {
    	System.out.println("servlet 생성자");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() -------");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		System.out.println(id + " " + pw);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() -------");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		System.out.println(id + " " + pw);
	}

}
