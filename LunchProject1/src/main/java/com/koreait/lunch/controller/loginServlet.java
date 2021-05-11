package com.koreait.lunch.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.koreait.lunch.model.MemberVO;
import com.koreait.lunch.model.ojmDAO;

@WebServlet("/login")
public class loginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println("성공");
		MyUtils.openJSP("/login", request, response);
		
	}

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
		if(hashedPw.equals("") || !BCrypt.checkpw(pw, hashedPw)) { //
			request.setAttribute("msg", msg);
			doGet(request, response);
			return;
		}
		if(BCrypt.checkpw(pw, hashedPw)) { // 암호화 되지 않는 값과 암호화된 db의 값과 비교한다. pw가 일치하면
			str = "nav2.jsp";
			session.setAttribute("str", str);
			session.setAttribute("userID", vo.getId());
			String userID = (String) session.getAttribute("userID");
			//로그인 성공 시 포인트를 획득함
			//로그인 포인트는 하루에 한번만 받을 수 있음
			ojmDAO.log(userID,"로그인"); //로그인시 로그들을 db에 저장
			ojmDAO.logCheck(userID); //하루에 첫 로그인 시 출석체크가 되는 메소드
			response.sendRedirect("/ojm");
		}
	}

}
