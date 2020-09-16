<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="com.art_comment.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	Art_CommentService art_commentService = new Art_CommentService();
	List<Art_CommentVO> list = art_commentService.getAll();
	pageContext.setAttribute("list", list);
%>


<jsp:useBean id="Art_CommentService" scope="page"
	class="com.art_comment.model.Art_CommentService" />

<html>
<head>
<title>�Ҧ��d����� - listAllArt_Comment.jsp</title>

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
				<h3>�Ҧ��峹��� - listAllArt_Comment.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/front-end/art_comment/select_page.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">�^����</a>
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
			<th>�d���s��</th>
			<th>�峹�s��</th>
			<th>�|���s��</th>
			<th>�d�����e</th>
			<th>�o�G�ɶ�</th>
			<th>�d�����A</th>

		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="art_commentVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${art_commentVO.com_no}</td>
				<td>${art_commentVO.article_no}</td>
				<td>${art_commentVO.mem_id}</td>
				<td>${art_commentVO.mes_content}</td>
				<td>${SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(art_commentVO.com_release)}</td>
				<td>${art_commentVO.com_status == 'AC1' ? "���":"����"}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/art_comment/art_comment.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�ק�"> <input type="hidden"
							name="com_no" value="${art_commentVO.com_no}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				  <td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/art_comment/art_comment.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�R��"> 
						<input type="hidden"name="com_no" value="${art_commentVO.com_no}">
					    <input type="hidden" name="action" value="delete">
							
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>