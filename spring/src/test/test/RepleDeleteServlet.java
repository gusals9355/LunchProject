package com.koreait.lunch.board.reple.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.lunch.model.dao.RepleDAO;
import com.koreait.lunch.controller.MyUtils;

@WebServlet("/board/delReple")
public class RepleDeleteServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MyUtils.getLoginUser(request) == null) {
			response.sendRedirect("/ojm/login");
			return;
		}
		RepleDAO.delReple(MyUtils.getParamInt("no", request), MyUtils.getLoginUserID(request), MyUtils.getParamInt("repleNo", request));
		
		response.sendRedirect("views?no="+MyUtils.getParamInt("no", request));
	}
}
