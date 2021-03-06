<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.worker.model.*"%>
<%@ page import="com.foods.model.*"%>
<%@ page import="java.util.*"%>

<%
 	WorkerVO workerVOlogin = (WorkerVO)session.getAttribute("workerVOLogin");
	FoodVO foodVO = (FoodVO) request.getAttribute("foodVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
//     String wp = (String)request.getAttribute("wp");
//     pageContext.setAttribute("wp",wp);
%>

<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>更新食物</title>

  <!-- Custom fonts for this template-->
  <link href="assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="assets/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<%=request.getContextPath()%>/back-end/index.jsp">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-dumbbell"></i>   
        </div>
        <div class="sidebar-brand-text mx-3">IN GYM</div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        	選單列表
      </div>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#one" aria-expanded="true" aria-controls="one">
    <i class="far fa-grin"></i>
        <span>員工</span>
        </a>
        <div id="one" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Custom Components:</h6>
            <a class="collapse-item" href="<%=request.getContextPath() %>/back-end/worker/listAllEmp.jsp">搜尋員工</a>
            <a class="collapse-item" href="<%=request.getContextPath() %>/back-end/worker/addEmp.jsp">新增員工</a>
          </div>
        </div>
      </li>

      	<!-- Nav Item - Pages Collapse Menu -->
		      <li class="nav-item">
		        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#two" aria-expanded="true" aria-controls="two">
		          <i class="fas fa-user"></i>
		          <span>會員</span>
		        </a>
		        <div id="two" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
		          <div class="bg-white py-2 collapse-inner rounded">
		            <h6 class="collapse-header">Member Components:</h6>
		            <a class="collapse-item" href="<%=request.getContextPath() %>/back-end/mem/listAllMem.jsp">管理會員</a>
		          </div>
		        </div>
		      </li>
		
		      <!-- Nav Item - Pages Collapse Menu -->
		      <li class="nav-item">
		        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#three" aria-expanded="true" aria-controls="three">
		    <i class="fas fa-object-ungroup"></i>
		          <span>課程</span>
		        </a>
		        <div id="three" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
		          <div class="bg-white py-2 collapse-inner rounded">
		            <h6 class="collapse-header">Class Components:</h6>
		            <a class="collapse-item" href="<%=request.getContextPath() %>/back-end/class/listAllClassType.jsp">管理課程</a>
					<a class="collapse-item" href="<%=request.getContextPath() %>/back-end/class/listCoachClassAuth.jsp">專長審查</a>
					<a class="collapse-item" href="<%=request.getContextPath() %>/back-end/class/listCoachAuth.jsp">教練審查</a>
		          </div>
		        </div>
		      </li>
		      
      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#four" aria-expanded="true" aria-controls="four">
    <i class="fas fa-store"></i>
          <span>商城</span>
        </a>
        <div id="four" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Mall Components:</h6>
            <a class="collapse-item" href="<%=request.getContextPath()%>/back-end/product/listAllProduct.jsp">商品管理</a>
            <a class="collapse-item" href="<%=request.getContextPath()%>/back-end/product/listAllOrder.jsp">訂單管理</a>
          </div>
        </div>
      </li>

      

      <!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#six" aria-expanded="true"
				aria-controls="six"> <i class="fas fa-bullhorn"></i> <span>檢舉</span>
			</a>
				<div id="six" class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">Report Components:</h6>
           				<a class="collapse-item" href="<%=request.getContextPath() %>/back-end/report/listAllReport.jsp">揪團檢舉</a>
					</div>
				</div></li>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#seven" aria-expanded="true" aria-controls="seven">
    <i class="fas fa-hamburger"></i>
          <span>食物資料庫</span>
        </a>
        <div id="seven" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Food Components:</h6>
            <a class="collapse-item" href="<%=request.getContextPath() %>/back-end/food/listAllFoodBackEnd.jsp">搜尋食物資料庫</a>
            <a class="collapse-item" href="<%=request.getContextPath() %>/back-end/food/addFood.jsp">新增食物資料</a>
          </div>
        </div>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider d-none d-md-block">

      <!-- Sidebar Toggler (Sidebar) -->
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

          <!-- Topbar Navbar -->
          <ul class="navbar-nav ml-auto">

            <div class="topbar-divider d-none d-sm-block"></div>

            <!-- Nav Item - User Information -->
            <%if(workerVOlogin != null) {%>
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small"><%=workerVOlogin.getW_name() %></span>
                <img class="img-profile rounded-circle" src="<%=request.getContextPath() %>/workerptool.do?work_id=<%=workerVOlogin.getWork_id() %>">
              </a>
              <!-- Dropdown - User Information -->
              <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#Setting">
                  	<i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                	Settings
                </a>
                <a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/worker/worker.do?action=logout" >
                  <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                  Logout
                </a>
              </div>
            </li>
            <%} %>

          </ul>

        </nav>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <h1 class="h3 mb-4 text-gray-800">修改食物資料</h1>

        </div>
        <!-- /.container-fluid -->
        <div class="row justify-content-center">
        	
			<FORM METHOD="post" ACTION="food.do" name="form1">
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<table class="table text-nowrap" style="border:3px #cccccc solid;">
				<tr>
      				<td colspan=2><img src="images/food.jpg" width=100% height="150" border="0"></td>
   			   	</tr>
				<tr>
					<td>食物編號:<font color=red><b>*</b></font></td>
					<td><%=foodVO.getFood_no()%></td>
				</tr>
				<tr>
					<td>食物名稱:</td>
					<td><input type="TEXT" name="food_name" size="30" value="<%=foodVO.getFood_name()%>" /></td>
				</tr>
				<tr>
					<td>澱粉(g):</td>
					<td><input type="TEXT" name="starch" size="30" value="<%=foodVO.getStarch()%>" /></td>
				</tr>
				<tr>
					<td>蛋白質(g):</td>
					<td><input type="TEXT" name="protein" size="30" value="<%=foodVO.getProtein()%>" /></td>
					
				</tr>
				<tr>
					<td>脂肪(g):</td>
					<td><input type="TEXT" name="fat" size="30" value="<%=foodVO.getFat()%>" /></td>
				</tr>
				<tr>
					<td>熱量(kcal):</td>
					<td><input type="TEXT" name="kcal" size="30" value="<%=foodVO.getKcal()%>" /></td>
				</tr>
			</table>
			
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="food_no" value="<%=foodVO.getFood_no()%>">
			<input type="submit" value="送出修改">
			</FORM>
        </div>

      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
      <footer class="sticky-footer bg-white">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright &copy; Your Website 2020</span>
          </div>
        </div>
      </footer>
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

 <!-- 				Setting Modal -->
  <div class="modal fade" id="Setting" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">修改密碼</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">
        	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/worker/worker.do" name="form1">
				<table>
				<tr>
				<td>原密碼:</td>
				<td><input type="password" name="w_pw" size=45/></td>
				</tr>
				<tr>
				<td>新密碼:</td>
				<td><input type="password" name="w_npw" id="t_name" size=45/></td>
				</tr>
				</table>
				<br>
				<input type="hidden" name="work_id" value="${workerVOLogin.work_id}" >
				<input type="hidden" name="action" value="update_pw">
				<input type="submit" value="送出修改">
			</FORM>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="assets/vendor/jquery/jquery.min.js"></script>
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="assets/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="assets/js/sb-admin-2.min.js"></script>

</body>

</html>

