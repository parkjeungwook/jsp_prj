package kr.co.sist.mypage;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.json.simple.JSONObject;

import kr.co.sist.chipher.DataDecryption;
import kr.co.sist.member.memberDTO;

public class MyPageService {
	@SuppressWarnings("unchecked")
	public String searchMyPage(String id) {
		JSONObject jsonObj = new JSONObject();
		memberDTO mDTO = null;
		
		MyPageDAO mpDAO = MyPageDAO.getInstance();
		try {
			mDTO = mpDAO.selectUserInfo(id);
			if(mDTO != null) {
				DataDecryption dd = new DataDecryption("a012345678912345");
				try {
				mDTO.setName(dd.decrypt(mDTO.getName()));
				mDTO.setEmail(dd.decrypt(mDTO.getEmail()));
				mDTO.setPhone(dd.decrypt(mDTO.getPhone()));
				} catch (Exception e) {
					e.printStackTrace();
				}//end catch
				jsonObj.put("name", mDTO.getName());
				jsonObj.put("email", mDTO.getEmail());
				jsonObj.put("phone", mDTO.getPhone());
				jsonObj.put("zipcode", mDTO.getZipcode());
				jsonObj.put("address", mDTO.getAddress());
				jsonObj.put("address2", mDTO.getAddress2());
				jsonObj.put("profile", mDTO.getProfile());
				jsonObj.put("ip", mDTO.getIp());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EEE a kk:mm:ss");
				jsonObj.put("input_date", sdf.format(mDTO.getInputeDate()));
			}//end if
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		return jsonObj.toJSONString();
	}//searchLogin
}//class
