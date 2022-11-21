package com.bitacademy.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {
	// Spring 5버전 이상부터는 HandlerIntercepor의 추상메소드를 다 적을필요는 없다 (보통은 preHandle만 쓰고 postHandle, afterCompletion은 안쓰기때문)

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
