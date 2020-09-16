<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.art_comment.model.*"%>
<%@ page import="com.article.model.*"%>

<%


// String article_no = request.getParameter("article_no");
// ArticleVO articleVO = new ArticleService().getOneArticle(article_no);
// pageContext.setAttribute("articleVO", articleVO);
%>


<html>
<head><title>�峹���d�� - listArt_Comments_ByArticleno.jsp</title>



<style>
  table#table-2 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-2 h4 {
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

<jsp:useBean id="Art_CommentService" scope="page"class="com.art_comment.model.Art_CommentService" />

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-2">
	<tr><td>
		 <h3>�峹���d�� - listArt_Comments_ByArticleno.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/listAllArticle.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
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
		<th>�峹�s��</th>
		<th>�|���s��</th>
		<th>�d�����e</th>
		<th>�d���ɶ�</th>
		
	</tr>
	
	<c:forEach var="art_commentVO" items="${Art_CommentService}" >
		<tr>
		
			<td>${art_commentVO.article_no}</td>			
			<td>${art_commentVO.mem_id}</td>
			<td>${art_commentVO.mes_content}</td>
			<td>${art_commentVO.com_release}</td>
			<td><c:forEach var="art_commentVO" items="${art_commentSvc.all}">
                    <c:if test="${art_commentVO.article_no==articleVO.article_no}">
	                    ${art_commentVO.article_no}�i<font color=orange>${art_commentVO.mem_id}</font> - ${art_commentVO.mes_content} - ${art_commentVO.com_release}�j
                    </c:if>
                </c:forEach>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/art_comment/art_comment.do" style="margin-bottom: 0px;">
			    <input type="submit" value="�ק�"> 
			    <input type="hidden" name="com_no"      value="${art_commentVO.com_no}">
			    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller--><!-- �ثe�|���Ψ�  -->
			    <input type="hidden" name="action"	   value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/art_comment/art_comment.do" style="margin-bottom: 0px;">
			    <input type="submit" value="�R��">
			    <input type="hidden" name="com_no"      value="${art_commentVO.com_no}">
			    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			    <input type="hidden" name="action"     value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

<br>�����������|:<br><b>
   <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b>

</body>
</html>