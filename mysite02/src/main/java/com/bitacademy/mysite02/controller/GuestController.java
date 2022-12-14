package com.bitacademy.mysite02.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitacademy.mysite02.dao.GuestbookDao;
import com.bitacademy.mysite02.vo.GuestbookVo;

public class GuestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("a");
		
		if("insert".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String contents = request.getParameter("contents");
			
			GuestbookVo vo = new GuestbookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContents(contents);
			
			new GuestbookDao().insert(vo);
			
			response.sendRedirect(request.getContextPath() + "/guestbook");

//			System.out.println(request.getContextPath() + "/guestbook");
//			System.out.println("/WEB-INF/views/guestbook");
			
		} else if("deleteform".equals(action)) {
			request.getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp").forward(request, response);
		} else if("delete".equals(action)) {
			String sno = request.getParameter("no");
			Long no = Long.parseLong(sno);
			String password = request.getParameter("password");
			
			new GuestbookDao().deleteByNoAndPassword(no,password);

			response.sendRedirect("./guestbook");
		} else {
			List<GuestbookVo> list = new GuestbookDao().findAll();
			
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
