<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
<title><#if site??>${site.seoTitle!''}-</#if>关于我们</title>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<link rel="shortcut icon" href="/client/images/lixue.ico" />
<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/about_us.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="/client/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/client/js/info-entry.js"></script> 
</head>

<body>
<!--header-->
<#include "/client/common_header.ftl" />
<!--header_end -->
<!--main-->
<div class="main">
	<!--left_content-->
	<#include "/client/common_menu.ftl" />    
    <!--right_content-->
    <div class="right_content">
        <#include "/client/info_entry_content_detail.ftl" />
    </div>        
</div>
<!--footer-->
<#include "/client/common_footer.ftl" />
<!--footer_end-->
</body>
</html>
