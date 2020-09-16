<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sportrecord.model.*"%>
<%@ page import="com.sports.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	SportRecordService sportRecordSvc = new SportRecordService();
	List<SportRecordVO> list = sportRecordSvc.getAll();
	pageContext.setAttribute("list", list);
	
	SportsService sportsSvc = new SportsService();
	List<SportsVO> sportslist = sportsSvc.getAll();
	pageContext.setAttribute("sportslist", sportslist);
%>


<html>
<head>
<title>所有會員運動紀錄資料 - listAllSportRecord.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有會員運動紀錄資料 - listAllSportRecord.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>運動紀錄編號</th>
			<th>會員編號</th>
			<th>紀錄日期</th>
			<th>運動項目</th>
			<th colspan = 2>記錄</th>
			
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="sportRecordVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${sportRecordVO.record_no}</td>
				<td>${sportRecordVO.mem_id}</td>
				<td><fmt:formatDate value="${sportRecordVO.record_date}"
						pattern="yyyy-MM-dd" /></td>
				<c:forEach var="sportsVO" items="${sportslist}" begin="0"
							end="<%=sportslist.size()%>">
       						<c:choose>
       							<c:when test="${sportRecordVO.sport_no == sportsVO.sport_no}">
       								<td>${sportsVO.sport_name} </td>
       								<td>${sportRecordVO.record1}${sportsVO.unit1}</td>
									<td>${sportRecordVO.record2}${sportsVO.unit2}</td>
								</c:when>
							</c:choose>
				</c:forEach>
				
				
			</tr>
		</c:forEach>
	</table>

	<%@ include file="page2.file"%>

</body>

</html>