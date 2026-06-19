<%@page import="java.util.ArrayList"%>
<%@page import="day0612.TestDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	// 디자인을 응답을 하지 않기 때문에 디자인에 관련된 코드를 정의할 필요가 없다.
	// 업무로직에 집중한다.
	String name = "홍길동";
	List<TestDTO> list= new ArrayList<TestDTO>();
	list.add(new TestDTO("홍길동",20));
	list.add(new TestDTO("김기돌",21));
	list.add(new TestDTO("윤인성",22));
	list.add(new TestDTO("정동성",23));
	
	request.setAttribute("name", name);
	request.setAttribute("memberList", list);

	// 1. 이동할 페이지 설정 
	RequestDispatcher rd = request.getRequestDispatcher("forwardB.jsp");
	// 2. 페이지 이동 
	rd.forward(request, response);


%>
