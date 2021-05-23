package com.koreait.lunch.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.lunch.model.dao.MemberDAO;
import com.koreait.lunch.model.vo.MemberVO;

@WebServlet("/user/findPw")
public class FindPwServelt extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MyUtils.openJSP("오늘 점심 뭐먹지?", "findPw", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberVO vo = new MemberVO();
		vo.setName(request.getParameter("name"));
		vo.setEmail(request.getParameter("email"));
		vo.setId(request.getParameter("id"));
		if(MemberDAO.findPw(vo)) {
			response.sendRedirect("/user/edit/pw?id="+vo.getId());
			return;
		}
		request.setAttribute("msg", "일치하는 정보가 없습니다.");
		doGet(request, response);
	}

}
