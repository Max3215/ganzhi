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
<script type="text/javascript" src="/client/js/Validform_v5.3.2_min.js"></script>

<script>
$(document).ready(function(){
	$("#form1").Validform({
	    	  tiptype:4,
			  ajaxPost:true,
	          callback: function (data) { 
		   		  if (data.code == 0) {
			          alert("提交成功");
		              window.location.reload();
	              }
	             else {
	                alert(data.msg);
	             }
	        }
	});
});

   document.onkeydown = function(event){
    if((event.keyCode || event.which) == 13){
        $("#btn_submit").click();
    }
   }
</script>
</head>

<body>
<#include "/client/common_header.ftl" />

<!--main开始-->
<div class="mainBox">
	<div class="main">
	<#include "/client/common_menu.ftl" />  
	   
    <!--右侧具体内容开始-->
    <div class="right_content">
    	<!--面包屑导航-->
    	<div class="crumb">
        	<a href="/">首页</a>
            <i>></i>
            <a href="">${menu_name!''}</a>
            <i>></i>
            <a>${info_name!''}</a>
        </div>
        <#if menu_name == "入驻申请">
	        <!--入驻申请开始-->
	        <div class="online_application">
	            <div class="guide">
	            	<div>马上申请,只需2个工作日</div>
	            	<i></i>
	            </div>
	            <div class="application_information">
	            <form id="form1" action="/cooperation/submit" method="post">
	            	<div><label>联系人姓名：</label><input type="text" name="username" datatype="*"/></div>
	                <div><label>联系人手机号码：</label><input type="text" name="mobile" datatype="m"/></div>
	                <div><label>联系人邮箱：</label><input type="text" name="email" datatype="e"/></div>
	                <div><label>入驻人数：</label><input type="text" name="goodsId" datatype="n" value="1" onfocus="if(this.value=='1'){this.value='';}" onblur="if(this.value==''){this.value='1'}"/></div>
	                <div><label>备注留言：</label><textarea rows="5" cols="48" name="content" datatype="*"></textarea></div>
	                <div class="notice">提交信息后我们会在一个工作日联系您.</div>
	                <div><input class="ipt1" type="submit" id="btn_submit" value="确认提交信息" /></div>
	            </form>
	            </div>
	        </div>
	        <!---入驻申请结束-->
        <#elseif menu_name == "联系我们">
	        <!--留言板开始-->
	        <div class="message_board">
	        	<form  id="form1" action="/suggestion/submit" method="post">
		            <div class="links_2">
		                <p class="profile">留言板</p>
		                <p><span>MESSAGE BOARD</span></p>
		            </div>
		            <div class="board">
		                <div class="phone_num">
		                    <input type="text" name="name" value="您的姓名" datatype="*" onfocus="if(this.value=='您的姓名'){this.value='';}" onblur="if(this.value==''){this.value='您的姓名'}"/>
		                    <input class="phone" type="text" name="mobile" value="您的电话" datatype="m" onfocus="if(this.value=='您的电话'){this.value='';}" onblur="if(this.value==''){this.value='您的电话'}"/>
		                    <input type="text" value="您的邮箱"name="mail" datatype="e" onfocus="if(this.value=='您的邮箱'){this.value='';}" onblur="if(this.value==''){this.value='您的邮箱'}"/>
		                </div>
		                    <textarea name="content" datatype="*5-255" errormsg="请输入5到255个字符" datatype="*" onfocus="if(this.value=='留言内容'){this.value='';}" onblur="if(this.value==''){this.value='留言内容'}">留言内容</textarea>
		            </div>
		            <!--提交按钮-->
		            <input class="tj" id="btn_submit" type="submit" value="提 交" />
	            </form>
	        </div>
	        <!---留言板结束-->
        </#if>
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
