package com.koreait.lunch.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.lunch.model.dao.BoardDAO;
import com.koreait.lunch.controller.MyUtils;
import com.koreait.lunch.model.dao.MemberDAO;

@WebServlet("/board/delBoard")
public class BoardDelServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MyUtils.getLoginUser(request) == null) {
			response.sendRedirect("/ojm/login");
			return;
		}
		BoardDAO.delBoard(MyUtils.getParamInt("no", request), MyUtils.getLoginUserID(request));
		MemberDAO.setPoint(MyUtils.getLoginUserID(request), "-");
		MyUtils.reUserInfo(request);
		response.sendRedirect("/ojm");
	}
}
