package kr.co.sist.user.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.sist.dao.GetConnection;
import kr.co.sist.member.memberDTO;

public class LoginDAO {
	private static LoginDAO lDAO;
	private LoginDAO() {
	}//LoginDAO
	
	public static LoginDAO getInstance() {
		if(lDAO == null) {
			lDAO = new LoginDAO();
		}//end if
		return lDAO;
	}
	
	public memberDTO selectLogin(LoginDTO lDTO)throws SQLException {
		memberDTO mDTO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		try {
			// 1. 커넥션 얻기 
			con = gc.getConn("dbcp");
			// 2. 쿼리문 수행 객체 얻기 
			String selectId = "select name,zipcode from web_member where id = ? and password = ? and cesession='N'";
			pstmt = con.prepareStatement(selectId);
			// 바인드 변수에 값 설정 
			pstmt.setString(1, lDTO.getId());
			pstmt.setString(2, lDTO.getPassword());
			// 3. 쿼리문 수행 결과 얻기 
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mDTO = new memberDTO();
				mDTO.setId(lDTO.getId());
				mDTO.setName(rs.getString("name"));
				mDTO.setZipcode(rs.getString("zipcode"));
			}//end if 
		} finally {
			// 4. 연결 끊기 
			gc.dbClose(rs, pstmt, con);
		}//end finally
		return mDTO;
	}//selectLogin
}//class
