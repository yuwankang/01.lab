-- 9.integrity.sql
-- DB 자체적으로 강제적인 제약 사항 설정
/* 설정 방식
 * 	1. table 생성 시점에 제약 적용
 *  	1-1. 컬럼 선언시 해당 컬럼에 적용
 * 		1-2. 컬럼들 선언 후 마지막 영역에서 제약조건 설정
 * 	2. table 생성 후 추가 - alter
 * ---
 * 제약조건 사용자 정의 설정시 권장사항
 * 	- 제약조건에 이름 부여 권장
 * 	- 제약조건명 table명 컬럼명
 * 
 */

/*
1. table 생성시 제약조건을 설정하는 기법 
CREATE TABLE table_name (
    column1 datatype constraint,
    column2 datatype constraint,
    column3 datatype constraint,
    ....
);   

2. Data Dictionary란?
	- 제약 조건등의 정보 확인
	- user_constraints
	- oracle의 경우 이런 사전용 table에는 대문자명으로 table들 관리


3. 제약 조건 종류
	emp와 dept의 관계
		- dept 의 deptno가 원조 / emp 의 deptno는 참조
		- dept : 부모 table / emp : 자식 table(dept를 참조하는 구조)
		- dept의 deptno는 중복 불허(not null) - 기본키(pk, primary key)
		- emp의 deptno - 참조키(fk, foreign key, 외래키)
	
	
	2-1. PK[primary key] - 기본키, 중복불가, null불가, 데이터들 구분을 위한 핵심 데이터
		: not null + unique
		: 예시 - 회원가입
			id중복불허, null 불허, 이메일 필수로 필요로 함
			..
			이메일로 로그인 .. null과 중복 불허..
			이메일 
			
	2-2. not null - 반드시 데이터 존재
	2-3. unique - 중복 불가, null 허용
	2-4. check - table 생성시 규정한 범위의 데이터만 저장 가능 
	2-5. default - insert시에 특별한 데이터 미저장시에도 자동 저장되는 기본 값
				- 자바 관점에는 멤버 변수 선언 후 객체 생성 직후 멤버 변수 기본값으로 초기화
	* 2-6. FK[foreign key] 
		- 외래키[참조키], 다른 table의 pk를 참조하는 데이터 
		- table간의 주종 관계가 형성
		- pk 보유 table이 부모, 참조하는 table이 자식
	
4. 제약조건 적용 방식
	4-1. table 생성시 적용
		- create table의 마지막 부분에 설정
			방법1 - 제약조건명 없이 설정
			방법2 - constraints 제약조건명 명식
			
		- 참고 : mysql의 pk에 설정한 사용자 정의 제약조건명은 data 사전 table에서 검색 불가
			oracle db는 명확하게 사용자 정의 제약조건명 검색 

	4-2. alter 명령어로 제약조건 추가
	- 이미 존재하는 table의 제약조건 수정(추가, 삭제)명령어
		alter table 테이블명 modify 컬럼명 타입 제약조건;
		
	4-3. 제약조건 삭제(drop)
		- table삭제 
		alter table 테이블명 alter 컬럼명 drop 제약조건;
		
*/



-- 1. table 삭제
drop table  emp01;

-- 사전 테이블 검색
SELECT * FROM USER_CONSTRAINTS;
-- 2. 사용자 정의 제약 조건명 명시하기
-- 개발자는 sql 문법 ok된 상태로 table + 제약조건 생성
-- db 관점에서 기록
-- 난수 발생 이름 부여
create table emp01(
	empno int not null,
	ename varchar(10)
);

SELECT * FROM USER_CONSTRAINTS
WHERE TABLE_NAME ='emp01';

desc emp01;

-- mysql에 사전 table 검색
select TABLE_SCHEMA, TABLE_NAME 
from information_schema.TABLES
where TABLE_SCHEMA = 'information_schema';

-- 3. emp table의 제약조건 조회
-- table 생성시 컬럼 선언시에 not null ???
-- oracle db에선 table명은 대문자로 데이터화 해서 저장
SELECT * FROM USER_CONSTRAINTS
WHERE TABLE_NAME ='emp01';

SELECT * FROM USER_CONSTRAINTS
WHERE TABLE_NAME ='EMP01';

select * from information_schema.TABLE_CONSTRAINTS tc 
where TABLE_NAME = 'emp01';

-- empno : not null / ename : null
insert into emp01 (empno, ename) values (1, '재석');
select * from emp01;

insert into emp01 (empno) values (2);  -- ok
select * from emp01;

