/* 	현업 구조 변환
 * 	1. db의 접속 설정 정보를 자바 소스에서 분리
 * 	2. 형식
 * 		url/id/pw등을 구분을 위한 고유한 별칭
 * 		key와 value 구조 
 * 		properties라느 확장자 파일로 주로 관리
 * 
 * 		spring에서도 설정에 대한 모든 내용이 properries 파일로 분리되는 근원
 * 	3. 개발
 * 	 1단계 : properties 파일로 분리
 *   2단계 : 자바 소스에 해당 파일을 read하는 코드로 파일 인식
 *   3단계 : properies의 key로 선별해서 매핑된 데이터 read 후 사용
 *   
 *  4. io 관련 API
 *   1. 데이터 제공 근원지 (파일)
 *   	1. 1byte 단위로 read api -- 이미지
 *   		- FileInputStream
 *   	2. 2byte 단위로 read api -- 한글 데이터
 *   		- FileReader
 * 	 2. property 파일로  부터 key로 분리해서 read하는 주요 API
 * 		- Map 스펙 하위 클래스
 * 		- java.util.Properties를 key로 데이터 구분하면서 read
 * 	5. 개발 고려 사항
 * 		1. 파일이 존재?
 * 			- 파일없을 경우 - 비정상 종료 발생 가능성이 있음, 해소
 * 						- 예외처리를 사전에 적용
 * 			- 파일 존재 할 경우 - 정상
 * 		2. 파일 인식 단 내용물 key value 구조가 적합
 * 		3. 코드상에서 정확한 key 사용?
 */
package step01.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

public class ConnectionTest1 {
	@Test
	public void fileTest() {
		try {
			//key로 value 뽑는 기능의 객체
			Properties p = new Properties();
			
			//new FileInputStream("db.properties") 파일로 부터 1byte씩 read기능
			p.load(new FileInputStream("db.properties"));
			/* 용어 정리
			 * 변수 - field, variable, attribute, 속성, property
			 * 
			 */
			System.out.println(p.getProperty("jdbc.driver"));
			
			System.out.println("파일 read 가능");
		} catch (FileNotFoundException e) {
			System.out.println("파일없음");
			e.printStackTrace();// 발생된 히스토리 확인
		}catch (IOException e) {
			System.out.println("IO문제 발생");
			e.printStackTrace();
		}
	}
	//@Test
    public void oracleConnect() {
        // MySQL 연결 정보 설정
        String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe"; // MySQL 서버 URL (본인의 호스트 및 포트에 맞게 수정)
        String user = "scott"; // MySQL 사용자 이름
        String password = "tiger"; // MySQL 비밀번호

        try {
            // JDBC 드라이버 로드
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 데이터베이스 연결
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Oracle 데이터베이스에 성공적으로 연결되었습니다.");

            // 연결 해제
            conn.close();
            System.out.println("연결이 성공적으로 해제되었습니다.");

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버를 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Oracle 데이터베이스 연결 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        
        System.out.println("예외 처리로 인해 정상, 비정상 이어도 실행되는 영역");
        
    }

	//@Test
    public void mysqlConnect() {
        // MySQL 연결 정보 설정
        String url = "jdbc:mysql://localhost:3306/fisa"; // MySQL 서버 URL (본인의 호스트 및 포트에 맞게 수정)
        String user = "root"; // MySQL 사용자 이름
        String password = "root"; // MySQL 비밀번호

        try {
            // JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 데이터베이스 연결
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("MySQL 데이터베이스에 성공적으로 연결되었습니다.");

            // 연결 해제
            conn.close();
            System.out.println("연결이 성공적으로 해제되었습니다.");

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버를 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("MySQL 데이터베이스 연결 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        
        System.out.println("예외 처리로 인해 정상, 비정상 이어도 실행되는 영역");
        
    }
}
