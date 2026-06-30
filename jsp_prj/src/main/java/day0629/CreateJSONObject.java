package day0629;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CreateJSONObject {

	@SuppressWarnings("unchecked")
	public String jsonObj() {
		
		// 1. JSONObject을 생성 
		JSONObject jsonObj = new JSONObject();
		
		// 2. 값 할당 ) 이름이 없으면 추가되고, 이름이 있다면 덮어쓴다. 
		jsonObj.put("name", "홍길동");
		jsonObj.put("age", 20);
		jsonObj.put("addr", "서울시 강남구 대치동");
		jsonObj.put("addr", "서울시 동대문구 동대문동");
		jsonObj.put("flag", true);
		
		
		// 3. 생성 된 객체를 JSONObject 형태의 문자열로 반환 
		return jsonObj.toJSONString();
		
	}//jsonObj
	
	@SuppressWarnings("unchecked")
	public String jsonArray() {
		
		// 1. 객체 생성 
		JSONArray jsonArr = new JSONArray();
		
		// 2. JSONObject 생성 
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name", "테스트");
		jsonObj.put("age", 20);
		
		JSONObject jsonObj2 = new JSONObject();
		jsonObj2.put("name", "테스트2");
		jsonObj2.put("age", 200);
		
		// 3. 생성된 JSONObject을 JSONArray에 할당 
		jsonArr.add(jsonObj);
		jsonArr.add(jsonObj2);
		
		// 3. 생성된 객체를 JSONArray형태의 문자열로 반환 
		return jsonArr.toJSONString();
	}//jsonArray
	
	@SuppressWarnings("unchecked")
	public String compositJson() {
		// 1. 데이터와 부가적인 정보를 가지는 JSONObject을 생성 
		JSONObject infoJson = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EEEE");
		infoJson.put("pubDate", sdf.format(new Date()));
		infoJson.put("auth", "테스트");
		
		// 2. 테이터를 저장할 jsonArr을 생성  
		JSONArray jsonArr = new JSONArray();
		if ( new Random().nextBoolean()) {
			// 2-1 데이터를 저장할 JSONObject을 생성
			JSONObject dataJsonObject = new JSONObject();
			// 2-2 데이터를 저장 
			dataJsonObject.put("name", "테스트");
			dataJsonObject.put("age", 20);
			// 2-3 JSONArray에 데이터를 가진 JSONObject을 저장 
			jsonArr.add(dataJsonObject);
			
			// 2-1 데이터를 저장할 JSONObject을 생성
			JSONObject dataJsonObject2 = new JSONObject();
			// 2-2 데이터를 저장 
			dataJsonObject2.put("name", "테스트");
			dataJsonObject2.put("age", 20);
			// 2-3 JSONArray에 데이터를 가진 JSONObject을 저장 
			jsonArr.add(dataJsonObject2);
		}//end if
		
		// 부가적인 정보 
		infoJson.put("dataLength", jsonArr.size());
		infoJson.put("resultFlag", !jsonArr.isEmpty());
		
		// 데이터를 추가 
		infoJson.put("data", jsonArr);
		
		return infoJson.toJSONString();
	}//compositJson
}//class
