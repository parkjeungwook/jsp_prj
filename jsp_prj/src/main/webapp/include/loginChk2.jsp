<%@page import="java.util.Random"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
String sesName=null;
	session.setAttribute("sesName", null);
if( new Random().nextBoolean()){
	session.setAttribute("sesName", "hong");
	//sesName = (String)session.getAttribute("name");
}//end if
%>