package com.koreait.lunch.my.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.lunch.controller.MyUtils;
import com.koreait.lunch.model.dao.MemberDAO;

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
		String id = request.getParameter("id");
		String select = request.getParameter("select");
		if(id ==null) id="";
		if(select ==null) select="nickname";
		
		request.setAttribute("id", id);
		request.setAttribute("pageNum", sIdx);
		request.setAttribute("maxPage", MemberDAO.getAllPage(select,id));
		request.setAttribute("rankingList", MemberDAO.getRanking(select,id, sIdx,pageCount));
		MyUtils.openJSP("랭킹 | 오늘 점심 뭐먹지?","my/ranking", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setCharacterEncoding("utf-8");
//		String id = request.getParameter("id");
//		PrintWriter pw = response.getWriter();
//		MemberVO vo = MemberDAO.searchUser(id);
//		Gson gson = new Gson();
//		String json = gson.toJson(vo);
//		pw.append(json);
//		System.out.println(json);
//		request.setAttribute("rankingList", MemberDAO.searchUser(id));
//		MyUtils.openJSP("랭킹 | 오늘 점심 뭐먹지?","my/ranking", request, response);
	}

}