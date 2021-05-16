package com.koreait.lunch.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.koreait.lunch.member.model.MemberDAO;
import com.koreait.lunch.member.model.MemberVO;

@WebServlet("/ojm/login")
public class loginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyUtils.getNav(request);
		MyUtils.openJSP("login", request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String msg = "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.";
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPw(pw);
		
		String hashedPw = MemberDAO.getHashedPw(vo);
		if(hashedPw.equals("") || !BCrypt.checkpw(pw, hashedPw)) { //비번 틀릴경우
			request.setAttribute("msg", msg);
			doGet(request, response);
			return;
		}
		if(BCrypt.checkpw(pw, hashedPw)) { // 로그인 성공
			//로그인 성공 시 포인트를 획득함
			//로그인 포인트는 하루에 한번만 받을 수 있음
			MemberDAO.log(vo.getId(),"로그인"); //로그인시 로그들을 db에 저장
			MemberDAO.logCheck(vo.getId()); //하루 최초 로그인 시 출석체크가 되는 메소드
			session.setAttribute("userInfo", MemberDAO.getUserInfo(vo));
			MyUtils.getNav(request);
			response.sendRedirect("/ojm");
		}
	}

}
