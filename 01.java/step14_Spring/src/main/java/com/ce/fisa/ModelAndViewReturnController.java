package com.ce.fisa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ModelAndViewReturnController {
	
	/* url 구성 연습
	 * 1 단계 - 에러 발생
	 * 	return "redirect:main.jsp"; 코딩시
	 * 	http://localhost:82/step14/fisa7/id/main.jsp로 요청 미존재 경로 따라서 에러
	 * 
	 * 2. 단계 - 해결책
	 * 	- 경로 수정
	 * 		return "redirect:/main.jsp"
	 *		/ 표기 추가로 context명 인식
	 */
	
	//url 자체도 데이터로 구성하는 URL template 기술 적용
	//문법 : path/{key}/subpath
	//@PathVariable("key")
	@RequestMapping("fisa8/{id}/tester")
	public String m8(@PathVariable("id") String id) {
		System.out.println("m8() ***"+ id);
		return "redirect:/main.jsp"; 
	}
	
	@RequestMapping("fisa7/id/tester")
	public String m3() {
		System.out.println("m3() ***");//http://localhost:82/step14/fisa7/id/main.jsp
		return "redirect:/main.jsp"; // ?http://ip:port/context명/main.jsp
	}
	
	
	@GetMapping("fisa6")
	public ModelAndView m2(@RequestParam("id") String id) {
		System.out.println("m2() ****");
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("msg", "server에서 new data 저장");
		mv.setViewName("main");
		return mv; //forward 방식으로 정상 이동
	}
	
	
	//데이터 저장 후 /WEB-INF/main.jsp로 이동
	@RequestMapping(path = "fisa5",method = RequestMethod.GET)
	public ModelAndView m1() {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("msg", "server에서 new data 저장");
		mv.setViewName("main");
		return mv;
	}
}
