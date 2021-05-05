package com.koreait.lunch.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.lunch.model.MemberBean;
import com.koreait.lunch.model.MemberDAO;

@WebServlet("/login")
public class loginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String msg = "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.";
		
		MemberBean bean = new MemberBean();
		bean.setId(id);
		bean.setPw(pw);
		
		if(MemberDAO.tryLogin(bean)) { //로그인에 성공
			response.sendRedirect("/ojm");
		} else {					  //로그인 실패
			request.setAttribute("msg", msg);
			doGet(request, response);
		}
		
	}

}
