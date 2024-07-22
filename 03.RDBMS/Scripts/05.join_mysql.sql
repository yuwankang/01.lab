-- 5.join.sql
-- mysql용

USE fisa;

-- 급여별 등급 : 범위로 값 표현
DROP TABLE IF EXISTS salgrade;

CREATE TABLE salgrade
 ( 
	GRADE INT,
	LOSAL numeric(7,2),
	HISAL numeric(7,2) 
);
  
INSERT INTO salgrade VALUES (1,700,1200);
INSERT INTO salgrade VALUES (2,1201,1400);
INSERT INTO salgrade VALUES (3,1401,2000);
INSERT INTO salgrade VALUES (4,2001,3000);
INSERT INTO salgrade VALUES (5,3001,9999);

COMMIT;

SELECT * from salgrade 

/*
1. 조인이란?
	다수의 table간에  공통된 데이터를 기준으로 검색하는 명령어
	- 트랜드
		: 데이터가 굉장히 많은 시대, join은 실행 속도가 저하
		가급적 개별 테이블로 설계 권장
		꼭 table 분산해야 할 경우에만 다수 table로 설계
		결론 : join은 꼭 필요한 시점에만 적용 권장
		
	
	다수의 table이란?
		동일한 table을 논리적으로 다수의 table로 간주
			- self join
			- emp의 mgr 즉 상사의 사번으로 이름(ename) 검색
				: emp 하나의 table의 사원 table과 상사 table로 간주

		물리적으로 다른 table간의 조인
			- emp의 deptno라는 부서번호 dept의 부서번호를 기준으로 부서의 이름/위치 정보 검색
  
2. 사용 table 
	1. emp & dept 
	  : deptno 컬럼을 기준으로 연관되어 있음

	 2. emp & salgrade
	  : sal 컬럼을 기준으로 연관되어 있음

  
3. table에 별칭 사용 
	검색시 다중 table의 컬럼명이 다를 경우 table별칭 사용 불필요, 
	서로 다른 table간의 컬럼명이 중복된 경우,
	컬럼 구분을 위해 오라클 엔진에게 정확한 table 소속명을 알려줘야 함

	- table명 또는 table별칭
	- 주의사항 : 컬럼별칭 as[옵션], table별칭 as 사용 불가


4. 조인 종류 
	1. 동등 조인
		 = 동등비교 연산자 사용
		 : 사용 빈도 가장 높음
		 : 테이블에서 같은 조건이 존재할 경우의 값 검색 

	2. not-equi 조인
		: 100% 일치하지 않고 특정 범위내의 데이터 조인시에 사용
		: between ~ and(비교 연산자)

	3. self 조인 
		: 동일 테이블 내에서 진행되는 조인
		: 동일 테이블 내에서 상이한 칼럼 참조
			emp의 empno[사번]과 mgr[사번] 관계

	4. outer 조인 
		: 조인시 조인 조건이 불충분해도 검색 가능하게 하는 조인 
		: 두개 이상의 테이블이 조인될때 특정 데이터가 모든 테이블에 존재하지 않고 컬럼은 존재하나 
		  null값을 보유한 경우
		  검색되지 않는 문제를 해결하기 위해 사용되는 조인
*/		


use fisa;

-- 1. dept table의 구조 검색
show tables;

-- dept, emp, salgrade table의 모든 데이터 검색
select * from dept;
select * from emp;
select * from salgrade;



-- *** 1. 동등 조인 ***
-- = 동등 비교연산자 사용해서 검색


-- 2. SMITH 의 이름(ename), 사번(empno), 근무지역(부서위치)(loc) 정보를 검색
-- emp/dept : deptno가 공통 데이터
-- 비교 기준 데이터를 검색조건에 적용해서 검색
-- table명이 너무 복잡한 경우 별칭 권장
select ename, empno, deptno from emp where ename='SMITH';

select loc, deptno from dept;
select loc, deptno from dept where deptno=20;

-- 모범답안

SELECT ename, empno, loc from emp, dept WHERE emp.deptno = dept.deptno and ename ='SMITH';


SELECT ename, empno, loc from emp, dept WHERE ename ='SMITH' and emp.deptno = dept.deptno;

-- table 별칭 부여

SELECT ename, empno, loc from emp e, dept d WHERE ename ='SMITH' and e.deptno = d.deptno;

/*  이슈 ??? 
 * 	1. mysql - table 별칭 절대 권장
 * 	2. oracle - table 별칭 선언 후 사용 안해도 이슈 없음
 *  oracle 버전에 따른 차이 확인 예정
 */
