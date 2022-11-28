package com.bitacademy.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;
	
	// 게시판 목록
	public List<BoardVo> findAll() {
		List<BoardVo> result = sqlSession.selectList("board.findAll");
		
		return result;
	}

	// 글 쓰기
	public void write(BoardVo vo) {
		sqlSession.insert("board.write", vo);
	}

	// deleteByNoAndUserno
	public void deleteByNoAndUserno(Long no, Long userno) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("no", no);
		map.put("userNo", userno);
		
		sqlSession.delete("board.deleteByNoAndUserno" , map);
	}

	// 글 수정할 정보 불러오기
	public BoardVo findByNo(Long no) {
		BoardVo result = sqlSession.selectOne("board.findByNo", no);

		return result;
	}

	// 글 수정
	public void update(BoardVo vo) {
		sqlSession.update("board.update", vo);

	}

	// 조회수 올리기
	public void addHit(BoardVo vo) {
		sqlSession.update("board.addHit", vo);
	}
	
	// 답글
	public void reply(BoardVo vo) {
		sqlSession.update("board.reply", vo);
	}
}
