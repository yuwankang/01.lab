package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* Servlet&JSP 기반의 환경
 * 	- WEB-INF
 * 	- META-INF/context.xml - db의 설정, tomcat종속적인 설정
 * 	- javax.sql.DataSource - Connection 개수 제약, 관리, 제공해주는 내부 JDK API
 * 
 * Spring + JDBC 유지
 * - implementation 'org.springframework.boot:spring-boot-starter-jdbc'
 * - import org.springframework.jdbc.datasource.DriverManagerDataSource;
 * - 하단 코드처럼 명시적으로 Connection 객체 제공
 * 	: db접속 정보들은 별도의 properties로 파일로 분리 권장
 * 
 * 강사 코드 수정
 * 1. jsp & jstl tag 인식문제 
 * 	- 의존 library설정 부분
 *  - 주의사항
 *  	: 버전 명시할 경우 이슈 발생률이 더 높은듯 함
 *  	: 설정의 핵심 library들의 버전에 위임 	
 	implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'	
	implementation 'jakarta.servlet:jakarta.servlet-api' //스프링부트 3.0 이상
	implementation 'jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api' //스프링부트 3.0 이상
	implementation 'org.glassfish.web:jakarta.servlet.jsp.jstl' 
	
 * 2. 수정 부분
 * 	BoardController 
 * 	 - servlet -> SpringFramework
 * 	 - DAO 검색후 요청 객체에 저장후 list.jsp
 * 		: 요청객체에 데이터 저장 후 forward
 *  	: ModelAndView
 *  		Model - 요청객체에 데이터 저장
 *  		View - list.jsp
 *  			: WEB-INF/list.jsp
 *  			: application.properties 에 jsp 위치 추가
 */
import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceManager {

	private static DriverManagerDataSource ds = new DriverManagerDataSource();
	
	static {
		ds.setDriverClassName("oracle.jdbc.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521/xe");
		ds.setUsername("scott");
		ds.setPassword("tiger");  
	}
	
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	
	
	public static void close(Connection conn, Statement pstmt, ResultSet rset) {
		try {
			if(rset != null) {
				rset.close();
				rset = null;
			}
			if(pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if(conn != null) {
				conn.close();
				conn = null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn, Statement pstmt) {
		try {
			if(pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if(conn != null) {
				conn.close();
				conn = null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}	
}
