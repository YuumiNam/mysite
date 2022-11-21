package com.bitacademy.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.bitacademy.mysite.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 0. handler 종류 확인 (이미지파일 같은 것은 인증이 필요없음)
		if(handler instanceof HandlerMethod == false) {
			return true;
		}
		
		// 1. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		// 2. Handler Method에 @Auth를 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		// 3. Handler Method에 @Auth가 없다면
		if(auth == null) {
			return true;
		}
		
		// 4. @Auth가 붙어있기때문에 인증(Authentification) 여부 확인
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		// @Auth도 붙어있고 인증도 되어있고 -> 접근 가능!!!
		return true;
	}
	
}
