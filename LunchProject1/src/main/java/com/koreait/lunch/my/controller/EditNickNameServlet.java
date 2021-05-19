package com.koreait.lunch.my.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.lunch.controller.MyUtils;
import com.koreait.lunch.member.model.MemberDAO;
import com.koreait.lunch.member.model.MemberVO;


@WebServlet("/editNickName")
public class EditNickNameServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyUtils.openJSP("my/editNickName", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDAO.editNick(request.getParameter("nickname"), MyUtils.getLoginUserID(request));
		
		session.setAttribute("userInfo", MemberDAO.getUserInfo(MyUtils.getLoginUserID(request)));
		response.sendRedirect("/ojm");
	}

}