-- ? 실행해 보기
insert into emp01 (ename) values ('연아'); -- INSERT 불가능 empno가 NOT NULL 이기 때문에
select * from emp01;



-- *** not null ***
-- 4. alter 로 ename 컬럼을 not null로 변경
-- desc emp01;

-- emp01의 ename엔 null이 없어야 정상 실행
delete from emp01 where ename is null;
select * from emp01;

-- ? emp01 table의 ename 컬럼을 not null로 제약조건 추가해 보기
-- varchar(10)

-- 5. drop 후 dictionary table 검색
drop table  emp01;

-- emp01에 대한 정보가 소멸된 상태 확인을 위한 명령어
-- table 삭제시 제약조건도 자동 삭제
SELECT * FROM USER_CONSTRAINTS
WHERE TABLE_NAME ='EMP01';


-- 6. 제약 조건 설정후 insert 
DROP TABLE emp01;
-- table에 컬럼 선언시 제약조건명 적용 하면서 생성
-- 참고 : 보편적으로 not null은 제약조건 명을 꼭 명시하지 않는 빈도가 높기는 함
create table emp01(
	empno int CONSTRAINT emp01_empno_nn not null,
	ename varchar(10)
);

insert into emp01 values(1, 'tester');
select * from emp01;
insert into emp01 (empno, ename) values(2, 'tester');
insert into emp01 (empno) values(3);
select * from emp01;

insert into emp01 (empno) values(3);
-- not null은 중복 허락한다.
select * from emp01;

-- *** unique ***
-- 7. unique : 고유한 값만 저장 가능, null 허용
-- test를 위한 문자 구성해 보기
drop table  emp02;

CREATE TABLE emp02(
	empno int CONSTRAINT emp02_empno_nn UNIQUE,
	ename varchar2(10)
);




select * from information_schema.TABLE_CONSTRAINTS 
where table_name='EMP02';

insert into emp02 values(1, 'tester');
select * from emp02;

insert into emp02 (ename) values('master');  -- ok 즉 null 허용
select * from emp02;


insert into emp02 (empno) values(2);  -- ok 즉 null 허용
select * from emp02;

-- 중복 데이터 불허하는 test
-- insert into emp02 (empno) values(2);  
select * from emp02;

-- 컬럼 선언 후 마지막 영역에 제약조건 추가 
drop table  emp02;

CREATE TABLE emp02(
	empno int,
	ename varchar2(10),
	CONSTRAINT emp02_empno_nn UNIQUE (empno)
);

SELECT * FROM USER_CONSTRAINTS
WHERE TABLE_NAME ='EMP02';

/* 참고 : 하단 table 생성 문장에 new line 반영
 * 이 문장은 정상 실행 가능한 버전이 있고 비정상 실행 버전이 있음
 * 가급적 컬럼 선언과 제약조건 사이에 blank line 사용 비추
 * 
 */

CREATE TABLE emp02(
	empno int unique,
	ename varchar2(10),
	CONSTRAINT emp02_empno_nn UNIQUE (empno)
);

SELECT * FROM 
-- 8. alter 명령어로 ename에 unique 적용
drop table emp02;

CREATE TABLE emp02(
	empno int,
	ename varchar(10)
);


desc emp02;
select * from emp02;
-- alter  명령어로 제약 조건 추가
ALTER TABLE	emp02 ADD unique(empno);

SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE, TABLE_NAME
FROM USER_CONSTRAINTS
WHERE TABLE_NAME ='EMP02';

-- *** primary key ***
/* 현 시점 상황
 * 1. pk 값을 데이터의 무한 증가하는 양 - 숫자로 하는 케이스 다수
 * 	- 자동 증가 기법 : insert시 자동 증가분이 반영
 * 	- oracle : sequence(객체)
 * 				별도로 생성 후 insert문장 작성시 적용 문법 필수 반영
 *  - mysql : auto increment
 * 				table 생성시 컬럼에 명시만으로 끝
 * 
 * 2. table명, 컬럼명을 한글로 하기도 함
 * 	- db는 유니코드 지원 : 한글, 특수기호, 숫자, 영어, 다국어 컴퓨터가 인지할수 있는 모든 언어 
 * 	- 아스키코드 : 숫자, 특수기호, 영어의 표준 기호 체계
 */
-- ename이 null인 사람 삭제
delete from emp02 where ename is null;
desc emp02;

select * from emp02;

-- ? ename 컬럼에 unique 설정 추가


desc emp02;

