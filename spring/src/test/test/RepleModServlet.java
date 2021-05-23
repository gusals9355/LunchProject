package com.koreait.lunch.board.reple.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.lunch.model.dao.BoardDAO;
import com.koreait.lunch.model.vo.BoardVO;
import com.koreait.lunch.model.dao.RepleDAO;
import com.koreait.lunch.model.vo.RepleVO;
import com.koreait.lunch.controller.MyUtils;

@WebServlet("/board/modReple")
public class RepleModServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MyUtils.getLoginUser(request) == null) {
			response.sendRedirect("/ojm/login");
			return;
		}
		BoardVO vo = BoardDAO.getBoard(MyUtils.getParamInt("no", request),MyUtils.getLoginUserID(request));
		request.setAttribute("boards",vo);
		request.setAttribute("reples", RepleDAO.getReples(MyUtils.getParamInt("no", request)));
		request.setAttribute("repleNo", request.getParameter("repleNo")); //seta안하면 안넘어가네
		
		MyUtils.openJSP(vo.getTitle(),"board/reple/modReple", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = MyUtils.getParamInt("no", request);
		RepleVO vo = new RepleVO();
		vo.setNo(MyUtils.getParamInt("repleNo", request));
		vo.setReple(request.getParameter("reple"));
		vo.setStar(MyUtils.getParamInt("star", request));
		RepleDAO.modReple(no, MyUtils.getLoginUserID(request), vo);
		response.sendRedirect("views?no="+no);
	}

}