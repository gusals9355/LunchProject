package com.koreait.lunch.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.lunch.model.dao.BoardDAO;
import com.koreait.lunch.controller.MyUtils;

@WebServlet("/board/heart")
public class BoardHeartServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MyUtils.getLoginUser(request) == null) {
			response.sendRedirect("/ojm/login");
			return;
		}
		int no = MyUtils.getParamInt("no", request);
		int fav = MyUtils.getParamInt("fav", request);
		if(fav==1) {
			BoardDAO.insertHeart(no, MyUtils.getLoginUserID(request));
		}else {
			BoardDAO.removeHeart(no, MyUtils.getLoginUserID(request));
		}
		response.sendRedirect("/board/views?no="+no);
	}

}
