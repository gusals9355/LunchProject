package com.koreait.lunch.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.lunch.board.model.BoardDAO;

@WebServlet("/ojm")
public class OJMServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String[] typelist = {"한식","양식","일식","중식","분식","카페","기타"};
		request.setAttribute("list", BoardDAO.getAllBoard());
		request.setAttribute("typelist",typelist);
		MyUtils.openJSP("오늘 점심은 뭐먹지?","ojm", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
