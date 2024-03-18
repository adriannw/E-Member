<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ include file="/include/taglibs.jsp"%><c:set var="path" value="${pageContext.request.contextPath}" />
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="Buy your insurance on the web!" />
<meta name="keywords" content="asuransi,asuransi jiwa,sinarmas,ekalife" />
<meta name="author" content="PT Asuransi Jiwa Sinarmas MSIG Tbk. | Administered by: Yusuf Sutarko | Original design: Andreas Viklund - http://andreasviklund.com/" />
<link rel="stylesheet" type="text/css" href="${path}/include/css/general.css" media="screen" title="general" />
<script type="text/javascript" src="${path}/include/js/links.js"></script>
<script type="text/javascript">
<!--
path = '${path}';
//-->
</script>
<title>PT Asuransi Jiwa Sinarmas MSIG Tbk.</title>
</head>
<body>
<div id="toptabs"><p>&nbsp;</p></div>
<div id="container">
<div id="navitabs">
<h2 class="hide">Site menu:</h2>
<a class="navitab1" href="${path}/common/home.htm">&nbsp;</a>
</div>
<div id="desc" style="background: #505050 url(${path}/include/images/banner/home.jpg) top left no-repeat;"></div>
<h3></h3>
<c:choose>
	<c:when test="${param.e eq 404}">
		<div class="block">
		<h2>Maaf, halaman yang anda minta tidak ada.</h2> 
		Harap pastikan halaman yang anda masukkan benar. Terima kasih.
		</div>
		<div class="block">
		<h2>We're sorry, but the page you requested cannot be found in our server</h2> 
		Please make sure that you're entering the right address. Thank you.
		<br/><br/><img title="Back" src="${path}/include/images/beige/arrow-left.gif" onclick="history.go(-1);"/>
		</div>
	</c:when>
	<c:when test="${param.e eq 500}">
		<div class="block">
		<h2>Maaf, sedang ada masalah dalam server kami.</h2> 
		Kami telah mencatat error yang terjadi, dan akan memperbaikinya. <br/>Maaf atas ketidaknyamanan anda.
		</div>
		<div class="block">
		<h2>It seems an error has occured in our server.</h2> 
		We've logged the error, and will fix it as soon as we can. <br/>We're sorry for the inconvenience.
		<br/><br/><img title="Back" src="${path}/include/images/beige/arrow-left.gif" onclick="history.go(-1);"/>
		</div>
	</c:when>
	<c:otherwise>
		<div class="block">
		<h2>Error</h2> 
		<br/><br/>
		<input class="btnprev" type="button" value="" onmouseover="this.className='btnprev2';" onmouseout="this.className='btnprev';" onclick="history.go(-1);" />
		</div>
	</c:otherwise>
</c:choose>
<br/>
<div id="footer" style="text-align: center;">
Copyright &copy; 2007 PT Asuransi Jiwa Sinarmas MSIG Tbk.
</div>
</div>
</body>
</html>