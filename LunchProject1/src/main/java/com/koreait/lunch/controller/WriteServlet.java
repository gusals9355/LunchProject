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
		MyUtils.openJSP("write", request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String savePath = request.getRealPath("upload"); //저장경로
		String path = "C:\\Users\\Administrator\\git\\LunchProject1\\LunchProject1\\src\\main\\webapp\\upload"; //저장경로
		int sizeLimit = 1024*1024*15; //파일크기
		
		MultipartRequest multi = new MultipartRequest(request, path, sizeLimit, "utf-8", new DefaultFileRenamePolicy() /*중복이름 변경*/); 
		Enumeration files = multi.getFileNames(); // 폼에서 받아온 file 이름 반환
			try {
				//http://kaludin.egloos.com/v/2274255
				String id=multi.getParameter("id"); // 폼태그 enctype속성이 있으면 일반적인 방법으로 값을 받을수 없음 개씨발
				String pw=multi.getParameter("pw");
				String title=multi.getParameter("title");
				String content=multi.getParameter("content");
				String star=multi.getParameter("star");
				String category=multi.getParameter("category");
				String mapX=multi.getParameter("lng");
				String mapY=multi.getParameter("lat");
				List<String> list = new ArrayList<String>();
				while(files.hasMoreElements()) {
					String fileInput = (String) files.nextElement();
					String picture = multi.getFilesystemName(fileInput); //사용자가 지정해서 서버 상에 실제로 업로드된 파일명을 반환함
					System.out.println(picture);
					list.add(picture);
				}
				BoardVO vo = new BoardVO();
				vo.setId(id);
				vo.setPw(BCrypt.hashpw(pw, BCrypt.gensalt()));
				vo.setTitle(title);
				vo.setContent(content);
				vo.setStar(Integer.parseInt(star));
				vo.setCategory(category);
				vo.setMapX(Double.parseDouble(mapX));
				vo.setMapY(Double.parseDouble(mapY));
				vo.setPicture(list.get(0));
				ojmDAO.insertBoard(vo);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "카테고리 혹은 평점, 매장을 선택 해주세요.");
				System.out.println("업로드 실패");
				doGet(request, response);
				return;
			}
		response.sendRedirect("/ojm");
	}

}