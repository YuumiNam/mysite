package com.bitacademy.mysite02.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bitacademy.mysite02.vo.UserVo;

public class UserDao {
	public UserVo findByEmailAndPassword(String email, String password) {
		UserVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			// 1, 2
			conn = getConnection();

			// 3. statement 준비
			String sql = "select no, name from user where email = ? and password = ?"; // 쿼리

			pstmt = conn.prepareStatement(sql); // row값

			// 4. binding
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			// 5. 실행
			rs = pstmt.executeQuery();
			
			
			// 6. 결과처리
			if(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				result = new UserVo();
				result.setNo(no);
				result.setName(name);
			}


		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	
	// 회원가입
	public Boolean insert(UserVo vo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1, 2
			conn = getConnection();

			// 3. statement 준비
			String sql = "insert into user values(null, ?, ?, ?, ?, now())"; // 쿼리

			pstmt = conn.prepareStatement(sql); // row값

			// 4. binding
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());

			// 5. 실행
			int count = pstmt.executeUpdate(); // executeUpdate()는 insert등은 반영된 건수를 반환, create&drop은 -1을 반환

			// 5. 결과처리
			result = count == 1; // count == 1 << true

		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			// 1. JDBC Driver Class Loading
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}

}
