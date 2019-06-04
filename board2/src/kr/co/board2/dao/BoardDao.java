package kr.co.board2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.co.board2.config.DBConfig;
import kr.co.board2.config.SQL;
import kr.co.board2.vo.BoardVO;

public class BoardDao {
	
	private static BoardDao instance = new BoardDao();
	
	public static BoardDao getInstance() {
		return instance;
	}
	
	private BoardDao() {}
	
	
	public void insertBoard(BoardVO bvo) throws Exception {
		
		// 1´Ü°è, 2´Ü°è
		Connection conn = DBConfig.getConnection();
		
		// 3´Ü°è
		PreparedStatement psmt = conn.prepareStatement(SQL.INSERT_BOARD);
		psmt.setString(1, bvo.getTitle());
		psmt.setString(2, bvo.getContent());
		psmt.setInt(3, bvo.getFile());
		psmt.setString(4, bvo.getUid());
		psmt.setString(5, bvo.getRegip());
		
		// 4´Ü°è
		psmt.executeUpdate();
		
		// 5´Ü°è
		// 6´Ü°è
		psmt.close();
		conn.close();
	}
	
	public List<BoardVO> selectList() throws Exception {
		// 1´Ü°è, 2´Ü°è
		Connection conn = DBConfig.getConnection();
		
		// 3´Ü°è
		PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_LIST);
		psmt.setInt(1, 1);
		
		// 4´Ü°è
		ResultSet rs = psmt.executeQuery();
		
		// 5´Ü°è
		List<BoardVO> list = new ArrayList<>();
		
		while(rs.next()) {
			
			BoardVO vo = new BoardVO();
			vo.setSeq(rs.getInt(1));
			vo.setParent(rs.getInt(2));
			vo.setComment(rs.getInt(3));
			vo.setCate(rs.getString(4));
			vo.setTitle(rs.getString(5));
			vo.setContent(rs.getString(6));
			vo.setFile(rs.getInt(7));
			vo.setHit(rs.getInt(8));
			vo.setUid(rs.getString(9));
			vo.setRegip(rs.getString(10));
			vo.setRdate(rs.getString(11));
			vo.setNick(rs.getString(12));
			
			list.add(vo);
		}
		
		// 6´Ü°è
		rs.close();
		psmt.close();
		conn.close();
		
		
		return list;
	}
	
	
	
	
	
	

}