<%@page import="kr.co.sist.member.memberDTO"%>
<%@page import="kr.co.sist.user.login.LoginService"%>
<%@page import="kr.co.sist.chipher.DataEncryption"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/site_Property.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="lDTO" class="kr.co.sist.user.login.LoginDTO" scope="page"/>
<jsp:setProperty name="lDTO" property="*"/>
<%
// 비번은 일방향 해시
lDTO.setPassword(DataEncryption.messageDigest("SHA-1", lDTO.getPassword()));

// 로그인 수행 
// 성공 하면 session에 남길거임 
LoginService ls = new LoginService();
memberDTO mDTO = ls.searchLogin(lDTO);

if(mDTO != null){
	//세션에 정보를 추가 
	session.setAttribute("userInfo", mDTO);
	//로그인 히스토리 남기고
	//메인화면으로 이동 
	String url = application.getAttribute("CommonUrl")+"/index.jsp";
	//response.sendRedirect(application.getAttribute("CommonUrl")+"/index.jsp");
	%>
	<script type="text/javascript">
	location.replace("<%= url %>")
	</script>
	<%
} else {
response.sendRedirect("loginForm.jsp?flag=N");
}
%>