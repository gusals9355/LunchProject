package com.koreait.lunch.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.koreait.lunch.model.DBUtils;

public class MemberDAO {
	static PreparedStatement pstmt;
	static ResultSet rs;
	
	public static boolean insertMember(MemberVO bean) { //회원가입
		Connection con = null;
		con = DBUtils.getCon(con);
		boolean verify = true;
		
		final String sql ="insert into member(name,email,gender,id,pw,nickname) value(?,?,?,?,?,?)";
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
			DBUtils.close(con);
		}
		return verify;
	}
	
	public static String getHashedPw(MemberVO vo) { //패스워드 확인
		Connection con = null;
		con = DBUtils.getCon(con);
		String hashPW = "";
		final String sql = "select pw from member where id = ?";
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
			DBUtils.close(con);
		}
	}
	
	public static MemberVO getUserInfo(MemberVO vo) {
		Connection con = null;
		con = DBUtils.getCon(con);
		MemberVO userInfo = new MemberVO();
		final String sql = "select * from member where id = ?";
		
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
			DBUtils.close(con);
		}
		return userInfo;
	}
	
	public static List<String> selectIdList(MemberVO vo) {
		Connection con = null;
		con = DBUtils.getCon(con);
		
		List<String> idList = new ArrayList<String>();
		DBUtils.getCon(con);
		final String sql = "select id from member where email=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getEmail());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				idList.add(rs.getString(1));
			}
			
		} catch (Exception e) {
		}finally {
			DBUtils.close(con);
		}
		return idList;
	}
	
	public static int updatePassword(MemberVO vo) {
		Connection con = null;
		con = DBUtils.getCon(con);
		
		final String sql = "update member set pw = ? "+
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
			DBUtils.close(con);
		}
	}
	
	public static void log(String id, String str) { //로그인 정보 (로그)를 저장하는 메소드
		Connection con = null;
		con = DBUtils.getCon(con);
		final String sql = "insert into log (id, log) values(?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, str);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(con);
		}
	}
	
	public static void logCheck(String id) {
		Connection con = null;
		con = DBUtils.getCon(con);
		
		final String sql ="select * from log where id = ? and log='로그인' and date(reg_dt) = date(now()) limit 1";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt("attendance")!=1) {
					final String sql2 = "update log set attendance = 1 where id = ? and log='로그인' and date(reg_dt) = date(now()) limit 1";
					pstmt = con.prepareStatement(sql2);
					pstmt.setString(1, id);
					if(pstmt.executeUpdate() == 1) {
						int point = 25; //로그인 포인트
						final String sql3 = "update member set point=point+"+point+" where id = ?";
						pstmt= con.prepareStatement(sql3);
						pstmt.setString(1, id);
						pstmt.executeUpdate();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(con);
		}
	}
	
}
