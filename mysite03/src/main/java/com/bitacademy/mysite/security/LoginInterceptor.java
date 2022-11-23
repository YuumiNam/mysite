package com.bitacademy.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.bitacademy.mysite.service.UserService;
import com.bitacademy.mysite.vo.UserVo;

//Spring 5버전 이상부터는 HandlerIntercepor의 추상메소드를 다 적을필요는 없다 (보통은 preHandle만 쓰고 postHandle, afterCompletion은 안쓰기때문)
public class LoginInterceptor implements HandlerInterceptor {
	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		// UserService userService = new UserService(); 이렇게 쓰면 안됨!! 새로운 객체를 생성해주는게 아닌 Autowired로 된 Service를 가져와야함
		UserVo authUser = userService.findUser(email, password);
		
		if(authUser == null) {
			request.setAttribute("email", email);
			request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
			return false;
		}
		
		System.out.println(authUser);
		
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		
		response.sendRedirect(request.getContextPath());
		
		return false;
	}
}
