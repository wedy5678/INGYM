<%@page import="com.article_rep.model.Article_RepVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.art_comment.model.*"%>

<%
	Article_RepVO article_repVO = (Article_RepVO) request.getAttribute("article_repVO");
%>
<%= article_repVO ==null %>--${article_repVO==null}<br>--${article_repVO.art_rep_no}-- 
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���|��ƭק� - update_Article_Rep.jsp</title>

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
		 <h3>���|��ƭק� - update_Article_Rep.jsp</h3>
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

<FORM METHOD="post" ACTION="article_rep.do" name="form1">
<table>

	
	
	
	<tr>
		<td>���|���e:</td>
		<td><!--<input type="TEXT" name="rep_content" size="45" value="<%=article_repVO.getRep_content()%>"-->
		 <textarea style="width:329.5px" name="rep_content" cols="30" rows="3" align="center"><%= article_repVO.getRep_content()%></textarea></td>
	</tr>

	<jsp:useBean id="article_repSvc" scope="page" class="com.article_rep.model.Article_RepService" />
	<tr>
		<!-- <td>�d���s��:<font color=red><b>*</b></font></td> -->
		<td><input type="hidden" name="art_rep_no" size="45" value="<%=article_repVO.getArt_rep_no()%>" />
		</td>
	</tr>
		<tr>
	<td>	
		<select size="1" name="art_rep_no">
					<option value="R0" ${(article_repVO.getCom_status=="R0")? 'selected':'' }>���|���B�z
					<option value="R1" ${(article_repVO.getCom_status=="R1")? 'selected':'' }>���|�q�L
					<option value="R2" ${(article_repVO.getCom_status=="R2")? 'selected':'' }>���|���q�L
			       	</select>	
		</td>
	</tr>
	
	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="article_repVO" value="<%=article_repVO.getArt_rep_no()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>

