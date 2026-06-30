package kr.co.sist.user.login;

import java.sql.SQLException;

import kr.co.sist.chipher.DataDecryption;
import kr.co.sist.member.memberDTO;

public class LoginService {
	public memberDTO searchLogin(LoginDTO lDTO) {
		memberDTO mDTO = null;
		
		LoginDAO lDAO = LoginDAO.getInstance();
		try {
			mDTO = lDAO.selectLogin(lDTO);
			if(mDTO != null) {
				DataDecryption dd = new DataDecryption("a012345678912345");
				mDTO.setName(dd.decrypt(mDTO.getName()));
			}//end if
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}//end catch
		return mDTO;
	}//searchLogin
}//class
