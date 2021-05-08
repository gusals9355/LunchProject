package com.koreait.lunch.controller;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.lunch.model.BoardVO;
import com.koreait.lunch.model.ojmDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/write")
public class WriteServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Random rd = new Random();
		String anonymous = rd.ints(48, 123).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(10).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		
		request.setAttribute("anonymous", anonymous);
		        
		String ipAddress=request.getRemoteAddr();
		if(ipAddress.equalsIgnoreCase("0:0:0:0:0:0:0:1")){
		    InetAddress inetAddress=InetAddress.getLocalHost();
		    ipAddress=inetAddress.getHostAddress();
		}
		
		request.setAttribute("ipAddress", ipAddress);
		RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/view/write.jsp");
		dis.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = "";
		String pw = "";
		String title = "";
		String content = "";
		String star1 = "";
		String category = "";
		String mapX = "";
		String mapY = "";
		String picture = "";
		BoardVO vo = new BoardVO();
		
		String savePath = request.getRealPath("upload"); //저장경로
		String path = "C:\\Users\\user\\git\\LunchProject1\\LunchProject1\\src\\main\\webapp\\upload"; //저장경로
		System.out.println(path);
		System.out.println(savePath);
		int sizeLimit = 1024*1024*15; //파일크기
		
		MultipartRequest multi = new MultipartRequest(request, path, sizeLimit, "utf-8", new DefaultFileRenamePolicy() /*중복이름 변경*/); 
		Enumeration<String> files = multi.getFileNames(); // 폼에서 받아온 file 이름 반환
			try {
				//http://kaludin.egloos.com/v/2274255
				id=multi.getParameter("id"); // 폼태그 enctype속성이 있으면 일반적인 방법으로 값을 받을수 없음 개씨발
				pw=multi.getParameter("pw");
				title=multi.getParameter("title");
				content=multi.getParameter("content");
				star1=multi.getParameter("star1");
				category=multi.getParameter("category");
				mapX=multi.getParameter("mapX");
				mapY=multi.getParameter("mapY");
				
				List<String> list = new ArrayList<String>();
				
				while(files.hasMoreElements()) {
					String fileInput = files.nextElement();
					picture = multi.getFilesystemName(fileInput); //사용자가 지정해서 서버 상에 실제로 업로드된 파일명을 반환함
					System.out.println(picture);
					list.add(picture);
				}
				vo.setId(id);
				vo.setPw(pw);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setStar(Integer.parseInt(star1));
				vo.setCategory(category);
				vo.setMapX(Double.parseDouble(mapX));
				vo.setMapY(Double.parseDouble(mapY));
				vo.setPicture(list);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("업로드 실패");
			}
		
		ojmDAO.insertBoard(vo);
		response.sendRedirect("/ojm");
	}

}