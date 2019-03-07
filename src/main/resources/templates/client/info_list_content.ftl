<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--网页左上角小图标-->
<link rel="shortcut icon" href="/client/images/icon.ico" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${menu_name!''}<#if info??>-${info.title!''}</#if></title>
<meta name="keywords" content="${info.seoKeywords!''}">
<meta name="description" content="${info.seoDescription!''}">
<meta name="copyright" content="${info.copyright!''}" />
<!--css-->
<link href="/client/css/main.css" rel="stylesheet" type="text/css" />
<!--js-->
<script type="text/javascript" src="/client/js/jquery1.42.min.js"></script>
<script type="text/javascript" src="/client/js/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript" src="/client/js/info-list.js"></script>
</head>

<body>
<!--header-->
<#include "/client/common_header.ftl" />
<!--header_end -->

<!--main-->
<div class="mainBox">
	<div class="main">
		<!--left_content-->
		<#include "/client/common_menu.ftl" /> 
	    
	    <!--right_content-->
	    <div class="right_content">
	    <#include "/client/info_list_content_detail.ftl" />
	    </div>
	</div>
</div>
<!--footer-->
<#include "/client/common_footer.ftl" />
<!--footer_end-->

</body>
</html>
