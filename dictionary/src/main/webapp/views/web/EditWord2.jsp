<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UTE Dictionary - Từ điển Anh Việt, Việt Anh</title>
<meta name="description" content="Từ điển trực tuyến miễn phí cho người Việt. Cung cấp 2 bộ từ điển chính: Anh - Việt và Việt - Anh. Kho từ đồ sộ cùng hệ thống gợi ý từ thông minh, Laban Dictionary giúp tra cứu nhanh chóng nhất."/>
<meta name="keywords" content="tu dien, tra tu, dictionary, translate, tu dien anh viet, tu dien viet anh" />
<link href="https://stc-laban.zdn.vn/dictionary/images/favicon.ico" rel="shortcut icon">
<link rel="search" type="application/opensearchdescription+xml" title="Laban Dictionary" href="https://stc-laban.zdn.vn/dictionary/xml/open-search.xml" />
<!-- <link rel="stylesheet" type="text/css" href="https://stc-laban.zdn.vn/dictionary/css/styles-1.11.min.css" /> -->
<link rel="stylesheet" type="text/css" href="https://stc-laban.zdn.vn/dictionary/css/autocomplete-1.03.min.css" /> 

<script type="text/javascript" src="https://stc-laban.zdn.vn/dictionary/js/jquery.min.js"></script>
<script type="text/javascript" src="https://stc-laban.zdn.vn/dictionary/js/jquery.autocomplete-1.01.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/templates/web/css/autocomplete-1.03.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/templates/web/css/styles-1.11.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/templates/web/js/jquery.autocomplete-1.01.min.js">
<link rel="stylesheet" href="${pageContext.request.contextPath}/templates/web/js/jquery.draggable.min.js">
<link rel="stylesheet" href="${pageContext.request.contextPath}/templates/web/js/jquery.min.js">
<link rel="stylesheet" href="${pageContext.request.contextPath}/templates/web/js/lbutils-1.01.min.js">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script type="text/javascript">
var userAgent = navigator.userAgent.toLowerCase();
if (/iphone/i.test(userAgent) || /android/i.test(userAgent) || (/mobile/i.test(userAgent))) {
window.location = 'http://m.dict.laban.vn/';
}
if(typeof String.prototype.trim !== 'function') {
String.prototype.trim = function() {
return this.replace(/^\s+|\s+$/g, '');
}
}
</script>
</head>
<body>
<div class="overlay-bg hidden"></div>
<!-- Begin: HEADER -->
<div id="header">
<div class="wrapper">
<div class="header_content">
<h1 class="logo">
<a title="Về trang chủ" href="${pageContext.request.contextPath}/trang-chu" >UTE Dictionary - Từ diển Anh Việt, Việt Anh</a>
</h1>
<div class="clr"></div>
</div>
</div>
</div>
<!-- END HEADER -->

<!-- begin: Body site -->
<div class="wrapper"style="
    margin: 0 auto;
    width: 30%;">
<div class="laban_tudien">

<form action="${pageContext.request.contextPath}/EditWordServlet">
<div class="input_area">
<div>
<p style="font-size: x-large;font-style:oblique;">Word Edit:</p>
<input maxlength="49" type="text" class="input_box" id="txtSearchBox" placeholder="Enter Word" value="${nodeModel.getData().getWord()}" id="txtSearchBox" name="WordAdd" disabled="disabled">
<c:out value="${checkError.getErrorWord()}"/>
</div>
<div>
<p style="font-size: x-large;font-style:oblique;">Pronunciation Edit:</p>
<input maxlength="49" type="text" class="input_box"  value="${nodeModel.getData().getPronunciation()}" placeholder="In put pronunciation"  name="Pronunciation">
<c:out value="${checkError.getErrorPro()}"/>
</div>
<div>
<p style="font-size: x-large;font-style:oblique;">Word Type Edit:</p>
<input maxlength="49" type="text" class="input_box" value="${nodeModel.getData().getWordType()}" placeholder="Enter Word Type"  name="WordType">
<c:out value="${checkError.getErroWordType()}"/>
</div>
<div>
<p style="font-size: x-large;font-style:oblique;">Mean Edit:</p>
<input maxlength="49" type="text" class="input_box" value="${nodeModel.getData().getMean()}" placeholder="Enter Mean"  name="Mean">
<c:out value="${checkError.getErrorMean()}"/>
</div>
<!-- <input maxlength="49" type="text" class="input_box hidden" autocomplete="off" value="" id="txtSearchBox_vi" name="termEn"> -->
<select id="searchOptions" class="options" name="searchOptions" onchange="location = this.options[this.selectedIndex].value;">
<option value="2" >Việt - Anh</option>
<option value="${pageContext.request.contextPath}/views/web/AddNewWord.jsp" >Anh - Việt</option>

</select>
<input type="submit" name="action" id="add" value="Edit" style="background-color: #c47135;">
<input type="submit" name="action" id="add" value="Remove" onclick="return confirm('Are you sure you want to delete?')" style="background-color: #c47135;">
</div>
<input type="hidden" name="wordSearch" value="${nodeModel.getData().getWord()}">
<input type="hidden" name="wordMean" value="${nodeModel.getData().getMean()}">
<input type="hidden" name="searchOptions" value="2"> 
</form>
<a href="${pageContext.request.contextPath}/views/web/AddNewWord.jsp">Thêm một từ mới</a>
</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$('#txtSearchBox').autocomplete({
			source: '${pageContext.request.contextPath}/search?idSearchOption=1'
		});
	});
</script>





<div class="info_l">
<h3>UTE Dictionary</h3>
<ul>
<li>Từ điển trực tuyến miễn phí cho người Việt</li>
<li>Cung cấp 2 bộ từ điển chính: Anh – Việt, Việt – Anh</li>
<li>Kho từ đồ sộ cùng hệ thống gợi ý từ thông minh, UTE Dictionary giúp tra cứu nhanh chóng nhất.</li>
</ul>
</div>




<div id="footer">
<div class="wrapper">
<div class="footer_content">
<h2 class="logo_footer">UTE</h2>
<ul class="footer_info">
<li style="list-style:none;"><a target="_blank" href="https://app.laban.vn/laban-dictionary" title="">Giới thiệu</a></li>
<!-- <li><a target="_blank" href="https://laban.vn" title="">Vào Laban.vn</a></li> -->
<li><a target="_blank" href="/api" title="">API</a></li>
<li>Web có sử dụng một số nền của trang web laban.vn</li>
<li><a href="javascript:;" onclick="showContact();" title="">Liên hệ</a></li>
<li style="list-style:none; padding-left:275px; padding-right:0;">Copyright &copy; Laban.vn</li>
</ul>
<div class="clr"></div>
</div>
</div>
</div>
<!-- END FOOTER -->

</body>
</html>