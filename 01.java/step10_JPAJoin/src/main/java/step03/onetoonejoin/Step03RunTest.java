package step03.onetoonejoin;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Test;

import util.DBUtil;

public class Step03RunTest {

	@Test
	public void step01Test() {
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = DBUtil.getEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			Team3 t1 = new Team3("축구1팀");
			em.persist(t1);
			em.persist(new Team3("배구1팀"));
			
			em.persist(new Member3("손흥민", t1));  
			em.persist(new Member3("김연경", t1));  
			em.persist(new Member3("박찬호", t1));  
			
			tx.commit();
			
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			if(em != null) {
				em.close();
				em = null;
			}
		}
		
	}
}
