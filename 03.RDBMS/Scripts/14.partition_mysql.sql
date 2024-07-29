-- 14.partition_mysql.sql
/*
* - 원본 table 기반으로 생성하는 view도 원본 table의 파티션 기능 적용 받음
* - 학습 및 활용시
*  DBeaver의 F5 새로고침 필수
*  views, table, indexes 확인 필수
* 
* 1. 기능
* 	- 논리적으로 하나의 table 이미잠 물리적으로 여려 개의 table로 분리해서 관리하는 기술
* 	- 데이터와 index를 조각화해서 물리적 메모리를 효율적으로 사용
* 
* 2. 장점	
* 	- 하나의 table이 너무 클 경우 index의 크기가 물리적인 메모리 보다 훨씬 크거나 데이터 특성상 주기적인 삭제 작업이 필요한 경우 필요
* 	- 단일 insert와 빠른 검색
*
* 3. 파티션 키로 해당 데이터 저장 및 관리
* 
* 4. range() 사용한 partition
*  - 파티션 키로 연속된 범위로 파티션을 정의하는 방식
*  - 가장 일반적
*  - maxvalue라는 키워드를 사용 
* 	: 명시되지 않은 범위의 키 값이 담긴 레코드를 저장하는 파티션 정의가 가능
* 5. 참고
* - 대용량 데이터 처리에 사용되는 다양한 기술
* 	1. ES - 로그데이터 적재, 비정형 데이터베이스, JSON 
* 		  - pk는 없음 단 검색 기능은 충만
* 		  - 정형데이터베이스와 달리 견고성은 살짝 아쉬움
* 		  - 별도의 스키마가 없음
* 			: 스키마 - 제약조건이 충만한 table 설계의 구조
* 					 타입과 사이즈와 컬럼 개수와 각 테이블 간의 관계도가 매우 명확
* 				     dept의 deptno를 참조하는 emp의 deptno에 새로운 임의 부서
* 				     번호 저장 불가
* 					 ...
* 	2. 하둡
* 		- 대용량 데이터 처리에 좋은 sw
* 		- 관리가 너무 어렵고, 버전업시 관련된 생태계 sw들과 호환하는 설정이 너무 어려움
* 		- 유지보수 관리가 어렵다
* 		- 현추세 : 하둡대체용으로 회사들이 선별하는 추세
* 
* 	3. 카프카&레디스 .. 대용량 데이터 처리
* 		- 최종 프로젝트시 적용 권장 
* 
* 	4. db자체 버전업으로 정형데이터베이스도 대용량 데이터를 호율적으로 처리 가능한 구조로 개선
* 		- 하둡등에 있는 매커니즘인 파티션 기능이 mysql에 존재 
*
*/
use fisa;
-- drop table emp00;

/* 년도를 기준으로 하나의 table을 마치 물리적으로 다수의 table이 있는 것처럼 table 생성
  */
CREATE TABLE emp00 (
    empno                int  NOT NULL,
    ename                varchar(20),
    job                  varchar(20),
    mgr                  smallint,
    hiredate             date,
    sal                  numeric(7, 2),
    comm                 numeric(7, 2),
    deptno               int
) partition by range ( year(hiredate) )(  -- 파티션 정의, range() 가 파티션 키 설정
	partition p0 values less than (1980),
	partition p1 values less than (1983),
	partition p2 values less than (1986),
	partition p3 values less than maxvalue 
);

desc emp00;

