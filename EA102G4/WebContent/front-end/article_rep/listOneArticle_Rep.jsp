<%@page import="com.article_rep.model.Article_RepVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.art_comment.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%-- ���X Concroller EmpServlet.java�w�s�Jrequest��EmpVO����--%>
<%Article_RepVO article_repVO = (Article_RepVO) request.getAttribute("article_repVO");%>

<%-- ���X ������DeptVO����--%>

<html>
<head>
<title>���|��� - listOneArticle_Rep.jsp</title>

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
		 <h3>�d����� - listOneArticle_Rep.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/article_rep/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
			<th>���|�s��</th>
			<th>�|���s��</th>
			<th>�峹�s��</th>
			<th>���|���e</th>
			<th>���|�ɶ�</th>
			<th>���|���A</th>

	</tr>
	
	<tr>
		<td>${article_repVO.art_rep_no}</td>
		<td>${article_repVO.mem_id}</td>
		<td>${article_repVO.article_no}</td>
		<td>${article_repVO.rep_content}</td>
		<td>${SimpleDateFormat("yyyy/MM/dd hh:mm").format(article_repVO.rep_time)}</td>
		<td>${article_repVO.com_status == 'R0' ? "���B�z":"�B�z"}</td>
		
	</tr>
</table>

</body>
</html>