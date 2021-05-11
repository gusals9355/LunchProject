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

import com.koreait.lunch.model.MemberVO;
import com.koreait.lunch.model.ojmDAO;

@WebServlet("/login")
public class loginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//commit
		MyUtils.openJSP("login", request, response);
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
		
		if(ojmDAO.tryLogin(vo)) { //로그인에 성공
			str = "nav2.jsp";
			session.setAttribute("str", str);
			session.setAttribute("userID", vo.getId());
			String userID = (String) session.getAttribute("userID");
			//로그인 성공 시 포인트를 획득함
			//로그인 포인트는 하루에 한번만 받을 수 있음
			String date = ojmDAO.log(userID,"로그인"); //로그인시 로그들을 db에 저장
			SimpleDateFormat sdf = new SimpleDateFormat("dd");
			Date nowDate = new Date();
			String date2 = sdf.format(nowDate);
			if(!date.equals(date2)) {
				
			}
			
			response.sendRedirect("/ojm");
		} else {					  //로그인 실패
			request.setAttribute("msg", msg);
			doGet(request, response);
		}
		
	}

}
