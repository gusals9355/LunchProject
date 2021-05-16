package com.koreait.lunch.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.lunch.board.model.BoardDAO;
import com.koreait.lunch.board.reple.model.RepleDAO;
import com.koreait.lunch.controller.MyUtils;

@WebServlet("/board/views")
public class ViewsServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MyUtils.getLoginUser(request) == null) {
			response.sendRedirect("/ojm/login");
			return;
		}
		request.setAttribute("board", BoardDAO.getBoard(MyUtils.getParamInt("no", request)));
		request.setAttribute("reple", RepleDAO.getReple(MyUtils.getParamInt("no", request)));
		MyUtils.openJSP("board/views", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RepleDAO.insertReple(MyUtils.getParamInt("no", request), MyUtils.getLoginUser(request),
				request.getParameter("reple"), MyUtils.getParamInt("star", request));
		
		doGet(request, response);
	}

}