SELECT e.deptno from emp e, dept d WHERE ename ='SMITH' and e.deptno = d.deptno;


SELECT * from emp;


-- 3. deptno가 동일한 모든 데이터(*) 검색 : 논리적으론 부적합하다.
-- emp & dept 
-- 논리적으로 부적합 
SELECT * from emp, dept;

-- 1차 정제
SELECT * from emp, dept where emp.deptno = dept.deptno; -- deptno가 두개나 검색되는 현상

-- 2차 정제 - 중복된 컬럼 제거
-- 해결점 : 검색 컬럼명 일일이 명시
SELECT 



-- 4. 2+3 번 항목 결합해서 SMITH에 대한
--  모든 정보(ename, empno, sal, comm, deptno, loc) 검색하기
-- 추가 : 모든 직원의 검색
SELECT ename, empno, sal, comm, emp.deptno, loc from emp, dept 
WHERE ename = 'SMITH' and emp.deptno = dept.deptno;



-- 5.  SMITH에 대한 이름(ename)과 부서번호(deptno), 
-- 부서명(dept의 dname) 검색하기
SELECT ename, empno, emp.deptno, dept.dname from emp, dept WHERE ename ='SMITH' and emp.deptno = dept.deptno;

-- 모든 db에 동일한 표준 sql
SELECT ename, e.deptno, dname from emp e inner join dept d on ename ='SMITH' and e.deptno = d.deptno;

-- 6. 조인을 사용해서 뉴욕('NEW YORK')에 근무하는 사원의 이름(ename)과 급여(sal)를 검색 
select loc from dept;

SELECT ename, sal from emp, dept where loc = 'NEW YORK';

SELECT ename, sal from emp e inner join dept d on loc ='NEW YORK';


-- 7. 조인 사용해서 ACCOUNTING 부서(dname)에 소속된 사원의
-- 이름과 입사일 검색
select deptno, dname from dept;

SELECT * from emp;
SELECT ename, hiredate from emp e inner join dept d on dname='ACCOUNTING' and d.deptno = e.deptno;




-- 8. 직급(job)이 MANAGER인 사원의 이름(ename), 부서명(dname) 검색
SELECT ename, dname from emp e inner join dept d on job ='MANAGER';



-- *** 2. not-equi 조인 ***

-- salgrade table(급여 등급 관련 table)
select * from salgrade s;

-- 9. 사원의 급여가 몇 등급인지 검색
-- between ~ and : 포함 
SELECT  sal from emp;
desc emp;
desc salgrade;

-- ? bettween ~ and 
-- 추후 다른 db사용시 미만, 초과 또는 이하, 이상 여부 검증후 사용
SELECT ename, sal, grade from emp, salgrade WHERE sal BETWEEN losal and hisal;

SELECT sal from emp, salgrade WHERE 800 BETWEEN losal and hisal;

SELECT ename from emp, salgrade WHERE sal = 800 and sal BETWEEN losal and hisal;



-- 동등조인 review
-- 10. 사원(emp) 테이블의 부서 번호(deptno)로 
-- 부서 테이블(dept)을 참조하여 사원명(ename), 부서번호(deptno),
-- 부서의 이름(dname) 검색
select ename, e.deptno, dname
from emp e, dept d
where e.deptno=d.deptno;




-- *** 3. self 조인 ***
-- 11. SMITH 직원의 메니저 이름 검색
-- 하나의 테이블을 다수의 테이블로 논리적으로 구분한 조인
/* 하나의 table에 직원 이름
 * 이름으로 상사 사번을 통해서 상사 이름(직원명)을 검색
 */
-- emp e : 사원 table로 간주 / emp m : 상사 table로 간주
SELECT m.empno, m.ename from emp e, emp m WHERE e.ename ='SMITH' and e.mgr = m.empno;


-- 12. 메니저 이름이 KING(m ename='KING')인 
-- 사원들의 이름(e ename)과 직무(e job) 검색
SELECT e.ename, e.job from emp e, emp m WHERE m.ename = 'KING' and m.empno  = e.mgr;


-- 13. SMITH와 동일한 부서에서 근무하는 사원의 이름 검색
-- 단, SMITH 이름은 제외하면서 검색 : 부정연산자 사용 != 
SELECT friend.ename as 동료이름 
from emp my, emp friend  
WHERE my.ename ='SMITH' 
and friend.ename != 'SMITH' -- <> -> 부정
and my.deptno = friend.deptno;  




-- *** 4. outer join ***
select * from dept;
select empno, deptno from emp;  -- 40번 부서에 근무하는 직원들도 없음
select distinct deptno from emp;  -- 40번 부서에 근무하는 직원들도 없음

