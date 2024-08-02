/*  db driver 로딩
 *  접속을 위한 Connection 생성해서 반환
 *  자원 반환
 *  
 *  jpa db접속 Connection 
 */
package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

public class DBUtil {
	private static EntityManagerFactory emf;
	
	static {
		emf = Persistence.createEntityManagerFactory("dbinfo");
	}
	
	/*
	 * EntityManager : CRUD작업 가능한 객체 반환 
	 */
	public static EntityManager getEntityManager(){
		return emf.createEntityManager();
		
	}
	
	public static void close() {
		if(emf != null) {
			emf.close();
			emf = null;
		}
	}
	
	@Test
	public void test() {
		System.out.println(getEntityManager());
	}
}

