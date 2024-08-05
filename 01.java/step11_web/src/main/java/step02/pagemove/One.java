package step02.pagemove;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class One extends HttpServlet {
	
	//get방식, post방식 모두다 처리 하는 특화된 메소드
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("One의 service()");		
		String id = request.getParameter("id");
		System.out.println("id : " + id);
		
		//forward
		//http://localhost/step11_web/pageMove.html
		//http://localhost/step11_web/one?id=fisa
		//pageMove.html -> One -> Two 까지 페이지 이동
		/* 출력결과
		One의 service()
		id : fisa
		Two의 service()
		id : fisa
		 */
		request.setAttribute("heart", "공부만 해"); //새로운 데이터 저장
		request.setAttribute("name", "양궁 선수"); //새로운 데이터 저장
		request.getRequestDispatcher("two.jsp").forward(request, response);
		
		//redirect
		//http://localhost/step11_web/pageMove.html
		//http://localhost/step11_web/two
		//pageMove.html -> One -> Two 까지 페이지 이동
		/* 출력결과
		One의 service()
		id : fisa
		Two의 service()
		id : null
		 */
//		response.sendRedirect("two");//two로 넘어가면서 새로운 요청, 응답 객체 생성
		
	}
}