select * from information_schema.TABLE_CONSTRAINTS tc 
where table_name='emp02';


-- *** Primary key ***

-- 9. pk설정 : 데이터 구분을 위한 기준 컬럼, 중복과 null 불허
-- 컬럼에 제약조건명 없이 적용 -> table 하단에 제약 조건명 명시해서 만들기
-- -> alter 명령어로 직접 추가
DROP TABLE emp03;


CREATE TABLE emp03(
	empno int PRIMARY KEY,
	ename varchar2(10)
);
-------------------------------------------------------
CREATE TABLE emp03(
	empno int,
	ename varchar2(10)
);
alter table emp03 add constraint pk_empno_emp03 primary key(empno);
---------------------------------------
CREATE TABLE emp03(
	empno int ,
	ename varchar2(10),
	CONSTRAINT emp_pk_empno PRIMARY KEY(empno)
);



DROP TABLE emp03;
SELECT * FROM emp03;


SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE, TABLE_NAME
FROM USER_CONSTRAINTS
WHERE TABLE_NAME ='EMP03';

-- ? 동일한 1값 insert 시도해 보기
insert into emp03 values (1, 'tester');
insert into emp03 values (1, 'master'); 

select * from emp03;

drop table  emp03;

-- ?
insert into emp03 values (1, 'tester');
insert into emp03 values (1, 'master'); 

select * from emp03;



-- 12. 제약 조건 삭제

SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE, TABLE_NAME
FROM USER_CONSTRAINTS
WHERE TABLE_NAME ='EMP03';

CREATE TABLE emp03(
	empno int ,
	ename varchar2(10),
	CONSTRAINT emp_pk_empno PRIMARY KEY(empno)
);

ALTER TABLE	emp03 DROP PRIMARY KEY;


alter table emp03 add constraint pk_empno_emp03 primary key(empno);


-- *** foreign key ***

-- 13. 외래키[참조키]
-- emp table 기반으로 emp04 복제 단 제약조건은 적용되지 않음
-- alter 명령어로 table의 제약조건 추가 

drop table  emp04;

create table emp04 as select * from emp;

SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE, TABLE_NAME
FROM USER_CONSTRAINTS
WHERE TABLE_NAME ='EMP04';

-- dept table의 deptno를 emp04의 deptno에 참조 관계 형성
SELECT * FROM emp04;

-- 생성시 fk 설정
drop table  emp04;

-- empno pk, deptno fk
CREATE TABLE emp04(
	empno NUMBER(4),
	ename varchar2(20) NOT NULL,
	deptno NUMBER(4),
	CONSTRAINT emp_pk_empno PRIMARY KEY(empno),
	CONSTRAINT dept_fk_deptno foreign KEY(deptno) References dept(deptno)
);

SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE, TABLE_NAME
FROM USER_CONSTRAINTS
WHERE TABLE_NAME ='EMP04';

insert into emp04 values (1, '연아', 10);
insert into emp04 values (2, '재석', 10);
-- insert into emp04 values (3, '구씨', 70);  -- dept에 없는 데이터 에러 발생


select * from emp04;

-- 14. alter & fk drop : dict table에서 이름 확인후 삭제 
SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE, TABLE_NAME
FROM USER_CONSTRAINTS
WHERE TABLE_NAME ='EMP04';

ALTER TABLE emp04 DROP PRIMARY KEY; -- 성공
ALTER TABLE emp04 DROP FOREIGN KEY; 
-- 실패
ALTER TABLE emp04 DROP CONSTRAINT DEPT_FK_DEPTNO; -- 성공

-- ALTER TABLE emp04 DROP FOREIGN KEY DEPT_FK_DEPTNO; -- 에러
-- ALTER TABLE emp04 DROP PRIMARY KEY EMP04_EMPNO_PK; -- 에러

-- 이름으로 제약조건 삭제

-- *** check ***	
-- 15. check : if 조건식과 같이 저장 직전의 데이터의 유효 유무 검증하는 제약조건 
-- Y or M / 학년 표현등 고정 소량의 데이터 처리시 절대 권장

SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE, TABLE_NAME
FROM USER_CONSTRAINTS
WHERE TABLE_NAME ='EMP05';

drop table  emp05;

CREATE TABLE emp05(
	empno NUMBER(4),
	ename varchar2(20) NOT NULL,
	age int,
	CHECK (age BETWEEN 1 AND 150)
);

SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE, TABLE_NAME
FROM USER_CONSTRAINTS
WHERE TABLE_NAME ='EMP05';

-- 0초과 데이터만 저장 가능한 check

