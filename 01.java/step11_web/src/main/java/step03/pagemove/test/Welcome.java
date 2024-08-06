package step03.pagemove.test;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//http://ip:port/context/html/login.html
//http://ip:port/context/loginTest
//http://ip:port/context/view/welcome

@WebServlet("/view/welcome")
public class Welcome extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Welcome doGet");
		process(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Welcome doPost");
		process(request, response);
		
	}
	// 공통 로직으로 분리된 사용자 정의 메서드
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//step01 - cookie 정보 client 브라우저로 부터 획득해서 출력
		//해당 Domain의 쿠키 정보만 획득
		/*
		 * Cookie[] all = request.getCookies();
		 * 
		 * for(Cookie c:all) { if(c.getName().equals("msg1")) { //쿠키 key값 뽑고 비교
		 * out.println(c.getValue()+"<br>"); //해당 쿠키 value값 뽑고 출력 } }
		 */
		
		// step02 - HttpSession 생성 및 데이터 저장
		/* 생성은 코드로 개발자가, 데이터 저장도 개발자가
		 * 단 생성된 객체 관리는 컨테이너 내부 로직
		 * request.getSession() : 없을 경우 생성, 이미 존재할 경우 제공
		 * 
		 */
		HttpSession session = request.getSession();//client만의 고유한 요청 객체로 부터 획득
		System.out.println(session.getId()); //컨테이너가 구분하기 위해 부여한 id
		//데이터 활용
		out.println(session.getAttribute("key1"));
		out.println("<br>");
		
		
		//로그아웃 클릭시에 쿠키 정보 삭제하고 login.html로 이동 되는 servlet 이동
		/* login.html -> LoginCheck -> Welcome : 쿠키데이터 확인 및 로그아웃
		 * -> Logout : 로그아웃 -> login.html
		 *  
		 */
		//url 표기에 /로 시작할경우 http://ip:port까지 의미한다.
		out.println("<a href='/step11_web/logout'>");// localhost/-> /로 대체된다.
		
		out.println("logout</a>");
		out.close();
		out = null;
	}	

}
