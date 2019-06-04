package kr.co.board2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import kr.co.board2.config.DBConfig;
import kr.co.board2.config.SQL;
import kr.co.board2.vo.TermsVO;
import kr.co.board2.vo.UserVO;

public class UserDao {

	private static UserDao instance = new UserDao();
	
	public static UserDao getInstance() {
		return instance;
	}
	
	private UserDao() {}
	
	
	public TermsVO getTerms() throws Exception {
		
		//1´Ü°è, 2´Ü°è
		Connection conn = DBConfig.getConnection();
		//3´Ü°è
		Statement stmt = conn.createStatement();
		//4´Ü°è
		ResultSet rs = stmt.executeQuery(SQL.SELECT_TERMS);
		//5´Ü°è
		TermsVO vo = new TermsVO();
		if(rs.next()) {
			vo.setTerms(rs.getString(1));
			vo.setPrivacy(rs.getString(2));
		}
		
		//6´Ü°è
		rs.close();
		stmt.close();
		conn.close();
		
		return vo;
	}
	
	public void insertUser(UserVO vo) throws Exception {
		// 1´Ü°è, 2´Ü°è
		Connection conn = DBConfig.getConnection();
		
		// 3´Ü°è
		PreparedStatement psmt = conn.prepareStatement(SQL.INSERT_USER);
		psmt.setString(1, vo.getUid());
		psmt.setString(2, vo.getPass());
		psmt.setString(3, vo.getName());
		psmt.setString(4, vo.getNick());
		psmt.setString(5, vo.getEmail());
		psmt.setString(6, vo.getHp());
		psmt.setString(7, vo.getZip());
		psmt.setString(8, vo.getAddr1());
		psmt.setString(9, vo.getAddr2());
		psmt.setString(10, vo.getRegip());
		
		// 4´Ü°è
		psmt.executeUpdate();
		
		// 5´Ü°è
		// 6´Ü°è
		psmt.close();
		conn.close();
	}
	
	public int uidCheck(String uid) throws Exception {
		
		// 1´Ü°è, 2´Ü°è
		Connection conn = DBConfig.getConnection();
		// 3´Ü°è
		PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_USER_COUNT);
		psmt.setString(1, uid);
		
		// 4´Ü°è
		ResultSet rs = psmt.executeQuery();
		
		// 5´Ü°è
		int count = 0;
		
		if(rs.next()) {
			count = rs.getInt(1);			
		}
		
		// 6´Ü°è
		rs.close();
		psmt.close();
		conn.close();
		
		return count;
	}
	
	public UserVO login(String uid, String pass) throws Exception {
		// 1´Ü°è, 2´Ü°è
		Connection conn = DBConfig.getConnection();
		
		// 3´Ü°è
		PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_USER);
		psmt.setString(1, uid);
		psmt.setString(2, pass);
		
		// 4´Ü°è
		ResultSet rs = psmt.executeQuery();
		
		// 5´Ü°è
		UserVO vo = null;
		
		if(rs.next()) {
			vo = new UserVO();
			vo.setUid(rs.getString(1));
			vo.setPass(rs.getString(2));
			vo.setName(rs.getString(3));
			vo.setNick(rs.getString(4));
			vo.setEmail(rs.getString(5));
			vo.setHp(rs.getString(6));
			vo.setGrade(rs.getInt(7));
			vo.setZip(rs.getString(8));
			vo.setAddr1(rs.getString(9));
			vo.setAddr2(rs.getString(10));
			vo.setRegip(rs.getString(11));
			vo.setRdate(rs.getString(12));
		}
		
		
		// 6´Ü°è
		rs.close();
		psmt.close();
		conn.close();
		
		return vo;
	}
	
	
}

