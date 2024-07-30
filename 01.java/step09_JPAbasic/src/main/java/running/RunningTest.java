package running;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Test;

import java.util.List;
import model.domain.entity.Customer;
import model.domain.entity.Dept;
import util.DBUtil;

public class RunningTest {
	
	//step04
	/* dept entity 개발 -> 설정 파일에 등록 -> 모든 부서 검색해서 출력 
	 * 
	 * JPA만의 select 문장
	 * 	- JPA는 entity를 기반으로 rdbms의 table과 소통
	 * - select 엔티티멤버변수들 from entity 명
	 * - select d from Dept d;
	 * - Dept entity의 모든 내용 검색(모든 멤버 변수)
	 * 		:rdbms로 select * from dept; 문장 처리
	 * 				select dname from dept;
	 * 				-> JPA의 select문장으로 변환시
	 * 				select d.dname from Dept d;
	 * 				d : 참조변수 간주
	 * 				d.dname : d가 참조하는 객체의 dname 멤버 변수명 호출
	 */
	@Test
	public void step04() {
		EntityManager em = DBUtil.getEntityManager();
		
		List<Dept> datas= em.createQuery("select d from Dept d", Dept.class).getResultList();
		
		datas.forEach(System.out::println);
		
		//모든 부서 번호의 합 출력
		int deptnoSum= em.createQuery("select d from Dept d", Dept.class).getResultStream().mapToInt(Dept::getDeptno).sum();
		System.out.println("모든 부서 번호의 합 :" + deptnoSum);
		
		//? 부서 번호가 40 미만인 부서 번호들만 합을 구하기
		deptnoSum= em.createQuery("select d from Dept d", Dept.class).getResultStream().mapToInt(Dept::getDeptno).filter(deptno -> deptno < 40).sum();
		System.out.println("부서번호가 40 미만 :" + deptnoSum);
		
		em.close();
	}
	
	//step03
	/* 존재하는 customer에 검색 -> 수정 -> 삭제
	*/
	//@Test
	public void step03() {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Customer c = em.find(Customer.class, 1);
		System.out.println(c);
		
		c = em.find(Customer.class, 1);
		System.out.println(c);
		
		tx.begin();
		c.setAge(20); //update 준비(DB에 영구 적용 직전, 영속성 영역에 이미 반영)
		em.remove(c);//delete 준비(DB에 영구 적용 직전, 영속성 영역에 이미 반영)
		
		em.clear(); //persistent context를 경우에 따라 c
		
		//tx.commit(); //db에 update와 delete 수행
		
		c = em.find(Customer.class, 1);//pk값으로 하나만 검색
		System.out.println(c);
		
		em.close();
	}
	//step02 
	//저장 시도는 하지만 commit 안함, 단 setXxx 값 수정
	//@Test
	public void step02() {
		
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.persist(new Customer("id2", "연아", 50)); 
		
		/*persistent context 라는 영속성(데이터) 저장소에 없다.
		 *  -> db에 select 해서 검색된 데이터인 entity 객체를 
		 *  persistent context에 저장해 놓음
		*/
		Customer c1 = em.find(Customer.class, 1); //no가 2인 데이터 검색
		System.out.println("-1-"+c1); //-1- null
		
		
		c1.setName("박지오");
		System.out.println("-2-"+c1);
		
		/*persistent context 라는 영속성(데이터) 저장소에 1pk의
		 * Entity가 있다 db에 select 할 필요가 없다.
		*/
		c1 = em.find(Customer.class, 10);
		System.out.println("-3-"+c1);
		em.close();
	}

	
	

	//step01 - table 생성, craete, update
	//@Test
	public void step01() {
		
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.persist(new Customer("id2", "연아이", 30)); 
		
		Customer c1 = em.find(Customer.class, 1);
		System.out.println(c1);
		
		
		//c1.setName("유다연");
		tx.commit(); 
		em.close();
	}



}
