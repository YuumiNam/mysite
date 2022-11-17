package com.bitacademy.mysite.repository;

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
		int count = sqlSession.update("user.update", vo);
		
		return count == 1;
	}
}
