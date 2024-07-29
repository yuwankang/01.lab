/* DAO 클래스
 * - dept01 table과 매핑되는 db 연동 클래스
 * 
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

public class Depto01DAO {

	// 모든 부서 정보 검색
	/*
	 * PreparedStatement API특징 - db에 요청이 되면 동작방식 문장 받고 -> 문법 검증 -> 실행 매 순간 요청시 마다:
	 * 검증 작업 수행 - Statement 최초 받고 매 용청시 재사용 - PreparedStatement(문법 검증 생략)
	 * 
	 * - JPA & Mybatis라는 db연동 framework등의 기본 API로 내부적으로 사용
	 */


	
	
	@Test
	public void juniTest() {
		try {
//			System.out.println(getDept01ALL());
			System.out.println(getDept01One(10));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Dept01DTO> getDept01ALL() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<Dept01DTO> datas = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from dept01");
			rs = pstmt.executeQuery();
			
			datas = new ArrayList<>();
			while (rs.next()) { // 반복 획수가 불명확 할때 주로 사용되는 반복문
				datas.add(new Dept01DTO(rs.getInt(1), rs.getString("dname"), rs.getString(3)));
			}
		} finally {
			DBUtil.close(con, pstmt, rs);
		}
		return datas;
	}

	// 하나의 부서 번호로 검색
	/*
	 * 필요 데이터: deptno 반환값: DeptDTO
	 * client -> view: 데이터 입력 -> Controller: 입력 여부 검증
	 * -> controller: 입력 여부 검증 -> 입력이 되었을 경우만 실행되게 작업
	 */
	public Dept01DTO getDept01One(int deptno) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Dept01DTO datas = null;
		
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from dept01 WHERE deptno = ?");
			pstmt.setInt(1, deptno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 반복 획수가 불명확 할때 주로 사용되는 반복문
				datas=new Dept01DTO(rs.getInt(1), rs.getString("dname"), rs.getString(3));
			}
		
		} finally {
			DBUtil.close(con, pstmt, rs);
		}
		return datas;
	}
	
	
	

	// 새로운 부서 정보 저장
	public boolean saveDept01(Dept01DTO oneDept) {
		return false;
	}
}