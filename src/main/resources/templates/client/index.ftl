<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--网页左上角小图标-->
<link rel="shortcut icon" href="/client/images/icon.ico" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><#if site??>${site.seoTitle!''}-</#if>首页</title>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<!--css-->
<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
<!--js-->
<script type="text/javascript" src="/client/js/jquery1.42.min.js"></script>
<script type="text/javascript" src="/client/js/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript" src="/client/js/jquery.js"></script>

<script type="text/javascript">
	var jQuery_1_8_2 = $.noConflict(true);
</script>
</head>

<body>
<#include "/client/common_header.ftl" />

<!--banner图开始-->
<div id="slideBox" class="slideBox">
    <!--banner点
    <div class="hd">
        <ul><li></li><li></li></ul>
    </div>
    -->
    <div class="bd">
        <ul>
            <#if ad_list??>
                <#list ad_list as item>
                    <li><a href="#"><img src="${item.fileUri!''}" /></a></li>
                </#list>
            </#if>
        </ul>
    </div>

    <!-- 下面是前/后按钮代码，如果不需要删除即可 --> 
    <a class="prev" href="javascript:void(0)"></a>
    <a class="next" href="javascript:void(0)"></a>
    
</div>

<script type="text/javascript">
jQuery(".slideBox").slide({mainCell:".bd ul",autoPlay:true});
</script>
<!--banner图结束-->

<!--新闻开始-->
<div class="news">
	<!--通知公告-->
	<ul>
		<li class="title">
	    	<h3>通知公告</h3>
	        <a class="more" href="/info/list/8?catId=62">更多>></a>
	    </li>
		<#if inform_page??>
			<#list inform_page.content as item>
				<#if item_index lt 5>
			    	<#if item_index == 0>
				        <li class="news_photo">
				            <a href="/info/list/content/${item.id?c!''}?mid=${item.menuId?c!''}">
				            	<img src="${item.imgUrl!''}" width="380px" height="190px"/>
				            </a>
				            <div class="note">${item.title!''}</div>
				        </li>
				    <#else>
				        <li class="news_list_1">
				            <a href="/info/list/content/${item.id?c!''}?mid=${item.menuId?c!''}">
					            <span>${item.title!''}</span>
		               		    <span class="date">${item.createTime?string("yyyy-MM-dd")!''}</span>
				            </a>
				        </li>		
				    </#if>
				</#if>    
			</#list>
		</#if>	        	        
    </ul>
    <!--园区新闻-->
	<ul>
		<li class="title">
	    	<h3>园区新闻</h3>
	        <a class="more" href="/info/list/8?catId=26">更多>></a>
	    </li>
		<#if news_page??>
			<#list news_page.content as item>
				<#if item_index lt 5>
			    	<#if item_index == 0>
				        <li class="news_photo">
				            <a href="/info/list/content/${item.id?c!''}?mid=${item.menuId?c!''}">
				            	<img src="${item.imgUrl!''}"  width="380px" height="190px"/>
				            </a>
				            <div class="note">${item.title!''}</div>
				        </li>
				    <#else>
				        <li class="news_list_1">
				            <a href="/info/list/content/${item.id?c!''}?mid=${item.menuId?c!''}">
					            <span>${item.title!''}</span>
		               		    <span class="date">${item.createTime?string("yyyy-MM-dd")!''}</span>
				            </a>
				        </li>		
				    </#if>
				</#if>    
			</#list>
		</#if>	            	
    </ul>
    <!--优惠政策-->
	<ul>
 		<li class="title">
	    	<h3>优惠政策</h3>
	        <a class="more" href="/info/list/86?catId=54">更多>></a>
	    </li>
		<#if policy_page??>
			<#list policy_page.content as item>
				<#if item_index lt 5>
			    	<#if item_index == 0>
				        <li class="news_photo">
				            <a href="/info/list/content/${item.id?c!''}?mid=${item.menuId?c!''}">
				            	<img src="${item.imgUrl!''}"  width="380px" height="190px"/>
				            </a>
				            <div class="note">${item.title!''}</div>
				        </li>
				    <#else>
				        <li class="news_list_1">
				            <a href="/info/list/content/${item.id?c!''}?mid=${item.menuId?c!''}">
					            <span>${item.title!''}</span>
		               		    <span class="date">${item.createTime?string("yyyy-MM-dd")!''}</span>
				            </a>
				        </li>		
				    </#if>
				</#if>    
			</#list>
		</#if>	        
    </ul>
