package com.ce.fisa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ce.fisa.exception.NotExistEmp2Exception;
import com.ce.fisa.model.dto.Emp2DTO;
import com.ce.fisa.service.CompanyService;

@RestController //모든 메소드의 결과가 문자열로 반환, 비동기에 적합
public class CompanyController {

	@Autowired
	private CompanyService service;
	

	//emp이름으로 해당 사원 검색
	//http://127.0.0.1/emp2?ename=SMITH
	@GetMapping("emp2")
	public Emp2DTO getEmpEname(@RequestParam("ename") String ename) 
				throws NotExistEmp2Exception {
		return service.getEmp(ename);//json으로 변환되어 client에게 응답 
	}
	
	@ExceptionHandler
	public String notDeptHander(NotExistEmp2Exception e) {
		e.printStackTrace();
		return e.getMessage();
	}
	
	//부서 번호로 해당 부서 직원 이름, 급여 검색
	//http://localhost/step17/emp2all?deptno=10
	@GetMapping("emp2all")
	public List<Object[]> getEmpsByDept(int deptno){
		return service.getEmpsbyDeptno(deptno); 
	}
	
	//사번과 새로운 부서 번호로 부서 이동
	//http://127.0.0.1/emp2?empno=7369&newDeptno=10
	@PostMapping("emp2")
	public String updateEmpBydeptno(@RequestParam("empno") int empno, 
								    @RequestParam("newDeptno") int newDeptno) throws NotExistEmp2Exception  {
		System.out.println("post");
		boolean result = service.updateDeptnoByEmpno(empno, newDeptno);
		if(result == true) {
			return "사원 부서 이동 성공";
		}else {
			return "사원 부서 이동 실패";
		}
	}
}