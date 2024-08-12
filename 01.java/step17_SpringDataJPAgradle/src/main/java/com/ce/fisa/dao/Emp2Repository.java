/* spring data jpa
 * - sql문장 직접 개발 하지 않음
 * - db 설정 정보는 application.properties에 저장
 * - libary 셋팅 완료
 * - 비즈로직 지정 
 * - 개발 기술
 * 	- 어떤 table/pk
 *  - 서비스 기능의 메소드 제시
 */
package com.ce.fisa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ce.fisa.model.entity.Emp2;

@Repository  //=@Component 동일
public interface Emp2Repository extends JpaRepository<Emp2, Integer>{
	//사번(empno)으로 이름 검색? 상속 안 함 즉 우리가 개발 
	//select * from emp2 where ename='SMITH';
	public Emp2 findByEname(String ename); //public abstract Emp2 findByEname(String ename);
	
	//사번으로 부서번호 수정 메소드도 우리가 개발 
	/*
	 * update emp2 set deptno=? where empno=?
	 * - 동적 바인딩
	 * 	: ?  or :변수명
	 * 
	 * 필요데이터 : 사번과 갱신용부서번호
	 * insert/update/delete 후 반드시 commit or rollback - 트랜젝션
	 * 
	 * sql문장으로 개발 기술 학습 - table명 기준 entity명 기준
	 * 
	 * update emp2 set deptno=? where empno=?
	 * emp2가 아닌 entity명으로 해야만 인식되는 방법
	 * 	Emp2에는 4개의 멤버 변수 존재, 멤버 변수 호출시 참조변수.
	 */
	@Modifying //DML에선 필수 !!!
	@Query("update Emp2 e set e.deptno=:deptno where e.empno=:empno")
	public int updateByEmpnoDeptno(@Param("empno") int empno, 
								   @Param("deptno") int deptno);
	
}

// contrller <-> servcie <-> repository <-> rdbms


