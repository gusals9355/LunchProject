package com.koreait.lunch.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.lunch.member.model.MemberVO;
import com.koreait.lunch.model.DBUtils;

@WebServlet("/findPw")
public class FindPwServelt extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String id = request.getParameter("id");
		// 암호화 후 비교검사
		String pw = CryptoUtil.sha256(request.getParameter("pw"));
		String pwck = CryptoUtil.sha256(request.getParameter("pwck"));
		
		if(pw != pwck) {
			// 다시 findPw.jsp 호출해주기
		}
		// 위 값들을 vo에 담에 dao로 넘기기
		MemberVO memberVo = new MemberVO();
		memberVo.setName(name);
		memberVo.setEmail(email);
		memberVo.setId(id);
		memberVo.setPw(pw);
		// dao 에서 해당하는 id의 email, name을 검사 후 pw 변경해주기
		// 실제로는 휴대폰 인증 or 메일 인증등의 otp 체크를 통하여 검사하기
		DBUtils.updatePassword(memberVo);
		
		
		// main페이지 or 로그인 페이지로 넘기기
		
	}

}
