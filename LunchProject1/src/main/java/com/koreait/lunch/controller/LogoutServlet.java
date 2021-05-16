package com.koreait.lunch.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.lunch.member.model.MemberDAO;
import com.koreait.lunch.member.model.MemberVO;
import com.koreait.lunch.model.DBUtils;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO.log(MyUtils.getLoginUserID(request), "로그아웃");
		MyUtils.logOutSession(request.getSession());
		response.sendRedirect("/ojm");
	}

}
