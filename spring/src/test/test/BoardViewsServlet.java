package com.koreait.lunch.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.lunch.model.dao.BoardDAO;
import com.koreait.lunch.model.vo.BoardVO;
import com.koreait.lunch.model.dao.RepleDAO;
import com.koreait.lunch.controller.MyUtils;

@WebServlet("/board/views")
public class BoardViewsServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MyUtils.getLoginUser(request) == null) {
			response.sendRedirect("/ojm/login");
			return;
		}
		int no = MyUtils.getParamInt("no", request);
		BoardVO vo = BoardDAO.getBoard(no,MyUtils.getLoginUserID(request));
		request.setAttribute("boards", vo);
		request.setAttribute("reples", RepleDAO.getReples(no));
		request.setAttribute("selRepleNo", request.getParameter("repleNo"));
		MyUtils.openJSP(vo.getTitle(),"board/views", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RepleDAO.insertReple(MyUtils.getParamInt("no", request), MyUtils.getLoginUser(request),
				request.getParameter("reple"), MyUtils.getParamInt("star", request));
		
		doGet(request, response);
	}

}