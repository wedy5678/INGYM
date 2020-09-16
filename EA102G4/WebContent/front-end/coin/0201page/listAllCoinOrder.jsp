<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coin.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    CoinService coinSvc = new CoinService();
    List<CoinVO> list = coinSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ��Ҫ���� - listAllCoinOrder.jsp</title>

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
		 <h3>�Ҧ��Ҫ��L�l - listAllCoinOrder.jsp</h3>
		 <h4><a href="select_coin_order.jsp"><img src="images/321.jpeg" width="100" height="100" border="0">�^����</a></h4>
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
		<th>�Ҫ��s��</th>
		<th>�Ҫ��|��</th>
		<th>�Ҫ��ɶ�</th>
		<th>�Ҫ�$$</th>
		<th>�Ҫ��I��</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="coinVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${coinVO.coin_id}</td>
			<td>${coinVO.mem_id}</td>
			<td><fmt:formatDate value="${coinVO.coin_date}" pattern="yyyy-MM-dd"/></td>
			<td>${coinVO.amount}</td>
			<td>${coinVO.deposit_coin}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/coin/coin.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="coin_id"  value="${coinVO.coin_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>