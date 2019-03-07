<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--网页左上角小图标-->
<link rel="shortcut icon" href="/client/images/icon.ico" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${menu_name!''}</title>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<!--css-->
<link href="/client/css/main.css" rel="stylesheet" type="text/css" />
<!--js-->
<script type="text/javascript" src="/client/js/jquery1.42.min.js"></script>
<script type="text/javascript" src="/client/js/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript" src="/client/js/info-list.js"></script>
</head>

<body>
<#include "/client/common_header.ftl" />

<!--main开始-->
<div class="mainBox">
	<div class="main">
	<#include "/client/common_menu.ftl" />  
    
    <!--右侧具体内容开始-->
        <div class="right_content ">
			<#include "/client/info_list_detail.ftl" />  
    	</div>			
    <!--右侧具体内容结束-->
	</div>
</div>
<!--main结束-->
<div class="clear"></div>
<!--底部开始-->
<#include "/client/common_footer.ftl" />
<!--底部结束-->
</body>
</html>
