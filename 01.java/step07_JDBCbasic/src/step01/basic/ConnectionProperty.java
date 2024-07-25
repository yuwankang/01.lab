/* 요청 사항
 * 	- 최적화, db.properties 사용 코드로 리펙토링
 */
package step01.basic;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

public class ConnectionProperty {
	private static Properties p = new Properties(); //값에 대한 제어 막기 위해 private과 static을 사용하여 최적화 한다.
	// byte code인 실행 파일이 로딩시 단 한번만 실행
	// db연동 driver는 24시간 365일 구동되는 서버에 단 한번만 실행 권장
	static{
		try {
			p.load(new FileInputStream("db.properties"));
			// JDBC 드라이버 로드
            Class.forName(p.getProperty("jdbc.driver"));
			System.out.println(p.getProperty("jdbc.driver"));
			System.out.println("파일 read 가능");
		}catch (Exception e) {
			System.out.println("IO문제 발생");
			e.printStackTrace();
		}
	}
	@Test
    public void Connect() {
        // MySQL 연결 정보 설정
        String url = p.getProperty("jdbc.url"); // 서버 URL (본인의 호스트 및 포트에 맞게 수정)
        String user = p.getProperty("jdbc.id"); //  사용자 이름
        String password = p.getProperty("jdbc.pw"); // 비밀번호

        try {
            // 데이터베이스 연결
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("데이터베이스에 성공적으로 연결되었습니다.");

            // 연결 해제
            conn.close();
            System.out.println("연결이 성공적으로 해제되었습니다.");

        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        System.out.println("예외 처리로 인해 정상, 비정상 이어도 실행되는 영역");
        
    }

}