select ename, mgr from emp;   -- KING의 mgr은 null 


-- 14. 모든 사원명(KING포함), 메니저 명 검색, 단 메니저가 없는 사원(KING)도 검색되어야 함
-- step01 : 사원명에 KING 검색 누락 / 상사 자체가 없는 (null)인 데이터는 검색 불가
SELECT e.ename as 사원명, m.ename  as 상사명 from emp e, emp m where e.mgr = m.empno;

-- step02 : ANSI 표준 sql 문장으로 변환
SELECT e.ename as 사원명, m.ename as 상사명 from  emp e Inner Join emp m on e.mgr = m.empno;

-- step03 : KING 사원명 검색
/* 	ANSI 조인 문장으로 이해하기
 * 	왼쪽 table - 사원테이블 / 오른쪽 table - 상사테이블
 * 	NULL 값을 보유한 table은 사원? 상사?
 * 
 */
SELECT e.ename as 사원명, m.ename as 상사명 
from  emp e left Join emp m 
on e.mgr = m.empno;

SELECT e.ename as 사원명, m.ename as 상사명 
from  emp e right Join emp m 
on e.mgr = m.empno;

SELECT e.ename as 사원명, m.ename as 상사명 
from  emp e right Join emp m 
on m.mgr = e.empno;

-- right join 모든 사원명과 상사명 출력(null 포함)
-- 상사번호가 null인 사원의 사원명까지 검색
-- 사용되는 컬럼에 null 값을 보유하는 테이블을 기준으로 해라.
-- 사원 테이블의 mgr은 null 포함
-- 상사 테이블의 empno는 null 자체가 존재하지 않음

SELECT e.ename as 사원명, m.ename as 상사명 
from emp m right join  emp e
on e.mgr and m.empno;

-- 15. 모든 직원명(ename), 부서번호(deptno), 부서명(dname) 검색
-- 부서 테이블의 40번 부서와 조인할 사원 테이블의 부서 번호가 없지만,
-- outer join이용해서 40번 부서의 부서 이름도 검색하기 
-- 기준 talbe : dept 기준 
-- KING 값 보유 사원 테이블 
-- dept가 기준이 되어야 한다.
SELECT  * from emp;
SELECT * from dept;

-- 40번 부서 정보 누락
-- step01 - 40번 부서 자체가 누락
SELECT ename, e.deptno, dname
FROM emp e, dept d
WHERE e.deptno = d.deptno;

-- step02 - 40번 부서 자체가 누락
-- 왜? 40번 정보 자체가 없는 그래서 null 자체도 아닌 emp가 기준이었기 때문
SELECT ename, e.deptno, dname
FROM emp e left join dept d
on e.deptno = d.deptno; 

-- step03 - 40번 번호는 누락된 상태에서 부서명만 검색
SELECT ename, e.deptno, dname
FROM emp e right join dept d
on e.deptno = d.deptno; 

--- step04 - step03 개선
SELECT ename, d.deptno, dname 
FROM dept d left join emp e
on e.deptno = d.deptno;

SELECT ename, d.deptno, dname
FROM emp e right join dept d
on e.deptno = d.deptno; 


-- 미션? 모든 부서번호가 검색(40)이 되어야 하며 급여가 3000이상(sal >= 3000)인 사원의 정보 검색
-- 특정 부서에 소속된 직원이 없을 경우 사원 정보는 검색되지 않아도 됨
-- 검색 컬럼 : deptno, dname, loc, empno, ename, job, mgr, hiredate, sal, comm
/*

검색 결과 예시

+--------+------------+----------+-------+-------+-----------+------+------------+---------+------+
| deptno | dname      | loc      | empno | ename | job       | mgr  | hiredate   | sal     | comm |
+--------+------------+----------+-------+-------+-----------+------+------------+---------+------+
|     10 | ACCOUNTING | NEW YORK |  7839 | KING  | PRESIDENT | NULL | 1981-11-17 | 5000.00 | NULL |
|     20 | RESEARCH   | DALLAS   |  7788 | SCOTT | ANALYST   | 7566 | 1987-04-19 | 3000.00 | NULL |
|     20 | RESEARCH   | DALLAS   |  7902 | FORD  | ANALYST   | 7566 | 1981-12-03 | 3000.00 | NULL |
|     30 | SALES      | CHICAGO  |  NULL | NULL  | NULL      | NULL | NULL       |    NULL | NULL |
|     40 | OPERATIONS | BOSTON   |  NULL | NULL  | NULL      | NULL | NULL       |    NULL | NULL |
+--------+------------+----------+-------+-------+-----------+------+------------+---------+------+
*/

