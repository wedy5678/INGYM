<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group.model.*"%>
<%@ page import="com.mem.model.*"%>

<%
	GroupVO groupVO = (GroupVO) request.getAttribute("groupVO");
	MemVO memVOLogin = (MemVO) session.getAttribute("memVOLogin");
%>

<%
 response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
 response.setHeader("Pragma", "no-cache"); //HTTP 1.0
 response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>groupAdd</title>

<%@ include file="include/css_link"%>
<script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>

<style type="text/css">
/* Always set the map height explicitly to define the size of the div
       * element that contains the map. */

/* Optional: Makes the sample page fill the window. */
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
	font-family: microsoft JhengHei !important;
}

#map {
	height: 500px;
	width: 500px;
	border: 2px solid #333;
	background: #CCC;
}

#right-panel select, #right-panel input {
	font-size: 15px;
}

#right-panel select {
	width: 100%;
}

#right-panel i {
	font-size: 12px;
}

#right-panel {
	line-height: 30px;
	padding-left: 10px;
	height: 500px;
	width: 390px;
	overflow: auto;
	display: inline-block;
	border: 2px solid #333;
}

label {
	color: black;
	font-family: microsoft JhengHei;
	margin-left: 3px;
}

#hi_mem {
	font-family: Microsoft JhengHei;
}

@media screen and (max-width: 1400px) {
	#hi_mem {
		display: none;
	}
}

#logout {
	border-style: none;
	background: none;
	transition: all .3s;
	color: rgba(255, 255, 255, 0.5);
}

#logout:hover {
	text-decoration: none;
	outline: none;
	color: inherit;
}

.info_img {
	height: 210px;
	width: auto;
}

#place-img {
	max-width: 100%;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
}

#place-name {
	font-family: microsoft JhengHei;
	font-weight: bold;
	font-size: 20px;
}

#place-address {
	font-family: microsoft JhengHei;
}

.content-part {
    background-color: #E3E3E3;
}

span.material-icons {
    position: relative;
    top: 5px;
    left: -3px;
}

label.custom-file-label .material-icons + span {
    display: inline-block;
    position: absolute;
    top: 7.5px;
    margin-left: 3px;
}

label[for="inputGroupFile02"] .material-icons {
    top: 0;
}

 .blog-breadcrumb-overlay {
    background: -webkit-linear-gradient(left,rgb(0 0 0 / 0.4),rgb(0 0 0 / 0.4)), url(img/1244995.jpg);
    background-position: 97px -340px;
    background-size: 90%;
}

img#gro_pic_preview {
    position: relative;
    left: 65px;
    top: 32px;
}

span#magicbuttom {
    position: relative;
    top: -1px;
    left: -169px;
    font-size: 18px;
}
</style>

