//sessiontracking 공통 url
// <a href="sessiontracking/cookietest">
// <a href="sessiontracking/sessiontest2">
// <a href="sessiontracking/sessiontest3">
package com.ce.fisa;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ce.fisa.model.domain.Customer;

import jakarta.servlet.http.HttpSession;

@Controller
/* 세션 기반의 개발 코드
 * 1. HttpSession API
 * 2. Spring API
 * 	- key를 사전에 등록 해야 함
 * 	session.setAttribute("id", "user01");
	session.setAttribute("pw", "77");
 * 
 */
@RequestMapping("sessiontracking")
@SessionAttributes({"id", "pw","cust"}) //HttpSession의 invalidate()메소드와 혼용 금지
public class sessionTracking {
	
	//sessionview
	@GetMapping("/sessionview")
	public String m7(@ModelAttribute("cust") Customer c) {
		//? 세션에 저장된 Customer의 name값만 출력
		System.out.println("m7() : " + c.getName());
		
		
		return "redirect:/sessionView.jsp";
	}
	
	// sessiontest3?id=user01&name=khk&age=30
	/* CustomerDTO or Customer or CustomerBean or CustomerVO
	 * client가 새로운 데이터를 전송 -> controller 데이터 받음 -> service -> dao -> db -> dao -> service ->controller -> sessionView.jsp
	 * 
	 * sessionView.jsp
	 * 
	 * Model
	 * 	- 세션과 함께 사용시 HttpSession 의미
	 *  - forward 방식으로 jsp로 이동시에 해당 로직의 메소드
	 *  	parameter로 선언시 HttpServletRequest 의미
	 * 
	 */
	@GetMapping("/sessiontest3")
	public String m6(Customer cust, Model sessionModel) {
		System.out.println("m6() - " + cust);
		
		//@SessionAttributes에 등록한 key
		//session.setAttribute("cust", cust)동일
		//세션 관리 - SessionStatus의 setComplete()로 처리
		sessionModel.addAttribute("cust", cust);
		return "redirect:/sessionView.jsp";
	}
	
	
	/* Spring API로 작업시
	 * 1 단계 - class 선언구에 사용하고자 하는 세션 key명 등록
	 * 		@
	 * 2 단계 - controller에서 설정한 key로 데이터 획득
	 * 		@ModelAttribute("id") String id =
	 * 		HttpSession session = request.getSession();
	 * 			String id = (String)session.getAttibute("id");
	 */
	//logout2
	@GetMapping("/logout2")
	public String m5(SessionStatus status) {
		status.setComplete();
		System.out.println("m5() - 세선 소멸 성공");
		return "redirect:/sessionView.jsp";
	}
	
	//존재하는 세션 활용하는 메소드
	//logout1
	@GetMapping("/sessiontest2")
	public String m4(@ModelAttribute("id") String id,
					@ModelAttribute("pw") String pw) {
		System.out.println("m4() - " + id + " "+ pw);
		return "redirect:/sessionView.jsp";
	}
	
	//sessiontracking/logout1 버튼 클릭시 HttpSession API로 생성된 세션 삭제
	/* ? invalidate()
	 * 
	 */
	@GetMapping("/logout1")
	public String m3(HttpSession session) {
		System.out.println("m3() --- "+ session.getAttribute("id"));
		session.invalidate();
		session = null;
		return "redirect:/sessionView.jsp";
	}
	
	
	/* sessionTracking.jsp -> SessionTracking.java -> cookieView.jsp	
	 * Cookie c1 = new Cookie("id", "tester");
		c1.setMaxAge(60*60);
		response.addCookie(c1);
	 */
	//<a href="sessiontracking/cookietest">
	
	//sessionTracking.jsp -> sessionTracking.java -> sessionView.jsp
	//생성 및 데이터 저장      -> 생성된 세션 공유해서 받음 -> 생성된 세션 공유해서 받음
	//HttpSession API를 사용한 개발 기술
	@GetMapping("/sessiontest")
	public String m2(HttpSession session) {
		System.out.println("m2() --- " + session.getAttribute("id"));
		return "redirect:/sessionView.jsp"; 
	}
	
	@GetMapping("/cookietest")
	public String m1(@CookieValue("id") String value) {
		System.out.println("m1() ----- " + value);
		return "redirect:/cookieView.jsp"; // step14/cookieView.jsp
	}
	
	
}
