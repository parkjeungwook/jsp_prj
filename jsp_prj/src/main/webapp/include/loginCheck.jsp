<%@page import="kr.co.sist.member.memberDTO"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
memberDTO mDTO = (memberDTO)session.getAttribute("userInfo");
pageContext.setAttribute("mDTO", mDTO);
%>
<c:if test="${empty mDTO }">
	<c:redirect url="${ CommonUrl }/index.html"/>
</c:if>