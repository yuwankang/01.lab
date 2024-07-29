-- oracle 전용 속성
-- mysql의 경우 limit로 처리

/* rownum
 * 1. table에 자동 생성되는 컬럼
 * 2. 
 * 
 */

SELECT * FROM dept;
SELECT DEPTNO, DNAME, LOC FROM dept ;
SELECT rownum, deptno, dname, loc FROM	dept ORDER BY deptno desc;
SELECT rownum, deptno, dname, loc FROM	dept ORDER BY deptno asc;

/* view
 * - 원본을 기반으로 파생되는 논리적인 가짜 table
 * - view 위치에 따른 구분
 * 		- from 절 적용시 inline view 
 * 
 */

-- deptno를 내림차순 정렬 후 deptno값이 높은 순으로 2개의 부서만 검색
-- order by 절 보다 뒤에 where이 들어갈수 없다.
-- SELECT rownum, deptno, dname, loc FROM	dept ORDER BY deptno DESC WHERE rownum < 3;

/*
-- 실행 순서 : from -> where -> select -> order by
-- 순서 보장 : rownum은 쿼리 결과에 순서를 보장하지 않는다.
			그래서 먼저 정렬한 후 ROWNUM을 사용하는 것이 좋다.
			방법적으로 해결책
			- inline view 또는 버전업된 오라클에선 ROW_NUMBER() 함수 사용 권장
	성능: ROWNUM은 쿼리 결과의 행을 순차적으로 필터링 하기 때문에 큰 데이터에서 성능에 영향을 주는가?
 		 이런 경우, 인덱스를 적절히 사용하거나 쿼리 최적화를 고려해야 한다
*/

-- 정상 실행 - 문법 불안정
SELECT rownum, deptno ,dname, LOC
FROM dept
WHERE rownum < 3
ORDER BY deptno DESC;

SELECT deptno, dname, loc 
FROM (
	  SELECT rownum, deptno, dname, loc FROM DEPT
	  ORDER BY deptno desc
)
WHERE rownum < 3;

-- emp의 deptno의 값이 오름차순으로 정렬된 상태로 상위 3개의 데이터 검색
-- 인라인 뷰 사용 (from절에 적용되는 select)

SELECT deptno, empno FROM emp ORDER BY deptno ASC;
SELECT * FROM emp ORDER BY deptno ASC;

SELECT *
FROM (
    SELECT * FROM emp
    ORDER BY deptno ASC
)
WHERE rownum < 4
ORDER BY empno DESC;

-- dept01
DROP TABLE dept01;

CREATE TABLE dept01 AS SELECT * FROM dept;
-- deptno 컬럼 alter명령어로 pk 설정 추가 

ALTER TABLE dept01 
ADD CONSTRAINT pk_deptno_dept 
PRIMARY KEY (deptno);

SELECT * FROM dept01;

-- 프로시저 DB자체에 권한 부여해서 필요할때마다 호출
