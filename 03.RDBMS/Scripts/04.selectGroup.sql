/* 학습내용
 * 1. 그룹 함수
 * 	- null 값은 내부적으로 자동 필터링
 * 2. 예시
 * 	- 단일행 함수: 이름 길이 counting 이름수 만큼 직원 수 만큼 검ㅅ맥
 * 	- 값이 하낭니 함수 : 전체직원 수 검색
 * 3. 실행 순서
 *  - from -> where -> select -> order by
 * 	select절
 *	from절
 *	where 절
 *  order by 절
 */

use fisa;

SELECT CHAR_LENGTH(ename) from emp; -- 단일행 함수 
SELECT COUNT(*) from emp; -- 그룹함수  


-- 1. 모든 직원의 월 급여 합(comm 배제) : sum()  
SELECT * from emp;
SELECT sum(sal) from emp;

-- 2. 모든 직원의 comm 합
SELECT count(comm) from emp; -- null 배제
SELECT sum(comm) from emp;

-- 3. deptno 부서번호 종류 검색
SELECT COUNT(deptno) from emp; -- 직원의 수가 나온다.
SELECT COUNT(DISTINCT deptno) from emp; -- 부서 3개가 나온다.
SELECT DISTINCT deptno from emp order by deptno desc; -- 부서 3개가 나온다. + order by 리뷰
-- 검색된 deptno의 내림차순 적용
-- 14명의 직원이 3개의 부서에 소속

-- 4. 최저 급여 검색
SELECT min(sal) from emp;

-- **** 그룹핑 : group by절 
-- 1. 부서별로 comm 받는 직원 수
	-- 부서별 그룹핑(group by deptno) , comm count(count(comm)
SELECT COUNT(comm) from emp;
SELECT deptno , comm from emp;  -- 10, 20번 무, 30번 부서 4명

SELECT deptno,COUNT(comm)
from emp 
group by deptno;

-- 2. 부서 내림차순 정령 추가
-- from -> group by -> select -> order by 순
SELECT deptno,COUNT(comm)
from emp 
group by deptno order by deptno desc;


-- 3. 문법 오류 및 논리적으로 오류
-- SELECT ename,deptno,COUNT(comm)
-- from emp 
-- group by deptno order by deptno desc;

-- 4. 부서별 급여 합, 평균 구하기
SELECT deptno,sum(sal), AVG(sal) from emp group by deptno;

-- 5. 소속 부서별 최대 급여와 최소 급여 단, deptno 오름차순
SELECT deptno, MAX(sal), MIN(sal) from emp group by deptno order by deptno asc; 

-- 6. 소속 부서별 최대 급여와 최소 급여 단, 최대 급여 오름차순 정렬
SELECT deptno, MAX(sal), MIN(sal) from emp group by deptno order by MAX(sal) asc; 

-- 실행순서 참고사항
-- 7. 최대급여를 order by 절에서 사용 했다는 것은 select 절 후 order by실행 입증
SELECT deptno, MAX(sal) as 최대급여, MIN(sal) as 최소급여 from emp group by deptno order by 최대급여 asc; 

-- 8. 7번 검색시 최대 급여가 5000인 부서 정보 제외하면서 검색
-- having 절 : 그룹화 문장의 조건식
SELECT deptno, MAX(sal) , MIN(sal) from emp group by deptno HAVING MAX(sal) < 5000;


-- 9. 8번 문제의 결과에 부서번호 내림차순으로 요청
SELECT deptno, MAX(sal), MIN(sal) from emp group by deptno HAVING MAX(sal) < 5000 order by deptno DESC;   

/* oracle 실행 불가
 * mysql 실행
 * 일반 DB 실행 인식 프로세스 from -> group by -> having -> select 최대급여는 인식이 안되어야 하는데
 * mysql에선 일반적인 실행 순서 대비 마킹등으로 select 절 별칭을 having절에서도 사용 가능하게함
 */
SELECT deptno, MAX(sal) as 최대급여, MIN(sal) as 최소급여 from emp group by deptno HAVING 최대급여 < 5000;

-- ? 내 짝궁을 위한 문제 한문제 제출하기 + 모범답안 도출
SELECT * from emp;
-- 부서별 입사일이 가장 빠른 사람의 입사일을 도출해주세요 단 입사일은 1981년 02월 19일 이후이며 부서명은 내림차순으로 정렬해주세요!
SELECT deptno , MIN(hiredate) from emp group by deptno HAVING MIN(hiredate) > '1981-02-19' order by deptno DESC; 
