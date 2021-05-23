package com.koreait.lunch.my.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.koreait.lunch.controller.MyUtils;
import com.koreait.lunch.model.dao.MemberDAO;

@WebServlet("/user/edit/pw")
public class EditPwServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MyUtils.openJSP("내정보 | 오늘 점심 뭐먹지?", "my/editPw", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pw = request.getParameter("pw");
		String hashPw =BCrypt.hashpw(pw, BCrypt.gensalt());
		String resId = request.getParameter("id");
		if(resId.equals("")) {
			MemberDAO.editPw(MyUtils.getLoginUserID(request), hashPw);
			MemberDAO.log(MyUtils.getLoginUserID(request), "로그아웃");
			MyUtils.logOutSession(request.getSession());
		} else {
			MemberDAO.editPw(resId, hashPw);
		}
		response.sendRedirect("/user/login");
	}
}