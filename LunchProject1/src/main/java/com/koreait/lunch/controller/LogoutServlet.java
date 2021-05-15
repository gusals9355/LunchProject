package com.koreait.lunch.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.lunch.member.model.MemberVO;
import com.koreait.lunch.model.OJMDAO;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO userInfo = (MemberVO) session.getAttribute("userInfo");
		System.out.println(userInfo.getId());
		OJMDAO.log(userInfo.getId(), "로그아웃");
		session.invalidate();
		response.sendRedirect("/ojm");
	}

}
