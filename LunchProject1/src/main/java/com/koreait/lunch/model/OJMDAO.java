package com.koreait.lunch.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.koreait.lunch.board.model.BoardVO;
import com.koreait.lunch.member.model.MemberVO;

public class OJMDAO {
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
		
		String sql ="insert into member(name,email,gender,id,pw,nickname) value(?,?,?,?,?,?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getGender());
			pstmt.setString(4, bean.getId());
			pstmt.setString(5, bean.getPw());
			pstmt.setString(6, bean.getName());
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
		
//		String pictureList = "";
//		for (String i : vo.getPicture()) { //다중 파일이 업로드될때 구분자를 주기 위한 문장
//			pictureList+=i+|;
//		}
		
		String sql = "insert into board(title, content, id, pw, picture, star, category, mapX, mapY, store) "
				+ "values(?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getId());
			pstmt.setString(4, vo.getPw());
			pstmt.setString(5, vo.getPicture());
			pstmt.setInt(6, vo.getStar());
			pstmt.setString(7, vo.getCategory());
			pstmt.setDouble(8, vo.getMapX());
			pstmt.setDouble(9, vo.getMapY());
			pstmt.setString(10, vo.getStore());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		
	}
	
	public static String getHashedPw(MemberVO vo) { //패스워드 확인
		getCon();
		String hashPW = "";
		String sql = "select pw from member where id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			rs = pstmt.executeQuery();
			if(rs.next()) { //로그인 성공한다면
				hashPW = rs.getString(1); //db에 있는 암호화 된 값
			}
			return hashPW;
		} catch (Exception e) {
			e.printStackTrace();
			return hashPW;
		} finally {
			close(con);
		}
	}
	
	public static MemberVO getUserInfo(MemberVO vo) {
		getCon();
		MemberVO userInfo = new MemberVO();
		String sql = "select * from member where id = ?";
		
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				userInfo.setId(rs.getString("id"));
				userInfo.setGender(rs.getString("gender"));
				userInfo.setName(rs.getString("name"));
				userInfo.setEmail(rs.getString("email"));
				userInfo.setNickName(rs.getString("nickname"));
				userInfo.setPoint(rs.getString("point"));
			}
			return userInfo;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con);
		}
		return userInfo;
	}
	
	public static void log(String id, String str) { //로그인 정보 (로그)를 저장하는 메소드
		getCon();
		String sql = "insert into log (id, log) values(?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, str);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
	}
	
	public static void logCheck(String id) {
		getCon();
		
		String sql ="select * from log where id = ? and log='로그인' and date(reg_dt) = date(now()) limit 1";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt("attendance")!=1) {
					String sql2 = "update log set attendance = 1 where id = ? and log='로그인' and date(reg_dt) = date(now()) limit 1";
					pstmt = con.prepareStatement(sql2);
					pstmt.setString(1, id);
					if(pstmt.executeUpdate() == 1) {
						int point = 25; //로그인 포인트
						String sql3 = "update member set point=point+"+point+" where id = ?";
						pstmt= con.prepareStatement(sql3);
						pstmt.setString(1, id);
						pstmt.executeUpdate();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		
	}
	
	public static List<String> selectIdList(MemberVO vo) {
		List<String> idList = new ArrayList<String>();
		getCon();
		String sql = "select id from member where email=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getEmail());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				idList.add(rs.getString(1));
			}
			
		} catch (Exception e) {
		}finally {
			close(con);
		}
		return idList;
	}
	
	public static int updatePassword(MemberVO vo) {
		getCon();
		String sql = "update member set pw = ? "+
					"where name = ? and email = ? and id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getPw());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getId());
			return pstmt.executeUpdate(); // 정상 실행시 1 반환
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			close(con);
		}
	}
	
	public static List<BoardVO> getAllBoard(){
		getCon();
		List<BoardVO> list = new ArrayList<BoardVO>();
		String sql = "select * from board";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setNo(rs.getInt("no"));
				vo.setStore(rs.getString("store"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setId(rs.getString("id"));
				vo.setReadCount(rs.getInt("readcount"));
				vo.setPicture(rs.getString("picture"));
				vo.setReg_dt(rs.getString("reg_dt"));
				vo.setStar(rs.getInt("star"));
				vo.setTitle(rs.getString("category"));
				vo.setMapX(rs.getDouble("mapX"));
				vo.setMapY(rs.getDouble("mapY"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return list;
	}
	
}
