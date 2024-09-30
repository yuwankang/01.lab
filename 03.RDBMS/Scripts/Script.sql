SELECT * FROM emp;

--- emp2 복제본 : Spring Data JPA
DROP TABLE emp2;

CREATE TABLE emp2 AS SELECT empno, ename, sal, deptno FROM emp;

SELECT * FROM emp2;

