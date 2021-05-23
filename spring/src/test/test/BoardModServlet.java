package com.koreait.lunch.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.lunch.model.dao.BoardDAO;
import com.koreait.lunch.model.vo.BoardVO;
import com.koreait.lunch.controller.MyUtils;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/board/modBoard")
public class BoardModServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MyUtils.getLoginUser(request) == null) {
			response.sendRedirect("/ojm/login");
			return;
		}
		final String[] typelist = {"한식","양식","일식","중식","분식","카페","기타"};
		BoardVO vo = BoardDAO.getBoard(MyUtils.getParamInt("no", request),MyUtils.getLoginUserID(request));
		
		request.setAttribute("typelist",typelist);
		request.setAttribute("board", vo);
		
		MyUtils.openJSP(vo.getTitle(),"board/modBoard", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String path = "C:\\\\Users\\\\user\\\\git\\\\LunchProject\\\\LunchProject1\\\\src\\\\main\\\\webapp\\\\upload"; //저장경로
		final int sizeLimit = 1024*1024*15; //파일크기
		
		MultipartRequest multi = new MultipartRequest(request, path, sizeLimit, "utf-8", new DefaultFileRenamePolicy() /*중복이름 변경*/); 
		Enumeration files = multi.getFileNames(); // 폼에서 받아온 file 이름 반환
		try {
			//http://kaludin.egloos.com/v/2274255
			// 폼태그 enctype속성이 있으면 일반적인 방법으로 값을 받을수 없음 개씨발
			String no = multi.getParameter("no");
			String star=multi.getParameter("star");
			String mapX=multi.getParameter("lat");
			String mapY=multi.getParameter("lng");
			List<String> list = new ArrayList<String>();
			while(files.hasMoreElements()) {
				String fileInput = (String) files.nextElement();
				String picture = multi.getFilesystemName(fileInput); //사용자가 지정해서 서버 상에 실제로 업로드된 파일명을 반환함
				System.out.println(picture);
				list.add(picture);
			}
			BoardVO vo = new BoardVO();
			vo.setNo(MyUtils.parseStringToInt(no));
			vo.setId(MyUtils.getLoginUserID(request));
			vo.setNickname(MyUtils.getLoginUser(request).getNickName());
			vo.setStore(multi.getParameter("store"));
			vo.setTitle(multi.getParameter("title"));
			vo.setContent(multi.getParameter("content"));
			vo.setStar(Integer.parseInt(star));
			vo.setCategory(multi.getParameter("category"));
			vo.setMapX(Double.parseDouble(mapX));
			vo.setMapY(Double.parseDouble(mapY));
			vo.setPicture(list.get(0));
			System.out.println(multi.getParameter("store"));
			System.out.println(mapX);
			System.out.println(mapY);
			
			BoardDAO.modBoard(MyUtils.getParamInt("no", request), MyUtils.getLoginUserID(request), vo);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "카테고리 혹은 평점, 매장을 선택 해주세요.");
			System.out.println("업로드 실패");
			doGet(request, response);
			return;
		}
		response.sendRedirect("/board/views?no="+MyUtils.parseStringToInt(multi.getParameter("no")));
	}

}
