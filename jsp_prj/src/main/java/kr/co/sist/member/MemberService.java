package kr.co.sist.member;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import kr.co.sist.chipher.DataEncryption;

public class MemberService {
	
	/**
	 * @param id
	 * @return true 아이디 존재 false 아이디 없음 
	 */
	public boolean searchDupId(String id) {
		boolean idFlag = false;
		
		MemberDAO mDAO=MemberDAO.getInstance();
		
		try {
			idFlag=mDAO.selectId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch 

		return idFlag;
	}//searchDupId
	
	
	public boolean addMember(memberDTO mDTO) {
		boolean flag = false;
		//DAO클래스를 사용하여 DB 추가 작업 수행.
		MemberDAO mDAO = MemberDAO.getInstance();
		try{
			
			// 일방향 해시 : 비밀번호
			mDTO.setPassword(DataEncryption.messageDigest("SHA-1", mDTO.getPassword()));
			// 암호화 
			String key = "a012345678912345";
			DataEncryption de = new DataEncryption(key);

			mDTO.setName(de.encrypt(mDTO.getName()));
			mDTO.setEmail(de.encrypt(mDTO.getEmail()));
			mDTO.setPhone(de.encrypt(mDTO.getPhone1()+"-"+mDTO.getPhone2()+"-"+mDTO.getPhone3()));
			
			
			mDAO.insertWebMember(mDTO);
			flag=true;// 취미가 실패하더도 true 반환 
			if(mDTO.getHobby() != null) {
				mDAO.insertWebMemberHobby(mDTO);
			}//end if
			flag = true;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (NoSuchAlgorithmException nae) {
			nae.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}//addMember
}//MemberService
