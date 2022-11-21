package com.bitacademy.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.mysite.repository.BoardRepository;
import com.bitacademy.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	BoardRepository boardRepository;
	
	public List<BoardVo> findContentsList() {
		// 리스트 가져오기
		boardRepository.findAll();
		
		// view의 페이징을 처리하기 위한 데이터 값 계산
		int beginPage = 0;
		int endPage = 0;
		
		return boardRepository.findAll();
	}
	
	public void addContents(BoardVo vo) {
		boardRepository.write(vo);
	}
	
	public BoardVo findContents(Long no) {
		return boardRepository.findByNo(no);
	}
	
	public BoardVo findContents(Long no, Long userNo) {
		return null;
	}
	
	public void updateContents(BoardVo vo) {
		boardRepository.update(vo);
	}
	
	public void deleteContents(Long no, Long userNo) {
		boardRepository.deleteByNoAndUserno(no, userNo);
	}
	
	
}

//public Map<String, Object> findContentsList(int currentPage) {
//	// 리스트 가져오기
//	boardRepository.findAll();
//	
//	// view의 페이징을 처리하기 위한 데이터 값 계산
//	int beginPage = 0;
//	int endPage = 0;
//	
//	return null;
//}

