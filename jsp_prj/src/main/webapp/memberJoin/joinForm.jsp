<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/site_Property.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko" data-bs-theme="auto">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원가입 정보입력</title>

<c:import url="${CommonUrl}/frogments/external_file.jsp"></c:import>

<script type="text/javascript">
$(function() {
	$("#duplicateButton").click(function(){
		var id = $("#id").val();
		if(id == ""){
			alert("아이디는 필수 입력");
			return;
		}

		var openPage = "idDup.jsp?id=" + id;
		window.open(openPage, "idDupWin",
			"width=487,height=370,top=" + (window.screenY + 300) + ",left=" + (window.screenX + 400));
	});
	
	$("#postcodeButton").click(function(){
		find_zipcode();
	});
});
</script>
<c:import url="${CommonUrl}/frogments/findZipcode.jsp"></c:import>
</head>
<body>
	<header data-bs-theme="dark">
		<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
			<c:import url="${CommonUrl}/frogments/navigationBar.jsp"></c:import>
		</nav>
	</header>

	<main class="member-page" style="margin-top: 50px;">
		<section class="member-shell">
			<h1 class="member-title">회원가입</h1>
			<div class="member-steps" aria-label="회원가입 단계">
				<div class="member-step"><span class="member-step-number">1</span>약관동의</div>
				<div class="member-step active"><span class="member-step-number">2</span>정보입력</div>
				<div class="member-step"><span class="member-step-number">3</span>가입완료</div>
			</div>

			<% if (request.getAttribute("errorMessage") != null) { %>
			<div class="member-error"><%=request.getAttribute("errorMessage")%></div>
			<% } %>

			<form id="joinForm" name="joinForm" method="post"
				action="${CommonUrl}/memberJoin/joinComplete.jsp" novalidate>
				<input type="hidden" name="smsReceiveYN" value="${smsReceiveYN}">
				<input type="hidden" name="emailReceiveYN" value="${emailReceiveYN}">
				<input type="hidden" id="codeChecked" value="N">

				<table class="member-table">
					<tbody>
						<tr>
							<th><label for="id">아이디 <span class="member-required">*</span></label></th>
							<td>
								<div class="member-inline">
									<input class="member-input" type="text" id="id" name="id" value="test"
										maxlength="20" autocomplete="username" placeholder="영문 또는 숫자 4~20자">
									<button class="member-button member-button-light member-button-small"
										type="button" id="duplicateButton">중복확인</button>
								</div>
							</td>
						</tr>
						<tr>
							<th><label for="password">비밀번호 <span class="member-required">*</span></label></th>
							<td>
								<input class="member-input" type="password" id="password" name="password"
									maxlength="20" autocomplete="new-password" value="1234">
								<p class="member-help">영문, 숫자, 특수문자를 포함한 8~20자</p>
							</td>
						</tr>
						<tr>
							<th><label for="passwordConfirm">비밀번호 확인 <span class="member-required">*</span></label></th>
							<td>
								<input class="member-input" type="password" id="passwordConfirm" name="passwordConfirm"
									maxlength="20" autocomplete="new-password" value="1234">
							</td>
						</tr>
						<tr>
							<th><label for="name">이름 <span class="member-required">*</span></label></th>
							<td>
								<input class="member-input" type="text" id="name" name="name"
									maxlength="30" autocomplete="name" value="테스트">
							</td>
						</tr>
						<tr>
							<th><label for="email">이메일 <span class="member-required">*</span></label></th>
							<td>
								<input class="member-input" type="email" id="email" name="email"
									maxlength="100" autocomplete="email" placeholder="example@ballpick.com" value="Test@test.com">
							</td>
						</tr>
						<tr>
							<th><label>취미 <span class="member-required">*</span></label></th>
							<td>
								<input type="checkbox" name="hobby" value="낚시">낚시
								<input type="checkbox" name="hobby" value="등산" checked="checked">등산
								<input type="checkbox" name="hobby" value="음악감상" checked="checked">음악감상
								<input type="checkbox" name="hobby" value="운동">운동
							</td>
						</tr>
						<tr>
							<th><span class="member-label">휴대폰 <span class="member-required">*</span></span></th>
							<td>
								<div class="member-phone">
									<select class="member-select" id="phone1" name="phone1" aria-label="휴대폰 앞자리">
										<option value="010">010</option>
										<option value="011">011</option>
										<option value="016">016</option>
										<option value="017">017</option>
										<option value="019">019</option>
									</select>
									<input class="member-input" type="text" id="phone2" name="phone2"
										maxlength="4" inputmode="numeric" aria-label="휴대폰 중간자리" value="1234">
									<input class="member-input" type="text" id="phone3" name="phone3"
										maxlength="4" inputmode="numeric" aria-label="휴대폰 끝자리" value="5678">
								</div>
							</td>
						</tr>
						<tr>
							<th><span class="member-label">주소 <span class="member-required">*</span></span></th>
							<td>
								<div class="member-address">
									<div class="member-address-top">
										<input class="member-input" type="text" id="zipcode" name="zipcode"
											readonly placeholder="우편번호" value="12345">
										<button class="member-button member-button-light member-button-small"
											type="button" id="postcodeButton">주소검색</button>
									</div>
									<input class="member-input" type="text" id="address" name="address"
										readonly placeholder="기본주소" value="서울시 강남구 대치동">
									<input class="member-input" type="text" id="address2" name="address2"
										maxlength="100" placeholder="상세주소" value="123번지">
								</div>
							</td>
						</tr>
					</tbody>
				</table>

				<div id="clientError" class="member-error" hidden></div>
				<div class="member-actions">
					<a class="member-button member-button-light"
						href="${CommonUrl}/memberJoin/joinAgree.jsp">이전</a>
					<button class="member-button" type="submit">가입하기</button>
				</div>
			</form>
		</section>
	</main>

	<footer class="container">
		<c:import url="${CommonUrl}/frogments/footer.jsp"/>
	</footer>

	<script src="${CommonUrl}/common/js/bootstrap.bundle.min.js"
		integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
		class="astro-vvvwv3sm"></script>
</body>
</html>
