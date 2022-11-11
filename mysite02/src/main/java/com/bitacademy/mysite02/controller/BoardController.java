package com.bitacademy.mysite02.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitacademy.mysite02.dao.BoardDao;
import com.bitacademy.mysite02.dao.GuestbookDao;
import com.bitacademy.mysite02.vo.BoardVo;

public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("a");
		
		if("writeform".equals(action)) {
			request.getRequestDispatcher("/WEB-INF/views/board/writeform.jsp").forward(request, response);
		} else if("write".equals(action)) {
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			String suserno = request.getParameter("no");
			Long userno = Long.parseLong(suserno);
			
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setContents(contents);
			vo.setUserNo(userno);
			
			new BoardDao().write(vo);
			
			response.sendRedirect(request.getContextPath() + "/board");
		} else if("delete".equals(action)) {
			String sno = request.getParameter("no");
			Long no = Long.parseLong(sno);
			
			new BoardDao().deleteByNo(no);

			response.sendRedirect("./board");
		} else {
			List<BoardVo> list = new BoardDao().findAll();
			
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