</head>
<body onload="connectChat();" onunload="disconnectChat();">

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

	<%@ include file="include/pageNavbar.file"%>

	<!-- breadcrumb area -->
	<div class="breadcrumb-style-1 blog-breadcrumb-overlay">
		<div class="breadcrumb-inner">
			<h1 class="page-title">Create Activity</h1>
			<ul class="page-list margin-bottom-4">
				<li><a href="<%=request.getContextPath()%>/front-end/gpt/listAllGroup.jsp">Activity List</a></li>
				<li><a href="<%=request.getContextPath()%>/front-end/gpt/group.do?action=viewMyGroup&mem_id=${memVOLogin.mem_id }">My Activity</a></li>
			</ul>
		</div>
	</div>
	<!-- breadcrumb area end -->

	<!-- home blog start -->
	<div class="product-details-tab padding-top-100 padding-bottom-135">
		<!--------- bottom�Q�令200�����D�������style�ʤ��F --------->
		<div class="container">
			<div class="row">
				<div class="col-lg-7">
					<jsp:useBean id="grouptypeSvc" scope="page" class="com.grouptype.model.GroupTypeService" />
					<FORM METHOD="post" action="group.do" method="post" enctype="multipart/form-data">
						<div class="content-part">
							<!---------------------------------��g�ʪ����------------------------------------>
							<div class="row">

								<div class="col-lg-12">
									<%-- ���~��C --%> 
							 		<c:if test="${not empty errorMsgs}"> 
							 			<font style="color: red">�Эץ��H�U���~:</font> 
										<ul> 
							 				<c:forEach var="message" items="${errorMsgs}"> 
							 					<li style="color: red">${message}</li> 
											</c:forEach> 
							 			</ul>
							 		</c:if> 
							 		
									<h2 style="font-family:microsoft JhengHei;">��g���θ��:<span id="magicbuttom" class="material-icons">control_point</span></h2>
									
								</div>

								<div class="col-lg-6">
									<div class="form-group">
										<label for="usr"><span class="material-icons">title</span>���ΦW��:</label> <input type="text" class="form-control" id="gro_name" name="gro_name" placeholder="�п�J���ΦW��"
											maxlength="15" value="<%=(groupVO == null) ? "" : groupVO.getGro_name()%>">
									</div>
								</div>

								<div class="col-lg-6">
									<div class="form-group">
										<label for="sel1"><span class="material-icons">sports_handball</span>���κ���:</label> <select class="form-control" id="show" name="type_no">
											<c:forEach var="grouptypeVO" items="${grouptypeSvc.all}">
												<option value="${grouptypeVO.type_no}">${grouptypeVO.type_name}
											</c:forEach>
										</select>
									</div>
								</div>

								<div class="col-lg-6">
									<div class="form-group">
										<label for="gro_start"><span class="material-icons">alarm_on</span>�}�l�ɶ�:</label> <input type="text" class="form-control" name="gro_start" id="gro_start"
											value="<%=(groupVO == null) ? "" : groupVO.getGro_start()%>">
									</div>
								</div>


								<div class="col-lg-6">
									<div class="form-group">
										<label for="gro_end"><span class="material-icons">alarm_off</span>�����ɶ�:</label> <input type="text" class="form-control" name="gro_end" id="gro_end"
											value="<%=(groupVO == null) ? "" : groupVO.getGro_end()%>">
									</div>
								</div>

								<div class="col-lg-6">
									<div class="form-group">
										<label for="gro_min"><span class="material-icons">person</span>�̤p�H��:</label> <input type="text" id="gro_min" class="form-control" name="gro_min" placeholder="�п�J�̤p�H��"
											value="<%=(groupVO == null) ? "" : groupVO.getGro_min()%>">
									</div>
								</div>


								<div class="col-lg-6">
									<div class="form-group">
										<label for="gro_max"><span class="material-icons">people</span>�̤j�H��:</label> <input type="text" id="gro_max" class="form-control" name="gro_max" placeholder="�п�J�̤j�H��"
											value="<%=(groupVO == null) ? "" : groupVO.getGro_max()%>">
									</div>
								</div>

								<div class="col-lg-12 col google-map">
									<div class="form-group">
										<label for="gro_addr"><span class="material-icons">add_location_alt</span>���ʦa�}:</label> <input type="text" class="form-control" name="gro_addr" placeholder="�п�J���ʦa�I�a�}"
											 ref="site" v-model="site" id="pac-input" value="<%=(groupVO == null) ? "" : groupVO.getGro_addr()%>">
									</div>
								</div>
								
								<div class="col-lg-12">
									<div class="form-group">
										<label for="cate"><span class="material-icons">add_comment</span>����²��:</label>
										<textarea id="cate" class="form-control" rows="7" name="gro_intro"></textarea>
									</div>
								</div>

								<div class="input-group mb-1" style="padding: 30px;">
									<div class="custom-file">
										<input type="file" class="custom-file-input" id="gro_pic" name="gro_pic" value="gro_pic"> <label
											class="custom-file-label" for="inputGroupFile02" style="font-size: 14px"><span class="material-icons" >photo</span><span>��ܹϤ�</span></label>
									</div>
								</div>




								<div class="btn-wrapper" style="text-align: center;margin-bottom: 55px;width: 100%;">
									<span><input class="btn btn-element btn-medium-size btn-main-color btn-rounded" id="productSubmit" type="submit" value="Confirm" style=" width: 49%;"></span>
									<span><input class="btn btn-element btn-medium-size btn-main-color btn-rounded" type="button" value="Back" onclick="history.back()" style=" width: 49%;"></span>
								</div>
								<input type="hidden" name="action" value="ADD">
								<input type="hidden" name="gro_lat" value="" id="gro_lat">
								<input type="hidden" name="gro_lng" value="" id="gro_lng">								
								
							</div>
						</div>
						<!---------------------------------��g�ʪ����------------------------------------>
					</FORM>
				</div>

				<div class="col-lg-5">
					<!-- �Ϥ��ϰ�W -->
					<div class="col google-map">
						<h2 style="font-size: 32px;font-weight: 500;">Google Map�G</h2>
						<div id="map"></div>
						<div id="preview">
							<img src="img/add.png" id="gro_pic_preview" width=400px height=274px>
						</div>
					</div>
						
						<div id="infowindow-content" style="color: black;">
							<img src="" width="16" height="16" id="place-icon"> <span id="place-name" class="title"></span><br> <span
								id="place-address"></span><br> <img src="" id="place-img">
						</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="include/pageFooter.file"%>

