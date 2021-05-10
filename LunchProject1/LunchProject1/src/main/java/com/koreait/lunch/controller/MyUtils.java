package com.koreait.lunch.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
}
