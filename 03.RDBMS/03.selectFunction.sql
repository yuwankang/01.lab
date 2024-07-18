-- 3.selectFunction.sql
/*
 * db 함수 종류
 * 	- 내장함수
 * 		- parameter로 받고 return 값들도 있음
 * 		- select count(*) from emp;
 * 	- 사용자 정의 함수
 * 		- 프로시저 학습 추천
 * 		
 */
/*
   내장 함수 종류
      1. 단일행 함수 - 입력한 행수(row) 만큼 결과 반환
      	- ex) length() 입력된 row수 만큼 결과 반환
      2. 집계(다중행, 그룹) 함수 - 입력한 행수와 달리 결과값 하나만 반환
      	- ex) count() - 반환값이 해당 합계인 하나
      	
      	
*/
use fisa;
-- 단일행 함수 : 입력 데이터 수만큼 출력 데이터
/* Mysql Db 자체적인 지원 함수 다수 존재
1. 숫자 함수 MySQL Numeric Functions
2. 문자 함수
3. 날짜 함수
**/
-- *** [숫자함수] ***
-- 1. 절대값 구하는 함수 : abs()
select 3.5, -3.5, +3.5;
SELECT 3.5, abs(3.5), +3.5;
-- 2. 반올림 구하는 함수 : round(데이터 [, 반올림자릿수])
-- 반올림자릿수 : 소수점을 기준으로 양수는 소수점 이하 자리수 의미
         -- 음수인 경우 정수자릿수 의미
SELECT 10/3; -- 3.3333
SELECT ROUND(10/3); -- 3
SELECT ROUND(10/3, 2); -- 3.33
-- 3. 지정한 자리수 이하 버리는 함수 : truncate(),   oracle의 경우 trunc()
-- 반올림 미적용
-- truncate(데이터, 소수자릿수)
-- 자릿수 : +(소수점 이하), -(정수 의미)
-- 참고 : 존재하는 table의 데이터만 삭제시 방법 : delete[복원]/truncate[복원불가]
select 10/4;
select round(10/4);
select TRUNCATE(10/3, 1); -- 소수점 이하 1자리까지 버림
show tables;
-- 원본 테이블 복제 : 제약조건 제외한 구조와 데이터 복제
CREATE  table emp01 as select * from emp;
desc emp01; -- 제약조건은 가져와지 않음
SELECT *from emp01;
-- 실습 전 autocommit 설정 무효화
DELETE FROM emp01;  -- 테이블 삭제
SELECT * from emp01; -- 검색 불가
rollback; -- DML(delete/ insert, update) 에만 적용되는 복원
SELECT * from emp01; -- 검색
TRUNCATE emp01;-- 스키마 빼고 데이터 다 날려버림 (rollback도 안됨) 임시저장소로 가지 않고 바로 영구저장소로 넘겨버림
			   -- 복구 불가인 데이터 삭제
		       -- 삭제 속도 빠름
			   -- rollback 불가
SELECT * from emp01;
rollback;
SELECT * from emp01;
-- 4. 나누고 난 나머지 값 연산 함수 : mod()
-- 모듈러스 연산자, % 표기로 연산, 오라클에선 mod() 함수명 사용
SELECT 5%3;
SELECT mod(10, 3);
-- 5. ? emp table에서 사번(empno)이 홀수인 사원의 이름(ename),
SELECT ename from emp WHERE empno % 2 != 0
-- 사번(empno) 검색
-- *** [문자함수] ***
/* tip : 영어 대소문자 의미하는 단어들
대문자 : upper
소문자 : lower
철자 : case
*/
-- 1. 대문자로 변화시키는 함수
-- upper() : 대문자[uppercase]
-- lower() : 소문자[lowercase]
-- 2. ? manager로 job 칼럼과 뜻이 일치되는 사원의 사원명 검색하기
drop table emp02;
CREATE  table emp02 as select ename , empno, job from emp;
SELECT * from emp02;
-- mysql은 데이터값의 대소문자 구분없이 검색 가능
-- 해결책 1 : binary()  대소문자 구분을 위한 함수
SELECT * from emp02 WHERE job = "manager";
SELECT * from emp02 WHERE BINARY (job) = "manager";
SELECT * from emp02 WHERE BINARY (job) = "MANAGER";
-- 해결책 2 : alter 명령어로 처리
SELECT * from emp02 WHERE job = "manager";  -- 원래는 됐는데
ALTER table emp02 change job job varchar(20) binary;
SELECT * from emp02 WHERE job = "manager";  -- 안됨
-- 3. 문자열 길이 체크함수 : length()
SELECT  CHAR_LENGTH(dname) from dept01;
-- 문자열의 길이를 byte 단위로 반환
-- 영어 , 숫지 - byte(8bit)
-- 한글 한글자 - 2byte(16bit)
-- mysql에선 한글 두글자 - 3byte이다 나중에 설정 변경 가능 2바이트먹기로
CREATE table dept01 as select dname from dept;
desc dept01
SELECT * from dept01;
SELECT dname, LENGTH(dname) from dept01;
insert into dept01 values('상암');
-- 4. 문자열 일부 추출 함수 : substr()
-- 서브스트링 : 하나의 문자열타입과 date 타입 일부 언어 발췌하는 로직의 표현
SELECT substr(dname, 1, 2) from dept; -- 여기서 1부터 2까지는 1번째 글자부터 2번째 글자까지라는 뜻, 0부터 시작 안함.
-- substr(데이터, 시작위치, 추출할 개수)
-- 시작위치 : 1부터 시작

