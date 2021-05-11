package com.koreait.lunch.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.lunch.model.MemberVO;
import com.koreait.lunch.model.ojmDAO;

/**
 * Servlet implementation class FindIdServlet
 */
@WebServlet("/findid")
public class FindIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		MemberVO memberVo = new MemberVO();
		memberVo.setEmail(email);
		//TODO : �̸��Ϸ� ID ã�ƿ��� SQL �� �ۼ�
		
		// �̸��Ϸ� �޾ƿ� id ����Ʈ �޾ƿ���
		List<String> idList = ojmDAO.selectIdList(memberVo);
		request.setAttribute("idList",idList);
		// �ٽ� findid.jsp ����
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
