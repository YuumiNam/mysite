package com.bitacademy.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
	@Autowired
	private SqlSession sqlSession;
	
	// findAll()
	public List<GuestbookVo> findAll() {
		List<GuestbookVo> result = sqlSession.selectList("guestbook.findAll");
	
		return result;
	}
	
	
	// insert()
	public Boolean insert(GuestbookVo vo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1, 2
			conn = getConnection();
			
			
			// 3. statement 준비
			String sql = "insert into guestbook values(null, ?, ?, ?,now())"; // 쿼리
			
			
			pstmt = conn.prepareStatement(sql); // row값
			
			
			// 4. binding
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContents());
			
			
			// 5. 실행
			int count = pstmt.executeUpdate(); // executeUpdate()는 insert등은 반영된 건수를 반환, create&drop은 -1을 반환
			
			//5. 결과처리
			result = count == 1; // count == 1 << true
			
			
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	//delete by password
	public Boolean deleteByNoAndPassword(Long no, String password) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1, 2
			conn = getConnection();
			
			
			// 3. statement 준비
			String sql = 
					"delete" +  
					" from guestbook" +
				    " where no = ? and password = ?"; // 쿼리
			
			pstmt = conn.prepareStatement(sql); // row값
	
			// 4. Binding
			pstmt.setLong(1, no);
			pstmt.setString(2, password);
			
			// 5. SQL 실행
			int count = pstmt.executeUpdate(); // 
			
			// 6. 결과처리
			result = count == 1;
			
			
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		return result;
		
	}
	
	// 예외처리
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			//1. JDBC Driver Class Loading
			Class.forName("org.mariadb.jdbc.Driver");
			
			
			//2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
			
		return conn;
	}
}
