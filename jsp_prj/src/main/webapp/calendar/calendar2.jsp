<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%!
public static final int START_DAY = 1;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="shortcut icon" href="http://192.168.10.81/jsp_prj/common/images/favicon.ico"/>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.min.js"></script>

<style type="text/css">
#wrap{ 
	width: 1000px; height: 900px; margin: 0px auto; 
}
#header{ height: 200px; }
#container{ height: 600px; }
#footer{ height: 100px; }

a{
	text-decoration: none;
	color: #333;
}

a:hover {
	text-decoration: underline;
	color: #1E4183;
}

#calHeader{
	font-size: 20px;
	text-align: center;
	margin-bottom: 20px;
}

.calTitle{
 	font-weight: bold;
 	font-size: 30px;
}

#calTab, th, td{
	border: 1px solid #E5E5E5;
	margin: 0px auto;
}

th{
	text-align: center;
	color: #909090;
}

td{
	text-align: right;
	vertical-align: top;
	color: #909090;
	width: 120px;
	height: 80px;
	font-size: 15px;
}

.sunTitle{
	width: 120px;
	height: 30px;
	background-color: #E72203;
	color: #FFFFFF;
	font-weight: bold;
}

.weekTitle{
	width: 120px;
	height: 30px;
}

.satTitle{
	width: 120px;
	height: 30px;
	background-color: #5379E1;
	color: #FFFFFF;
	font-weight: bold;
}

.sunTextColor{
	color: #E72203;
	font-weight: bold;
}

.weekTextColor{
	color: #909090;
}

.satTextColor{
	color: #5797E1;
	font-weight: bold;
}

.todayCss{
	border: 1px solid #6C86C4;
	background-color: #A5C6E0;
}

.dayCss{
	border: 1px solid #E5E5E5;
}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> 
<script type="text/javascript">
$(function(){
	
});

function moveCal(month, year) {
	// 입력된 값을 hidden에 설정하고 
	$("#month").val(month);
	$("#year").val(year);
	//form에 submit을 수행
	#("#calFrm").submit();
}
</script>
</head>

<body>
<div id="wrap">

<div id="header"></div>

<div id="container">
<div id="calWrap">

<%
	Calendar cal = Calendar.getInstance();
	StringBuilder toDay = new StringBuilder();
	toDay.append(cal.get(Calendar.YEAR)).append(cal.get(Calendar.MONTH)+1);
	Calendar today = Calendar.getInstance();

	int nowYear = 0;
	String year = request.getParameter("year");

	if(year != null){
		cal.set(Calendar.YEAR, Integer.parseInt(year));
	}

	nowYear = cal.get(Calendar.YEAR);

	int nowMonth = 0;
	String month = request.getParameter("month");

	if(month != null){
		cal.set(Calendar.MONTH, Integer.parseInt(month)-1);		
	}

	nowMonth = cal.get(Calendar.MONTH)+1;

	int nowDay = cal.get(Calendar.DAY_OF_MONTH);

	int prevMonth = nowMonth - 1;
	int prevYear = nowYear;

	if(prevMonth == 0){
		prevMonth = 12;
		prevYear--;
	}

	int nextMonth = nowMonth + 1;
	int nextYear = nowYear;

	if(nextMonth == 13){
		nextMonth = 1;
		nextYear++;
	}
	//log(today.toString()); //현재년 현재월 
	StringBuilder selectDay = new StringBuilder();
	selectDay.append(nowYear).append(nowMonth);
	//log(selectDay.toString()); // 선택한 년과 선택한 월
	
	//today와 selectDay가 같으면 true를 출력하고 다르면 false를 출력
	//log(String.valueOf(today.toString().equals(selectDay.toString())));
	
	//오늘을 표현하기 위한 flag 변수
	boolean toDayFlag = today.toString().equals(selectDay.toString());
%>
	<form action="calendar2.jsp" method="post" id="calFrm">
	<input type="hidden" name="year" id="year"/>
	<input type="hidden" name="month" id="month"/>
	</form>
	<div id="calHeader">
		<a href="void" onclick="moveCal(<%=prevMonth%>,<%=prevYear%>)" title="이전월">&lt;&lt;</a>

		<a href="calendar.jsp" title="오늘">
			<span class="calTitle"><%=nowYear%>.<%=nowMonth%></span>
		</a>

		<a href="#void" onclick="moveCal(<%=nextMonth%>,<%=nextYear%>)" title="다음월">&gt;&gt;</a>
	</div>

	<div id="calContainer">
		<table id="calTab">
			<thead>
				<tr>
					<th class="sunTitle">일</th>
					<th class="weekTitle">월</th>
					<th class="weekTitle">화</th>
					<th class="weekTitle">수</th>
					<th class="weekTitle">목</th>
					<th class="weekTitle">금</th>
					<th class="satTitle">토</th>
				</tr>
			</thead>

			<tbody>
				<tr>
				<%
					String textCss = "";
					String todayCss = "";

					for(int tempDay=1; ; tempDay++){

						cal.set(Calendar.DAY_OF_MONTH, tempDay);

						if(cal.get(Calendar.DAY_OF_MONTH) != tempDay){

							int startDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

							for(int blankTd=startDayOfWeek; blankTd <= Calendar.SATURDAY; blankTd++){
				%>
								<td></td>
				<%
							}
							break;
						}

						switch(tempDay){
						case START_DAY:
							int startDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

							for(int blankTd=1; blankTd < startDayOfWeek; blankTd++){
				%>
								<td></td>
				<%
							}
						}

						textCss = "weekTextColor";

						switch(cal.get(Calendar.DAY_OF_WEEK)){
						case Calendar.SUNDAY:
							textCss = "sunTextColor";
							break;

						case Calendar.SATURDAY:
							textCss = "satTextColor";
							break;
						}

						todayCss = "dayCss";

						if(
							tempDay == today.get(Calendar.DAY_OF_MONTH)
							&& nowMonth == today.get(Calendar.MONTH)+1
							&& nowYear == today.get(Calendar.YEAR)
						){
							todayCss = "todayCss";
						}
				%>

						<td class="<%=todayCss%>">
							<span class="<%=textCss%>"><%=tempDay%></span>
						</td>

				<%
						switch(cal.get(Calendar.DAY_OF_WEEK)){
						case Calendar.SATURDAY:
				%>
							</tr>
							<tr>
				<%
						}
					}
				%>
				</tr>
			</tbody>
		</table>
	</div>

</div>
</div>

<div id="footer"></div>

</div>
</body>
</html>