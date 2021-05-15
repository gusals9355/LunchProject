package com.koreait.lunch.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.lunch.member.model.MemberVO;

public class MyUtils {
	public static void openJSP(String jsp, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/"+jsp + ".jsp").forward(request, response);
	}
	
	public static int parseStringToInt(String num) {
		try {
			return Integer.parseInt(num);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static int getParamInt(String s, HttpServletRequest request) {
		return parseStringToInt(request.getParameter(s));
	}
	
	public static void getNav(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("str"));
		if(session.getAttribute("userInfo") == null) {
			String str = "nav.jsp";
			session.setAttribute("str", str);
		}
	}
	
	public static MemberVO getLoginUser(HttpServletRequest request) {
		if(request == null) return null;
		HttpSession hs = request.getSession();
		return (MemberVO) hs.getAttribute("loginUser");
	}
	
	public static String getLoginUserID(HttpServletRequest request) {
		return getLoginUser(request).getId();
	}
	
}
