//controllere <-> service <-> DAO
/* interface 용도
 * 1. 스펙
 * 2. 반드시 하위 클래스 필수
 * 3. 메소드 재정의 필수 
 * 4. 명명 권장 규칙
 * 	- 하위클래스명 : ~인터페이스Impl.java 
 */

package com.ce.fisa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ce.fisa.exception.NotExistEmp2Exception;
import com.ce.fisa.model.dto.Emp2DTO;

/* 다수의 DAO를 하나의 스펙으로 관리하겠다는 선언
 */
@Service //spring bean, 동일한 설정 @Component 단, 서비스 기능으로 구현하겠다는 의미일뿐
public interface CompanyService {
	
	//사원명으로 해당 사원 모든 정보 검색
	public Emp2DTO getEmp(String ename) throws NotExistEmp2Exception;
		
	//부서번호로 해당 부서에 소속된 모든 직원의 이름, 급여만 검색
	public List<Object[]> getEmpsbyDeptno(int deptno);
	
	//사번을 기준으로 부서번호 수정
	public boolean updateDeptnoByEmpno(int empno, int newDeptno) 
			throws NotExistEmp2Exception;
}








