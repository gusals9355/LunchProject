package com.koreait.lunch.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.koreait.lunch.model.ojmDAO;
import com.koreait.lunch.model.board.BoardVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/write")
public class WriteServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] typelist = {"한식","양식","일식","중식","분식","카페","기타"};
		request.setAttribute("typelist",typelist);
		
		if(MyUtils.getLoginUser(request) == null) {
			Random rd = new Random();
			String anonymous = rd.ints(48, 123).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(10).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
			request.setAttribute("anonymous", anonymous);
		}
//		String ipAddress=request.getRemoteAddr();
//		if(ipAddress.equalsIgnoreCase("0:0:0:0:0:0:0:1")){
//			InetAddress inetAddress=InetAddress.getLocalHost();
//			ipAddress=inetAddress.getHostAddress();
//		}
//		request.setAttribute("ipAddress", ipAddress);
		MyUtils.openJSP("/write", request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = "";
		String pw = "";
		String title = "";
		String content = "";
		String star = "";
		String category = "";
		String mapX = "";
		String mapY = "";
		String picture = "";
		BoardVO vo = new BoardVO();
		
		String savePath = request.getRealPath("upload"); //저장경로
		String path = "C:\\Users\\user\\git\\LunchProject\\LunchProject1\\src\\main\\webapp\\upload"; //저장경로
		System.out.println(path);
		System.out.println(savePath);
		int sizeLimit = 1024*1024*15; //파일크기
		
		MultipartRequest multi = new MultipartRequest(request, path, sizeLimit, "utf-8", new DefaultFileRenamePolicy() /*중복이름 변경*/); 
		Enumeration files = multi.getFileNames(); // 폼에서 받아온 file 이름 반환
			try {
				//http://kaludin.egloos.com/v/2274255
				id=multi.getParameter("id"); // 폼태그 enctype속성이 있으면 일반적인 방법으로 값을 받을수 없음 개씨발
				pw=multi.getParameter("pw");
				title=multi.getParameter("title");
				content=multi.getParameter("content");
				star=multi.getParameter("star");
				category=multi.getParameter("category");
				mapX=multi.getParameter("lng");
				mapY=multi.getParameter("lat");
				if(star==null||category==null||mapX==null||mapY==null) {
					doGet(request, response);
					return;
				}
				List<String> list = new ArrayList<String>();
				while(files.hasMoreElements()) {
					String fileInput = (String) files.nextElement();
					picture = multi.getFilesystemName(fileInput); //사용자가 지정해서 서버 상에 실제로 업로드된 파일명을 반환함
					System.out.println(picture);
					list.add(picture);
				}
				vo.setId(id);
				vo.setPw(BCrypt.hashpw(pw, BCrypt.gensalt()));
				vo.setTitle(title);
				vo.setContent(content);
				vo.setStar(Integer.parseInt(star));
				vo.setCategory(category);
				vo.setMapX(Double.parseDouble(mapX));
				vo.setMapY(Double.parseDouble(mapY));
				vo.setPicture(list);
				ojmDAO.insertBoard(vo);
			} catch (Exception e) {
				e.printStackTrace();
				doGet(request, response);
				System.out.println("업로드 실패");
				return;
			}
		response.sendRedirect("/ojm");
	}

}