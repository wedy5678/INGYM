<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.groupclass.model.*"%>

<%
  GroupClassVO gcVO = (GroupClassVO) request.getAttribute("gcVO");
%>
<%= gcVO==null %>--${gcVO==null}<br>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�s�W�ν� - addGroupClass.jsp</title>

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
		 <h3>�s�W�ν� - addGroupClass.jsp</h3></td><td>
		 <h4><a href="groupClassSelectPage.jsp"><img src="../images/123.jpg" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="groupClass.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>�νҦW��:</td>
		<td><input type="TEXT" name="g_name" size="45" 
			 value="<%= (gcVO==null)? "�h�H�B��" : gcVO.getG_name()%>" /></td>
	</tr>
	<tr>
		<td>�ν����O:</td>
		<td><input type="TEXT" name="c_type_no" size="45"
			 value="<%= (gcVO==null)? "CT001" : gcVO.getC_type_no()%>" /></td>
	</tr>
		<tr>
		<td>�@��h��:</td>
		<td><input type="TEXT" name="p_coin" size="45"
			 value="<%= (gcVO==null)? "300" : gcVO.getP_coin()%>" /></td>
	</tr>
	<tr>
		<td>�W�Ҧa�I:</td>
		<td><input type="TEXT" name="loc" size="45"
			 value="<%= (gcVO==null)? "�x�_������ϤT�C��" : gcVO.getLoc()%>" /></td>
	</tr>
	<tr>
		<td>�нmID:</td>
		<td><input type="TEXT" name="pro_id" size="45"
			 value="<%= (gcVO==null)? "PRO1000001" : gcVO.getPro_id()%>" /></td>
	</tr>
	<tr>
		<td>�H�ƤW��:</td>
		<td><input type="TEXT" name="g_max" size="45"
			 value="<%= (gcVO==null)? "5" : gcVO.getG_max()%>" /></td>
	</tr>
	
	<tr><td>�Ϥ��W��:</td>
	<td><input type="file" id="g_pic" name="g_pic" /></td></tr>
	
	<tr><td>�νҤ���:</td>
	<td><textarea cols="30" rows="10" name="g_detail" ><%= (gcVO==null)? "�w�w" : gcVO.getG_detail()%></textarea>
	</td></tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W">
</FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->


</html>
<button id="button">�s�W�νҮɶ�</button>
	<Form id="gchr" METHOD="post" ACTION="groupClass.do">

		<input type="hidden" name="g_class_no"
			value="<%=gcVO.getG_class_no()%>"> <input type="hidden"
			name="action" value="insertForGCTime"> <input type="hidden"
			name="addCount"> <input type="submit" name="�e�X">
	</Form>