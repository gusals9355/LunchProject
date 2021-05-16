package com.koreait.lunch.my.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.lunch.member.model.MemberDAO;
import com.koreait.lunch.member.model.MemberVO;

/**
 * Servlet implementation class FindIdServlet
 */
@WebServlet("/findid")
public class FIndIdServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		MemberVO memberVo = new MemberVO();
		memberVo.setEmail(email);
		//TODO : 이메일로 ID 찾아오는 SQL 문 작성
		
		// 이메일로 받아온 id 리스트 받아오기
		List<String> idList = MemberDAO.selectIdList(memberVo);
		request.setAttribute("idList",idList);
		// 다시 findid.jsp 가기
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
