package com.koreait.lunch.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.koreait.lunch.model.dao.MemberDAO;
import com.koreait.lunch.model.vo.MemberVO;

@WebServlet("/user/join")
public class JoinServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyUtils.openJSP("회원가입 | 오늘 점심 뭐먹지?","join", request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pw = request.getParameter("pw");
		String pw2 = request.getParameter("pw2");
		if(!pw.equals(pw2)) { //패스워드 일치 여부
			doGet(request, response);
			return;
		}
		String hashedPw = BCrypt.hashpw(pw, BCrypt.gensalt());
		MemberVO bean = new MemberVO();
		bean.setName(request.getParameter("name"));
		bean.setId(request.getParameter("id"));
		bean.setEmail(request.getParameter("email"));
		bean.setPw(hashedPw);
		bean.setGender(request.getParameter("gender"));
		
		if(MemberDAO.insertMember(bean)) { //아이디 중복 검사
			request.setAttribute("msg", "이미 존재하는 아이디입니다");
			doGet(request, response);
			return;
		}
		response.sendRedirect("/ojm");
		
	}

}
