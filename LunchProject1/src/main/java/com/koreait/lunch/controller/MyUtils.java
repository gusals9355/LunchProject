package com.koreait.lunch.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.lunch.member.model.MemberDAO;
import com.koreait.lunch.member.model.MemberVO;

public class MyUtils {
	
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
		if(getLoginUser(request) == null) {
			session.setAttribute("str", "nav.jsp");
			return;
		}
		session.setAttribute("str", "loginNav.jsp");
	}
	
	public static MemberVO getLoginUser(HttpServletRequest request) {
		if(request == null) return null;
		HttpSession hs = request.getSession();
		return (MemberVO) hs.getAttribute("userInfo");
	}
	
	public static String getLoginUserID(HttpServletRequest request) {
		return getLoginUser(request).getId();
	}
	
	public static void logOutSession(HttpSession session) {
		session.invalidate();
	}
	
	public static void openJSP(String jsp, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/"+jsp +".jsp").forward(request, response);
	}
	
	public static void reUserInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("userInfo", MemberDAO.getUserInfo(MyUtils.getLoginUserID(request)));
	}
	
}
