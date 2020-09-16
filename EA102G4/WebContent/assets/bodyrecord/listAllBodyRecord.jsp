<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bodyrecord.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	BodyRecordService bodyRecordSvc = new BodyRecordService();
	List<BodyRecordVO> list = bodyRecordSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>�Ҧ��|������ƾڸ�� - listAllBodyRecord.jsp</title>

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
				<h3>�Ҧ��|������ƾڸ�� - listAllBodyRecord.jsp</h3>
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
			<th>����ƾڬ����s��</th>
			<th>�|���s��</th>
			<th>�������</th>
			<th>����</th>
			<th>�魫</th>
			<th>BMI����</th>
			<th>�~��</th>
			<th>��¦�N�²v(BMR)</th>
			<th>�B���W�v</th>
			<th>�C���`���Ӽ��q(TDEE)</th>
			<th>�Ӥ�</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="bodyRecordVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${bodyRecordVO.body_no}</td>
				<td>${bodyRecordVO.mem_id}</td>
				<td><fmt:formatDate value="${bodyRecordVO.body_date}"
						pattern="yyyy-MM-dd" /></td>
				<td>${bodyRecordVO.mem_height}</td>
				<td>${bodyRecordVO.mem_weight}</td>
				<td>${bodyRecordVO.mem_bmi}</td>
				<td>${bodyRecordVO.mem_old}</td>
				<td>${bodyRecordVO.mem_bmr}</td>
				<td><c:choose>
						<c:when test="${bodyRecordVO.frequency == '0'}">
       						 �[��(�줽�Ǥu�@�B�S���B�ʲߺD) 
    					</c:when>
						<c:when test="${bodyRecordVO.frequency == '1'}">
       						 ����(�B��1-2��/�g)
						</c:when>
						<c:when test="${bodyRecordVO.frequency == '2'}">
        					����(�B��3-5��/�g)
						</c:when>
						<c:when test="${bodyRecordVO.frequency == '3'}">
        					����(�B��6-7��/�g)
						</c:when>
						<c:otherwise>
        					������(�B�ʭ����šA�C�ѹB��2��)
						</c:otherwise>
					</c:choose></td>
				<td>${bodyRecordVO.mem_tdee}</td>
				<td>${bodyRecordVO.photo}</td>
				
			</tr>
		</c:forEach>
	</table>

	<%@ include file="page2.file"%>

</body>

</html>