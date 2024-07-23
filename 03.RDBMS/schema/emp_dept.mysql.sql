-- 현 mysql에 어떤 공간으로 분할(database들 확인)
show databases;

-- 사용자 정의로 database 생성
CREATE DATABASE fisa;
CREATE database fisa;


-- database에 접속 필수 
USE fisa;


-- emp와 dept라는 table이 혹여 존재할 경우 삭제하는 명령어
-- drop table emp; emp table 미존재시 에러 
drop table IF EXISTs emp;
drop table IF EXISTs dept;

-- dept table 생성
-- 한부서 표현 속성 : 부서번호(중복불허)/ 부서명 / 지역 
-- 주의사항 : varcher(size필수)
-- 제약사항 : pk_dept라는 이름으로 기본키 설정
-- 설정 방법 : 컬럼 선언 또는 선언 후 마지막 라인에 선언,
-- 			table 생성 후 alter 명령으로 제약조건 추가
CREATE TABLE dept (
    deptno               int  NOT NULL ,
    dname                varchar(20),
    loc                  varchar(20),
    CONSTRAINT pk_dept PRIMARY KEY ( deptno )
 );

-- 고유한 숫자값을 자동 적용하는 설정
-- 1부터 1씩 증가하면서 자동 실행 : mysql 엔진에게 권한 부여
-- 권장 : 게시글, 직원가입, 상품등록 .., pk용으로도 다수 사용
 
CREATE TABLE emp (
    empno                int  NOT NULL  AUTO_INCREMENT,
    ename                varchar(20),
    job                  varchar(20),
    mgr                  smallint ,
    hiredate             date,
    sal                  numeric(7,2),
    comm                 numeric(7,2),
    deptno               int,
    CONSTRAINT pk_emp PRIMARY KEY ( empno )
 );
 
-- emp table 구조 확인
desc emp;
-- dept table의 부서 번호와 emp table의 부서 번호를 동기화
-- 즉 dept의 deptno(부서번호)가 없는 데이터는 절대 emp의 deptno에 insert 불가
-- db 자체가 거부
-- table 생성시에 sql문장으로 설정
-- 제약조건을 사용자 정의 이름 부여 하는 사유
-- 데이터 구조가 방대해지기 때문에 자동 부여되는 이름 보다 관리 측면에서 의미 부여해서 설정
-- 명명 규칙 - table명 연관된 컬럼명, 제약조건명 혼용 축약
-- 예 : fk(foreign key, 참조키, 외래키) - pk를 참조해서 동일하게 활용하는 컬럼 속성 지정
		-- emp : 테이블 명, deptno - 연관 럴럼명
ALTER TABLE emp 
ADD CONSTRAINT fk_emp_deptno FOREIGN KEY ( deptno ) REFERENCES dept( deptno ) 
ON DELETE NO ACTION ON UPDATE NO ACTION;


-- 데이터의 대소문자 구분을 위한 설정
-- emp table의 ename은 반드시 대소문자 구분하라는 설정
alter table emp change ename ename varchar(20) binary;
/* 데이터값의 대소문자는 구분필수
 * - test와 Test는 다른 데이터
 * - mysql은 대소문자 구분없이 뜻만 동일하면 동일하다 간주
 * - 수업시간에 table 생성 후 바로 대소문자 구분 기질로 설정
 * 
 */

/* insert/update/delete 문장에만 해당
 * 	- 실행 후 임시 저장소에 저장
 *  - 사용을 위해서 commit 영구 저장 필수
 *  - tool들은 commit 자체적으로 제공
 *  
 * insert/update/delete -> commit : 단일 작업으로 간주
 * : 작은 단위의  실행 여러개가 하나의 작업으로 관리 되는 메카니즘 : 트랜젝션
 */
-- 존재하는 table에 데이터 저장
insert into dept values(10, 'ACCOUNTING', 'NEW YORK');
insert into dept values(20, 'RESEARCH', 'DALLAS');
insert into dept values(30, 'SALES', 'CHICAGO');
insert into dept values(40, 'OPERATIONS', 'BOSTON');
  
-- db는 자체적으로 데이터를 쉽게 가공가능하게 다양한 내장 함수들 제공
-- STR_TO_DATE() : 단순 문자열을 날짜 형식의 타입으로 변환 
insert into emp values( 7839, 'KING', 'PRESIDENT', null, STR_TO_DATE ('17-11-1981','%d-%m-%Y'), 5000, null, 10);
insert into emp values( 7698, 'BLAKE', 'MANAGER', 7839, STR_TO_DATE('1-5-1981','%d-%m-%Y'), 2850, null, 30);
insert into emp values( 7782, 'CLARK', 'MANAGER', 7839, STR_TO_DATE('9-6-1981','%d-%m-%Y'), 2450, null, 10);
insert into emp values( 7566, 'JONES', 'MANAGER', 7839, STR_TO_DATE('2-4-1981','%d-%m-%Y'), 2975, null, 20);
insert into emp values( 7788, 'SCOTT', 'ANALYST', 7566, DATE_ADD(STR_TO_DATE('13-7-1987','%d-%m-%Y'), INTERVAL -85 DAY)  , 3000, null, 20);
insert into emp values( 7902, 'FORD', 'ANALYST', 7566, STR_TO_DATE('3-12-1981','%d-%m-%Y'), 3000, null, 20);
insert into emp values( 7369, 'SMITH', 'CLERK', 7902, STR_TO_DATE('17-12-1980','%d-%m-%Y'), 800, null, 20);
insert into emp values( 7499, 'ALLEN', 'SALESMAN', 7698, STR_TO_DATE('20-2-1981','%d-%m-%Y'), 1600, 300, 30);
insert into emp values( 7521, 'WARD', 'SALESMAN', 7698, STR_TO_DATE('22-2-1981','%d-%m-%Y'), 1250, 500, 30);
insert into emp values( 7654, 'MARTIN', 'SALESMAN', 7698, STR_TO_DATE('28-09-1981','%d-%m-%Y'), 1250, 1400, 30);
insert into emp values( 7844, 'TURNER', 'SALESMAN', 7698, STR_TO_DATE('8-9-1981','%d-%m-%Y'), 1500, 0, 30);
insert into emp values( 7876, 'ADAMS', 'CLERK', 7788, DATE_ADD(STR_TO_DATE('13-7-1987', '%d-%m-%Y'),INTERVAL -51 DAY), 1100, null, 20);
insert into emp values( 7900, 'JAMES', 'CLERK', 7698, STR_TO_DATE('3-12-1981','%d-%m-%Y'), 950, null, 30);
insert into emp values( 7934, 'MILLER', 'CLERK', 7782, STR_TO_DATE('23-1-1982','%d-%m-%Y'), 1300, null, 10);
   
-- db에 작업한 내용 영구 저장 하는 필수 명령어
-- insert/update/delete 명령어에만 적용
-- 참고 : commit 영구 저장은 tool별로 기본 속성으로 처리 하기도 하나 코드로 commit 작업 권장
commit;

-- 데이터 정상 저장 단순 검색
-- emp 라는 table로 부터 모든 데이터 검색
SELECT * from emp;
