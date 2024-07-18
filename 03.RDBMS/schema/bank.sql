use fisa;

SELECT * FROM empAll;
SELECT COUNT(*) from empAll;
SELECT sum(sal) from empAll;


/*
 * bank index 구조를 csv 형식으로 가공
 * banl table에 저장
 * 
 * select 절 - 검색
 * from 절 - table
 * where 절 - 조건절
 * 
 * * : 모든 검색
 * location = '강남'
 * sum(customers)
 */

select sum(customers) from  bank WHERE location = '강남';

SELECT  sum(sal) from empAll;

SELECT
  COUNT(*) AS count,
  SUM(customers) AS sum,
  AVG(customers) AS avg,
  MIN(customers) AS min,
  MAX(customers) AS max
FROM
  bank
WHERE
  location = '강남';
 
 
 desc bank;

desc empAll;

-- customer 수가 100이상 ~ 150명 미만인 구간의 문서(row) 검색
/*	table - bank
 *  고객수 기준 - customer 컬럼
 *  100 <=< 150 범위 조건 - between and
 * 	모든 검색
 * 	해당 데이터 수(row 수) : count()
 */

SELECT * FROM bank WHERE customers BETWEEN 100 and 150; 


SELECT COUNT(*) FROM bank WHERE customers BETWEEN 100 and 150; 

# bank별 합계 후 평균 고객수 집계
/* sum() /avg()
 * bank 별
 * 	- 그룹핑, group by 절
 *  select절 from절 group by 절
 *  select 합 또는 평균 from bank group by bank
 * 
 *  실행 순서
 *  - from -> group by 절 -> select절
 */
SELECT bank as 은행이름, AVG(customers) as 평균, SUM(customers) as 고객총수 
FROM bank 
group by
bank ;
