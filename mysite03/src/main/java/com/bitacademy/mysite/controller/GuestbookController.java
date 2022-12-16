package com.bitacademy.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitacademy.mysite.service.GuestbookService;
import com.bitacademy.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	@Autowired
	GuestbookService guestbookService;
	
	@RequestMapping("")
	public String getContentsList(Model model) {
		model.addAttribute("list", guestbookService.getContentsList());
		
		return "guestbook/list";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addContents(GuestbookVo vo) {
		guestbookService.addContents(vo);
		
		return "redirect:/guestbook";
	}
	
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String deleteContents(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no", no);
		
		return "guestbook/delete";
	}
	
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.POST)
	public String deleteContents(@PathVariable("no") Long no, @RequestParam(value = "password", required = true, defaultValue = "") String password) {
		guestbookService.deleteContents(no, password);
		
		return "redirect:/guestbook";
	}
}
