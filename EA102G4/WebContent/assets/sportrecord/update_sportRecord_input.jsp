<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sportrecord.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.sports.model.*"%>



<%
	MemVO memVO = (MemVO)session.getAttribute("memVO");
	SportRecordVO sportRecordVO = (SportRecordVO) request.getAttribute("sportRecordVO"); //SportRecordServlet.java (Concroller) �s�Jreq��sportRecordVO���� (�]�A�������X��sportRecordVO, �]�]�A��J��ƿ��~�ɪ�sportRecordVO����)


%>
<%=sportRecordVO == null%>--${sportRecordVO==null}--
<br>${sportRecordVO.mem_id}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�|������ƾڸ�ƭק� - update_sportRecord_input.jsp</title>

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
/* �|���L�k�ۦ��諸��� */

</style>

<style>
table {
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
		<tr>
			<td>
				<h3>�B�ʬ����ק� - update_sportRecord_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��ƭק�:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="sportrecord.do" name="form1">
		<table>
		<tr>
<!-- 				<td>����ƾڬ����s��:</td> -->
				<td><input type="hidden" name="record_no" size="45"
					value="<%=sportRecordVO.getRecord_no()%>" /></td>
			</tr>
			<tr>
<!-- 			<td>�|���s��:</td> -->
				<td><input type="hidden" name="mem_id" size="30" value="<%=sportRecordVO.getMem_id() %>" /></td> <!-- ${memVO.mem_id} -->
			</tr>
			<tr>
				<td>�������:</td>
				<td><input type="text" name="record_date" id="f_date1"/></td>
			</tr>
			<jsp:useBean id="sportsSvc" scope="page" class="com.sports.model.SportsService" />
			<tr>
				<td>�B�ʶ���:</td>
				<td>
 				<select id = "sport_no" name="sport_no">
         			<c:forEach var="sportsVO" items="${sportsSvc.all}" > 
          				<c:choose>
          					<c:when test="${sportRecordVO != null}">
          						<c:choose>
          							<c:when test="${sportsVO.sport_no == sportRecordVO.sport_no}">
          								<option value="${sportsVO.sport_no}" selected>${sportsVO.sport_name}
          							</c:when>
          							<c:otherwise>
          								<option value="${sportsVO.sport_no}">${sportsVO.sport_name}
          							</c:otherwise>
          						</c:choose>
          					</c:when>
          					<c:otherwise>
          						<option value="${sportsVO.sport_no}">${sportsVO.sport_name}
          					</c:otherwise>
          				</c:choose>
         			</c:forEach>   
       			</select>
				</td>
				
			</tr>
			<tr>
				<td></td>
				<td id="unit1"><input type="text" name="record1" value="<%=(sportRecordVO == null) ? "" : sportRecordVO.getRecord1()%>" /></td>
			</tr>
			<tr>
			<td></td>
				<td id="unit2"><input type="text" name="record2" value="<%=(sportRecordVO == null) ? "" : sportRecordVO.getRecord2()%>" /></td>
			</tr>
			
		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="record_no" value="<%=sportRecordVO.getRecord_no()%>"> <input
			type="submit" value="�e�X�ק�">
	</FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}

</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=sportRecordVO.getRecord_date()%>'
	});
</script>
<script>

var sport_no = document.getElementById('sport_no');
var unit1 = document.getElementById('unit1');
var unit2 = document.getElementById('unit2');



sport_no.addEventListener('change', function(){
	if(sport_no.value == "SPORT00001"){
		unit1.innerHTML='<input type="text" name="record1" value="" />����';
		unit2.innerHTML='<input type="text" name="record2" value="" />��';
		unit2.style.display = "inline";
	}else if(sport_no.value == "SPORT00002" || sport_no.value == "SPORT00003"){
		unit1.innerHTML='<input type="text" name="record1" value="" />����';
		unit2.innerHTML='<input type="text" name="record2" value="" />����';
		unit2.style.display = "inline";
	}else{
		unit1.innerHTML='<input type="text" name="record1" value="" />����';
		unit2.style.display = "none";
	}
});

window.onload = function(){
	if(sport_no.value == "SPORT00001"){
		unit1.append("����");
		unit2.append("��");
	}else if(sport_no.value == "SPORT00002" || sport_no.value == "SPORT00003"){
		unit1.append("����");
		unit2.append("����");
		
	}else{
		unit1.append("����");
		unit2.style.display = "none";
	}
};

</script>
</html>