package com.koreait.lunch.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.lunch.model.dao.MemberDAO;
import com.koreait.lunch.model.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

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
	
	public static MemberVO getLoginUser(HttpSession session) {
		if(session == null) return null;
		return (MemberVO) session.getAttribute("userInfo");
	}
	
	public static String getLoginUserID(HttpSession session) {
		return getLoginUser(session).getId();
	}
	
	public static void logOutSession(HttpSession session) {
		session.invalidate();
	}
	
	public static void reUserInfo(HttpSession session) {
		MemberVO vo = new MemberVO();
		vo.setId(MyUtils.getLoginUserID(session));
		@Autowired
		MemberDAO memberDAO;
		session.setAttribute("userInfo", memberDAO.getUserInfo(vo));
	}

	public static void setTemplate(Model model, String title, String page, HttpSession session){
		if(getLoginUser(session) == null) model.addAttribute("nav","nav");
		else model.addAttribute("nav","loginNav");
		model.addAttribute("title",title);
		model.addAttribute("page",page);
	}
	public static final String TEMPLATE = "template";

}
