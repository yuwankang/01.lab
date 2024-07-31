package step01.nonjoin;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Test;

import util.DBUtil;

public class Step01RunTest {

	@Test
	public void step01Test() {
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = DBUtil.getEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			Team1 t1 = new Team1("축구1팀");
			em.persist(t1);
			em.persist(new Team1("배구1팀"));
			
			em.persist(new Member1("손흥민", 1));  
			em.persist(new Member1("김연경", 2));  
			em.persist(new Member1("박지성", 3));  
			
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
