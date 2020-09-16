<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.classAuth.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    ClassAuthService classAuthSvc = new ClassAuthService();
    List<ClassAuthVO> list = classAuthSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<jsp:useBean id="proSvc" scope="page" class="com.pro.model.ProService" />
<jsp:useBean id="classTypeSvc" scope="page" class="com.classType.model.ClassTypeService" />

<html>
<head>
<title>�Ҧ����u��� - listAllEmp.jsp</title>

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
	<tr><td>
		 <h3>�Ҧ����u��� - listAllEmp.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>auth_no</th>
		<th>pro_ID</th>
		<th>MEM_name</th>
		<th>C_type_no</th>
		<th>�ҵ{���O�v���W��</th>
		<th>�v��</th>

	</tr>
	<c:forEach var="classAuthVO" items="${list}">
		
		<tr>
			<td>${classAuthVO.auth_no}</td>
			<td>${classAuthVO.pro_ID}</td>
			<td>${memSvc.getOneMem(proSvc.getOnePro(classAuthVO.getPro_ID()).mem_ID).mem_name}</td>
			<td>${classAuthVO.c_type_no}</td>
			<td> ${classTypeSvc.getOneClassType(classAuthVO.getC_type_no()).t_name}</td>	
			<td>${classAuthVO.ca_status}</td>
		
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="empno"  value="${empVO.empno}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="empno"  value="${empVO.empno}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>