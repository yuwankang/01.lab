-- 8.DML.sql
/* 
존재하는 table 수행하는 작업 의미
- DML : Data Mainpulation Language
            데이터 조작 언어
	   (select(DQL, query, 질의, 검색)/insert/update/delete 모두 다 DML)
	   - CRUD
	   C : create - insert / 
	   R : read - select /
	   U : update - update /
	   D : delete - delete /
	   - 이미 존재하는 table에 데이터 저장, 수정, 삭제, 검색 
	   - commit과 rollback(트랜젝션)이 적용되는 명령어
	   	insert/update/delete에만 적용
	   	
	   	REST FULL - es의 소통 언어 표현 방식
	   	- 데이터 요청 - get : es 조작언어 데이터 검색
	   	- 데이터 수정 - put/post : 데이터 저장 및 수정
	   	- 데이터 저장 - put/post : 데이터 저장 및 수정
	   	- 데이터 삭제 - delete : 데이터 삭제
	   
   
1. insert sql문법
	-- 주의 사항 : 중복 데이터 저장시 불허
		- db 자체가 무결성 에러 발생
	1-1. 모든 칼럼에 데이터 저장시 
		insert into table명 values(데이터값1, ...)

	1-2.  특정 칼럼에만 데이터 저장시,
		명확하게 칼럼명 기술해야 할 경우 
		insert into table명 (칼럼명1, ...) values(칼럼과매핑될데이터1...)

	1-3. 다수의 row 데이터 값을 한번에 저장 하는 insert 문장
		insert into table명 values (), (), (), ... ;
2. update 
	- 경우의 수
		1. 존재하는 데이터 수정 시도
			- 정상 수정
		2. 미존재하는 데이터 수정 시도
			- 에러? - 데이터가 없을 뿐
			- 정상 실행 단 데이터가 없습니다.
		3. 
	2-1. 모든 table(다수의 row)의 데이터 한번에 수정
		- where조건문 없는 문장
		- update table명 set 칼럼명=수정데이타;

	2-2. 특정 row값만 수정하는 방법
		- where조건문으로 처리하는 문장
		- update table명 set 칼럼명=수정데이타[, 컬럼명2=수정데이터2,..] where 조건sql;
		
		
3. delete		
	- 존재하는 데이터 삭제

*/
-- mysql oracle 큰 차이 없음
use fisa;

-- *** insert ****
-- 1. 칼럼명 기술없이 데이터 입력
-- table 자체가 생성시에 컬럼 순으로 데이터 값들도 설정해서 저장
drop table if exists emp01;
drop table if exists emp02;

-- 데이터 구조만 복제해서 새로 생성
create table emp01 as 
	select empno, ename, deptno 
	from emp WHERE 1=0;

desc emp01;
SELECT * from emp01;



-- 2. 칼럼명 기술후 데이터 입력 
-- 저장하고자 하는 데이터의 순서를 컬럼명 명시하면서 변경 가능
-- null을 허용하는 컬럼에 값 미저장시 특정 컬럼만 명시해서 값 저장 가능
INSERT into emp01 values (10, '재석', 10);
select * from emp01;
desc emp01;
INSERT into emp01 values (10, '재석2', null);
INSERT into emp01 (empno, ename)values (10, '재석2');



-- 0으로 검색하는 현 sql문장은 사번이 0인 사람은 없기 때문에 가치없는 문장
select * from emp01 where empno=0;

insert into emp01 (empno, ename, deptno ) values (5, '재석2', 70);
select * from emp01;


-- 3. 하나의 table에 한번에 데이터 insert하기 
-- , 구분자로 () 표현을 적용해서 저장
drop table if exists emp01;
drop table if exists emp02;
create table emp01 as select empno, ename, deptno from emp where 1=0;
create table emp02 as select empno, ename, deptno from emp where 1=0;
select * from emp01;
select * from emp02;
desc emp01;

INSERT INTO emp01 values (1, '재석', 10), (1, '재석2', 20);
SELECT * from emp01;




select * from emp01;



-- 4. 데이터만 삭제 - rollback으로 복구 불가능한 데이터 삭제 명령어
truncate table emp01;
truncate table emp02;

rollback;  -- 의미 없음
select * from emp01;

-- dept의 10번 부서 삭제 시도
/* dept의 deptno는 emp의 deptno가 참조
 * 40번 부서 정보는 미활용/10번 부서 정보는 활용
 */
-- 자식 테이블에서 사용되는 데이터인 경우 삭제 금지
-- DELETE FROM dept WHERE deptno=10; -- 삭제 가능
DELETE FROM dept WHERE deptno = 40; -- 자식테이블에 데이터가 없어서 삭제 가능
SELECT * from dept; 