-- 5. ? 년도 구분없이 2월에 입사한 사원(mm = 02)이름, 입사일 검색
-- date 타입에도 substr() 함수 사용 가능
-- 문자열 index 시작 - 1
select hiredate from emp;

SELECT ename, hiredate  from emp WHERE SUBSTR(hiredate, 6, 7) = 02;
-- 년도만 검색
SELECT SUBSTR(hiredate, 6, 2) from emp; 
-- 월만 검색

-- 일만 검색

-- 7. 문자열 앞뒤의 잉여 여백 제거 함수 : trim()

/*length(trim(' abc ')) 실행 순서
   ' abc ' 문자열에 디비에 생성
   trim() 호출해서 잉여 여백제거
   trim() 결과값으로 length() 실행 */
SELECT 'abc', CHAR_LENGTH(' abc '), LENGTH(' abc '), LENGTH (TRIM(' abc ')) ; 
-- *** [날짜 함수] ***
-- 1. ?어제, 오늘, 내일 날짜 검색
-- 현재 시스템 날짜에 대한 정보 제공 함수
-- sysdate() & now(): 날짜 시분 초
-- curdate() : 날짜
SELECT SYSDATE(), NOW(), CURDATE() ;
-- oracle : select sysdate from dual;
-- 2.?emp table에서 근무일수 계산하기, 사번과 근무일수 검색
SELECT * from emp;
SELECT  empno as 사번, CURDATE()-hiredate as 근무일수 from emp; 

-- 3. ? 교육시작 경과일수
-- 순수 문자열을 날짜 형식으로 변환해서 검색
/*
	yy/mm/dd 포멧으로 연산시에는 반드시 to_date() 라는 날짜 포멧으로
	변경하는 함수 필수
	단순 숫자 형식으로 문자 데이터 연산시 정상 연산
*/
SELECT CURDATE() - 20240708; 
SELECT CURDATE() - 2024/07/08; -- 원하지 않는 포맷으로 결과가 나온다. 
SELECT CURDATE() - str_to_date('2024/07/08','%y %m %d'); -- 원하지 않는 결과가 나옴

SELECT str_to_date('2024/07/08','%y %m %d'); -- 원하지 않는 결과가 나옴
SELECT str_to_date('2024/07/08','%y/%m/%d'); -- 원하지 않는 결과가 나옴

SELECT DATEDIFF(NOW(),'20240708'); 

-- 4. 문자열 날짜로 변경
-- 5. 특정 일수 및 개월수 더하는 함수 : ADDATE()
-- 10일 이후 검색
-- 15분 이후
-- 6. ? emp table에서 입사일 이후 3개월 지난 일자 검색
-- 7. 두 날짜 사이의 개월수 검색 : months_between()
-- 오늘(sysdate) 기준으로 2021-09-19
-- 특정 기준일로 오늘은 며칠차?(기준일 포함할 경우 +1)
-- 8. 오늘을 기준으로 100일은?(오늘이 1일로 계산할 경우 기준일 포함)
-- emp 직원들의 입사일 기준으로 5개월 후의 일자는?
-- 9. ?근무 연차(입사하자마자 1년 차로 계산될 경우)
-- 10. 1년 365일중 오늘은 몇일차?
-- 11. 주어진 날짜를 기준으로 해당 달의 가장 마지막 날짜 : last_day()
-- 12.? 2020년 2월의 마지막 날짜는?
-- *** [형변환 함수] ***
-- Data type
-- DATETIME : 'YYYY-MM-DD HH:MM:SS'
-- DATE : 'YYYY-MM-DD'
-- TIME : 'HH:MM:SS'
-- CHAR : String
-- SIGNED : Integer(64bit), 부호 사용 가능
-- UNSIGNED : Integer(64bit), 부호 사용 불가
-- BINARY : binary String
-- 1. cast() - 특정 type으로 형변환
-- 숫자를 문자로 변환
-- 문자를 숫자로 변환
-- 2. STR_TO_DATE() : 날짜로 변경 시키는 함수
--  올해 며칠이 지났는지 검색(포멧 yyyy/mm/dd)
-- select sysdate - 20200719; 오류
-- select sysdate - '20200719'; 오류
-- 3. 문자열로 date타입 검색 가능[데이터값 표현이 유연함]
-- 1980년 12월 17일 입사한 직원명 검색
-- 4. to_number(문자열, 변환포멧) : 문자열을 숫자로 변환
-- 어떤 숫자 형식으로 변환가능한지에 대한 명확성 필요한 함수
-- 1. '20,000'의 데이터에서 '10,000' 산술 연산하기
-- 힌트 - 9 : 실제 데이터의 유효한 자릿수 숫자 의미(자릿수 채우지 않음)
-- ?
-- *** 조건식 함수 ***
-- decode()-if or switch문과 같은 함수 ***
-- decode(조건칼럼, 조건값1,  출력데이터1,
--			   조건값2,  출력데이터2,
--				...,
--			   default값) from table명;
-- 1. deptno 에 따른 출력 데이터
-- 10번 부서는 A로 검색/20번 부서는 B로 검색/그외 무로 검색
-- 2. emp table의 연봉(sal) 인상계산
-- job이 ANALYST 5%인상(sal*1.05), SALESMAN 은 10%(sal*1.1) 인상,
-- MANAGER는 15%(sal*1.15), CLERK 20%(sal*1.2) 인상

-- 3. 'MANAGER'인 직군은 '갑', 'ANALYST' 직군은 '을',나머지는 '병'으로 검색