package com.bitacademy.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;


public class EncodingFilter extends HttpFilter implements Filter {
	private String encoding = "utf-8";
	
	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/* request처리 */
		request.setCharacterEncoding(encoding);
		
		chain.doFilter(request, response);
		
		/* response처리 */
		
	}
	
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
