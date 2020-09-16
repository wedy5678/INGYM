<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coin.model.*"%>

<%
  CoinVO coinVO = (CoinVO) request.getAttribute("coinVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�L�l��ƭק� - update_coin_input.jsp</title>

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
		 <h3>�L�l�q��ק� - update_coin_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/coin/select_coin_order.jsp"><img src="images/123.jpeg" width="100" height="100" border="0">�^����</a></h4>
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

<FORM METHOD="post" ACTION="coin.do" name="form1">
<table>
	<tr>
		<td>�q��s��:<font color=red><b>*</b></font></td>
		<td><%=coinVO.getCoin_id()%></td>
	</tr>
	<tr>
		<td>�|���s��:</td>
		<td><input type="TEXT" name="mem_id" size="45" value="<%=coinVO.getMem_id()%>" /></td>
	</tr>
	<tr>
		<td>�q��ɶ�:</td>
		<td><input type="TEXT" name="coin_date" size="45"	value="<%=coinVO.getCoin_date()%>" /></td>
	</tr>
	<tr>
		<td>�L�l�h�j:</td>
		<td><input type="TEXT" name="amount" size="45"	value="<%=coinVO.getAmount()%>" /></td>
	</tr>
	<tr>
		<td>�L�F�h��:</td>
		<td><input type="TEXT" name="deposit_coin" size="45" value="<%=coinVO.getDeposit_coin()%>" /></td>
	</tr>

<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>����:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="coin_id" value="<%=coinVO.getCoin_id()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>


</html>