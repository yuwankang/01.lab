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
		//persistence.xml 파일의 내용을 별칭으로 찾아와서 객체화
		//EntityManagerFactory db의 접속정보 다 보유한 객체
		//접속 user수와 무관하게 효율성 면에서 단일 객체로 처리
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

