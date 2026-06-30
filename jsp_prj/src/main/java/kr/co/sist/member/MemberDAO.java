package kr.co.sist.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.sist.dao.GetConnection;

public class MemberDAO {
	public static MemberDAO mDAO;
	private MemberDAO(){
	}
	
	public static MemberDAO getInstance() {
		if(mDAO == null) {
			mDAO=new MemberDAO();
		}//end if
		return mDAO;
	}//getInstance
	
	public boolean selectId(String id) throws SQLException{
		//String searchId = "";
		boolean idFlag = false;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		try {
			// 1. 커넥션 얻기 
			con = gc.getConn("dbcp");
			// 2. 쿼리문 수행 객체 얻기 
			String selectId = "select 1 from web_member where id = ?";
			pstmt = con.prepareStatement(selectId);
			// 바인드 변수에 값 설정 
			pstmt.setString(1, id);
			// 3. 쿼리문 수행 결과 얻기 
			rs = pstmt.executeQuery();
			
			idFlag=rs.next();//레코드가 존재하면 true 존재하지 않으면 false 
		} finally {
			// 4. 연결 끊기 
			gc.dbClose(rs, pstmt, con);
		}//end finally
		
		return idFlag;
	}//selectId
	

	/**
	 * 회원의 정보
	 * @param mDTO
	 * @throws SQLException
	 */
	public void insertWebMember(memberDTO mDTO) throws SQLException{
		// 1. JDNI사용 객체 생성. 
		// 2. DBCP에서 DataSource 얻기
		// 3. DataSource에서 Connection 얻기 
		Connection con = null;
		PreparedStatement pstmt = null;
		
		GetConnection gc = GetConnection.getInstance();
		try {
			con = gc.getConn("dbcp");
		// 4. 쿼리문 생성 객체 얻기 
			StringBuilder insertMember = new StringBuilder();

			insertMember
			.append("	insert into web_member	")
			.append("	(ID, PASSWORD, NAME, EMAIL, PHONE, ZIPCODE, ADDRESS, ADDRESS2, IP, SMSRECEIVEYN, EMAILRECEIVEYN)	")
			.append("	values(?,?,?,?,?,?,?,?,?,?,?)	");
			pstmt = con.prepareStatement(insertMember.toString());
		// 5. 바인드 변수에 값 설정 
			pstmt.setString(1, mDTO.getId());
			pstmt.setString(2, mDTO.getPassword());
			pstmt.setString(3, mDTO.getName());
			pstmt.setString(4, mDTO.getEmail());
			pstmt.setString(5, mDTO.getPhone());
			pstmt.setString(6, mDTO.getZipcode());
			pstmt.setString(7, mDTO.getAddress());
			pstmt.setString(8, mDTO.getAddress2());
			pstmt.setString(9, mDTO.getIp());
			pstmt.setInt(10, mDTO.getSmsReceiveYN());
			pstmt.setInt(11, mDTO.getEmailReceiveYN());
		// 6. 쿼리문 실행 결과 얻기 
			pstmt.executeUpdate();
			
		} finally {
			// 7. 연결 끊기
			gc.dbClose(null, pstmt, con);
		}
		
		
	}//insertWebMember

	/**
	 * 회원의 취미
	 * @param mDTO
	 * @throws SQLException
	 */
	public void insertWebMemberHobby(memberDTO mDTO) throws SQLException{
		// 1. JDNI사용 객체 생성. 
				// 2. DBCP에서 DataSource 얻기
				// 3. DataSource에서 Connection 얻기 
				Connection con = null;
				PreparedStatement pstmt = null;
				
				GetConnection gc = GetConnection.getInstance();
				try {
					con = gc.getConn("dbcp");
				// 4. 쿼리문 생성 객체 얻기 
					StringBuilder insertMember = new StringBuilder();

					insertMember
					.append("	insert into web_member_hobby	")
					.append("	(ID, hobby)	")
					.append("	values(?,?)	");
					pstmt = con.prepareStatement(insertMember.toString());
				// 5. 바인드 변수에 값 설정
					String[] hobby = mDTO.getHobby();
					for(int i=0; i < hobby.length; i++) {
						pstmt.setString(1, mDTO.getId());
						pstmt.setString(2, hobby[i]);
						pstmt.executeUpdate();
					}
					
				} finally {
					// 7. 연결 끊기
					gc.dbClose(null, pstmt, con);
				}
	}//insertWebMember
}//class
