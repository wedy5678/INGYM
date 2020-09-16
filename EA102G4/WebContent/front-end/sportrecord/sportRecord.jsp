<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.sportrecord.model.*"%>
<%@ page import="com.sports.model.*"%>



<%
	SportRecordVO sportRecordVO = (SportRecordVO) request.getAttribute("sportRecordVO");  //(錯誤處理)接收來自controller的sportRecordVO
	SportsVO sportsVO = (SportsVO) request.getAttribute("sportsVO");//(錯誤處理)接收來自controller的sportsVO
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
	pageContext.setAttribute("memVO", memVOLogin);
	String changedRecordNo = (String)request.getAttribute("record_no");
	pageContext.setAttribute("changedRecordNo", changedRecordNo);
	
	
	SportsService sportsSvc = new SportsService();
	List<SportsVO> sportslist = sportsSvc.getAll();
	pageContext.setAttribute("sportslist", sportslist);
	
	if (memVOLogin == null) {
		response.sendRedirect(request.getContextPath() + "/front-end/mem/signin.jsp");
	} else {
		SportRecordService sportRecordSvc = new SportRecordService();
		List<SportRecordVO> list = sportRecordSvc.getOneSportRecordByMemId(memVOLogin.getMem_id());
		pageContext.setAttribute("list", list);
		
		
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Zarxio Fitness & Gym HTML Template</title>
<!-- favicon -->
<link rel=icon href=assets/img/favicon.png sizes="20x20"
	type="image/png">
<!-- animate -->
<link rel="stylesheet" href="assets/css/animate.css">
<!-- bootstrap -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<!-- magnific popup -->
<link rel="stylesheet" href="assets/css/magnific-popup.css">
<!-- Slick -->
<link rel="stylesheet" href="assets/css/slick.css" />
<link rel="stylesheet" href="assets/css/slick-theme.css" />
<!-- nice select -->
<link rel="stylesheet" href="assets/css/nice-select.css">
<!-- owl carousel -->
<link rel="stylesheet" href="assets/css/owl.carousel.min.css">
<!-- fontawesome -->
<link rel="stylesheet" href="assets/css/font-awesome.min.css">
<!-- flaticon -->
<link rel="stylesheet" href="assets/css/flaticon.css">
<!-- hamburgers -->
<link rel="stylesheet" href="assets/css/hamburgers.min.css">
<!-- Main Stylesheet -->
<link rel="stylesheet" href="assets/css/style.css">
<!-- responsive Stylesheet -->
<link rel="stylesheet" href="assets/css/responsive.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/footerFile/footer.css">

<style>
#hi_mem {
	font-family: Microsoft JhengHei;
}

@media screen and (max-width:1400px) {
	#hi_mem {
		display: none;
	}
}

#PageSelecter {
	padding: 5px 10px;
	margin-left: 50px;
	font-family: Microsoft JhengHei;
}

#sportdata-bar {
	box-shadow: 2px 2px 10px black;
}

form {
	display: flex;
}

section {
	margin-top: 20px;
}

.line {
	display: flex;
	text-align: center;
	padding: 5px;
}

.sportdata-div {
	margin-bottom: 1px;
	border: 1px solid transparent;
	border-radius: .25rem;
}

.right-border-bar {
	border-right: 1px lightgray double;
}

.right-border-data {
	border-right: 1px lightblue double;
}

.btn {
	opacity: .5;
}

.btn:hover {
	opacity: 1;
}

.changecolor {
	animation: mymove 2s alternate;
}

@keyframes mymove {
	0%{
		background-color: #cce5ff;
	}
	50%{
		background-color:#cccfff;
	}
	100%{
		background-color:#cce5ff;		
	}
}

.sign-content.text-center.h-100 .row {
    margin-left: 42px;
    margin-top: 23px;
}
</style>

