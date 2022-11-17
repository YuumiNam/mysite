package com.bitacademy.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitacademy.mysite.service.BoardService;
import com.bitacademy.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@RequestMapping("")
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
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String view(Long no, Model model) {
		model.addAttribute("BoardVo", boardService.findContents(no));
		
		return "board/view";
	}
	
	@RequestMapping(value="/modify/{no}", method=RequestMethod.GET)
	public String updateContents(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no", no);
		return "board/modify";
	}
	
}


//@RequestMapping
//public String list(Model model) {
//	model.addAttribute("list", boardService.findContentsList(0));
//	
//	return "/board/list";
//}