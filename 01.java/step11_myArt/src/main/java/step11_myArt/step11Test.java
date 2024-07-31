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
            
           
            List<Object[]> results = em.createQuery(
                "SELECT e, d FROM Emp e JOIN e.deptNo d", Object[].class)
                .getResultList();
            
            results.forEach(result -> {
                Emp emp = (Emp) result[0];
                Dept dept = (Dept) result[1];
                System.out.println(emp);
                System.out.println(dept);
            });
            
            
            
            tx.commit();
            
        } catch(Exception e) {
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
