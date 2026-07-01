package kr.co.sist.board;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.GetConnection;

public class BoardDAO {
	private static BoardDAO bDAO;
	private BoardDAO() {
	}//LoginDAO
	
	public static BoardDAO getInstance() {
		if(bDAO == null) {
			bDAO = new BoardDAO();
		}//end if
		return bDAO;
	}
	
	public int selectTotalCnt()throws SQLException {
		int totalCnt = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		try {
			// 1. 커넥션 얻기 
			con = gc.getConn("dbcp");
			// 2. 쿼리문 수행 객체 얻기 
			String selectTotal= 
					"select count(*) cnt from board";
			pstmt = con.prepareStatement(selectTotal);
			// 바인드 변수에 값 설정 
			// 3. 쿼리문 수행 결과 얻기 
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalCnt=rs.getInt("cnt");
			}//end if 
		} finally {
			// 4. 연결 끊기 
			gc.dbClose(rs, pstmt, con);
		}//end finally
		return totalCnt;
	}//selectTotalCnt
	
	public List<BoardDTO> selectBoard(RangeDTO rDTO)throws SQLException {
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		try {
			// 1. 커넥션 얻기 
			con = gc.getConn("dbcp");
			// 2. 쿼리문 수행 객체 얻기 
			StringBuilder selectBoard = new StringBuilder();
			
			selectBoard
				.append("	select num, id, title, input_date, cnt				")
				.append("	from (select num, id, title, input_date, cnt,		")
				.append("	row_number() over(order by input_date desc) rnum	")
				.append("	from board)											")
				.append("	where rnum between ? and ?							");
				
			
			pstmt = con.prepareStatement(selectBoard.toString());
			// 바인드 변수에 값 설정 
			pstmt.setInt(1, rDTO.getStartNum());
			pstmt.setInt(2, rDTO.getEndNum());
			
			// 3. 쿼리문 수행 결과 얻기 
			rs = pstmt.executeQuery();
			BoardDTO bDTO = null;
			while(rs.next()) {
				bDTO = new BoardDTO();
				bDTO.setNum(rs.getInt("num"));
				bDTO.setId(rs.getString("id"));
				bDTO.setTitle(rs.getString("title"));
				bDTO.setInputDate(rs.getDate("input_date"));
				bDTO.setCnt(rs.getInt("cnt"));
				
				boardList.add(bDTO);
			}//end while 
		} finally {
			// 4. 연결 끊기 
			gc.dbClose(rs, pstmt, con);
		}//end finally
		return boardList;
	}//selectBoard
	
	public BoardDTO selectBoardDetail(int num)throws SQLException {
		BoardDTO bDTO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		try {
			// 1. 커넥션 얻기 
			con = gc.getConn("dbcp");
			// 2. 쿼리문 수행 객체 얻기 
			StringBuilder selectBoardDetail = new StringBuilder();
			
			selectBoardDetail
				.append("	select id, title, content, input_date, cnt, ip	")
				.append("	from board										")
				.append("	where num=?										");
				
			
			pstmt = con.prepareStatement(selectBoardDetail.toString());
			// 바인드 변수에 값 설정 
			pstmt.setInt(1, num);
			
			// 3. 쿼리문 수행 결과 얻기 
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bDTO = new BoardDTO();
				bDTO.setNum(rs.getInt("num"));
				bDTO.setId(rs.getString("id"));
				bDTO.setTitle(rs.getString("title"));
				bDTO.setInputDate(rs.getDate("input_date"));
				bDTO.setCnt(rs.getInt("cnt"));
				bDTO.setIp(rs.getString("ip"));
				
				Clob clob = rs.getClob("content");
				StringBuilder content = new StringBuilder();
				if(clob != null) { //글의 내용이 존재 
					BufferedReader br = new BufferedReader(clob.getCharacterStream());
					try {
						String temp = "";
						while((temp=br.readLine()) != null) {
							content.append(temp).append("\n");
						}//end while
						if(br != null) { br.close();}//end if
					}catch (IOException ie) {
						ie.printStackTrace();
					}//end catch
				}//end if
				bDTO.setContent(content.toString());
			}//end if 
		} finally {
			// 4. 연결 끊기 
			gc.dbClose(rs, pstmt, con);
		}//end finally
		return bDTO;
	}//selectBoardDetail
	
	public void insertBoard(BoardDTO bDTO)throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		GetConnection gc = GetConnection.getInstance();
		try {
			// 1. 커넥션 얻기 
			con = gc.getConn("dbcp");
			// 2. 쿼리문 수행 객체 얻기 
			StringBuilder insertBoard = new StringBuilder();
			
			insertBoard
			.append("	insert into board(num, id, title, content, ip) ")
			.append("	values (seq_board.nextval,?,?,?,?)		");
			
			
			pstmt = con.prepareStatement(insertBoard.toString());
			// 바인드 변수에 값 설정 
			pstmt.setString(1, bDTO.getId());
			pstmt.setString(2, bDTO.getTitle());
			pstmt.setString(3, bDTO.getContent());
			pstmt.setString(4, bDTO.getIp());
			
			// 3. 쿼리문 수행 결과 얻기 
			pstmt.executeUpdate();
			
		} finally {
			// 4. 연결 끊기 
			gc.dbClose(null, pstmt, con);
		}//end finally
	}//insertBoard
	
	
	
}//class