</head>
<body>

	<!-- preloader area start -->
	<div class="preloader" id="preloader">
		<div class="preloader-inner">
			<div class="spinner">
				<div class="dot1"></div>
				<div class="dot2"></div>
			</div>
		</div>
	</div>
	<!-- preloader area end -->

	<!-- search Popup -->
	<div class="body-overlay" id="body-overlay"></div>
	<div class="search-popup" id="search-popup">
		<form action="index.html" class="search-form">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search.....">
			</div>
			<button type="submit" class="submit-btn">
				<i class="fa fa-search"></i>
			</button>
		</form>
	</div>
	<!-- //. search Popup -->

	<%@ include file="../memInclude/navbar.file"%>
	
	<!-- breadcrumb area -->
	<div class="breadcrumb-style-1"
		style="background-image: url(assets/img/bg/service.png);">
		<div class="breadcrumb-inner">
			<h1 class="page-title">My SportData</h1>
		</div>
	</div>
	<!-- breadcrumb area end -->

	<div id="modal-button" data-toggle="modal" data-target="#myModal"
		style="display: none"></div>



	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<jsp:include page="updateSportRecord.jsp" />
				</div>

			</div>
		</div>
	</div>

	<!--  trainer start -->
	<section class="margin-bottom-100">
		<div class="container">
			<div class="row">
				<div class="form-div col-md-12">
					<form class="col-md-12">
						<div>
							<input type="hidden" name="mem_id" id="mem_id" size="30"
								value="${memVO.mem_id }" />
						</div>
						<div class="col-md-3">
							<input type="text" class="form-control" name="record_date"
								id="record_date"
								value="<%=(sportRecordVO == null)?"":sportRecordVO.getRecord_date() %>"
								required /><br>
						</div>
						<div class="col-md-3">
							<select id="sport_no" class="form-control" name="sport_no"
								value="<%=(sportRecordVO == null)?"":sportRecordVO.getSport_no() %>">
								<c:forEach var="sportsVO" items="${sportslist}">
									<option value="${sportsVO.sport_no}">${sportsVO.sport_name}
								</c:forEach>
							</select>
						</div>
						<div id="unit1" class="col-md-3">
							<input type="text" class="form-control" name="record1"
								id="record1" value="" placeholder="公斤" required />
						</div>
						<div id="unit2" class="col-md-3">
							<input type="text" class="form-control" name="record2"
								id="record2" value="" placeholder="次" required />
						</div>
						<div class="col-md-2">
							<input type="submit" name="submit" id="submit" value="新增" />
						</div>
					</form>

				</div>
			</div>
			<div class="container">
				<div class="row class-slider-style-2" id="change-space">

					<div class="alert alert-dark line col-md-12" id="sportdata-bar">
						<div class="col-md-2 right-border-bar">紀錄日期</div>
						<div class="col-md-2 right-border-bar">運動項目</div>
						<div class="col-md-6 right-border-bar">記錄</div>
						<div class="col-md-2 right-border-bar ">修改</div>
					</div>
					<%@ include file="page1.file"%>

					<input type="hidden" name="whichPage" id="whichPage"
						value="<%=whichPage %>">
						
					<!-- 運動記錄內容 START  -->
					<div id="data-div" class="col-md-12">
						
						<!-- 運動記錄列表 START -->
						<c:forEach var="sportRecordVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<div class="alert-primary line col-md-12 sportdata-div  ${sportRecordVO.record_no == changedRecordNo ? 'changecolor': ''}">
							<div class="col-md-2 right-border-data">
								<fmt:formatDate value="${sportRecordVO.record_date}" pattern="yyyy-MM-dd" />
							</div>
							<c:forEach var="sportsVO" items="${sportslist}" begin="0" end="<%=sportslist.size()%>">
								<c:choose>
									<c:when test="${sportRecordVO.sport_no == sportsVO.sport_no}">
										<div class="col-md-2 right-border-data">${sportsVO.sport_name}
										</div>
										<div class="col-md-3 right-border-data">${sportRecordVO.record1}${sportsVO.unit1}</div>
										<c:choose>
											<c:when test="${sportRecordVO.record2==0}">
												<div class="col-md-3 right-border-data"></div>
											</c:when>
											<c:otherwise>
												<div class="col-md-3 right-border-data">${sportRecordVO.record2}${sportsVO.unit2}</div>
											</c:otherwise>
										</c:choose>
									</c:when>
								</c:choose>
							</c:forEach>
							<center>
								<div class="col-md-2">
									<!-- Trigger the modal with a button -->
									<FORM METHOD="post" class="align-center"
										ACTION="<%=request.getContextPath()%>/front-end/sportrecord/sportrecord.do">
										<input type="submit" class="btn btn-primary" value="修改資料">
										<input type="hidden" name="action" value="getOne_From">
										<input type="hidden" name="whichPage" id="whichPage"
											value="<%=whichPage%>"> <input type="hidden"
											name="record_no" value="${sportRecordVO.record_no}">
									</FORM>
								</div>
							</center>
						</div>
					</c:forEach>
					<!-- 運動記錄列表 END -->
					
					<!-- 分頁器 START -->
					<div class="row">
						<div class="col-md-12">
							<div
								class="pagination-area d-flex justify-content-center margin-top-50">
								<ul>
									<li id="before"><span class="next page-bumber"><i
											class="fa fa-angle-left"></i> Prev</span></li>

									<li><span class="page-bumber current"><%=whichPage%></span></li>

									<li id="after"><span class="next page-bumber">Next
											<i class="fa fa-angle-right"></i>
									</span></li>
								</ul>
								<%if (pageNumber > 1) {%>
								<div class="dropdown">
									<a class="btn btn-secondary dropdown-toggle" type="button" id="PageSelecter" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 第<%=whichPage%>頁</a>
									<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
										<%for (int i = 1; i <= pageNumber; i++) {%>
										<a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/sportrecord/sportRecord.jsp?whichPage=<%=i%>">第<%=i%>頁</a>
										<%}%>
									</div>
								</div>
								<%} %>
							</div>
						</div>
					</div>
					<!-- 分頁器 END -->
					
				</div>
				<!-- 運動記錄內容 END -->
				
			</div>
		</div>
	</section>
	<!--  trainer end -->

	<%@ include file="../footerFile/pageFooter.file"%>

	<!-- back to top area start -->
	<div class="back-to-top">
		<span class="back-top"><i class="fa fa-angle-up"></i></span>
	</div>
	<!-- back to top area end -->

	<!-- jquery -->
	<script src="assets/js/jquery-2.2.4.min.js"></script>
	<!-- popper -->
	<script src="assets/js/popper.min.js"></script>
	<!-- bootstrap -->
	<script src="assets/js/bootstrap.min.js"></script>
	<!-- magnific popup -->
	<script src="assets/js/jquery.magnific-popup.js"></script>
	<!-- wow -->
	<script src="assets/js/wow.min.js"></script>
	<!-- nice select -->
	<script src="assets/js/nice-select.js"></script>
	<!-- owl carousel -->
	<script src="assets/js/owl.carousel.min.js"></script>
	<!-- Slick -->
	<!-- 	<script src="assets/js/slick.min.js"></script> -->
	<!-- Slick Animation -->
	<!-- 	<script src="assets/js/slick-animation.js"></script> -->
	<!-- waypoint -->
	<script src="assets/js/waypoints.min.js"></script>
	<!-- counterup -->
	<script src="assets/js/jquery.counterup.min.js"></script>
	<!-- imageloaded -->
	<script src="assets/js/imagesloaded.pkgd.min.js"></script>
	<!-- isotope -->
	<script src="assets/js/isotope.pkgd.min.js"></script>
	<!-- rProgressbar -->
	<script src="assets/js/jQuery.rProgressbar.min.js"></script>
	<!-- main js -->
	<script src="assets/js/main.js"></script>
	<!-- 	<script src="assets/js/script.js"></script> -->
	<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
	<%
		java.sql.Date record_date = new java.sql.Date(System.currentTimeMillis());
		
		
		java.sql.Date record_date2 = null;
		try {
			record_date2 = sportRecordVO.getRecord_date();
		} catch (Exception e) {
			record_date2 = new java.sql.Date(System.currentTimeMillis());
		}
		
	%>

	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script
		src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

	<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px;*/
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

	<script>
	        $.datetimepicker.setLocale('zh');
	        $('#record_date').datetimepicker({
		       theme: '',              //theme: 'dark',
		       timepicker:false,       //timepicker:true,
		       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
		       format:'Y-m-d',         //format:'Y-m-d H:i:s',
			   value: "<%=record_date%>"
			});
	        $('#record_date2').datetimepicker({
		 	       theme: '',              //theme: 'dark',
		 	       timepicker:false,       //timepicker:true,
		 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
		 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		 		   value: "<%=record_date2%>"
		 	});
	
	var sport_no = document.getElementById('sport_no');
	var unit1 = document.getElementById('unit1');
	var unit2 = document.getElementById('unit2');
	var sport_no2 = document.getElementById('sport_no2');
	var unit12 = document.getElementById('unit12');
	var unit22 = document.getElementById('unit22');
	var before = document.getElementById("before");
	var after = document.getElementById("after");
	
	
	
	before.addEventListener("click", function(){
		<%if (whichPage > 1) {%>
		
		window.location.href="<%=request.getContextPath()%>/front-end/sportrecord/sportRecord.jsp?whichPage=<%=whichPage - 1%>";
		<%}%>
	});
	after.addEventListener("click", function(){
		<%if (whichPage < pageNumber) {%>
		
		window.location.href="<%=request.getContextPath()%>/front-end/sportrecord/sportRecord.jsp?whichPage=<%=whichPage + 1%>";
		<%}%>
	});
	
	
	sport_no.addEventListener('change', function(){
		if(sport_no.value == "SPORT00001"){
			unit1.innerHTML='<input type="text" class="form-control" name="record1" id = "record1" value="" placeholder="公斤"/>';
			unit2.innerHTML='<input type="text" class="form-control" name="record2" id = "record2" value="" placeholder="次"/>';
			
		}else if(sport_no.value == "SPORT00002" || sport_no.value == "SPORT00003"){
			unit1.innerHTML='<input type="text" class="form-control" name="record1" id = "record1" value="" placeholder="公尺"/>';
			unit2.innerHTML='<input type="text" class="form-control" name="record2" id = "record2" value="" placeholder="分鐘"/>';
		}else{
			unit1.innerHTML='<input type="text" class="form-control" name="record1" id = "record1" value="" placeholder="分鐘"/>';
			unit2.innerHTML='<input type="hidden" class="form-control" id = "record2" name="record2" value="0"/>';
		}
	});
	sport_no2.addEventListener('change',function() {
		if (sport_no2.value == "SPORT00001") {
			unit12.innerHTML = '<input type="text" class="form-control" name="record1" value="" placeholder="公斤" required/>';
			unit22.innerHTML = '<input type="text" class="form-control" name="record2" value="" placeholder="次" required/>';
		} else if (sport_no2.value == "SPORT00002"|| sport_no2.value == "SPORT00003") {
			unit12.innerHTML = '<input type="text" class="form-control" name="record1" value="" placeholder="公尺" required/>';
			unit22.innerHTML = '<input type="text" class="form-control" name="record2" value="" placeholder="分鐘" required/>';
		} else {
			unit12.innerHTML = '<input type="text" class="form-control" name="record1" value="" placeholder="分鐘" required/>';
			unit22.innerHTML = '<input type="hidden" class="form-control" name="record2" value="0"/>';
		}
	});
	window.onload = function(){
		if(sport_no.value == "SPORT00001"){
			unit1.innerHTML='<input type="text" class="form-control" name="record1" id = "record1" value="" placeholder="公斤" required/>';
			unit2.innerHTML='<input type="text" class="form-control" name="record2" id = "record2" value="" placeholder="次" required/>';
		}else if(sport_no.value == "SPORT00002" || sport_no.value == "SPORT00003"){
			unit1.innerHTML='<input type="text" class="form-control" name="record1" id = "record1" value="" placeholder="公尺" required/>';
			unit2.innerHTML='<input type="text" class="form-control" name="record2" id = "record2" value="" placeholder="分鐘" required/>';
			
		}else{
			unit1.innerHTML='<input type="text" class="form-control" name="record1" id = "record1" value="" placeholder="分鐘" required/>';
				unit2.innerHTML = '<input type="hidden" class="form-control" name="record2" id = "record2" value="0"/>';
			}
		if(sport_no2.value == "SPORT00001"){
			unit12.innerHTML='<input type="text" class="form-control" name="record1" value="<%=(sportRecordVO == null)?"":sportRecordVO.getRecord1() %>" placeholder="公斤" required/>';
			unit22.innerHTML='<input type="text" class="form-control" name="record2" value="<%=(sportRecordVO == null)?"":sportRecordVO.getRecord2() %>" placeholder="次" required/>';
		}else if(sport_no2.value == "SPORT00002" || sport_no2.value == "SPORT00003"){
			unit12.innerHTML='<input type="text" class="form-control" name="record1" value="<%=(sportRecordVO == null)?"":sportRecordVO.getRecord1() %>" placeholder="公尺" required/>';
			unit22.innerHTML='<input type="text" class="form-control" name="record2" value="<%=(sportRecordVO == null)?"":sportRecordVO.getRecord2() %>" placeholder="分鐘" required/>';
			
		}else{
			unit12.innerHTML='<input type="text" class="form-control" name="record1" value="<%=(sportRecordVO == null)?"":sportRecordVO.getRecord1() %>" placeholder="分鐘" required/>';
			unit22.innerHTML='<input type="hidden" class="form-control" name="record2" value="0"/>';
		}

			if ("${openModal}") {
				$('#modal-button').click();
			}

			$('.btn-secondary').click(function() {
				if ($('.dropdown-menu').css('display') === 'none') {
					$('.dropdown-menu').css('display', 'block');
				}
				$('.dropdown-menu').mouseover(function() {
					$('.dropdown-menu').css('display', 'block');
				})
				$('.dropdown-menu').mouseout(function() {
					$('.dropdown-menu').css('display', 'none');
				})
			});

		};

		$("#submit").click(function(e) {
			e.preventDefault();

			$.ajax({
				url : "NewSportRecord.jsp",
				type : "GET",
				data : {
					mem_id : $('#mem_id').val(),
					record_date : $('#record_date').val(),
					sport_no : $('#sport_no').val(),
					record1 : $('#record1').val(),
					record2 : $('#record2').val(),
					whichPage : $('#whichPage').val()

				},
				success : function(data) {
					$('#change-space').html(data);
				},
				error : function() {
					alert("請確認輸入的資訊!");
				}
			});
		});
	</script>
</body>
</html>

<%
	}
%>