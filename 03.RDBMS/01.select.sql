/* 주의 사항
 * 단일 line 주석 작성시 -- 와 내용 사이에 blank 필수
 */
use fisa;




SELECT * from dept;

desc dept;
-- 1. 해당 계정이 사용 가능한 모든 table 검색
show tables;

-- 2. emp table의 모든 내용(모든 사원(row), 속성(컬럼)) 검색
SELECT * from emp;
-- 3. emp의 구조 검색
desc emp;
-- 4. emp의 사번(empno)만 검색
SELECT empno from emp;

-- 5. emp의 사번(empno), 이름(ename)만 검색
SELECT empno, ename from emp;

-- 6. emp의 사번(empno), 이름(ename)만 검색(별칭 적용)
SELECT empno as 사번, ename as 이름 from emp;

-- 7. 부서 번호(deptno) 검색
SELECT COUNT(*) deptno from emp; -- 14명의 직원 보유
SELECT deptno from emp; -- 14개 검색, 중복
-- 8. 중복 제거된 부서 번호 검색 /distinct
SELECT DISTINCT deptno from emp;

-- 9. 8 + 오름차순 정렬(order by)해서 검색
-- 오름 차순 : asc  / 내림차순 : desc
SELECT DISTINCT deptno FROM emp order by deptno DESC ;
SELECT DISTINCT deptno FROM emp order by deptno ASC ;

-- 10. ? 사번(empno)과 이름(ename) 검색 단 사번은 내림차순(order by desc) 정렬
SELECT empno as 사번, ename as 이름 from emp order by empno desc;

-- 11. ? dept table의 deptno 값만 검색 단 오름차순(asc)으로 검색
desc dept; -- dept 테이블 검색을 먼저 해야 한다.
SELECT deptno from emp order by deptno asc;

-- 12. ? 입사일(hiredate) 검색, 
-- 입사일이 오래된 직원부터 검색되게 해 주세요
-- 고려사항 : date 타입도 정렬(order by) 가능 여부 확인
SELECT hiredate from emp order by hiredate asc;

-- 13. ?모든 사원의 이름과 월 급여(sal)와 연봉 검색
SELECT ename as 사원이름,sal as 월급여, sal*12 as 연봉 from emp;
SELECT comm from emp; -- 커미션
-- db 자체적으로 연산 확인
-- mysql : from 절 생략 가능 / oracle : select 1+2 from dual;

-- 14. ?모든 사원의 이름과 월급여(sal)와 연봉(sal*12) 검색
-- 단 comm 도 고려(+comm) = 연봉(sal*12) + comm
-- null값과 연산시에는 모든 데이터가 null
-- 해결책 : null을 0값으로 대체
-- 모든 db는 지원하는 내장 함수 
-- null -> 숫자값으로 대체하는 함수 : IFNULL(null보유컬럼명, 대체값)
-- 모든 사원의 이름과 월급여(sal)와 연봉(sal*12)+comm 검색
SELECT COUNT(*) from emp; -- 직원수 확인
SELECT COUNT(comm) from emp;
SELECT empno, comm, comm+1 from emp; -- 데이터 유실
SELECT empno, sal, comm, comm+1 from emp; -- null값으로 인해 데이터 결측치가 생긴다.
-- null 값을 수치연산에 어떻게 적용할 것인가? 0 으로 대체
SELECT sal, comm, IFNULL(comm, sal*12) as 연봉 from emp; 

-- *** 조건식 ***
-- 15. comm이 null인 사원들의 이름과 comm만 검색
-- where 절 : 조건식 의미
SELECT ename, comm from emp where comm is null;

-- 16. comm이 null이 아닌 사원들의 이름과 comm만 검색
-- where 절 : 조건식 의미
-- 아니다 라는 부정 연산자 : not 
SELECT empno as 사원이름, comm as 커미션 from emp where comm is not null;
-- ?comm 안받는 검색
SELECT ename, comm from emp where comm is not null;
-- ? 모든 직원명, comm로 내림차순 정렬
SELECT ename, comm from emp order by comm desc; --null이 아닌 값부터 나온다.

SELECT ename, comm from emp order by comm asc; -- null값 부터 나온다. 
-- 17. ? 사원명이 스미스인 사원의 이름과 사번만 검색
-- = : db에선 동등비교 연산자
-- 참고 : 자바에선  == 동등비교 연산자 / = 대입연산자
SELECT  ename from emp; -- SMITH의 이름을 찾는다.

SELECT ename as 사원명, empno as 사번 from emp where ename ='SMITH';
SELECT ename as 사원명, empno as 사번 from emp where ename ='smith'; -- 소문자 검색이 불가능하다.

-- 18. 부서번호가 10번 부서의 직원들 이름, 사번, 부서번호 검색
-- 단, 사번은 내림차순 검색
SELECT ename as 이름, empno as 사번, deptno as 부서명 from emp where deptno = '10';