</div>
<!--新闻结束-->

<!--快速通道开始-->
<div class="expressway">
	<div class="top">
        <p><span>快速通道</span></p>
        <div class="clear"></div>
        <!-- 图片缩放 开始 -->
        <div class="special_service">  
            <div class="bd view view-first" onmouseover="mouseOver1()" onmouseout="mouseOut1()">
                <a target="_blank" href="/info/list/content/cat/59?mid=11" class="new-product-image">
                    <img width="190" height="190" name="image1" src="/client/images/pic-190-189.png">
                </a>
                <div class="mask">
                    <h4 class="title">
                        <a title="${profile_article.title!''}" class="text-hide" target="_blank" href="/info/list/content/cat/59?mid=11">园区简介</a>
                    </h4>
                    <i class="text">
						<#if profile_article.brief?length lt 60>
							${profile_article.brief!''}
						<#elseif profile_article.brief?length gt 59>
							${profile_article.brief[0..60]!''}...
						</#if>	
					</i>
                </div>
            </div>
            <div class="bd view view-first" onmouseover="mouseOver2()" onmouseout="mouseOut2()">
                <a target="_blank" href="/info/list/10?catId=52" class="new-product-image">
                    <img width="190" height="190" name="image2" src="/client/images/pic-190-190.png">
                </a>
                <div class="mask">
                    <h4 class="title">
                        <a title="${service_article.title!''}" class="text-hide" target="_blank" href="/info/list/10?catId=52">服务体系</a>
                    </h4>
                    <i class="text">
                    	<#if service_article.brief?length lt 60>
							${service_article.brief!''}
						<#elseif service_article.brief?length gt 59>
							${service_article.brief[0..60]!''}...
						</#if>	
                    </i>
                </div>
            </div>
            <div class="bd view view-first" onmouseover="mouseOver3()" onmouseout="mouseOut3()">
                <a target="_blank" href="/info/list/86?catId=55" class="new-product-image">
                    <img width="190" height="190" name="image3" src="/client/images/pic-190-191.png">
                </a>
                <div class="mask">
                    <h4 class="title">
                        <a title="${project_article.title!''}" class="text-hide" target="_blank" href="/info/list/86?catId=55">项目申报</a>
                    </h4>
                    <i class="text">
                    	<#if project_article.brief?length lt 60>
							${project_article.brief!''}
						<#elseif project_article.brief?length gt 59>
							${project_article.brief[0..60]!''}...
						</#if>	                    
                    </i>
                </div>
            </div>
            <div class="bd view view-first" onmouseover="mouseOver4()" onmouseout="mouseOut4()">
                <a target="_blank" href="/info/list/12" class="new-product-image">
                    <img width="190" height="190" name="image4" src="/client/images/pic-190-192.png">
                </a>
                <div class="mask">
                    <h4 class="title">
                        <a title="创客空间" class="text-hide" target="_blank" href="/info/list/12">创客空间</a>
                    </h4>
                    <i class="text">重庆感知科技孵化器有限公司由重庆仙桃数据谷投资管理​有限公司与深圳清华研究院旗下的深圳力合天使创业投资有限公司合作设立...</i>
                </div>
            </div>
            <div class="bd view view-first" onmouseover="mouseOver5()" onmouseout="mouseOut5()">
                <a target="_blank" href="/cooperation" class="new-product-image">
                    <img width="190" height="190" name="image5" src="/client/images/pic-190-193.png">
                </a>
                <div class="mask">
                    <h4 class="title">
                        <a title="在线申请" class="text-hide" target="_blank" href="/cooperation">在线申请</a>
                    </h4>
                    <i class="text">
                    	<#if coo_article.brief?length lt 60>
							${coo_article.brief!''}
						<#elseif coo_article.brief?length gt 59>
							${coo_article.brief[0..60]!''}...
						</#if>	                   
                    </i>
                </div>
            </div>
            <script>
				//鼠标经过
            	function mouseOver1(){
					document.image1.src="/client/images/pic-191-189.png";
					}
				//鼠标离开
				function mouseOut1(){
					document.image1.src="/client/images/pic-190-189.png";
					}
				//鼠标经过
            	function mouseOver2(){
					document.image2.src="/client/images/pic-191-190.png";
					}
				//鼠标离开
				function mouseOut2(){
					document.image2.src="/client/images/pic-190-190.png";
					}
				//鼠标经过
            	function mouseOver3(){
					document.image3.src="/client/images/pic-191-191.png";
					}
				//鼠标离开
				function mouseOut3(){
					document.image3.src="/client/images/pic-190-191.png";
					}
				//鼠标经过
            	function mouseOver4(){
					document.image4.src="/client/images/pic-191-192.png";
					}
				//鼠标离开
				function mouseOut4(){
					document.image4.src="/client/images/pic-190-192.png";
					}
				//鼠标经过
            	function mouseOver5(){
					document.image5.src="/client/images/pic-191-193.png";
					}
				//鼠标离开
				function mouseOut5(){
					document.image5.src="/client/images/pic-190-193.png";
					}
            </script>
        </div>
		<!-- 图片缩放 结束 -->
    </div>
    <div class="top">
        <p><span>入驻企业</span></p>
        <div class="links">
    	<div class="links_list"><a class="link_prev" href="javascript:;" hidefocus="true" title="上一组" id="link_prev">上一组</a>
            <div id="links">
                <ul id="slideContainer" class="slideContainer" >
                	<#if settle_list??>
                		<#list settle_list as item>
                 		    <li><a href="${item.linkUri!''}" target="_blank" title="${item.linkUri!''}"><img src="${item.imgUrl!''}"></a></li>
                    	</#list>
                    </#if>	
                    
                </ul>
            </div>
            <a class="link_next" href="javascript:;" hidefocus="true" title="下一组" id="link_next">下一组</a> 
        </div>
	</div>
	<!--links end-->
	<script type="text/javascript">
            jQuery_1_8_2(function($) {
                $('div.pro_box').hover(function() {
                    $(this).toggleClass('pro_box_hover')
                });
				//c的值为每次滚动数
                var slideContainer = $('#slideContainer'), c = 1, s_w = 110 * c, counts_l = 0, counts_r = 0, maxCounts = slideContainer.find('li').size() - 0, gameOver = true, slideCounts = 7, sTimer;
                $('#link_prev').on('click', function() {
                    clearInterval(sTimer);
                    if (gameOver) {
                        gameOver = false;
                        counts_l++;
                        slideContainer.animate({
                            left: '+=' + s_w
                        }, 500, function() {
                            gameOver = true;
                            slideContainer.animate({
                                left: '-=' + s_w
                            }, 0);
                            var html = '';
                            slideContainer.find('li:gt(' + (maxCounts - c - 1) + ')').each(function() {
                                html += '<li>' + $(this).html() + '</li>';
                            });
                            slideContainer.find('li:gt(' + (maxCounts - c - 1) + ')').remove();
                            slideContainer.html(html + slideContainer.html());
                        });
                    }
                });
                $('#link_next').on('click', function() {
                    clearInterval(sTimer);
                    link_next_event();
                });

                function link_next_event() {
                    if (gameOver) {
                        gameOver = false;
                        counts_r++;
                        slideContainer.animate({
                            left: '-=' + s_w
                        }, 500, function() {
                            gameOver = true;
                            slideContainer.animate({
                                left: '+=' + s_w
                            }, 0);
                            slideContainer.find('li:lt(' + c + ')').clone().appendTo(slideContainer);
                            slideContainer.find('li:lt(' + c + ')').remove();
                        });
                    }
                }

                lastCLiHtml();
                slideContainer.find('li:gt(' + (maxCounts - 1) + ')').remove();
                function lastCLiHtml() {
                    var html = '';
                    slideContainer.find('li:gt(' + (maxCounts - c - 1) + ')').each(function() {
                        html += '<li>' + $(this).html() + '</li>';
                    });
                    slideContainer.html(html + slideContainer.html()).css({
                        'margin-left': -s_w + 'px'
                    });
                }

                var l_hover = false, m_hover = false, r_hover = false;
                $('#links').on({
                    'mouseover': function() {
                        m_hover = true;
                        clearInterval(sTimer);
                    },
                    'mouseout': function() {
                        m_hover = false;
                        isStartGo();
                    }
                });

                $('#link_next, #link_prev').on('mouseout', function() {
                    l_hover = false;
                    r_hover = false;
                    isStartGo();
                })
                $('#link_next, #link_prev').on('mouseover', function() {
                    l_hover = true;
                    r_hover = true;
                    clearInterval(sTimer);
                })
                setInverterTimer();
                function setInverterTimer() {
                    clearInterval(sTimer);
                    sTimer = setInterval(function() {
                        link_next_event();
                    }, 2000);
                }

                function isStartGo() {
                    var st = setTimeout(function() {
                        if (!l_hover && !m_hover && !r_hover) {
                            setInverterTimer();
                        }
                    }, 1000);
                }

            });
        </script>
    </div>
