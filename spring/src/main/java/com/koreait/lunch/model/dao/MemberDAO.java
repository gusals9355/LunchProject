package com.koreait.lunch.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.koreait.lunch.model.DBUtils;
import com.koreait.lunch.model.vo.MemberVO;
import org.springframework.stereotype.Repository;

@Repository("memberDAO")
public interface MemberDAO {

	boolean insertMember(MemberVO bean);

	int getAllPage(String select, MemberVO vo);

	String getHashedPw(MemberVO vo);

	
	int getBoardCount(MemberVO vo);

	List<MemberVO> getRanking(String select, MemberVO vo, int sIdx, int pageCount);

	MemberVO searchUser(MemberVO vo);

	MemberVO getUserInfo(MemberVO vo);

	List<String> selectIdList(MemberVO vo);

	boolean findPw(MemberVO vo);
	
	boolean regiManager(String code);

	//로그인 정보 (로그)를 저장하는 메소드
	void log(MemberVO vo, String str);

	void logCheck(MemberVO vo);

	void removeUser(MemberVO vo);

	void editNick(MemberVO vo);

	void editPw(MemberVO vo);

	void setPoint(MemberVO vo, String sign);

	void modManager(MemberVO vo);
}
