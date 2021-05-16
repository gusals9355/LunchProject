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
		MyUtils.getNav(request);
		request.setAttribute("list", BoardDAO.getAllBoard());
		MyUtils.openJSP("ojm", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
