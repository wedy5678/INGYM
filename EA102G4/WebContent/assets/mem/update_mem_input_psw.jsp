<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

<%
	MemVO memVO = (MemVO) request.getAttribute("memVO"); //MemServlet.java (Concroller) �s�Jreq��memVO���� (�]�A�������X��memVO, �]�]�A��J��ƿ��~�ɪ�memVO����)
%>
<%=memVO == null%>--${memVO==null}--
<br>${memVO.mem_id}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�|����ƭק� - update_mem_input.jsp</title>

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
		<tr>
			<td>
				<h3>�|����ƭק� - update_mem_input_psw.jsp</h3>
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

	<FORM METHOD="post" ACTION="mem.do" name="form1">
		<table>
			<tr>
<!-- 				<td>�|���s��:</td> -->
				<td><input type="hidden" name="mem_id" size="45"
					value="<%=memVO.getMem_id()%>" /></td>
			</tr>
			<tr>
<!-- 				<td>�m�W:</td> -->
				<td><input type="hidden" name="mem_name" size="45"
					value="<%=memVO.getMem_name()%>" /></td>
			</tr>
			
			<tr>
<!-- 				<td>�K�X:</td> -->
				<td><input type="hidden" name="mem_psw" size="45"
					value="<%=memVO.getMem_psw()%>"/></td>
			</tr>
			<tr>
				<td>��K�X:</td>
				<td><input type="password" name="mem_psw_check" size="45"
					value=""/></td>
			</tr>
			<tr>
				<td>�s�K�X:</td>
				<td><input type="password" name="mem_psw_new" size="45"
					value=""/></td>
			</tr>
			<tr>
				<td>�T�{�K�X:</td>
				<td><input type="password" name="mem_psw_newcheck" size="45"
					value=""/></td>
			</tr>
			<tr>
<!-- 				<td>�ͤ�:</td> -->
				<td><input name="mem_bir" id="f_date1" type="hidden"></td>
			</tr>
			<tr>
<!-- 				<td>�ʧO:</td> -->
				<td>
					<%if (memVO.getSex().equals("�k")) {%> 
					<input type="radio" id="male"
					name="sex" value="�k" checked hidden> <label for="male" hidden>�k</label> <input
					type="radio" id="female" name="sex" value="�k" hidden> <label
					for="female" hidden>�k</label><br> 
					<%} else {%>
					<input type="radio"
					id="male" name="sex" value="�k" hidden><label for="male">�k</label>
					<input type="radio" id="female" name="sex" value="�k" checked hidden><label
					for="female">�k</label><br>
					<%}%>
				</td>
			</tr>
			<tr>
<!-- 				<td>�a�}:</td> -->
				<td><input type="hidden" name="mem_addr" size="45"
					value="<%=memVO.getMem_addr()%>" /></td>
			</tr>
			<tr>
<!-- 				<td>�q�l�H�c:</td> -->
				<td><input type="hidden" name="mem_email" size="45"
					value="<%=memVO.getMem_email()%>" /></td>
			</tr>
			<tr>
<!-- 				<td>���:</td> -->
				<td><input type="hidden" name="mem_phone" size="45"
					value="<%=memVO.getMem_phone()%>" /></td>
			</tr>
			<tr>
<!-- 				<td>�а�����:</td> -->
				<td><input type="hidden" name="mem_absent"
					size="45" value="<%=memVO.getMem_absent()%>" /></td>
			</tr>
			<tr>
<!-- 				<td>�I��:</td> -->
				<td><input type="hidden" name="coin" size="45"
					value="<%=memVO.getCoin()%>" /></td>
			</tr>
			<tr>
<!-- 				<td>�ۧڤ���:</td> -->
				<td><input type="hidden" name="mem_resume" size="45"
					value="<%=memVO.getMem_resume()%>" /></td>
			</tr>
			<tr>
<!-- 				<td>���U�ɶ�:</td> -->
				<td><input name="m_reg_date" id="f_date2"
					type="hidden"></td>
			</tr>
			<tr>
<!-- 				<td>��a�v��:</td> -->
				<td>
				<%if (memVO.getSel_auth().equals("S1")) {%>
				<select name="sel_auth" hidden>
					<option value="S1" selected>�ҥ�
					<option value="S0">����
				</select> 
				<%} else {%> 
				<select name="sel_auth" hidden>
					<option value="S1">�ҥ�
					<option value="S0" selected>����
				</select> 
				<%}%>
				</td>
			</tr>
			<tr>
<!-- 				<td>�o���v��:</td> -->
				<td>
				<%if (memVO.getArt_auth().equals("A1")) {%> 
				<select name="art_auth" hidden>
					<option value="A1" selected>�ҥ�
					<option value="A0">����
				</select>
				<%} else {%> 
				<select name="art_auth" hidden>
					<option value="A1">�ҥ�
					<option value="A0" selected>����
				</select> 
				<%}%>
				</td>
			</tr>
			<tr>
<!-- 				<td>�d���v��:</td> -->
				<td>
				<%if (memVO.getCom_auth().equals("C1")) {%> 
				<select name="com_auth" hidden>
					<option value="C1" selected>�ҥ�
					<option value="C0">����
				</select> 
				<%} else {%> 
				<select name="com_auth" hidden>
					<option value="C1">�ҥ�
					<option value="C0" selected>����
				</select> 
				<%}%>
				</td>
			</tr>

			<%-- 			<jsp:useBean id="deptSvc" scope="page" --%>
			<%-- 				class="com.dept.model.DeptService" /> --%>
			<!-- 			<tr> -->
			<!-- 				<td>����:<font color=red><b>*</b></font></td> -->
			<!-- 				<td><select size="1" name="deptno"> -->
			<%-- 						<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
			<%-- 							<option value="${deptVO.deptno}" --%>
			<%-- 								${(empVO.deptno==deptVO.deptno)?'selected':'' }>${deptVO.dname} --%>
			<%-- 						</c:forEach> --%>
			<!-- 				</select></td> -->
			<!-- 			</tr> -->

		</table>
		<br> <input type="hidden" name="action" value="update_psw"> <input
			type="hidden" name="mem_id" value="<%=memVO.getMem_id()%>"> <input
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
 		   value: '<%=memVO.getMem_bir()%>'
	});
        $('#f_date2').datetimepicker({
           theme: '',              
  	       timepicker:false,       
  	       step: 1,                
  	       format:'Y-m-d H:i:s',
  		   value: '<%=memVO.getM_reg_date()%>'
  		   });

	// ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

	//      1.�H�U���Y�@�Ѥ��e������L�k���
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.�H�U���Y�@�Ѥ��᪺����L�k���
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
							</html>