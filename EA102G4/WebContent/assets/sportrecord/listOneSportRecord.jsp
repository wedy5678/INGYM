<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sportrecord.model.*"%>
<%@ page import="com.sports.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%

// List<SportRecordVO> list = (List<SportRecordVO>)request.getAttribute("list"); //SportRecordServlet.java(Concroller), �s�Jreq��sportRecordVO����
SportRecordService sportRecordSvc = new SportRecordService();
List<SportRecordVO> list = sportRecordSvc.getOneSportRecordByMemId("MEM0000001"); //session.getAttribute("memVO").mem_id
pageContext.setAttribute("list", list);

SportsService sportsSvc = new SportsService();
List<SportsVO> sportslist = sportsSvc.getAll();
pageContext.setAttribute("sportslist", sportslist);

%>

<html>
<head>
<title>�|���ӤH�B�ʬ������ - listOneSportRecord.jsp</title>

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

	<h4>�����m�߱ĥ� EL ���g�k����:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>�|���ӤH�B�ʬ������ - listOneSportRecord.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>�B�ʬ����s��</th>
			<th>�|���s��</th>
			<th>�������</th>
			<th>�B�ʶ���</th>
			<th colspan = 2>�O��</th>
			<th>�ק���</th>
			<th>�R��</th>
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
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/sportrecord/sportrecord.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�ק���"> <input type="hidden"
							name="record_no" value="${sportRecordVO.record_no}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/sportrecord/sportrecord.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�R��"> <input type="hidden"
							name="record_no" value="${sportRecordVO.record_no}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
			
		</c:forEach>
	</table>

	<%@ include file="page2.file"%>

</body>

</html>