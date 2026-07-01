package kr.co.sist.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.sist.dao.GetConnection;
import kr.co.sist.member.memberDTO;

public class MyPageDAO {
	private static MyPageDAO mpDAO;
	private MyPageDAO() {
	}//LoginDAO
	
	public static MyPageDAO getInstance() {
		if(mpDAO == null) {
			mpDAO = new MyPageDAO();
		}//end if
		return mpDAO;
	}
	
	public memberDTO selectUserInfo(String id)throws SQLException {
		memberDTO mDTO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		try {
			// 1. 커넥션 얻기 
			con = gc.getConn("dbcp");
			// 2. 쿼리문 수행 객체 얻기 
			String selectId = 
					"select name,email,phone,zipcode,address,address2,profile,ip,inputdate from web_member where id = ?";
			pstmt = con.prepareStatement(selectId);
			// 바인드 변수에 값 설정 
			pstmt.setString(1, id);
			// 3. 쿼리문 수행 결과 얻기 
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mDTO = new memberDTO();
				mDTO.setId(id);
				mDTO.setName(rs.getString("name"));
				mDTO.setEmail(rs.getString("email"));
				mDTO.setPhone(rs.getString("phone"));
				mDTO.setZipcode(rs.getString("zipcode"));
				mDTO.setAddress(rs.getString("address"));
				mDTO.setAddress2(rs.getString("address2"));
				mDTO.setProfile(rs.getString("profile"));
				mDTO.setIp(rs.getString("ip"));
				mDTO.setInputeDate(rs.getDate("inputdate"));
			}//end if 
		} finally {
			// 4. 연결 끊기 
			gc.dbClose(rs, pstmt, con);
		}//end finally
		return mDTO;
	}//selectUserInfo
}//class
