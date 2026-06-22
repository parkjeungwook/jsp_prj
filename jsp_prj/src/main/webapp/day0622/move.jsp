<%@page import="day0622.CounterDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
CounterDTO cDTO = (CounterDTO)request.getAttribute("cDTO");
CounterDTO cDTO2 = (CounterDTO)session.getAttribute("cDTO");
%>
<%= cDTO %>