<!-- 	<!-- back to top area start --> -->
<!-- 	<div class="back-to-top"> -->
<!-- 		<span class="back-top"><i class="fa fa-angle-up"></i></span> -->
<!-- 	</div> -->
<!-- 	<!-- back to top area end --> -->
	
<%@ include file="include/chatBoxBody.file"%>

	<%@ include file="include/js_src"%>
	<!-- =========================================�H�U��  gro_start �������]�w========================================== -->
	<%
		java.sql.Timestamp gro_start = null;
		try {
			gro_start = groupVO.getGro_start();
		} catch (Exception e) {
			gro_start = new java.sql.Timestamp(System.currentTimeMillis());
		}
		java.sql.Timestamp gro_end = null;
		try {
			gro_end = groupVO.getGro_end();
		} catch (Exception e) {
			gro_end = new java.sql.Timestamp(System.currentTimeMillis());
		}
	%>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

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
        $('#gro_start').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 5,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d H:i',         //format:'Y-m-d H:i:s',
		   value: '<%=gro_start%>',// value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
	//startDate:	            '2017/07/10',  // �_�l��
		   minDate: '<%=gro_start%>', // �h������(���t)���e
	//maxDate:               '+1970-01-01'  // �h������(���t)����
	});
        $.datetimepicker.setLocale('zh');
        $('#gro_end').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 5,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d H:i',         //format:'Y-m-d H:i:s',
		   value: '<%=gro_end%>',  // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
	//startDate:	            '2017/07/10',  // �_�l��
		   minDate: '<%=gro_end%>',  // �h������(���t)���e
	//maxDate:               '+1970-01-01'  // �h������(���t)����
	});
