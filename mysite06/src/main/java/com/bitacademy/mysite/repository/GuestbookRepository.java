package com.bitacademy.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
	@Autowired
	private SqlSession sqlSession;
	
	// 글 목록
	public List<GuestbookVo> findAll() {
		List<GuestbookVo> result = sqlSession.selectList("guestbook.findAll");
		
		return result;
	}
	
	
	// 글 작성
	public Boolean insert(GuestbookVo vo) {
		int count = sqlSession.insert("guestbook.insert", vo);
		return count == 1;
	}
	
	// 글 삭제
	public Boolean deleteByNoAndPassword(Long no, String password) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("no", no);
		map.put("password", password);
		
		int count = sqlSession.delete("guestbook.deleteByNoAndPassword", map);
		
		return count == 1;
	}
}
