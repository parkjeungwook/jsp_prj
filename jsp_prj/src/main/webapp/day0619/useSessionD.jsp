<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
//세션의 값을 삭제하고(세션은 존재 값 삭제)
session.removeAttribute("name");
log("삭제 된 후"+session.getAttribute("name"));

//브라우저에 할당된 무효화
session.invalidate();
//메인화면으로 이동
//response.sendRedirect("useSessionA.jsp");
%>
<!--
<script type="text/javascript">
location.href="http://localhost:8081/jsp_prj/day0619/useSessionA.jsp";
</script> 
-->
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="refresh" content="0;http://localhost:8081/jsp_prj/day0619/useSessionA.jsp">
<body>
</body>
</head>
</html>