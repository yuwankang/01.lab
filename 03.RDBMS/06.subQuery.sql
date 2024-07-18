-- 6.subQuery.sql

-- select문 내에 포함된 또 다른 select문 작성 방법

-- 문법 : 비교 연산자(대소비교, 동등비교) 오른쪽에 () 안에 select문 작성 
--	   : create 및 insert 에도 사용 가능
-- 실행순서 : sub query가 main 쿼리 이전에 실행

use fisa;  

-- 1. SMITH라는 직원 부서명 검색

-- inner join
SELECT d.DNAME 
FROM emp e INNER JOIN DEPT d
ON e.ENAME = 'SMITH' AND e.DEPTNO = d.DEPTNO;
  

-- sub query



-- 2. SMITH와 동일한 직급(job)을 가진 사원들의 모든 정보 검색(SMITH 포함)



-- 3. SMITH와 급여가 동일하거나 더 많은(>=) 사원명과 급여 검색
-- SMITH 가 포함된 검색 후에 SMITH 제외된 검색해 보기 




-- 4. DALLAS에 근무하는 사원의 이름, 부서 번호 검색
select loc from dept where loc='DALLAS';




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



