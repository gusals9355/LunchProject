package com.koreait.lunch.board.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import com.koreait.lunch.board.reple.model.RepleVO;
import com.koreait.lunch.member.model.MemberVO;
import com.koreait.lunch.model.DBUtils;

public class BoardDAO {
	static PreparedStatement pstmt;
	static ResultSet rs;
	
	public static void insertBoard(BoardVO vo) {
		Connection con = null;
		con = DBUtils.getCon(con);
		
//		String pictureList = "";
//		for (String i : vo.getPicture()) { //다중 파일이 업로드될때 구분자를 주기 위한 문장
//			pictureList+=i+|;
//		}
		final String sql = "insert into board(title, content, id, nickname, picture, star, category, mapX, mapY, store) "
				+ "values(?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getId());
			pstmt.setString(4, vo.getNickname());
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
			DBUtils.close(con);
		}
	}
	
	public static void insertHeart(int no, String id) {
		Connection con = null;
		con = DBUtils.getCon(con);
		
		final String sql = "insert into favorite(no, id) values(?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(con);
		}
	}
	
	public static List<BoardVO> getAllBoard(){
		Connection con = null;
		con = DBUtils.getCon(con);
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		final String sql = "select * from board";
		
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
				vo.setCategory(rs.getString("category"));
				vo.setMapX(rs.getDouble("mapX"));
				vo.setMapY(rs.getDouble("mapY"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(con);
		}
		return list;
	}
	
	
	public static BoardVO getBoard(int no, String id) {
		Connection con = null;
		con = DBUtils.getCon(con);
		
		BoardVO vo = null;
		final String sql = "select B.*, if(F.no is null, 0,1) as isFav from board B "
				+ "left join favorite F "
				+ "on B.no = F.no "
				+ "and F.id = ? "
				+ "where B.no=?;";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new BoardVO();
				vo.setNo(rs.getInt("no"));
				vo.setStore(rs.getString("store"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setId(rs.getString("id"));
				vo.setReadCount(rs.getInt("readcount"));
				vo.setPicture(rs.getString("picture"));
				vo.setReg_dt(rs.getString("reg_dt"));
				vo.setStar(rs.getInt("star"));
				vo.setCategory(rs.getString("category"));
				vo.setMapX(rs.getDouble("mapX"));
				vo.setMapY(rs.getDouble("mapY"));
				vo.setIsFav(rs.getInt("isFav"));
			}
			return vo;
		} catch (Exception e) {
			e.printStackTrace();
			return vo;
		}finally {
			DBUtils.close(con);
		}
	}
	
	public static void delBoard(int boardNo, String id) {
		Connection con = null;
		con = DBUtils.getCon(con);
		
		final String sql = "delete from board where no = ? and id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con);
		}
	}
	
	public static void removeHeart(int no, String id) {
		Connection con = null;
		con = DBUtils.getCon(con);
		
		final String sql = "delete from favorite where no=? and id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(con);
		}
	}
	
	//TODO: 사진, map까지 수정
	public static void modBoard(int no, String id, BoardVO vo) {
		Connection con = null;
		con = DBUtils.getCon(con);
		
		final String sql = "update board set title = ?, content = ?, picture = ?, star=?, category = ?, reg_dt = now(),"
				+ " mapX=?,mapY=?, store=? where no = ? and id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getPicture());
			pstmt.setInt(4, vo.getStar());
			pstmt.setString(5, vo.getCategory());
			pstmt.setDouble(6, vo.getMapX());
			pstmt.setDouble(7, vo.getMapY());
			pstmt.setString(8, vo.getStore());
			pstmt.setDouble(9, vo.getNo());
			pstmt.setString(10, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con);
		}
	}
	
	
}
