package com.koreait.lunch.my.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.lunch.controller.MyUtils;
import com.koreait.lunch.model.dao.MemberDAO;


@WebServlet("/user/edit/nickname")
public class EditNickNameServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MyUtils.getLoginUser(request) == null) {
			response.sendRedirect("/ojm/login");
			return;
		}
		MyUtils.openJSP("오늘 점심 뭐먹지?","my/editNickName", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDAO.editNick(request.getParameter("nickname"), MyUtils.getLoginUserID(request));
		
		session.setAttribute("userInfo", MemberDAO.getUserInfo(MyUtils.getLoginUserID(request)));
		response.sendRedirect("/ojm");
	}

}