</script>

	<script>
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
  $('#gro_end').datetimepicker({
   format:'Y-m-d H:i',
   onShow:function(){
    this.setOptions({
     minDate:$('#gro_start').val()?$('#gro_start').val():false
    })
   },
   timepicker:true,
   value: '<%=gro_end%>'
   });
});
	</script>

	<script>
		$(document).ready(
				function() {
					$("#gro_pic").change(function() {
						readURL(this);
					});
					function readURL(input) {
						if (input.files && input.files[0]) {
							var reader = new FileReader();

							reader.onload = function(e) {
								$('#gro_pic_preview').attr('src',
										e.target.result).fadeIn('slow');
							}
							reader.readAsDataURL(input.files[0]);
						}
					}
				});
	</script>
	<script>
		// This example requires the Places library. Include the libraries=places
		// parameter when you first load the API. For example:
		// <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">

		function initMap() {
			var map = new google.maps.Map(document.getElementById('map'), {
				center : {
					lat : 24.9680014,
					lng : 121.1900142
				},
				zoom : 13
			});
			var card = document.getElementById('pac-card');
			var input = document.getElementById('pac-input');
			var options = {
				componentRestrictions : {
					country : 'tw'
				}
			// ����b�x�W�d��
			};

			var strictBounds = document
					.getElementById('strict-bounds-selector');

			map.controls[google.maps.ControlPosition.TOP_RIGHT].push(card);

			var autocomplete = new google.maps.places.Autocomplete(input,
					options);

			// Bind the map's bounds (viewport) property to the autocomplete object,
			// so that the autocomplete requests use the current map bounds for the
			// bounds option in the request.
			autocomplete.bindTo('bounds', map);

			var infowindow = new google.maps.InfoWindow();
			var infowindowContent = document
					.getElementById('infowindow-content');
			infowindow.setContent(infowindowContent);
			var marker = new google.maps.Marker({
				map : map,
				anchorPoint : new google.maps.Point(0, -45),
				animation : google.maps.Animation.BOUNCE,
				draggable : true
			});
			autocomplete
					.addListener(
							'place_changed',
							function() {
								infowindow.close();
								marker.setVisible(false);
								var place = autocomplete.getPlace();
								console.log(place);
								if (!place.geometry) {
									// User entered the name of a Place that was not suggested and
									// pressed the Enter key, or the Place Details request failed.
									window
											.alert("No details available for input: '"
													+ place.name + "'");
									return;
								}

								// If the place has a geometry, then present it on a map.
								if (place.geometry.viewport) {
									map.fitBounds(place.geometry.viewport);
								} else {
									map.setCenter(place.geometry.location);
									map.setZoom(14); // Why 17? Because it looks good.
								}
								marker.setPosition(place.geometry.location);
								marker.setVisible(true);
								
								$('#gro_lat').attr("value",place.geometry.location.lat());
								$('#gro_lng').attr("value",place.geometry.location.lng());
								
								console.log("lat = "
										+ place.geometry.location.lat());
								console.log("lng = "
										+ place.geometry.location.lng());
								
								let addressSub = (place.formatted_address).substring(5);
								$("#pac-input").val(addressSub);

								console.log(place.address_components);

								infowindowContent.children['place-icon'].src = place.icon;
								infowindowContent.children['place-name'].textContent = place.name;
								infowindowContent.children['place-address'].textContent = place.formatted_address;
								infowindowContent.children['place-img'].src = place.photos[0].getUrl();
								infowindow.open(map, marker);
							});

			// Sets a listener on a radio button to change the filter type on Places
			// Autocomplete.

		}
	</script>
	
	<script>
		//magicbuttom
		$('#magicbuttom ').click(function(){
			$('#gro_name').val('Servlet���r�B��');
			$('#gro_min').val('1');
			$('#gro_max').val('5');
			$('#cate').val('���Java�{���Ө��AJVM�]Java Virtual Machine�^�O��@�~�t�ΡA.java �sĶ�� .class �ɮסA.class ��� JVM �Ө��A�N�O��i�����ɡA�A�� Java �{���򥻤W�u�{�o�@�ا@�~�t�ΡA�N�O JVM�C\n\n��A�}�l���g Servlet/JSP �{���ɡA�A�����}�l��Ĳ�e���]Container�^�������A�e���o�ӦW���]�Φb�p List�BSet �o���� Collection �W�A�]�N�O�Ψӫ����B�O�s���󪺸s���]Collection�^����A���L�A��󼶼g Servlet/JSP �ӻ��A�e���������󬰼s�x�A�b�̰򥻪��\��W�A�����ȫ�������A�٭t�d���󪺥ͩR�P���P�����A�Ȫ��s���C');
			
		});
	
	</script>
	
	   <!-- chatBox -->
	<%@ include file="include/chatBox.file"%>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBw1IB_wF8J22LKiqPxdO8wbfWgFIpoDTo&libraries=places&callback=initMap"
		defer></script>
</body>
</html>