-- ?
insert into emp05 values(1, 'master', 10); -- 성공
insert into emp05 values(2, 'master', -10); --  CHECK 하면서 데이터를 못받아들인다. 실패

select * from emp05;


-- ? age값이 1~100까지만 DB에 저장
drop table  emp05;

create table emp05(
	empno int primary key,
	ename varchar(10) not null,
	age int,
	?
);


-- ?
insert into emp05 values(1, 'master', 10);
insert into emp05 values(2, 'master', 200);
select * from emp05;


-- 16. alter & check
drop table  emp05;

create table emp05(
	empno int,
	ename varchar(10) not null,
	age int
);

SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE, TABLE_NAME
FROM USER_CONSTRAINTS
WHERE TABLE_NAME ='EMP05';




insert into emp05 values(1, 'master', 10);
insert into emp05 values(2, 'master', -10); 

select * from emp05;


-- 17. drop a check : 제약조건명 검색 후에 이름으로 삭제
select * from information_schema.TABLE_CONSTRAINTS where table_name='emp05';

alter table emp05 drop check emp05_chk_1; 

select * from information_schema.TABLE_CONSTRAINTS where table_name='emp05';



-- 18. ? gender라는 컬럼에는 데이터가 M 또는(or) F만 저장되어야 함
drop table  emp06;

create table emp06(
	empno int,
	ename varchar(10) not null,
	gender char(1),
	
	?
);


select * from information_schema.TABLE_CONSTRAINTS where table_name='emp06';
 
insert into emp06 values(1, 'master', 'F');
insert into emp01 values(2, 'master', 'T'); 
select * from emp06;




-- 19. alter & check

drop table  emp06;

-- char(3) -  무조건 3byte 메모리 점유(고정 문자열 크기 타입)
-- varchar(10) - 가변적인 문자열 메모리 즉 최대 10byte 의미 (가변적 문자열 크기 타입)
create table emp06(
	empno int,
	ename varchar(10) not null,
	gender char(1)
);

alter table emp06 add check ( gender in ('F', 'M') );


-- ?
insert into emp06 values(1, 'master', 'F');
insert into emp01 values(2, 'master', 'T'); 
select * from emp06;


-- *** default ***
-- 20. 컬럼에 기본값 설정
-- insert시에 데이터를 저장하지 않아도 자동으로 기본값으로 초기화(저장)
-- 자바는 멤버 변수 선언 후 객체 생성 시점에 자동 초기화와 같은 원리
-- 자바는 타입별 기본 값이 자동 대입 / RDBMS는 사용자가 기본값 직접 지정 
drop table  emp06;

create table emp06(
	empno int,
	ename varchar(10) not null,
	age int DEFAULT 1 -- default는 데이터를 안넣을 때만
);

SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE, TABLE_NAME
FROM USER_CONSTRAINTS
WHERE TABLE_NAME ='EMP06';

desc emp06;
select * from information_schema.TABLE_CONSTRAINTS where table_name='emp06';
 
insert into emp06 values(1, 'master', 10);
select * from emp06;

-- age 컬럼에 데이터 저장 생략임에도 1이라는 값 자동 저장
insert into emp06 (empno, ename) values(2, 'master');  
select * from emp06;


-- 21. alter & default

drop table  emp06;

create table emp06(
	empno int,
	ename varchar(10) not null,
	age int
);

SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE, TABLE_NAME
FROM USER_CONSTRAINTS
WHERE TABLE_NAME ='EMP06';

insert into emp06 values(1, 'master', 10);
select * from emp06;

insert into emp06 (empno, ename) values(2, 'use02');
select * from emp06;

-- oracle default 생성
ALTER TABLE	emp06 MODIFY (age DEFAULT 1);

-- ALTER TABLE	emp06 ADD age SET DEFAULT 1; mysql

drop table  emp06;
-- 22. default drop 
-- defalut 제약조건 삭제
-- age 컬럼은 존재만 하는 상황

create table emp06(
	empno int,
	ename varchar(10) not null,
	age int
);

-- mysql 용
alter table emp06 alter age drop default;

-- oracle용 drop
ALTER TABLE emp06 MODIFY age DEFAULT NULL;

select * from information_schema.TABLE_CONSTRAINTS where table_name='emp06';
desc emp06;

-- ?
insert into emp06 (empno, ename) values(13, 'use13');  
insert into emp06 (empno, ename, age) values(4, 'use04', 10);  
insert into emp06 (ename, age) values('use05', 50);  

select * from emp06;
