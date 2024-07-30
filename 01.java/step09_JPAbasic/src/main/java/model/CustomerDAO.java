package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.domain.entity.Customer;
import util.DBUtil;

public class CustomerDAO {
	
	public void save() throws Exception{
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try{
			em = DBUtil.getEntityManager();
		
			tx = em.getTransaction();
			tx.begin();
			
			em.persist(new Customer("id1", "재석", 11));
			
			tx.commit();   
			
		}catch(Exception e) {
			tx.rollback(); 
			throw e;
		}finally {
			if(em != null) {
				em.close();
				em = null;
			}
		}
	}
	
	public void search() throws Exception{
		EntityManager em = null;
		
		try {
			em = DBUtil.getEntityManager();
					
			Customer c1 = em.find(Customer.class, 1);
			
			System.out.println(c1);
			
		}finally {
			if(em != null) {
				em.close();
				em = null;
			}
		}
	}
	
}
