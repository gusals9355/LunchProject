package com.koreait.lunch.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class ojmDAO {
	static Connection con;
	static PreparedStatement pstmt;
	static ResultSet rs;
	
	public static void getCon() {
		try {
			Context initctx = new InitialContext();
			
			Context envctx = (Context) initctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool");
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void close(Connection con) {
		if(con != null) {try {con.close();}catch (Exception e) {e.printStackTrace();}}
	}
	
	public static boolean insertMember(MemberVO bean) { //회원가입
		getCon();
		boolean verify = true;
		
		String sql ="insert into member(name,email,gender,id,pw) value(?,?,?,?,?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getGender());
			pstmt.setString(4, bean.getId());
			pstmt.setString(5, bean.getPw());
			pstmt.executeUpdate();
			verify = false;
		} catch (Exception e) {
			e.printStackTrace();
			return verify;
		} finally {
			close(con);
		}
		return verify;
	}
	
	public static void insertBoard(BoardVO vo) {
		getCon();
		
		String pictureList = "";
		for (String i : vo.getPicture()) { //다중 파일이 업로드될때 구분자를 주기 위한 문장
			pictureList+=i;
		}
		
		String sql = "insert into board(title, content, id, pw, picture, star, category, mapX, mapY)"
				+ "values(?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getId());
			pstmt.setString(4, vo.getPw());
			pstmt.setString(5, pictureList);
			pstmt.setInt(6, vo.getStar());
			pstmt.setString(7, vo.getCategory());
			pstmt.setDouble(8, vo.getMapX());
			pstmt.setDouble(9, vo.getMapY());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		
	}
	
	public static boolean tryLogin(MemberVO bean) {
		getCon();
		boolean verify = false;
		
		String sql = "select id, pw from member where id = ? and pw = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getPw());
			rs = pstmt.executeQuery();
			
			if(rs.next()) verify = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con);
		}
		return verify;
	}
	
	public static void upPoint(MemberVO vo) {
		getCon();
		int point = 5; //로그인 포인트를 5로 설정함
		String sql = "update point set point = point +"+point+" where id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con);
		}
		
	}
	
	public static void log(String id, String str) { //로그인 로그를 저장하는 메소드
		getCon();
		int attendance = 1;
		String target="";
		
		String sql = "insert into log (id, log, attendance) values(?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, str);
			pstmt.setInt(3, attendance);
			pstmt.executeUpdate();
			if(str.equals("로그인")) {
				int cnt = 0;
				//출석체크하기 위해 현재 로그인 이전 날짜를 가져오기 위한 sql문
				String sql2 = "select day(reg_dt) from log where id = ? and log='로그인' order by reg_dt desc";
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					if(cnt==0) target = rs.getString(1);
					if(cnt == 1) {
						if(target.equals(rs.getString(1))) {
							attendance = 0;
						}
					}
					cnt++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		
	}
	
	public static void selectPoint(MemberVO vo) {
		getCon();
		
		String sql = "select ";
		
	}

}
