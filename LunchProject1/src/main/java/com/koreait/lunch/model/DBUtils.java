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

public class DBUtils {
	public static Connection getCon(Connection con) {
		try {
			Context initctx = new InitialContext();
			
			Context envctx = (Context) initctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool");
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection con) {
		if(con != null) {try {con.close();}catch (Exception e) {e.printStackTrace();}}
	}
	
}
