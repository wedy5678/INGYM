<%@page import="com.article_rep.model.Article_RepVO"%>
<%@page import="com.article_rep.model.Article_RepService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="com.art_comment.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	Article_RepService article_repService = new Article_RepService();
	List<Article_RepVO> list = article_repService.getAll();
	pageContext.setAttribute("list", list);
%>


<jsp:useBean id="Article_RepService" scope="page"
	class="com.article_rep.model.Article_RepService" />

<html>
<head>
<title>�Ҧ����|��� - listAllArticle_Rep.jsp</title>

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
				<h3>�Ҧ����|��� - listAllArticle_Rep.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/front-end/article_rep/select_page.jsp"><img
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
			<th>���|�s��</th>
			<th>�|���s��</th>
			<th>�峹�s��</th>
			<th>���|���e</th>
			<th>���|�ɶ�</th>
			<th>���|���A</th>

		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="article_repVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${article_repVO.art_rep_no}</td>
				<td>${article_repVO.mem_id}</td>
				<td>${article_repVO.article_no}</td>
				<td>${article_repVO.rep_content}</td>
				<td>${SimpleDateFormat("yyyy/MM/dd hh:mm").format(article_repVO.rep_time)}</td>
				<td>${article_repVO.com_status == 'R0' ? "���B�z":"�B�z"}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/article_rep/article_rep.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�ק�"> <input type="hidden"
							name="art_rep_no" value="${article_repVO.art_rep_no}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<!--  <td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/article/article.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�R��"> <input type="hidden"
							name="article_no" value="${articleVO.article_no}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>-->
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>