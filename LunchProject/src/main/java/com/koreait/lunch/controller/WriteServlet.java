package com.koreait.lunch.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/write")
public class WriteServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

	}

}