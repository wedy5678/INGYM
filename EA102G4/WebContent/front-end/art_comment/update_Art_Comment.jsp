<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.art_comment.model.*"%>

<%
	Art_CommentVO art_commentVO = (Art_CommentVO) request.getAttribute("art_commentVO");
%>
<%= art_commentVO==null %>--${art_commentVO==null}<br>--${art_commentVO.com_no}-- 
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�d����ƭק� - update_Art_Comment.jsp</title>

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
	width: 450px;
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
	<tr><td>
		 <h3>�d����ƭק� - update_Art_Comment.jsp</h3>
		<h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="art_comment.do" name="form1">
<table>

	
	
	
	<tr>
		<td>�d�����e:</td>
		<td><!--<input type="TEXT" name="mes_content" size="45" value="<%=art_commentVO.getMes_content()%>"-->
		 <textarea style="width:329.5px" name="mes_content" cols="30" rows="3" align="center"><%= art_commentVO.getMes_content()%></textarea></td>
	</tr>

	<jsp:useBean id="art_commentSvc" scope="page" class="com.art_comment.model.Art_CommentService" />
	<tr>
		<!-- <td>�d���s��:<font color=red><b>*</b></font></td> -->
		<td><input type="hidden" name="com_no" size="45" value="<%=art_commentVO.getCom_no()%>" />
		</td>
	</tr>
	
	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="art_commentVO" value="<%=art_commentVO.getCom_no()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>

