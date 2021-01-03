<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Laban Dictionary - Từ điển Anh Việt, Việt Anh, Anh Anh</title>
<meta name="description" content="Từ điển trực tuyến miễn phí cho người Việt. Cung cấp 2 bộ từ điển chính: Anh - Việt và Việt - Anh. Kho từ đồ sộ cùng hệ thống gợi ý từ thông minh, Laban Dictionary giúp tra cứu nhanh chóng nhất."/>
<meta name="keywords" content="tu dien, tra tu, dictionary, translate, tu dien anh viet, tu dien viet anh" />
<!-- <link href="https://stc-laban.zdn.vn/dictionary/images/favicon.ico" rel="shortcut icon">
<link rel="search" type="application/opensearchdescription+xml" title="Laban Dictionary" href="https://stc-laban.zdn.vn/dictionary/xml/open-search.xml" />
<link rel="stylesheet" type="text/css" href="https://stc-laban.zdn.vn/dictionary/css/styles-1.11.min.css" />
<link rel="stylesheet" type="text/css" href="https://stc-laban.zdn.vn/dictionary/css/autocomplete-1.03.min.css" />
 -->
<script type="text/javascript" src="https://stc-laban.zdn.vn/dictionary/js/jquery.min.js"></script>
<script type="text/javascript" src="https://stc-laban.zdn.vn/dictionary/js/jquery.autocomplete-1.01.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/template/web/css/autocomplete-1.03.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/template/web/css/styles-1.11.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/template/web/js/jquery.autocomplete-1.01.min.js">
<link rel="stylesheet" href="${pageContext.request.contextPath}/template/web/js/jquery.draggable.min.js">
<link rel="stylesheet" href="${pageContext.request.contextPath}/template/web/js/jquery.min.js">
<link rel="stylesheet" href="${pageContext.request.contextPath}/template/web/js/lbutils-1.01.min.js">

<!-- <script type="text/javascript">
	$(document).ready(function() {
		$('#search').autocomplete({
			source: '${pageContext.request.contextPath}/search'
		});
	});
</script> -->
</head>
<body>
<div class="overlay-bg hidden"></div>
<!-- Begin: HEADER -->
<div id="header">
<div class="wrapper">
<div class="header_content">
<h1 class="logo">
<a title="Về trang chủ" href="https://dict.laban.vn" >Laban Dictionary - Từ diển Anh Việt, Việt Anh</a>
</h1>
<div class="clr"></div>
</div>
</div>
</div>
<!-- END HEADER -->

<!-- begin: Body site -->
<form method="POST" action="${pageContext.request.contextPath}/trang-chu">
<a href="" >Login Admin</a>
<div class="input_area">
<input maxlength="49" type="text" class="input_box" value="" id="txtSearchBox" name="textboxSearch">
<input maxlength="49" type="text" class="input_box hidden" value="" id="txtSearchBox_vi" name="term">
<input maxlength="49" type="text" class="input_box hidden" value="" id="txtSearchBox_en_en" name="term"> 

<select name="ChooseType" id="searchOptions" class="options" >
<option value="1" >Anh - Việt</option>
<option value="2" >Việt - Anh</option>
</select>
<div><input type="submit" name="search" id="search"></div>
</div>
</form>
<c:out value="${nodeModel.getData().getWord()}"/>
<c:out value="${nodeModel.getData().getPronunciation()}"/>
<c:out value="${nodeModel.getData().getWordType()}"/>
<c:out value="${nodeModel.getData().getMean()}"/>
<form method="POST" action="${pageContext.request.contextPath}/EditWordServlet">
<input type="submit" name="action" id="search" value="update"></div>
<input type="hidden" name="WordEdit" value="${nodeModel.getData().getWord()}"> 
</form>
<div>
<a href="${pageContext.request.contextPath}/views/web/AddNewWord.jsp">Thêm một từ mới</a>
 <div class="info_l">
<h3>Laban Dictionary</h3>
<ul>
<li> Website have been develop by Khang and Dat</li>
<li>Website is the project to submit for subject Project 1</li>
<li>Instructors guide: ThS.Tran Cong Tu</li>
</ul>
</div>

<!--
<div class="info_r">
<div>
<img src="https://stc-laban.zdn.vn/dictionary/images/img_app.jpg" width="70" height="69" class="fl">
<div class="info_app">
<h3>Laban Dictionary trên mobile</h3>
<p>Tra từ offline, không cần kết nối internet.<br>
Hỗ trợ tra từ bằng giọng nói.<br>
"Cửa sổ tra nhanh" cho phép tra từ trong ứng dụng khác</p>
<a href="http://l.123c.vn/d/labandic_dictweb"><img src="https://stc-laban.zdn.vn/dictionary/images/ic_download.png" width="69" height="18"></a>
</div>
</div>
 <div class="clr"></div>
<div id="extension_recommend_download" style="display: none">
<img src="https://stc-laban.zdn.vn/dictionary/images/img_extension.jpg" width="65" height="60" class="fl">
<div class="info_app">
<h3>Laban Dictionary - Chrome extension</h3>
<p>Công cụ tra từ điển tiện lợi trên các website:<br>
Hỗ trợ từ điển Anh-Việt, Việt-Anh và Anh-Anh<br>
Tra từ dễ dàng với thao tác click đúp hoặc quét khối</p>
<a onclick="trackOutboundLink('https://chrome.google.com/webstore/detail/laban-dictionary-by-laban/kdoofkpcjhkbhedgkdbagobockcmeoeb')" href="https://chrome.google.com/webstore/detail/laban-dictionary-by-laban/kdoofkpcjhkbhedgkdbagobockcmeoeb" target="_blank"><img src="https://stc-laban.zdn.vn/dictionary/images/ic_download.png" width="69" height="18"></a>
-->
<!-- <div class="clr"></div>
</div>
</div>
</div>
<div class="clr"></div>
</div>
</div>
</div>
END FOOTER

 --></body>
</html>