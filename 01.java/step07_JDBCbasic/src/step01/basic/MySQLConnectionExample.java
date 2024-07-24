package step01.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionExample {
   public static void main(String[] args) {
       // MySQL 연결 정보 설정
       String url = "jdbc:mysql://localhost:3306/fisa"; // MySQL 서버 URL
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