SELECT ename as 이름, empno as 사번, deptno as 부서명 from emp where deptno = '10' order by empno desc;-- 내림차순


-- 19. ? 기본 syntax를 기반으로 
-- emp  table 사용하면서 문제 만들기
-- 부서 번호(deptno)는 오름 차순(asc) 단 해당 부서(deptno)에 속한 직원 번호(empno)도 오름차순(asc)
SELECT ename as 직원이름, deptno as 부서번호, empno as 직원번호 from emp order by deptno asc, empno asc;

-- 결과가 맞는 문장인지의 여부 확인을 위한 추가 문장 생성
SELECT ename as 직원이름, deptno as 부서번호, empno as 직원번호 from emp order by deptno desc, empno desc;


-- 20. 급여가 900이상인 사원들 이름, 사번, 급여 검색 
SELECT ename as 이름, empno as 사번, sal as 급여 from emp where sal > 900;

SELECT sal from emp; - 데이트 검증

-- 21. deptno 10, job 은 manager(대문자로) 이름, 사번, deptno, job 검색
SELECT job from emp; -- 직업을 먼저 확인해야한다.
SELECT job from emp WHERE job = 'manager'; -- 소문자도 이상이 없다. 대소문자 구분을 안함

SELECT ename from emp;
SELECT  ename from emp WHERE ename = 'smith'; -- 이름은 대소문자 구별

-- 대문자 : upper / 소문자 : lower / uppercase (대문자의미)..
-- smith 소문자를 대문자로 변경해서 비교
SELECT  ename from emp WHERE ename = UPPER('smith');
SELECT  ename from emp WHERE LOWER(ename) = 'smith';
-- 소문자 manager는 미존재 따라서 검색 안됨
-- 대소문자 구분 없이 검색을 위한 해결책(대소문자 호환 함수)
-- upper() : 소문자 -> 대문자 / lower() : 대문자 -> 소문자


-- 22.? deptno가 10 아닌 직원들 사번, 부서번호만 검색
-- 부정연산자 not / != / <>
SELECT empno, deptno from emp; -- 사번과 부서번호 먼저 검색해보아야함
SELECT empno as 사번, deptno as 부서번호 from emp where deptno != 10; -- 선호


-- 23. sal이 2000이하(sal <= 2000) 이거나(or) 3000이상(sal >= 3000)인 사원명, 급여 검색
SELECT ename as 사원명, sal as 급여 from emp WHERE sal <= 2000 or sal >= 3000;


-- 24.  comm이 300 or 500 or 1400인
SELECT ename, comm from emp where comm in (300, 500, 1400)

-- in 연산식 사용해서 좀더 개선된 코드

-- 25. ?  comm이 300 or 500 or 1400이 아닌 사원명 검색
-- null값 미포함
SELECT ename, comm from emp; 
SELECT ename, comm from emp where comm in (300, 500, 1400); -- 커미션이 300, 500, 1400 인 사원명 
SELECT ename, comm from emp where comm not in (300, 500, 1400);
SELECT ename, comm from emp where not comm in (300, 500, 1400);


-- 26. 81/09/28 날 입사한 사원 이름.사번 검색
-- date 타입 비교 학습
-- date 타입은 '' 표현식 가능
-- yy/mm/dd 포멧은 차후에 변경 예정(함수)
SELECT hiredate from emp; -- 날짜 확인부터 한다.
desc emp; -- date 타입인지 확인 먼저 해야한다.
-- 81/09/28 -> 1981-09-28 ?
SELECT ename, empno, hiredate from emp WHERE hiredate = '81/09/28' ; -- 검색은 되지만 비추
SELECT ename, empno, hiredate from emp WHERE hiredate = '81-09-28' ; -- 정상


-- 27. 날짜 타입의 범위를 기준으로 검색
-- 범위비교시 연산자 : between~and 1980-12-17| ~ 1981-09-08 까지
SELECT ename, empno, hiredate from emp where hiredate BETWEEN '1980-12-17' and '1981-09-08';

/* 면접시 질의 응답 참조
 * 여담 검색 속도 향상을 위한 구조를 어떻게?
 * 	- 시스템 장비(비용)/sw(app) 최적화/DB(sql) 문장
 * 	- sql 문장으로 처리할 경우 like - 느림
 */

-- 28. 검색시 포함된 모든 데이터 검색하는 기술
-- like 연산자 사용
-- % : 철자 개수 무관하게 검색 / _ : 철자 개수 하나를 의미


-- 29. 두번째 음절의 단어가 M인 모든 사원명 검색 
SELECT ename from emp;
SELECT ename from emp WHERE ename like '_M'; -- 불가능
SELECT ename from emp WHERE ename like '_M%';-- 가능

-- 30. 단어가 M을 포함한 모든 사원명 검색 
SELECT ename from emp WHERE ename like '%M%'; -- M이 포함된 모든 이름이 검색됨



