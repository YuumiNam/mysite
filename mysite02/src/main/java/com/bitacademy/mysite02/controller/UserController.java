package com.bitacademy.mysite02.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitacademy.mysite02.dao.UserDao;
import com.bitacademy.mysite02.vo.UserVo;


public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("a");
		
		if("joinform".equals(action)) {
			request.getRequestDispatcher("/WEB-INF/views/user/joinform.jsp").forward(request, response);
		} else if("join".equals(action)) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			
			UserVo vo = new UserVo();
			vo.setName(name);
			vo.setEmail(email);
			vo.setPassword(password);
			vo.setGender(gender);
			
			new UserDao().insert(vo);
			response.sendRedirect(request.getContextPath() + "/user?a=joinsuccess");
			
		} else if("joinsuccess".equals(action)) {
			request.getRequestDispatcher("/WEB-INF/views/user/joinsuccess.jsp").forward(request, response);
		} else if("loginform".equals(action)) {
			request.getRequestDispatcher("./WEB-INF/views/user/loginform.jsp").forward(request, response);
		} else if("updateform".equals(action)) {
			// Access Control
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authUser"); // updateform을 실행시킨다는것은 로그인이 되어있는상태라고 가정하에 getAttribute값에 authUser를 찾는다.
			
			if(authUser == null) {
				response.sendRedirect(request.getContextPath()+"/user?a=loginform");
				return;
			}
			////
			
			UserVo vo = new UserDao().findByNo(authUser.getNo());
			request.setAttribute("userVo", vo);
			
			request.getRequestDispatcher("/WEB-INF/views/user/updateform.jsp").forward(request, response);
		} else if("update".equals(action)) {
			// Access Control
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authUser"); //getAttribute는 set을 안해주면 빈 객체 null을 리턴 지금은 로그인이 되어있는상태라고 가정
						
			if(authUser == null) {
				response.sendRedirect(request.getContextPath()+"/user?a=loginform");
				return;
			}
			////
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			
			 UserVo vo= new UserVo();
			 vo.setNo(authUser.getNo());
			 vo.setName(name);
			 vo.setPassword(password);
			 vo.setGender(gender);
			 
			 // update db
			 new UserDao().update(vo);
			 
			 // update session
			 authUser.setName(name);
			 
			 response.sendRedirect(request.getContextPath() + "/user?a=updateform");
			
		} else if("login".equals(action)) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			UserVo authUser = new UserDao().findByEmailAndPassword(email, password);
			
			if(authUser == null) {
				/* 인증실패 */
				request.setAttribute("email", email);
				request.getRequestDispatcher("./WEB-INF/views/user/loginform.jsp").forward(request, response);
				return;
			}
			
			/* 로그인처리 */
			HttpSession session = request.getSession(true); // 입력한 authUser(로그인정보)를 넣은 요청을 객체로 생성된 session에다가 넣어줌
			session.setAttribute("authUser", authUser);		// jssessionid가 있는 이유는 요청을 분류해야할 필요가 있기 때문(user1이 보낸요청과 user2가 보낸요청을 분류해야함)
			
			response.sendRedirect(request.getContextPath());
		} else if("logout".equals(action)) {
			HttpSession session = request.getSession();
			if(session == null) {
				response.sendRedirect(request.getContextPath());
				return; // 확실히 코드를 끝내줘야함
			}
			
			session.removeAttribute("authUser"); // 로그인했던 jsessionid를 삭제해줌
			session.invalidate(); // 새로운 jsessionid 발급해줌
			
			response.sendRedirect(request.getContextPath());
		} else {
			response.sendRedirect(request.getContextPath()); // "user?a= "뒤에 이상한 값을 넣어도 메인화면으로 복귀할 수 있도록
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
