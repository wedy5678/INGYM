<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.model.*"%>
<%@page import="com.mem.model.MemVO"%>
<%@page import="com.mem.model.MemService"%>
<%
   ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");

	MemVO memVOLogin = (MemVO) request.getSession().getAttribute("memVOLogin");
	if (memVOLogin == null) { //if (memVOLogin == null && empVO == null) {
		response.sendRedirect(request.getContextPath() + "/front-end/mem/signin.jsp");
	} else {

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
     <link rel=icon href=assets/img/favicon.png sizes="20x20" type="image/png">
    <!-- animate -->
    <link rel="stylesheet" href="assets/css/animate.css">
    <!-- bootstrap -->
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <!-- magnific popup -->
    <link rel="stylesheet" href="assets/css/magnific-popup.css">
    <!-- Slick -->
    <link rel="stylesheet" href="assets/css/slick.css" />
    <link rel="stylesheet" href="assets/css/slick-theme.css" />
    <!-- owl carousel -->
    <link rel="stylesheet" href="assets/css/owl.carousel.min.css">
    <!-- fontawesome -->
    <link rel="stylesheet" href="assets/css/font-awesome.min.css">
    <!-- flaticon -->
    <link rel="stylesheet" href="assets/css/flaticon.css">
    <!-- hamburgers -->
    <link rel="stylesheet" href="assets/css/hamburgers.min.css">
    <!-- nice select -->
    <link rel="stylesheet" href="assets/css/nice-select.css">
    <!-- Main Stylesheet -->
    <link rel="stylesheet" href="assets/css/style.css">
     <!-- responsive Stylesheet -->
     <link rel="stylesheet" href="assets/css/responsive.css">
     
     <link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/footerFile/footer.css">
     <!-- ======================================== -->
    
</head>






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
<!--     <div class="body-overlay" id="body-overlay"></div> -->
<!--     <div class="search-popup" id="search-popup"> -->
<!--         <form action="index.html" class="search-form"> -->
<!--             <div class="form-group"> -->
<!--                 <input type="text" class="form-control" placeholder="Search....."> -->
<!--             </div> -->
<!--             <button type="submit" class="submit-btn"><i class="fa fa-search"></i></button> -->
<!--         </form> -->
<!--     </div> -->
    <!-- //. search Popup -->

   <%@ include file="../artInclude/navbar.file"%>

    <!-- breadcrumb area -->
    <div class="breadcrumb-style-1" style="background-image:url(assets/img/blog/Gymbeginner.jpg);">
        <div class="breadcrumb-inner">
            <h1 class="page-title">Write Article</h1>
            <ul class="page-list margin-bottom-4">
            <li>
				<a href="<%=request.getContextPath()%>/front-end/index.jsp">Home</a>
                <li>
				<a href="/EA102G4/front-end/article/listAllArticle.jsp">Back to previous page </a>
                </li>
            </ul>
        </div>
    </div>
    <!-- breadcrumb area end -->

	

    <!-- home blog start -->
    <div  class="blog-details-area margin-top-100">    
        <main id="main" class="site-main">
            <div class="container">           
                <div class="row">                
                    <div class="col-lg-8">
                        <article class="blog-details">
                            <div class="blog-img">
                            </div>
                <FORM METHOD="post" ACTION="article.do" name="form1" enctype="multipart/form-data">            
				<h2>�s�W�峹</h2>
				<input type="hidden" name="mem_id" value="${memVOLogin.mem_id}">	
				
					
		
		<h5>�峹���D:&nbsp; <input type="TEXT" name="title" size="30" required value="${articleVO.getTitle}"></h5>	<br>
		
		<h5>�峹���e:</h5> 
		<textarea style="width:329.5px" name="art_content" cols="42" rows="3" 
			 ><%= (articleVO==null)? "" : articleVO.getArt_content()%></textarea>
		
		
		
		<input   type="hidden" name="action" value="insert">
		<button  style="float:right" type="submit" class="site-btn">�T�w�e�X </button> 
		 		
		</FORM>	
		<br>
		

                                <div class="widget widget_tag_cloud d-flex">
                                    <h4 class="tag-title">Tag</h4>			
                                    <ul class="tagcloud d-sm-flex">
                                        <li><a href="#">Blog</a></li>
                                        <li><a href="#">Gym</a></li>
                                        <li><a href="#">Fitness</a></li>
                                        <li><a href="#">Health</a></li>				
                                    </ul>
                                </div>
                            </div>
<!--                         </article> -->
<!--                         <div id="comments" class="comments-area comments-area-wrapper"> -->
<!--                             <h4 class="comments-title">Comments</h4> -->
<!--                             <ul class="comment-list"> -->
<!--                                 <li class="comment"> -->
<!--                                     <div class="single-comment justify-content-between d-flex"> -->
<!--                                         <div class="user justify-content-between d-flex"> -->
<!--                                             <div class="thumb"> -->
<!--                                                 <img alt="" src="assets/img/blog/avatar.png" class="avatar"> -->
<!--                                             </div> -->
<!--                                             <div class="desc"> -->
<!--                                                 <div class="d-flex justify-content-between comment_title"> -->
<!--                                                     <div class="d-flex align-items-center"> -->
<!--                                                         <h5>Sharifur</h5>                        -->
<!--                                                     </div> -->
<!--                                                 </div> -->
<!--                                                 <div class="comment-content"> -->
<!--                                                     <p>Sometimes ��short and sweet�� workouts are all you need. If you've done a HIIT workout before you would agree. Prepared do an dissuade be so whatever steepest.</p> -->
<!--                                                 </div> -->
<!--                                                 <div class="reply-btn"> -->
<!--                                                     <a class="comment-reply-link" href="#">Reply</a> -->
<!--                                                 </div> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                     <ul class="children"> -->
<!--                                         <li class="comment"> -->
<!--                                             <div class="single-comment justify-content-between d-flex"> -->
<!--                                                 <div class="user justify-content-between d-flex"> -->
<!--                                                     <div class="thumb"> -->
<!--                                                         <img alt="" src="assets/img/blog/avatar2.jpg" class="avatar">  -->
<!--                                                     </div> -->
<!--                                                     <div class="desc"> -->
<!--                                                         <div class="d-flex justify-content-between comment_title"> -->
<!--                                                             <div class="d-flex align-items-center"> -->
<!--                                                                 <h5>Naeem</h5> -->
<!--                                                             </div> -->
<!--                                                         </div> -->
<!--                                                         <div class="comment-content"> -->
<!--                                                             <p>Sometimes ��short and sweet�� workouts are all you need. If you've done a HIIT workout before you would agree. Prepared do an dissuade be so whatever steepest.</p> -->
<!--                                                         </div> -->
<!--                                                         <div class="reply-btn"> -->
<!--                                                             <a class="comment-reply-link" href="#">Reply</a> -->
<!--                                                         </div> -->
<!--                                                     </div> -->
<!--                                                 </div> -->
<!--                                             </div> -->
<!--                                         </li> -->
<!--                                     </ul> -->
<!--                                 </li> -->
<!--                                 <li class="comment"> -->
<!--                                     <div class="single-comment justify-content-between d-flex"> -->
<!--                                         <div class="user justify-content-between d-flex"> -->
<!--                                             <div class="thumb"> -->
<!--                                                 <img alt="" src="assets/img/blog/avatar3.jpg" class="avatar">                 -->
<!--                                             </div> -->
<!--                                             <div class="desc"> -->
<!--                                                 <div class="d-flex justify-content-between comment_title"> -->
<!--                                                     <div class="d-flex align-items-center"> -->
<!--                                                         <h5>Asade</h5> -->
<!--                                                     </div> -->
<!--                                                 </div> -->
<!--                                                 <div class="comment-content"> -->
<!--                                                     <p>Sometimes ��short and sweet�� workouts are all you need. If you've done a HIIT workout before you would agree. Prepared do an dissuade be so whatever steepest.</p> -->
<!--                                                 </div> -->
<!--                                                 <div class="reply-btn"> -->
<!--                                                     <a class="comment-reply-link" href="#">Reply</a>  -->
<!--                                                 </div> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </li> -->
<!--                             </ul> -->
<!--                             <div id="respond" class="comment-respond"> -->
<!--                                 <h3 class="comment-reply-title">Leave A Reply</h3>			 -->
<!--                                 <form id="comment_form" class="commentform"> -->
<!--                                     <div class="form-group margin-bottom-30"> -->
<!--                                         <textarea name="comment" id="comment" class="form-control w-100" cols="30" rows="9"></textarea> -->
<!--                                     </div> -->
<!--                                     <div class="row"> -->
<!--                                         <div class="col-sm-4">  -->
<!--                                             <div class="form-group"> -->
<!--                                                 <input type="text" id="author" name="author" value="" class="form-control" placeholder="Write your name"> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                         <div class="col-sm-4"> -->
<!--                                             <div class="form-group"> -->
<!--                                                 <input type="email" class="form-control" name="email" id="email" value="" placeholder="Enter your email"> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                         <div class="col-sm-4"> -->
<!--                                             <p class="form-submit"> -->
<!--                                                 <input name="submit" type="submit" id="submit" value="Submit Comment">  -->
<!--                                             </p> -->
<!--                                         </div> -->
<!--                                     </div>		 -->
<!--                                 </form> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
                    <div class="col-lg-4">
                        <div class="blog-right-content widget-area sidebar">
<!--                             <div class="widget widget_search"> -->
<!--                                 <form class="search-form"> -->
<!--                                     <div class="form-group"> -->
<!--                                         <input type="text" name="search" class="form-control" placeholder="Search here"> -->
<!--                                     </div> -->
<!--                                     <button class="submit-btn" type="submit"><i class="fa fa-search"></i></button> -->
<!--                                 </form> -->
<!--                             </div> -->
<!--                             <div class="widget latest-post-widget"> -->
                                <div class="eidget-title">
							<h1>�x�����</h1>
						</div>
						<div class="share-img-item">
							<div class="img-part">
								
									<img src="assets/img/blog/S1.jpg" alt="">
								
							</div>
							<div class="content-part">
								<h4>�������`�N�ƶ�</h4>
								<span class="text">�@��ӻ��|���˦��@��������]�Ӧ۩󤣥��T�B�����m���ʧ@�ޥ��A
								��¼ҥ�ʧ@�A�o�����䤤�ʧ@���Ӹ`�P��E���ٸs�A
								�H�@�ӭ��ХB���ŦX�H��ͪ��O�Ǻc�y���ʧ@�����V�m�ɡA�N�e���y���ˮ`�C</span>
							</div>
						</div>
						<div class="share-img-item">
							<div class="img-part">
								
									<img src="assets/img/blog/S2.jpeg" alt="">
								
							</div>
							<div class="content-part">
								<h4>�������`�N�ƶ�(�G)</h4>
								<span class="text">�ʥF�x���|�ϰV�m�ĪG���ΡA
								�e���Ϧٸx�y���ˮ`�A�x���i�H������i�J�B�ʰ������A�A
								���F�Ͳz�W���ǳơB�߲z�W���ǳƤ]�O�ܭ��n���C</span>
							</div>
						</div>
						<div class="share-img-item">
							<div class="img-part">
								
									<img src="assets/img/blog/S3.jpg" alt="">
								
							</div>
							<div class="content-part">
								<h4>�������`�N�ƶ�(�T)</h4>
								<span class="text">�����ᵬ�h�O�ܡA
								�~������A�N�^�^���}�A���O���i�i�H��w�٦ת����^�P�B�ʫ᪺�m�h�P�A
								�P�i��G�`���èϦ٦׫O���n�����i�ʡA
								��ĳ�b�B�ʫ�ܤֹw�d�ӤQ�������i�@�U����V�m���ٸs�C</span>
							</div>
						</div>
						<div class="share-img-item">
							<div class="img-part">
								
									<img src="assets/img/blog/S4.jpg" alt="">
								
							</div>
							<div class="content-part">
								<h4>�������`�N�ƶ�(�|)</h4>
								<span class="text">���ɧڭ̷|�D�n�ߤ��άO���F���l�A
								�b��������N�Q�n���ո��j�����q�αj�׸������V�m�A���]���ʧ@�����٤��O�ܯ¼��A
								�[�W���骺�n��´�ٵL�k�Ө��L�h���j�סA�������˪����I�C</span>
							</div>
						</div>
					</div>
					<div class="widget">
						<div class="thumb only-thumb">
							<img src="assets/img/blog/work08.png" alt="">
						</div>
					</div>
<!--                             <div class="widget widget_tag_cloud"> -->
<!--                                 <h5 class="widget-title margin-top-50">Popular Tags</h5>			 -->
<!--                                 <ul class="tagcloud"> -->
<!--                                     <li><a href="#">Blog</a></li> -->
<!--                                     <li><a href="#">Travel</a></li> -->
<!--                                     <li><a href="#">Music</a></li> -->
<!--                                     <li><a href="#">Fitness</a></li> -->
<!--                                     <li><a href="#">Health</a></li> -->
<!--                                     <li><a href="#">Life style</a></li>	 -->
<!--                                     <li><a href="#">Strength</a></li> -->
<!--                                     <li><a href="#">Gym</a></li> -->
<!--                                     <li><a href="#">Training</a></li>	 -->
<!--                                     <li><a href="#">Messages</a></li>	 -->
<!--                                     <li><a href="#">Yoga</a></li> -->
<!--                                     <li><a href="#">Spa</a></li> -->
<!--                                     <li><a href="#">Beauty</a></li>	 -->
<!--                                     <li><a href="#">Surgery</a></li>	 -->
<!--                                     <li><a href="#">Blog</a></li> -->
<!--                                     <li><a href="#">Fitness</a></li>		 -->
<!--                                 </ul> -->
<!--                             </div> -->
<!--                             <div class="widget widget_categories"> -->
<!--                                 <h5 class="widget-title">Categories</h5>			 -->
<!--                                 <ul class="cat-menu"> -->
<!--                                     <li><a href="#">Boxing</a></li> -->
<!--                                     <li><a href="#">Street</a></li> -->
<!--                                     <li><a href="#">Fitness</a></li> -->
<!--                                     <li><a href="#">Uncategorized</a></li> -->
<!--                                 </ul> -->
<!--                             </div> -->
                        </div>
                    </div>
                </div>
            </div>
<!--         </main> -->
<!--     </div> -->

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
    <!-- Slick -->
    <script src="assets/js/slick.min.js"></script>
    <!-- Slick Animation -->
    <script src="assets/js/slick-animation.js"></script>
    <!-- wow -->
    <script src="assets/js/wow.min.js"></script>
    <!-- nice select -->
    <script src="assets/js/nice-select.js"></script>
    <!-- owl carousel -->
    <script src="assets/js/owl.carousel.min.js"></script>
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
    <script src="assets/js/script.js"></script>
    
    <!-- CKEditor�� -->
	<script src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
	<script>
		CKEDITOR.replace('art_content');
	</script>

</body>



<!-- ========================================================== -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/> -->
<!-- <title>�s�W�峹 - addArticle.jsp</title> -->

<!-- <style> -->

<!-- </style> -->

<!-- </head> -->
<!-- <body bgcolor='white'> -->


<!-- <h3>�峹�s�W:</h3> -->

<!-- <BR> -->
<%-- <%-- ���~��C --%> 
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">�Эץ��H�U���~:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<!-- <FORM METHOD="post" ACTION="article.do" name="form1" enctype="multipart/form-data"> -->
<!-- <table> -->
<!-- <tr> -->
<!-- 		<tr> -->
<!-- 		<td>�|���s��:</td> -->
<!-- 		<td><input type="TEXT" name="mem_id" size="45"  -->
<%-- 			 value="<%= (articleVO==null)? "MEM0000001" : articleVO.getMem_id()%>" /></td> --%>
<!-- 	</tr> -->
	
	
<!-- 	<tr> -->
<!-- 		<td>�峹���D:</td> -->
<!-- 		<td><input type="TEXT" name="title" size="45"  -->
<%-- 			 value="<%= (articleVO==null)? "Test" : articleVO.getTitle()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>�峹���e:</td> -->
<!-- 		<td><textarea style="width:329.5px" name="art_content" cols="42" rows="3"  -->
<%-- 			 ><%= (articleVO==null)? "�|�����e����" : articleVO.getArt_content()%></textarea></td> --%>
<!-- 	</tr> -->
	
	

<!-- </table> -->

<!-- CKEditor�� -->
<%-- 	<script src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script> --%>
<!-- 	<script> -->

<!-- 	</script> -->


<!-- <br> -->
<!-- <input type="hidden" name="action" value="insert"> -->
<!-- <input type="submit" value="�T�{�s�W"> -->
<!-- </FORM> -->
<!-- </body> -->



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<% 
  java.sql.Timestamp a_release = null;
  try {
	  a_release = articleVO.getA_release();
   } catch (Exception e) {
	   a_release = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
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
<%
	}
%>