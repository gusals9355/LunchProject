package com.koreait.lunch.my.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.lunch.controller.MyUtils;
import com.koreait.lunch.member.model.MemberDAO;

@WebServlet("/ranking")
public class RankingServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MyUtils.getLoginUser(request) == null) {
			response.sendRedirect("/ojm/login");
			return;
		}
		
		int page = MyUtils.getParamInt("page", request);
		if(page == 0) page = 1;
		int pageCount = 10;
		int sIdx = (page -1) * pageCount;
		
		request.setAttribute("pageNum", sIdx);
		request.setAttribute("maxPage", MemberDAO.getAllPage());
		request.setAttribute("rankingList", MemberDAO.getRanking(sIdx,pageCount));
		MyUtils.openJSP("랭킹 | 오늘 점심 뭐먹지?","my/ranking", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
