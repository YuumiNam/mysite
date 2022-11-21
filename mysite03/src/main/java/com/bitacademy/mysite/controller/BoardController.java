package com.bitacademy.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitacademy.mysite.security.Auth;
import com.bitacademy.mysite.service.BoardService;
import com.bitacademy.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@RequestMapping
	public String list(Model model) {
		model.addAttribute("list", boardService.findContentsList());
		
		return "board/list";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String addContents(Long no, Model model) {
		model.addAttribute("userNo", no);
		
		return "board/write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String addContents(BoardVo boardVo) {
		boardService.addContents(boardVo);
		
		return "redirect:/board";
	}
	
	@RequestMapping(value="/delete/{userno}", method=RequestMethod.POST)
	public String deleteContents(@PathVariable("userno") Long userNo, Long no) {
		boardService.deleteContents(no, userNo);
		
		return "redirect:/board";
	}
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String view(Long no, Model model) {
		model.addAttribute("BoardVo", boardService.findContents(no));
		
		return "board/view";
	}
	
	@RequestMapping(value="/reply/{no}", method=RequestMethod.GET)
	public String reply(@PathVariable("no") Long no, Model model) {
		
		return "board/reply";
	}
	
	@RequestMapping(value="/reply/{no}", method=RequestMethod.POST)
	public String reply(@PathVariable("no") Long no, BoardVo vo) {
		boardService.updateContents(vo);
		
		return "redirect:/board";
	}
	
	@Auth
	@RequestMapping(value="/modify/{no}", method=RequestMethod.GET)
	public String updateContents(@PathVariable("no") Long no, Model model) {
		
		return "board/modify";
	}
	
	@Auth
	@RequestMapping(value="/modify/{no}", method=RequestMethod.POST)
	public String updateContents(@PathVariable("no") Long no, BoardVo vo) {
		boardService.updateContents(vo);
		
		return "redirect:/board";
	}
	
}


//@RequestMapping
//public String list(Model model) {
//	model.addAttribute("list", boardService.findContentsList(0));
//	
//	return "/board/list";
//}