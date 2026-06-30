<%@page import="kr.co.sist.chipher.DataDecryption"%>
<%@page import="kr.co.sist.chipher.DataEncryption"%>
<%@page import="java.util.Base64"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.security.MessageDigest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/site_Property.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String password="1234";
	
	// 1. 알고리즘을 설정하여 객체를 얻는다. 
	MessageDigest md = MessageDigest.getInstance("SHA-1");
	// 2. 평문에 알고리즘 적용 
	md.update(password.getBytes());
	// 3. 알고리즘을 적용받은 
	byte[] algorithm = md.digest();
	String sha = new String(Base64.getEncoder().encode(algorithm));
	
%>

plain test <%= password %> : 일방향 Hash <%= sha %>
<%
String sha2 = DataEncryption.messageDigest("SHA-1", password);
%>
<br>
<%= sha2 %>
<br>
<%
String key = "a123456789012345"; 
DataEncryption de = new DataEncryption(key);
String name = "테스트";
String encription = de.encrypt(name);

DataDecryption dd = new DataDecryption(key);
%>
원본 문자열 : <%= name %> / 암호화된 문자열 : <%= encription%>
<br>
복호화된 문자열 : <%= dd.decrypt(encription) %>

