/* DAO 클래스
 * 	- dept01 table과 매핑되는 db 연동 클래스
 * PreparedStatement API 특징
 * - DB에 요청이 되면 동적 방식
 * 	- 문장 받고 -> 문법 검증 -> 실행
 * 	- 매순간 요청시 마다 : 검증 작업 수행 - Statement
 * 	- 최초 받고 매 요청시 재사용 : PreparedStatement(문법 검증 생략)
 * 
 * - JPA & Mybatis라는 db연동 framework들의 기본 API로 내부적으로 사용
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import db.util.DBUtil;
import model.domain.Dept01DTO;

public class Dept01DAO {
	//모든 부서 정보 검색
	@Test
	public void junitTest() {
		try {
			System.out.println(getDept01All());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Dept01DTO> getDept01All() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Dept01DTO> datas = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from dept01");
			rs = pstmt.executeQuery();
			
			datas = new ArrayList<>();
			
			while(rs.next()) {//반복 횟수가 불 명확할 때 사용하는 반복문
				datas.add(new Dept01DTO(rs.getInt(1),
										rs.getString("dname"), 
										rs.getString(3)) );
			}
		}finally {
			DBUtil.close(con, pstmt, rs);
		}
		
		return datas;
	}
	//하나의 부서 번호로 검색
	/* 필요 데이터 : deptno
	 * 반환값은 : DeptDTO
	 */
	public Dept01DTO getDept01One(int deptno) {
		return null;
	}
	
	//새로운 부서 정보 저장
	public boolean saveDept01(Dept01DTO oneDept) {
		return false;
	}
}
