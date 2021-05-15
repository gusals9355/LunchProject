package com.koreait.lunch.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.koreait.lunch.model.ojmDAO;
import com.koreait.lunch.model.member.MemberVO;

@WebServlet("/join")
public class JoinServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MyUtils.openJSP("join", request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String pw2 = request.getParameter("pw2");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String msg = "이미 존재하는 아이디입니다";
		
		if(!pw.equals(pw2)) { //패스워드 일치 여부
			doGet(request, response);
			return;
		}
		String hashedPw = BCrypt.hashpw(pw, BCrypt.gensalt());
		MemberVO bean = new MemberVO();
		bean.setName(name);
		bean.setId(id);
		bean.setEmail(email);
		bean.setPw(hashedPw);
		bean.setGender(gender);
		
		if(ojmDAO.insertMember(bean)) { //아이디 중복 검사
			request.setAttribute("msg", msg);
			doGet(request, response);
			return;
		}
		response.sendRedirect("/ojm");
		
	}

}
