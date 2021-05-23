package com.koreait.lunch.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import com.koreait.lunch.model.vo.RepleVO;
import com.koreait.lunch.model.vo.MemberVO;
import com.koreait.lunch.model.DBUtils;

public class RepleDAO {
	static PreparedStatement pstmt;
	static ResultSet rs;
	
	public static void insertReple(int boardNo, MemberVO vo, String reple, int star) {
		Connection con = null;
		con = DBUtils.getCon(con);
		
		final String sql ="insert into reple(boardno, id, nickname, reple, star) values(?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getNickName());
			pstmt.setString(4, reple);
			pstmt.setInt(5, star);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con);
		}
	}
	
	public static List<RepleVO> getReples(int boardNo) {
		Connection con = null;
		con = DBUtils.getCon(con);
		
		List<RepleVO> list = new ArrayList<RepleVO>();
		final String sql = "select * from reple where boardno = ? order by no desc";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				RepleVO vo = new RepleVO();
				vo.setNo(rs.getInt("no"));
				vo.setId(rs.getString("id"));
				vo.setNickname(rs.getString("nickname"));
				vo.setReple(rs.getString("reple"));
				vo.setReg_dt(rs.getString("reg_dt"));
				vo.setStar(rs.getInt("star"));
				list.add(vo);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}finally {
			DBUtils.close(con);
		}
	}
	
	public static void delReple(int boardNo, String id, int repleNo) {
		Connection con = null;
		con = DBUtils.getCon(con);
		
		final String sql = "delete from reple where boardno = ? and id = ? and no = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			pstmt.setString(2, id);
			pstmt.setInt(3, repleNo);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con);
		}
	}
	
	public static void modReple(int no, String id, RepleVO vo) {
		Connection con = null;
		con = DBUtils.getCon(con);
		
		final String sql = "update reple set reple = ?, star = ?, reg_dt = now() where boardno = ? and id = ? and no = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getReple());
			pstmt.setInt(2, vo.getStar());
			pstmt.setInt(3, no);
			pstmt.setString(4, id);
			pstmt.setInt(5, vo.getNo());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con);
		}
	}
	
	
}
