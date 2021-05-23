package com.koreait.lunch.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.lunch.model.dao.MemberDAO;

@WebServlet("/regi_manager")
public class ManagerResisterServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MyUtils.getLoginUser(request) == null) {
			response.sendRedirect("/user/login");
			return;
		}
		MyUtils.openJSP("오늘 점심 뭐먹지?", "register_manager", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MemberDAO.regiManager(request.getParameter("code"))) { //코드 입력성공
			MemberDAO.modManager(MyUtils.getLoginUserID(request));
			response.sendRedirect("/ojm");
			return;
		}
		request.setAttribute("msg", "유효하지 않은 코드입니다");
		doGet(request, response);
		
	}

}
