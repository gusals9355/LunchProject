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
	
	public static int getAllPage(String select, String id) {
		Connection con = null;
		con = DBUtils.getCon(con);
		final String sql = "select ceil(count(*)/10) from member where "
				+ select+" like ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+id+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) { 
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con);
		}
		return 0;
	}
	
	public static String getHashedPw(String id) { //패스워드 확인
		Connection con = null;
		con = DBUtils.getCon(con);
		String hashPW = "";
		final String sql = "select pw from member where id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
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
	
	public static int getBoardCount(String id) { //패스워드 확인
		Connection con = null;
		con = DBUtils.getCon(con);
		
		int count=0;
		final String sql = "select count(*) from member where id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con);
		}
		return count;
	}
	
	public static List<MemberVO> getRanking(String select, String id, int sIdx, int pageCount) {
		Connection con = null;
		con = DBUtils.getCon(con);
		List<MemberVO> list = new ArrayList<MemberVO>();
		final String sql = "select nickname, ranked, id, point, reg_dt from member where "+select+" like ? order by point desc, reg_dt limit ?,?";
		
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, "%"+id+"%");
			pstmt.setInt(2, sIdx);
			pstmt.setInt(3, pageCount);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setNickName(rs.getString(1));
				vo.setRank(rs.getString(2));
				vo.setId(rs.getString(3));
				vo.setPoint(rs.getString(4));
				vo.setReg_dt(rs.getString(5));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con);
		}
		return list;
	}
	
	public static MemberVO searchUser(String id) {
		Connection con = null;
		con = DBUtils.getCon(con);
		MemberVO userInfo = new MemberVO();
		final String sql = "select * from member where id = ?";
		
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				userInfo.setId(rs.getString("id"));
				userInfo.setRank(rs.getString("ranked"));
				userInfo.setGender(rs.getString("gender"));
				userInfo.setName(rs.getString("name"));
				userInfo.setEmail(rs.getString("email"));
				userInfo.setNickName(rs.getString("nickname"));
				userInfo.setPoint(rs.getString("point"));
				userInfo.setReg_dt(rs.getString("reg_dt"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con);
		}
		return userInfo;
	}
	
	public static MemberVO getUserInfo(String id) {
		Connection con = null;
		con = DBUtils.getCon(con);
		MemberVO userInfo = new MemberVO();
		final String sql = "select * from member where id = ?";
		
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				userInfo.setId(rs.getString("id"));
				userInfo.setRank(rs.getString("ranked"));
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
			e.printStackTrace();
		}finally {
			DBUtils.close(con);
		}
		return idList;
	}
	
	public static boolean findPw(MemberVO vo) {
		Connection con = null;
		con = DBUtils.getCon(con);
		
		final String sql = "select * from member where id=? and name=? and email=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getEmail());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(con);
		}
		return false;
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
						final int loginPoint = 100; //로그인 포인트
						final String sql3 = "update member set point=point+"+loginPoint+" where id = ?";
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
	
	public static void removeUser(String id) {
		Connection con = null;
		con = DBUtils.getCon(con);
		
		final String sql ="delete from member where id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(con);
		}
	}
	
	public static void editNick(String nickname, String id) {
		Connection con = null;
		con = DBUtils.getCon(con);
		
		final String sql = "update member set nickname=? where id=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickname);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con);
		}
	}
	
	public static void editPw(String id, String hashPw) {
		Connection con = null;
		con = DBUtils.getCon(con);
		
		final String sql = "update member set pw=? where id=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, hashPw);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con);
		}
	}
	
	public static void setPoint(String id, String sign) {
		int count = getBoardCount(id);
		final int writePoint = 25;
		Connection con = null;
		con = DBUtils.getCon(con);
		
		final String sql = "update member set point=point"+sign+"? where id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, writePoint);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con);
		}
	}
	
	
}
