package step11_myArt;



import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.junit.Test;

import util.DBUtil;

public class step11Test {

	@Test
	public void step01Test() {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = DBUtil.getEntityManager();
			tx = em.getTransaction();
			tx.begin();

//             Dept d1 = new Dept("test1", "test2");
//             em.persist(d1);
//             Emp newEmployee = new Emp("이름", "직업", 22L, new Date(System.currentTimeMillis()), 3000L, 200L, d1);
//             em.persist(newEmployee);

			tx.commit();

			// 패치 조인을 사용하여 Emp와 연관된 Dept 정보를 함께 조회
			// String jpql = "SELECT e FROM Emp e JOIN FETCH e.deptNo";
			// List<Emp> employees = em.createQuery(jpql, Emp.class).getResultList();
			List<Emp> datas = em.createQuery("select e from Emp e join e.deptNo", Emp.class).getResultList();
			// 인덱스 번호 초기화
			int index = 1;

			// 이쁘게 출력하기
			for (Emp emp : datas) {
				System.out.println("============== 직원 정보 [" + index + "] ==============");
				System.out.println("사원 번호: " + emp.getEmpNo());
				System.out.println("이름: " + emp.getEName());
				System.out.println("직업: " + emp.getJob());
				System.out.println("관리자: " + emp.getManager());
				System.out.println("입사일: " + emp.getHireDate());
				System.out.println("급여: " + emp.getSalary());
				System.out.println("커미션: " + emp.getComm());
				System.out.println("-------------- 부서 정보 --------------");
				if (emp.getDeptNo() != null) {
					System.out.println("부서 번호: " + emp.getDeptNo().getDEPTNO());
					System.out.println("부서 이름: " + emp.getDeptNo().getDNAME());
					System.out.println("부서 위치: " + emp.getDeptNo().getLOC());
				} else {
					System.out.println("연관된 부서 정보가 없습니다.");
				}
				System.out.println("======================================\n");
				index++; // 인덱스 증가
			}
//            Emp e = em.find(Emp.class, 7566L);
//            System.out.println(e);

//          //update
//          String updateJpql = "SELECT d FROM Dept d WHERE d.DEPTNO = 10";
//          List<Dept> depts = em.createQuery(updateJpql, Dept.class).getResultList();
//          
//          for (Dept dept : depts) {
//              dept.setLOC("PARIS2");  // 부서 번호 업데이트
//          }
//
//          tx.commit();
			
//          //delete
//			
//		  tx.begin();
//          String deleteJpql = "SELECT d FROM Dept d WHERE d.DEPTNO = 10";
//          List<Dept> depts = em.createQuery(deleteJpql, Dept.class).getResultList();
//          
//          for (Dept dept : depts) {
//              em.remove(dept);  // 부서 번호 삭제
//          }
//
//          tx.commit();

		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
}
