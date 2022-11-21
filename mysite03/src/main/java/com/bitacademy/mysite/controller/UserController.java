package com.bitacademy.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitacademy.mysite.security.Auth;
import com.bitacademy.mysite.security.AuthUser;
import com.bitacademy.mysite.service.UserService;
import com.bitacademy.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(UserVo userVo) {
		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@Auth // << login한 사람만 쓸수있게하는 나만의 annotation
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Model model, @AuthUser UserVo authUser) { // << authUser라는 Parameter를 쓰기위한 나만의 annotation
		
		UserVo userVo = userService.findUser(authUser.getNo());
		model.addAttribute("userVo",userVo);
		
		return "user/update";
	}
	
	@Auth // << login한 사람만 쓸수있게하는 나만의 annotation
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(UserVo userVo, @AuthUser UserVo authUser) { // << authUser라는 Parameter를 쓰기위한 나만의 annotation
		
		userVo.setNo(authUser.getNo()); // userVo에는 no가 설정이 안되어있기때문에 authUser에서 no를 갖고옴
										// param에서 no를 갖고오려고하지말기! 해킹의 위험이있음
		userService.updateUser(userVo);
		
		authUser.setName(userVo.getName());
		return "redirect:/user/update";
	}
}
