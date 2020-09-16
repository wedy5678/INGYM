<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dayoff.model.*"%>

<%
  DayoffVO dayoffVO = (DayoffVO) request.getAttribute("dayoffVO");
%>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���u��Ʒs�W - addEmp.jsp</title>

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
		 <h3>- addDayoff.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~���C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="dayoff.do" name="form1">
<table>
	<tr>
		<td>�нm�s��:</td>
		<td><input type="TEXT" name="pro_ID" size="45" 
			 value="<%=(dayoffVO==null)? "PRO1000000" : dayoffVO.getPro_ID()%>"/></td>
	</tr>
	<tr>
		<td>�нm�|���s��:</td>
		<td><input type="TEXT" name="mem_ID" size="45"
			 value="<%=(dayoffVO==null)? "MEM0000001" : dayoffVO.getMem_ID()%>" /></td>
	</tr>
<!-- 		<tr> -->
<!-- 		<td>�нm�m�W:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="mem_ID"> -->
<%-- 			<c:forEach var="memVO" items="${memSvc.all}"> --%>
<%-- 				<option value="${dayoffVO.mem_ID}" ${(dayoffVO.mem_ID==mem.mem_ID)? 'selected':'' } >${memVO.mem_name} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->
	<tr>
		<td>�𮧤��:</td>
		<td><input name="close_date" id="f_date1" type="text"></td>
	</tr>
	<tr>
		<td>�𮧮ɶ�:</td>
		<td><input type="TEXT" name="hr" size="45"
			 value="<%= (dayoffVO==null)? "111111111111000000000000" : dayoffVO.getHr()%>" /></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>
</html>