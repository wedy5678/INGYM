<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.memphoto.model.*"%>
<%@ page import="com.mem.model.*"%>

<%
	MemVO memVO = (MemVO)session.getAttribute("memVO");
	MemPhotoVO memPhotoVO = (MemPhotoVO) request.getAttribute("memPhotoVO"); //BodeyRecordServlet.java (Concroller) �s�Jreq��bodyRecordVO���� (�]�A�������X��bodyRecordVO, �]�]�A��J��ƿ��~�ɪ�bodyRecordVO����)
%>
<%=memPhotoVO == null%>--${memPhotoVO==null}--
<br>${memPhotoVO.photo_no}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�|���Ӥ��ק� - update_memPhoto_input.jsp</title>

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
/* �|���L�k�ۦ��諸��� */

</style>

<style>
table {
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>�|���Ӥ��ק� - update_memPhoto_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��ƭק�:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="memphoto.do" name="form1">
		<table>
		<tr>
<!-- 				<td>�Ӥ��s��:</td> -->
				<td><input type="hidden" name="photo_no" size="45"
					value="<%=memPhotoVO.getPhoto_no()%>" /></td>
			</tr>
			<tr>
				<td>�Ӥ����A:</td>
				<td><select id="photo_status" name = "photo_status">
						<option value="P0" />
						<option value="P1" />�]���j�Y�K
				</select>
			
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="photo_no" value="<%=memPhotoVO.getPhoto_no()%>"> <input
			type="submit" value="�e�X�ק�">
	</FORM>
</body>

</html>