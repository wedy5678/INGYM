<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.art_comment.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%-- ���X Concroller EmpServlet.java�w�s�Jrequest��EmpVO����--%>
<%Art_CommentVO art_commentVO = (Art_CommentVO) request.getAttribute("art_commentVO");%>

<%-- ���X ������DeptVO����--%>

<html>
<head>
<title>�峹��� - listOneArt_Comment.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�d����� - ListOneArt_Comment.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/art_comment/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�d���s��</th>
			<th>�峹�s��</th>
			<th>�|���s��</th>
			<th>�d�����e</th>
			<th>�o�G�ɶ�</th>
			<th>�d�����A</th>
		
	</tr>
	<tr>
		<td>${art_commentVO.com_no}</td>
		<td>${art_commentVO.article_no}</td>
		<td>${art_commentVO.mem_id}</td>
		<td>${art_commentVO.mes_content}</td>
		<td>${SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(art_commentVO.com_release)}</td>
		<td>${art_commentVO.com_status == 'AC1' ? "���":"����"}</td>
		
	</tr>
</table>

</body>
</html>