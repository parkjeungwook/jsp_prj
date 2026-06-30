<%@page import="kr.co.sist.dao.GetConnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:catch var="e">
<%
//1. JNDI 사용객체 생성 
//Context ctx = new InitialContext();

//이름으로 DBCP에 DB와 연결하고 있는 객체 
//DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/dbcp");
//Connection con = ds.getConnection();
GetConnection getConn = GetConnection.getInstance();

Connection con = getConn.getConn("dbcp");
pageContext.setAttribute("con", con);

//con.close();
getConn.dbClose(null, null, con);
%>
<c:out value="${con}"/>
</c:catch>
<c:if test="${not empty e}">
<c:out value="${e}"></c:out>
</c:if>
</body>
</html>