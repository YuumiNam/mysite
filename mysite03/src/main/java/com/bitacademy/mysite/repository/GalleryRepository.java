package com.bitacademy.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.mysite.vo.GalleryVo;

@Repository
public class GalleryRepository {
	@Autowired
	private SqlSession sqlSession;
	
	// 목록 보기
	public List<GalleryVo> findAll() {
		List<GalleryVo> result = sqlSession.selectList("gallery.findAll");
		
		return result;
	}
	
	// 업로드할 파일 저장
	public boolean insert(GalleryVo vo) {
		int count = sqlSession.insert("gallery.insert", vo);
		
		return count == 1;
	}
	
	
	// 파일 삭제
	public boolean deleteByNo(Long no) {
		int count = sqlSession.delete("gallery.delete", no);
		
		return count == 1;
	}
}
