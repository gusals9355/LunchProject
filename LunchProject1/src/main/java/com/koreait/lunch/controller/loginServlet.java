package com.koreait.lunch.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.koreait.lunch.model.ojmDAO;
import com.koreait.lunch.model.member.MemberVO;

@WebServlet("/login")
public class loginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyUtils.getNav(request);
		MyUtils.openJSP("login", request, response);
		
	}
	@de
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String str = "nav.jsp";
		session.setAttribute("str", str);
		String msg = "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.";
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPw(pw);
		
		String hashedPw = ojmDAO.getHashedPw(vo);
		if(hashedPw.equals("") || !BCrypt.checkpw(pw, hashedPw)) { //비번 틀릴경우
			request.setAttribute("msg", msg);
			doGet(request, response);
			return;
		}
		if(BCrypt.checkpw(pw, hashedPw)) { // 로그인 성공
			str = "loginNav.jsp";
			
			//로그인 성공 시 포인트를 획득함
			//로그인 포인트는 하루에 한번만 받을 수 있음
			ojmDAO.log(vo.getId(),"로그인"); //로그인시 로그들을 db에 저장
			ojmDAO.logCheck(vo.getId()); //하루 최초 로그인 시 출석체크가 되는 메소드
			
			MemberVO userInfo = ojmDAO.getUserInfo(vo);
			session.setAttribute("str", str);
			session.setAttribute("userInfo", userInfo);
			System.out.println(userInfo.getId());
			
			response.sendRedirect("/ojm");
		}
	}

}
