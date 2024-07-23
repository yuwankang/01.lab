-- 6.subQuery.sql
-- oracle db에서도 실행되는 문장으로 확인 수정
/* Oracle : 
 * 	- table 명 대소문자 혼용 가능
 * 	- 데이터값 대소문자 명확히 구분
 * 	- insert/update/delete 실행 후 자동 commit or rollback 필수;
 * 
 * MYsql:
 * 	- table명 대소문자 중요
 * 	- 데이터 값 대소문자 구분하지 않기 때문에 alter 명령어로 추가 설정
 * 	  또는 select시마다 binary 관련 함수 사용
 * 	- insert/update/delete 실행 후 자동 commit;
 * 
 */

SELECT * FROM  tab;

CREATE table emp03 as select * from emp WHERE 1=0;
-- desc emp03;
SELECT * from emp;
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
/* DALLAS - dept loc
 * 	사원의 이름 - emp의 ename, 부서 번호 - deptno 검색
 * 
 */
SELECT ename, deptno
from emp
WHERE deptno = (select deptno 
				from dept 
				WHERE loc='DALLAS');


SELECT * from emp;
SELECT * FROM dept;

-- 5. 평균 급여(avg(sal))보다 더 많이 받는(>) 사원번호, 사원명, 급여만 검색

-- 급여와 평균 급여 검색, 급여 14명 데이터, 평균 급여는 14 데이터를 하나로 취합해서 반환
-- 단순 컬럼과 그룹함수 사용 불가
-- 컬럼명으로 검색 추가하려면 group by 절 추가
-- SELECT AVG(sal) from emp; -- 문법 오류 
-- 부서별 평균 급여
SELECT deptno, AVG(sal) from emp group by deptno; -- 그룹 by 명시한 컬럼만 사용 가능 

SELECT empno, ename, sal
from emp
WHERE sal > (SELECT AVG(sal) from emp);

-- 다중행 서브 쿼리(sub query의 결과값이 하나 이상)
-- 6.급여가 3000이상 사원이 소속된 부서에 속한  사원이름, 급여 검색
	-- 급여가 3000이상 사원의 부서 번호
	-- in
SELECT sal, deptno FROM emp WHERE sal >= 3000;

SELECT ename, sal FROM emp WHERE deptno IN (10, 20);

-- sub query
SELECT sal, deptno 
FROM emp 
WHERE deptno 
IN (
	SELECT deptno 
	FROM emp 
	WHERE sal >= 3000
);
-- sub + order by

SELECT ename,sal, deptno 
FROM emp 
WHERE (sal,deptno) 
IN (
	SELECT sal, deptno 
	FROM emp 
	WHERE sal >= 3000
) ORDER BY ename ASC;

-- -------------------------
SELECT ename,sal, deptno 
FROM emp 
WHERE (sal,deptno) 
IN (
	SELECT sal, deptno 
	FROM emp 
	WHERE sal >= 3000
) ORDER BY deptno desc,ename ASC;


-- 7. in 연산자를 이용하여 부서별(group by)로 가장 급여(max())를 많이 
-- 받는 사원의 정보(사번, 사원명, 급여, 부서번호) 검색
SELECT deptno, max(sal)
FROM emp e 
GROUP BY deptno;

SELECT empno, ename, sal, deptno
from emp
WHERE (deptno, sal)
IN (
	SELECT deptno, MAX(sal)
	from emp 
	group by deptno 
);



	

-- 8. 직급(job)이 MANAGER인 사람이 속한 부서의 부서 번호와 부서명(dname)과 지역검색(loc)
SELECT count(job) FROM emp WHERE job='MANAGER';
SELECT deptno, job FROM emp WHERE job='MANAGER';

SELECT deptno, dname, loc 
FROM dept d 
WHERE deptno IN (10, 20, 30);

SELECT deptno, dname, loc
from dept
WHERE deptno
IN (
	SELECT deptno 
	from emp
	WHERE job='MANAGER'
);


