-- 6.subQuery.sql
-- sql 문장중 select 필수
-- inner view 기법으로 sql 품질 이해도 향상 예정 

-- select문 내에 포함된 또 다른 select문 작성 방법

-- 문법 : 비교 연산자(대소비교, 동등비교) 오른쪽에 () 안에 select문 작성 
--	   : create 및 insert 에도 사용 가능
-- 실행순서 : sub query가 main 쿼리 이전에 실행

-- create table table 명 as select * from emp;
use fisa;  

show tables;
-- where 조건문에 거짓 정보가 들어가면 조건식 반영시 제약조건이 배제된 table 기본 구조만 복제
/* 인프라 구축
 *  - 개발 app 서비스 안정성
 * 	- 유해한 유입으로 부터 차단
 * 	- sql 주입 차단, 북한에서 유입되는 ip 차단..
 * 	- test tool 
 * 
 */
CREATE table emp03 as select * from emp WHERE 1=0;
desc emp03;
SELECT * from emp03;
-- 1. SMITH라는 직원 부서명 검색(dept의 dname 검색)


-- inner join
-- mysql의 table명의 대소문자 중요
-- oracle은 데이터만 대소문자 중요
SELECT d.dname 
FROM emp e INNER JOIN dept d
ON e.ename = 'SMITH' AND e.deptno = d.deptno;
  

-- sub query
SELECT deptno from emp WHERE ename = 'SMITH';
SELECT dname from dept WHERE deptno = 20;

SELECT dname
from dept 
WHERE deptno =(
				SELECT deptno
				from emp
				WHERE ename = 'SMITH'
			  );


-- 2. SMITH와 동일한 직급(job)을 가진 사원들의 모든 정보 검색(SMITH 포함)
SELECT * 
from emp
WHERE job=(
			SELECT job
			from emp
			WHERE ename ='SMITH'
		  );

-- 2. SMITH와 동일한 직급(job)을 가진 사원들의 모든 정보 검색(SMITH 제외)
SELECT * 
from emp
WHERE job=(
			SELECT job
			from emp
			WHERE ename ='SMITH' 
) and ename != 'SMITH';		 

-- from select 사용해 보기
-- inline view
-- from절에 작업 : sql 관점에선 마치 e라는 테이블이 존재한다 생각하고 검색
-- ? empno, ename, sal로만 구성된 table 실제존재한다는 가정 후 작업해 보기
SELECT empno, ename, sal from emp;

-- 검색하겠다면 별칭 alias를 넣어야 한다.
SELECT empno, sal*12
from 
(SELECT empno, ename, sal from emp) as e;

-- comm 미 존재 존재 불가 에러 발생
-- SELECT empno, sal*12, comm
-- from 
-- (SELECT empno, ename, sal from emp) as e;

-- 3. SMITH와 급여가 동일하거나 더 많은(>=) 사원명과 급여 검색
-- SMITH 가 포함된 검색 후에 SMITH 제외된 검색해 보기 
SELECT ename, sal 
from
(SELECT ename, sal from emp WHERE sal >= 'SMITH') as e;
-- ----
SELECT ename, sal 
from
(SELECT ename, sal from emp WHERE sal >= 'SMITH'and ename !='SMITH') as e ;


-- 4. DALLAS에 근무하는 사원의 이름, 부서 번호 검색
select loc from dept where loc='DALLAS';

SELECT ename, empno
from
(select ename, empno, loc from dept, emp WHERE loc= 'DALLAS')as e;

SELECT * from emp;


-- 5. 평균 급여(avg(sal))보다 더 많이 받는(>) 사원만 검색



-- 다중행 서브 쿼리(sub query의 결과값이 하나 이상)
-- 6.급여가 3000이상 사원이 소속된 부서에 속한  사원이름, 급여 검색
	-- 급여가 3000이상 사원의 부서 번호
	-- in
SELECT sal, deptno FROM emp WHERE sal >= 3000;

SELECT ename, sal FROM emp WHERE deptno IN (10, 20);

-- sub query

-- sub + order by



-- 7. in 연산자를 이용하여 부서별(group by)로 가장 급여(max())를 많이 
-- 받는 사원의 정보(사번, 사원명, 급여, 부서번호) 검색
SELECT deptno, max(sal)
FROM emp e 
GROUP BY deptno;


	

-- 8. 직급(job)이 MANAGER인 사람이 속한 부서의 부서 번호와 부서명(dname)과 지역검색(loc)
SELECT count(job) FROM emp WHERE job='MANAGER';
SELECT deptno, job FROM emp WHERE job='MANAGER';

SELECT deptno, dname, loc 
FROM DEPT d 
WHERE deptno IN (10, 20, 30);