-- *** update ***
-- 1. 테이블의 모든 행 변경
drop table emp01;
create table emp01 as select * from emp;
select deptno from emp01;

-- deptno값을 50으로 변경
UPDATE emp01 set deptno = 50; 


select deptno from emp01;
rollback; -- autocommit

-- 10번 부서 번호를 50으로 변경
UPDATE emp01 set deptno = 50 WHERE deptno = 10;
SELECT * from emp01;
commit;

-- ? emp table의 10번 부서도 50으로 변경 가능?
-- 부모 테이블인 dept에는 50번 deptno가 없음
-- UPDATE emp set deptno = 50 WHERE deptno = 10; -- foregin키로 설정이 되어있어 설정 불가능
SELECT * from emp;



select * from emp01;
commit;

 


-- 2. ? emp01 table의 모든 사원의 급여를 10%(sal*1.1) 인상하기
-- ? emp table로 부터 empno, sal, hiredate, ename 순으로 table 생성
desc emp01;
drop table if exists emp01;

create table emp01 as select empno, sal, hiredate, ename from emp;
desc emp01;

select * from emp01;

select sal from emp01;

UPDATE emp01 set sal = sal*1.1 ;

select sal from emp01;
commit;



-- ? 3. emp01의 모든 사원의 입사일을 오늘로 바꿔주세요
select now();

select * from emp01;

UPDATE emp01 set hiredate = now();
select * from emp01;

commit;



-- ? 4. 급여가 3000이상(where sal >= 3000)인 사원의 급여만 10%인상
select sal from emp01;
UPDATE emp01 set sal = sal*1.1 WHERE sal >= 3000;



select sal from emp01;
commit;


-- 5. ?emp01 table 사원의 급여가 1000이상인 사원들의 급여만 500원씩 삭감 
-- insert/update/delete 문장에 한해서만 commit과 rollback 영향을 받음
select sal from emp01;

UPDATE emp01 set sal= sal-500 WHERE sal>=1000;
select sal from emp01;
commit;



-- 6. ? emp01 table에 DALLAS(dept의 loc)에 위치한 부서의 소속 사원들의 급여를 1000인상
-- 서브쿼리 사용
drop table emp01;
create table emp01 as select * from emp;
select * from emp01;
select * from dept;
select deptno from dept where loc='DALLAS';
select sal from emp01;

UPDATE emp01 
set sal = sal+1000
WHERE deptno = (SELECT deptno 
				from dept 
				WHERE loc='DALLAS');

select sal from emp01;
commit;


-- 7. ? emp01 table의 SMITH 사원의 부서 번호를 30으로, 직급은 MANAGER 수정
-- 두개 이상의 칼럼값 동시 수정
select deptno, job from emp01 where ename='SMITH';

UPDATE emp01 set deptno = 30, job = 'MANAGER'
WHERE ename = 'SMITH';


select deptno, job from emp01 where ename='SMITH';
commit;


-- *** delete ***
-- 8. 하나의 table의 모든 데이터 삭제
select * from emp01;

DELETE from emp01;

select * from emp01;
rollback;
select * from emp01;


-- 9. 특정 row 삭제(where 조건식 기준)
-- deptno가 30번 부서의 모든 사원들 사제
delete from emp01 where deptno=30;
select * from emp01;

rollback;

-- 10. emp01 table에서 comm 존재 자체가 없는(null) 사원 모두 삭제
DROP table emp01;

CREATE table emp01 as select * from emp;
select sal, comm from emp01;

SELECT * from emp01 WHERE comm is null;
SELECT * from emp01 WHERE comm is not null;


DELETE from emp01 WHERE comm is null;
SELECT * from emp01;

select sal, comm from emp01;
rollback;

-- 11. emp01 table에서 comm이 null이 아닌 사원 모두 삭제
DROP table emp01;
CREATE table emp01 as select * from emp;
select * from emp01;

DELETE from emp01 WHERE comm is not null;

select * from emp01;

-- 12. emp01 table에서 부서명이 RESEARCH 부서(dept table의 dname)에 소속된 사원 삭제 
-- 서브쿼리 활용
DROP table emp01;
CREATE table emp01 as select * from emp;

select * from emp01;

select * from dept;
select deptno from dept where dname='RESEARCH';

DELETE from emp01 
WHERE deptno = (select deptno from dept where dname='RESEARCH');


select * from emp01;
rollback;



-- 13. table 전체 내용 삭제
select * from emp01;

DELETE  from emp01;
select * from emp01;

rollback;
select * from emp01;



