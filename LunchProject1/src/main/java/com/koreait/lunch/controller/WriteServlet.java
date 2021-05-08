package com.koreait.lunch.controller;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Enumeration;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.lunch.model.BoardBean;
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
		String 
		
		String savePath = "C:\\Users\\Administrator\\Downloads\\LunchProject-main\\LunchProject-main\\LunchProject\\src\\main\\webapp\\upload";
		int sizeLimit = 1024*1024*15;
		//dao
		BoardBean bean = new BoardBean();
		String context = request.getContextPath();
		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
		int no = Integer.parseInt(multi.getParameter("no")); 
		Enumeration fileNames= multi.getFileNames(); //파일 이름 반환
		int insert = 0;
		boolean save= true; //파일 저장 성공
		
		String fileInput = ""; //폼으로 받아온 fileName
        String fileName = ""; //저장된 파일 이름
        String type = ""; //저장된 파일 종류
        File fileObj = null; //저장된 파일 객체 java.io? apache ?
        String originFileName = ""; //원본 파일 이름
        String fileExtend = ""; //jpg,png,gif 등 확장자
        String fileSize = ""; //저장된 파일 사이즈
        String newFileName = "board_"+System.currentTimeMillis(); //저장된 파일을 바꿀 이름
        System.out.println("newFileName:"+newFileName);
        
        while(fileNames.hasMoreElements()) {
        	fileInput = (String)fileNames.nextElement();
        	fileName = multi.getContentType(fileInput);
        	
        	if(fileName != null){
                type = multi.getContentType(fileInput);
                fileObj = multi.getFile(fileInput);
                originFileName = multi.getOriginalFileName(fileInput);
                fileExtend = fileName.substring(fileName.lastIndexOf(".")+1);//"file1.jpg"라면 jpg 반환
                fileSize = String.valueOf(fileObj.length());//file도 결국 문자열이므로 length()로 반환
                System.out.println("type:"+type+"||originFileName:"+originFileName+
                        "||fileExtend:"+fileExtend+"||fileSize:"+fileSize);
                
                String[] splitType = type.split("/");
                if(!splitType[0].equals("image")){
                    save=false;
                    fileObj.delete(); //저장된 파일 객체로 삭제
                    break;
                }else{//만약 이미지 파일이면 저장 파일의 이름 바꾼다.
                    newFileName += "."+fileExtend;
                    fileObj.renameTo(new File(savePath+"\\"+newFileName));
                }
            }
        }
        if(save){ //파일 저장 성공시
        	bean.setNo(no);
//            bean.setMem_num(Integer.parseInt(multi.getParameter("mem_num")));
        	bean.setTitle(newFileName)
            bean.setTitle(multi.getParameter("title"));
            bean.setContent(multi.getParameter("content"));
            bean.setPasswd(multi.getParameter("passwd"));
            bean.setFileName(fileName);
            bean.setAddress(savePath+"/"+newFileName);
            insert = dao.insertBoard(board);
        }
        if (save && insert > 0) { //DB insert까지 성공시
            System.out.println("저장 성공");
            response.sendRedirect(context + "/boardDetail.do?num="+num);
        } else {
            System.out.println("저장 실패");
            response.sendRedirect(context + "/boardAddForm.do");
        }
        }catch(Exception e){e.printStackTrace();}

	}

}