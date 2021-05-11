package com.koreait.lunch.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.lunch.model.MemberVO;
import com.koreait.lunch.model.ojmDAO;

import secUtil.CryptoUtil;


@WebServlet("/findpw")
public class FindPwServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String id = request.getParameter("id");
		// ��ȣȭ �� �񱳰˻�
		String pw = CryptoUtil.sha256(request.getParameter("pw"));
		String pwck = CryptoUtil.sha256(request.getParameter("pwck"));
		
		if(pw != pwck) {
			// �ٽ� findPw.jsp ȣ�����ֱ�
		}
		// �� ������ vo�� �㿡 dao�� �ѱ��
		MemberVO memberVo = new MemberVO();
		memberVo.setName(name);
		memberVo.setEmail(email);
		memberVo.setId(id);
		memberVo.setPw(pw);
		// dao ���� �ش��ϴ� id�� email, name�� �˻� �� pw �������ֱ�
		// �����δ� �޴��� ���� or ���� �������� otp üũ�� ���Ͽ� �˻��ϱ�
		ojmDAO.updatePassword(memberVo);
		
		
		// main������ or �α��� �������� �ѱ��
		
	}

}