</div>
<!--快速通道结束-->

		<!--留言板开始-->
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
        <div class="message_board">
	        <form  id="form1" action="/suggestion/submit" method="post">
				<div class="links_1 links_2">
		        	<p>留言板</p>
		            <p class="profile"><span>MESSAGE BOARD</span></p>
		        </div>
		        
		        <div class="board">
		                <div class="phone_num">
		                    <input type="text" name="name" value="您的姓名" datatype="*" onfocus="if(this.value=='您的姓名'){this.value='';}" onblur="if(this.value==''){this.value='您的姓名'}"/>
		                    <input class="phone" type="text" name="mobile" value="您的电话" datatype="m" onfocus="if(this.value=='您的电话'){this.value='';}" onblur="if(this.value==''){this.value='您的电话'}"/>
		                    <input type="text" value="您的邮箱"name="mail" datatype="e" onfocus="if(this.value=='您的邮箱'){this.value='';}" onblur="if(this.value==''){this.value='您的邮箱'}"/>
		                </div>
		          	      <textarea name="content" datatype="*5-255" errormsg="请输入5到255个字符"  datatype="*" onfocus="if(this.value=='留言内容'){this.value='';}" onblur="if(this.value==''){this.value='留言内容'}">留言内容</textarea>
		        </div>
		        
		        <!--提交按钮-->
		        <input class="tj" id="btn_submit" type="submit" value="提 交" />
		    </form>
	        <!--友情链接-->
	        <ul class="friend_link">
	        	<li class="lk1" >友情链接：</li>
               	<#if site_link_list??>
                    <#list site_link_list as link>
                    	<#if link_has_next>
                        		<li><a href="${link.linkUri}" target="_blank" title="${link.linkUri}">${link.title}</a></li>
                        <#else>
                        	<li class="last"><a href="${link.linkUri}" target="_blank" title="${link.linkUri}">${link.title}</a></li>
                        </#if>	
                    </#list>
                </#if>         
	        </ul>
        </div>
        <!---留言板结束->
        
        <!--底部开始-->
        <div class="foot">
        	<p>${site.copyright!''}</p>
        </div>
        <!--底部结束-->

</body>
</html>
