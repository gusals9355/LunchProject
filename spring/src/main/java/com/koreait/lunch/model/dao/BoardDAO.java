package com.koreait.lunch.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import com.koreait.lunch.model.vo.BoardVO;
import com.koreait.lunch.model.DBUtils;
import org.springframework.stereotype.Repository;

@Repository("boardDAO")
public interface BoardDAO {
	void insertBoard(BoardVO vo);

	void insertHeart(BoardVO vo);
	//	final String sql = "insert into favorite(no, id) values(?,?)";

	List<BoardVO> getAllBoard();

	BoardVO getBoard(BoardVO vo);

	void delBoard(BoardVO vo);

	void removeHeart(BoardVO vo);

	//TODO: 사진, map까지 수정
	void modBoard(BoardVO vo);
}