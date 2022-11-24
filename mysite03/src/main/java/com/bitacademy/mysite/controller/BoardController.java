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
		
		System.out.println("list === " + boardService.findContentsList());
		return "board/list";
	}
	
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String addContents(Long no, Model model) {
		model.addAttribute("userNo", no);
		
		return "board/write";
	}
	
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String addContents(BoardVo boardVo) {
		boardService.addContents(boardVo);
		
		return "redirect:/board";
	}
	
	@Auth
	@RequestMapping("/delete/{no}/{userno}")
	public String deleteContents(@PathVariable("no") Long no, @PathVariable("userno")Long userNo) {
		boardService.deleteContents(no, userNo);
		
		// System.out.println(no + ":" + userNo);
		return "redirect:/board";
	}
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String view(Long no, Model model, BoardVo vo) {
		model.addAttribute("BoardVo", boardService.findContents(no));
		
		System.out.println(vo);
		boardService.addHit(vo);
		
		return "board/view";
	}
	
	@Auth
	@RequestMapping(value="/reply/{no}/{userno}", method=RequestMethod.GET)
	public String reply(@PathVariable("no") Long no, @PathVariable("userno") Long userNo, Model model) {
	
		return "board/reply";
	}
	
	@Auth
	@RequestMapping(value="/reply/{no}/{userno}", method=RequestMethod.POST)
	public String reply(@PathVariable("no") Long no, @PathVariable("userno") Long userNo, BoardVo vo) {
		vo.setUserNo(userNo);
		boardService.replyContents(vo);
		
		System.out.println(vo);
		return "redirect:/board";
	}
	
	@Auth
	@RequestMapping(value="/modify/{no}", method=RequestMethod.GET)
	public String updateContents(@PathVariable("no") Long no) {
		
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