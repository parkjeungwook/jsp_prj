<%@page import="kr.co.sist.member.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/site_Property.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="http://192.168.10.81/html_prj/common/images/favicon.ico"/>
<!-- BootStrap CDN -->
<c:import url="${ CommonUrl }/frogments/external_file.jsp"/>
<style type="text/css">
#wrap{ 
	width: 470px; height: 379px; margin: 0px auto; 
}
#idDivFrm{
	position: relative;
	width: 464px;
	height: 366px;
	background: #FFF url(images/id_background.png) no-repeat;
}
#inputWrap{
	position: absolute; top: 190px; left: 60px;
}
#viewResult{
	position: absolute; top: 230px; left: 70px;
}

</style>
<script type="text/javascript">
	$(function() {
		$("#btnUseId").click(function(){
			chkNull();
		})//click
		$("#id").keyup(function(evt){
			if(evt.which == 13){
			chkNull();
			}//end if
		});//click
	})
	
	function chkNull(){
		var id = $("#id").val();
		if(id.replace(/ /g,"") == ""){
			alert("아이디는 필수 입력");
			$("#id").val("");
			return;
		}//end if
			$("#dupFrm").submit();
	}//chkNull
	
	function sendId(id){
		opener.window.document.joinForm.id.value=id;
		self.close();
	}//sendId

</script>

</head>
<body>
<div id="wrap">
	<div id="idDivFrm">
		<div id="inputWrap">
			<form name="dupFrm" id="dupFrm" action="${CommonUrl}/memberJoin/idDup.jsp">
			<label>아이디</label><input type="text" name="id" id="id" value="${param.id}"/>
			<input type="text" style="display: none;">
			<input type="button" value="중복확인" class="btn btn-outline-secondary btn-sm" id="btnUseId"/>
			</form>
		</div>
		<div id="viewResult">
		<%
		String id = request.getParameter("id");
		if(id != null && !"".equals(id) ){
			//중복확인 시작 
			MemberService ms = new MemberService();
			boolean idFlag = ms.searchDupId(id);
			pageContext.setAttribute("idFlag", !idFlag);
		%>
		입력하신 아이디인 <Strong style="font-size: 30px"><c:out value="${ param.id }"/></Strong>
		<c:choose>
			<c:when test="${ idFlag }">
				<span style="color: #0000FF;"> 사용가능 </span>합니다.	<a href="javascript:sendId('${ param.id }')">사용</a>
			</c:when>
			<c:otherwise>
				<span style="color: #FF0000;"> 이미 사용중 </span>입니다.
			</c:otherwise>
		</c:choose>
					
		<% 
		}//end if
		%>
		</div>
	</div>
</div>
</body>
</html>