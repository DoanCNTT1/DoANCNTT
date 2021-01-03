
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UTE Shopping</title>
<link href="${pageContext.request.contextPath}/template/web/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${pageContext.request.contextPath}/template/web/js/jquery.min.js"></script>
<!-- Custom Theme files -->
<!--theme-style-->
<link href="${pageContext.request.contextPath}/template/web/css/style.css" rel="stylesheet" type="text/css" media="all" />	
<!--//theme-style-->

<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--fonts-->
<link href='http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900' rel='stylesheet' type='text/css'><!--//fonts-->
<!-- start menu -->
<link href="${pageContext.request.contextPath}/template/web/css/memenu.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="${pageContext.request.contextPath}/template/web/js/memenu.js"></script>
<script>$(document).ready(function(){$(".memenu").memenu();});</script>
<script src="${pageContext.request.contextPath}/template/web/js/simpleCart.min.js"> </script>
</head>
<body>
		
	
	<!--Header -->
<%-- 	<jsp:include page="header.jsp"></jsp:include> --%>
	<!--Header  -->
	
	
	
	
	<div class="container">
		<div class="account">
		<h1>Account</h1>
		<div class="account-pass">
		<div class="col-md-8 account-top">
			<form method="POST" action="${pageContext.request.contextPath}/login">
			<c:out value="${errorString}" />
			<div> 	
				<span>UserName</span>
				<input type="text" name="username" placeholder="example: abcxyz" value=<c:out value="${user.getTenTaiKhoan()}" />> 
			</div>
			<div> 
				<span >Password</span>
				<input type="password" name="password" placeholder="Enter your password" value=<c:out value ="${user.getMatKhau()}" />>
			</div>				
				<input type="submit" value="Login"> 
			</form>
			
		</div>
		<div class="col-md-4 left-account ">
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
			<a href="${pageContext.request.contextPath}/views/web/register.jsp" class="create">Create an account</a>
<div class="clearfix"> </div>
		</div>
	<div class="clearfix"> </div>
	</div>
	</div>


	<!--Footer -->
<%-- 	<jsp:include page="footer.jsp"></jsp:include> --%>
	<!--Footer  -->

</div>
</body>
</html>