package com.koreait.lunch.my.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.koreait.lunch.controller.MyUtils;
import com.koreait.lunch.member.model.MemberDAO;

@WebServlet("/user/pwCheck")
public class PwCheckServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyUtils.openJSP("my/pwCheck", request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(BCrypt.checkpw(request.getParameter("pw"), MemberDAO.getHashedPw(request.getParameter("id")))) {
			response.sendRedirect("/mypage");
			return;
		}
		request.setAttribute("msg", "비밀번호를 다시 확인해주세요.");
		doGet(request, response);
	}

}
