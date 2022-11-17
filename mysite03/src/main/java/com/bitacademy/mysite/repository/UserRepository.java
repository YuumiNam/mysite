package com.bitacademy.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.mysite.exception.UserRepositoryException;
import com.bitacademy.mysite.vo.UserVo;

@Repository
public class UserRepository {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;
	
	// 로그인
	public UserVo findByEmailAndPassword(String email, String password) throws UserRepositoryException{
		Map<String, Object> map = new HashMap<>();
		map.put("email", email);
		map.put("password", password);
		
		UserVo result = sqlSession.selectOne("user.findByEmailAndPassword", map);
		
		return result;
	}
	
	// update할 정보 불러오기
	public UserVo findByNo(Long no) {
		UserVo result = sqlSession.selectOne("user.findByNo", no);
		
		return result;
	}
	
	
	// 회원가입
	public Boolean insert(UserVo vo) {
		int count = sqlSession.insert("user.insert", vo);

		return count == 1;
	}

	
	// 회원정보수정
	public boolean update(UserVo vo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			
			conn = dataSource.getConnection();
			
			
			//3. statement 준비
			if("".equals(vo.getPassword())) {
			String sql = "update user set name = ?, gender = ? where no = ?"; // 쿼리
			
			pstmt = conn.prepareStatement(sql); // row값
	
			//4. Binding
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getGender());
			pstmt.setLong(3, vo.getNo());
			} else {
				String sql = "update user set name = ?, password = ?, gender = ? where no = ?";
				
				pstmt = conn.prepareStatement(sql); // row값
				
				//4. Binding
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getPassword());
				pstmt.setString(3, vo.getGender());
				pstmt.setLong(4, vo.getNo());
			}
			
			//4. 쿼리 실행
			int count = pstmt.executeUpdate(); // 
			
			//5. result 처리
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

}
