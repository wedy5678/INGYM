<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.mem.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  MemVO memVO = (MemVO) request.getAttribute("memVO"); //MemServlet.java(Concroller), �s�Jreq��memVO����
%>

<html>
<head>
<title>�|����� - listOneMem.jsp</title>

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
	width: 600px;
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
		 <h3>�|����� - ListOneMem.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�|���s��</th>
		<th>�m�W</th>
		<th>�K�X</th>
		<th>�ͤ�</th>
		<th>�ʧO</th>
		<th>�a�}</th>
		<th>�q�l�H�c</th>
		<th>������X</th>
		<th>�а�����</th>
		<th>�I��</th>
		<th>�ۧڤ���</th>
		<th>���U�ɶ�</th>
		<th>��a�v��</th>
		<th>�o���v��</th>
		<th>�d���v��</th>
	</tr>
	<tr>
		<td><%=memVO.getMem_id()%></td>
		<td><%=memVO.getMem_name()%></td>
		<td><%=memVO.getMem_psw()%></td>
		<td><%=memVO.getMem_bir()%></td>
		<td><%=memVO.getSex()%></td>
		<td><%=memVO.getMem_addr()%></td>
		<td><%=memVO.getMem_email()%></td>
		<td><%=memVO.getMem_phone()%></td>
		<td><%=memVO.getMem_absent()%></td>
		<td><%=memVO.getCoin()%></td>
		<td><%=memVO.getMem_resume()%></td>
		<td><%=memVO.getM_reg_date()%></td>
		<td><%=memVO.getSel_auth()%></td>
		<td><%=memVO.getArt_auth()%></td>
		<td><%=memVO.getCom_auth()%></td>
		
	</tr>
</table>

</body>
</html>