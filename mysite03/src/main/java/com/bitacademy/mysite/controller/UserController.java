package com.bitacademy.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session, UserVo userVo, Model model) {
		UserVo authUser = userService.findUser(userVo);
		if(authUser == null) {
			model.addAttribute("email", userVo.getEmail());
			return "user/login";
		}
		
		session.setAttribute("authUser", authUser);
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// Access Control (사용자가 로그인도 안했는데 logout창으로 들어왔을때를 대비)
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		//////////////////
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(HttpSession session, Model model) {
		// Access Control (사용자가 로그인도 안했는데 logout창으로 들어왔을때를 대비)
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		//////////////////
			
		UserVo userVo = userService.findUser(authUser.getNo());
		model.addAttribute("userVo",userVo);
		
		return "user/update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpSession session, UserVo userVo) {
		// Access Control (사용자가 로그인도 안했는데 logout창으로 들어왔을때를 대비)
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		//////////////////
		
		userVo.setNo(authUser.getNo()); // userVo에는 no가 설정이 안되어있기때문에 authUser에서 no를 갖고옴
										// param에서 no를 갖고오려고하지말기! 해킹의 위험이있음
		userService.updateUser(userVo);
		
		authUser.setName(userVo.getName());
		return "redirect:/user/update";
	}
}