insert into emp00 values( 7839, 'KING', 'PRESIDENT', null, STR_TO_DATE ('17-11-1981','%d-%m-%Y'), 5000, null, 10);
insert into emp00 values( 7698, 'BLAKE', 'MANAGER', 7839, STR_TO_DATE('1-5-1981','%d-%m-%Y'), 2850, null, 30);
insert into emp00 values( 7782, 'CLARK', 'MANAGER', 7839, STR_TO_DATE('9-6-1981','%d-%m-%Y'), 2450, null, 10);
insert into emp00 values( 7566, 'JONES', 'MANAGER', 7839, STR_TO_DATE('2-4-1981','%d-%m-%Y'), 2975, null, 20);
insert into emp00 values( 7902, 'FORD', 'ANALYST', 7566, STR_TO_DATE('3-12-1981','%d-%m-%Y'), 3000, null, 20);
insert into emp00 values( 7369, 'SMITH', 'CLERK', 7902, STR_TO_DATE('17-12-1980','%d-%m-%Y'), 800, null, 20);
insert into emp00 values( 7499, 'ALLEN', 'SALESMAN', 7698, STR_TO_DATE('20-2-1981','%d-%m-%Y'), 1600, 300, 30);
insert into emp00 values( 7521, 'WARD', 'SALESMAN', 7698, STR_TO_DATE('22-2-1981','%d-%m-%Y'), 1250, 500, 30);
insert into emp00 values( 7654, 'MARTIN', 'SALESMAN', 7698, STR_TO_DATE('28-09-1981','%d-%m-%Y'), 1250, 1400, 30);
insert into emp00 values( 7844, 'TURNER', 'SALESMAN', 7698, STR_TO_DATE('8-9-1981','%d-%m-%Y'), 1500, 0, 30);
insert into emp00 values( 7900, 'JAMES', 'CLERK', 7698, STR_TO_DATE('3-12-1981','%d-%m-%Y'), 950, null, 30);
insert into emp00 values( 7934, 'MILLER', 'CLERK', 7782, STR_TO_DATE('23-1-1982','%d-%m-%Y'), 1300, null, 10);
insert into emp00 values( 7839, 'KING2', 'PRESIDENT', null, STR_TO_DATE ('17-11-1983','%d-%m-%Y'), 5000, null, 10);
insert into emp00 values( 7698, 'BLAKE2', 'MANAGER', 7839, STR_TO_DATE('1-5-1983','%d-%m-%Y'), 2850, null, 30);
insert into emp00 values( 7782, 'CLARK2', 'MANAGER', 7839, STR_TO_DATE('9-6-1983','%d-%m-%Y'), 2450, null, 10);
insert into emp00 values( 7566, 'JONES2', 'MANAGER', 7839, STR_TO_DATE('2-4-1983','%d-%m-%Y'), 2975, null, 20);
insert into emp00 values( 7902, 'FORD2', 'ANALYST', 7566, STR_TO_DATE('3-12-1983','%d-%m-%Y'), 3000, null, 20);
insert into emp00 values( 7369, 'SMITH2', 'CLERK', 7902, STR_TO_DATE('17-12-1980','%d-%m-%Y'), 800, null, 20);
insert into emp00 values( 7499, 'ALLEN2', 'SALESMAN', 7698, STR_TO_DATE('20-2-1983','%d-%m-%Y'), 1600, 300, 30);
insert into emp00 values( 7521, 'WARD2', 'SALESMAN', 7698, STR_TO_DATE('22-2-1983','%d-%m-%Y'), 1250, 500, 30);
insert into emp00 values( 7654, 'MARTIN2', 'SALESMAN', 7698, STR_TO_DATE('28-09-1983','%d-%m-%Y'), 1250, 1400, 30);
insert into emp00 values( 7844, 'TURNER2', 'SALESMAN', 7698, STR_TO_DATE('8-9-1983','%d-%m-%Y'), 1500, 0, 30);
insert into emp00 values( 7900, 'JAMES2', 'CLERK', 7698, STR_TO_DATE('3-12-1983','%d-%m-%Y'), 950, null, 30);
insert into emp00 values( 7934, 'MILLER2', 'CLERK', 7782, STR_TO_DATE('23-1-1984','%d-%m-%Y'), 1300, null, 10);
insert into emp00 values( 7839, 'KING2', 'PRESIDENT', null, STR_TO_DATE ('17-11-1985','%d-%m-%Y'), 5000, null, 10);
insert into emp00 values( 7698, 'BLAKE2', 'MANAGER', 7839, STR_TO_DATE('1-5-1985','%d-%m-%Y'), 2850, null, 30);
insert into emp00 values( 7782, 'CLARK2', 'MANAGER', 7839, STR_TO_DATE('9-6-1985','%d-%m-%Y'), 2450, null, 10);
insert into emp00 values( 7566, 'JONES2', 'MANAGER', 7839, STR_TO_DATE('2-4-1985','%d-%m-%Y'), 2975, null, 20);
insert into emp00 values( 7902, 'FORD2', 'ANALYST', 7566, STR_TO_DATE('3-12-1985','%d-%m-%Y'), 3000, null, 20);
insert into emp00 values( 7369, 'SMITH2', 'CLERK', 7902, STR_TO_DATE('17-12-1980','%d-%m-%Y'), 800, null, 20);
insert into emp00 values( 7499, 'ALLEN2', 'SALESMAN', 7698, STR_TO_DATE('20-2-1985','%d-%m-%Y'), 1600, 300, 30);
insert into emp00 values( 7521, 'WARD2', 'SALESMAN', 7698, STR_TO_DATE('22-2-1985','%d-%m-%Y'), 1250, 500, 30);
insert into emp00 values( 7654, 'MARTIN2', 'SALESMAN', 7698, STR_TO_DATE('28-09-1985','%d-%m-%Y'), 1250, 1400, 30);
insert into emp00 values( 7844, 'TURNER2', 'SALESMAN', 7698, STR_TO_DATE('8-9-1985','%d-%m-%Y'), 1500, 0, 30);
insert into emp00 values( 7900, 'JAMES2', 'CLERK', 7698, STR_TO_DATE('3-12-1985','%d-%m-%Y'), 950, null, 30);
insert into emp00 values( 7934, 'MILLER2', 'CLERK', 7782, STR_TO_DATE('23-1-1986','%d-%m-%Y'), 1300, null, 10);
commit;
select * from emp00;
desc emp00;

-- insert 데이터 상단 재사용
-- ? 월 기준으로 partition 구분하기
-- emp00 table 사용

drop table emp00;

CREATE TABLE emp00 (
    empno       INT NOT NULL,
    ename       VARCHAR(20),
    job         VARCHAR(20),
    mgr         SMALLINT,
    hiredate    DATE,
    sal         NUMERIC(7, 2),
    comm        NUMERIC(7, 2),
    deptno      INT
)
PARTITION BY RANGE (EXTRACT(MONTH FROM hiredate)) (
    PARTITION m1 VALUES LESS THAN (2),
    PARTITION m2 VALUES LESS THAN (3),
    PARTITION m3 VALUES LESS THAN (4),
    PARTITION m4 VALUES LESS THAN (5),
    PARTITION m5 VALUES LESS THAN (6),
    PARTITION m6 VALUES LESS THAN (7),
    PARTITION m7 VALUES LESS THAN (8),
    PARTITION m8 VALUES LESS THAN (9),
    PARTITION m9 VALUES LESS THAN (10),
    PARTITION m10 VALUES LESS THAN (11),
    PARTITION m11 VALUES LESS THAN (12),
    PARTITION m12 VALUES LESS THAN maxvalue 
);

select * from emp00;

-- 우리카드 데이터 인경우 파티션 어떻게?
-- 한번 생성된 테이블 구조 변경이 어려움

-- 논리적인 구조의 view는 원본테이블과 같이 파티셔닝한다.
CREATE view emp00_v as select * from emp00;
SELECT * from emp00_v;