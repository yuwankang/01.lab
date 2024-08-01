
-- oracle schema

-- 해당 database(oracle db 자체) 내에 존재하는 table들 검색
SELECT * FROM tab;

-- emp와 dept라는 table이 혹여 존재할 경우 삭제하는 명령어
drop table emp;
drop table dept;

-- dept table 생성
-- 한부서 표현 속성 : 부서번호(중복불허)/ 부서명 / 지역 
CREATE TABLE Dept (
    deptno               number(4),
    dname                varchar2(20),
    loc                  varchar2(20),
    CONSTRAINT pk_dept PRIMARY KEY (deptno)
);

SELECT * FROM Dept;

-- number(4): 최대 4자리까지의 정수
-- number(7, 2) : 최대 7자리 단 소수점 이하 2자리까지 ex:12345.24

CREATE TABLE emp (
    empno                number(4),
    ename                varchar2(20),
    job                  varchar2(20),
    mgr                  number(4),
    hiredate             date,
    sal                  number(7,2),
    comm                 number(7,2),
    deptno               number(4),
    CONSTRAINT pk_emp PRIMARY KEY (empno)
 );

-- 1씩 자동 증가 기능의 객체를 추가 개발
-- mysql의 auto_increment와 동일
-- sequence
-- 특정 table의 특정 컬럼에 적용
-- insert into table values (emp_empno_seq.nextval, ...)
-- nextval 속성 : 1씩 자동 증가 해서 해당 컬럼에 적용시에 사용
DROP SEQUENCE emp_empno_seq;
CREATE SEQUENCE emp_empno_seq;

SELECT * FROM emp;
 
-- emp에 dept table의 deptno 매핑
ALTER TABLE emp 
ADD CONSTRAINT fk_emp_dept FOREIGN KEY (deptno) REFERENCES dept(deptno);

-- 존재하는 table에 데이터 저장
insert into Dept values(10, 'ACCOUNTING', 'NEW YORK');
insert into Dept values(20, 'RESEARCH', 'DALLAS');
insert into Dept values(30, 'SALES', 'CHICAGO');
insert into Dept values(40, 'OPERATIONS', 'BOSTON');
  
SELECT * FROM Dept;

SELECT * FROM emp;
-- sequence 생성은 했으나 적용 없이 pk구분해서 저장
insert into emp values(7839, 'KING', 'PRESIDENT', null, TO_DATE('17-11-1981', 'DD-MM-YYYY'), 5000, null, 10);                       
insert into emp values(7698, 'BLAKE', 'MANAGER', 7839, TO_DATE('1-5-1981','DD-MM-YYYY'), 2850, null, 30);
insert into emp values(7782, 'CLARK', 'MANAGER', 7839, TO_DATE('9-6-1981','DD-MM-YYYY'), 2450, null, 10);
insert into emp values(7566, 'JONES', 'MANAGER', 7839, TO_DATE('2-4-1981','DD-MM-YYYY'), 2975, null, 20);
insert into emp values(7902, 'FORD', 'ANALYST', 7566, TO_DATE('3-12-1981','DD-MM-YYYY'), 3000, null, 20);
insert into emp values(7369, 'SMITH', 'CLERK', 7902, TO_DATE('17-12-1980','DD-MM-YYYY'), 800, null, 20);
insert into emp values(7499, 'ALLEN', 'SALESMAN', 7698, TO_DATE('20-2-1981','DD-MM-YYYY'), 1600, 300, 30);
insert into emp values( 7521, 'WARD', 'SALESMAN', 7698, TO_DATE('22-2-1981','DD-MM-YYYY'), 1250, 500, 30);
insert into emp values( 7654, 'MARTIN', 'SALESMAN', 7698, TO_DATE('28-09-1981','DD-MM-YYYY'), 1250, 1400, 30);
insert into emp values( 7844, 'TURNER', 'SALESMAN', 7698, TO_DATE('8-9-1981','DD-MM-YYYY'), 1500, 0, 30);
insert into emp values( 7900, 'JAMES', 'CLERK', 7698, TO_DATE('3-12-1981','DD-MM-YYYY'), 950, null, 30);
insert into emp values( 7934, 'MILLER', 'CLERK', 7782, TO_DATE('23-1-1982','DD-MM-YYYY'), 1300, null, 10);
   
commit;

SELECT * from emp;

select * from dept;
