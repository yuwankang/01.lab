-- 11.view.sql
/*
 *  주의 사항
 * 	- oracle은 기본적으로 일반 계정에게 view 생성 권한을 부여하지 않는다.
 * 	- 해결점
 * 		- admin이 view 생성 및 관리 권한 부여
 * 		- 명령어?
 * 		- root 계정으로 진입후 
 * 		: db에 일반 계정으로 접속된 상태에서 admin 갈아타기 명렁어
 * 		sql>connect system/oracle
 * 		
 * 		: scott 계정에서 view 사용 권한 부여하는 필수 명령어
 * 		sql>fgrian create view to scott;
 * 
 * 		: admin 계정에서 scott 계정으로 갈아티기 명령어
 * 		sql>connect soctt/tiger

 * 
 * emp의  comm 컬럼은 영업직원 이외는 존재 자체를 몰라야 하는 상황
 * 개발자도 직원	
 * 	- comm 컬럼 제외한 table 정보를 개발자에게 제공
 * 	- 방식
 * 		원본 table에서 comm 제외한 복제본
 * 		crud로 복제본에 반영시 원본 table 데이터 동기화
 * 		
*
1. view 에 대한 학습
	- 물리적으로는 미 존재, 단 논리적으로 존재
	- 하나 이상의 테이블을 조회한 결과 집합의 독립적인 데이터베이스 객체
	- 논리적(존재하는 table들에 종속적인 가상 table)

2. 개념
	- 보안을 고려해야 하는 table의 특정 컬럼값 은닉
	또는 여러개의 table의 조인된 데이터를 다수 활용을 해야 할 경우
	특정 컬럼 은닉, 다수 table 조인된 결과의 새로운 테이블 자체를 
	가상으로 db내에 생성시킬수 있는 기법 

3. 문법   
	- create와 drop : create view/drop view
	- crud는 table과 동일
	
	CREATE VIEW view_name AS
	SELECT column1, column2, ...
	FROM table_name
	WHERE condition;
*/

select sal, comm from emp;


-- 1. emp table과 dept table 기반으로 empno, ename, deptno, dname으로 view 생성
drop view if exists emp_dept;

-- join 문장으로 table생성 및 view 생성

-- 물리적으로 존재하는 새로우 table 생성
-- 주의 사항: 데이터 갱신 emp, dept에는 영향을 주지 않음

-- join 이슈 발생
create table emp_dept as select empno, ename, e.deptno, dname from emp e, dept d;
-- desc emp_dept;

-- desc emp_dept;
create table emp_dept 
as select empno, ename, e.deptno, dname 
from emp e, dept d
WHERE e.deptno = d.deptno;

SELECT * FROM emp_dept;

-- view 생성
create view emp_dept_v as select* from emp_dept;


desc emp_dept_v;

select * from emp_dept_v;

-- dept table의 SALES라는 데이터를 영업으로 변경 후 view 검색
-- view는 dept table의 가변적인 상황을 그대로 인지 따라서 변경된 내용으로 검색 완료
select * from dept;

update dept set dname='영업' where dname='SALES';
select * from dept;  -- 영업으로 검색
select * from emp_dept_v; -- view 테이블은 영향을 받지 않는다. emp_dept_v는 영향없음 업데이트와 무관

-- view와 원본 테이블의 동기화
drop table emp_dept;
drop view emp_dept_v;

SELECT * from emp;
SELECT * from dept;

create table emp_dept 
as select empno, ename, e.deptno, dname 
from emp e, dept d
WHERE e.deptno = d.deptno;


select * from emp_dept_v; -- table로 부터 파생된 논리적인 가상 table
SELECT * from emp_dept; -- view 파생 원본 table


-- 2. view 삭제




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


