<!--头部开始-->
<div id="go_topnav" class="header">
	<!--LOGO 尺寸520*100-->
	<a class="logo" href="#"><img width="520" height="100" src="${site.logoUri!'/client/images/logo.png'}" /></a>
    <!--<ul class="search">
    <form>
    	<li>
        	<input type="text" value="邮箱/用户名" />
            <input type="text" value="密码" />
            <span><a href="#">登录</a><i>丨</i><a href="#">注册</a></span>
        </li>
        <li class="li2">
        	<input class="searchBox" type="text" value="新闻、公告、活动" />
            <a class="searchbox" href="#"><img src="images/search_icon.png" /></a>
        </li>
    </form>
    </ul>-->
</div>
<!--头部结束-->

<!--顶部导航开始-->
<div class="nav">
	<ul class="menu">
    	<li>
    	     <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 0>
			            <i class="i_1"></i>
			            <a href="${bar.linkUri}">${bar.title[0]}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  ${bar.title[1]}</a>
                    </#if>
                </#list>
            </#if> 			            
        </li>
        <li>
            <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 1>        
			        	<i class="i_2"></i>
			        	<a href="${bar.linkUri}">${bar.title}</a>
                    </#if>
                </#list>
            </#if> 				        	
            <dl>
            	<dt></dt>
			    <#if profile_list??>
	                <#list profile_list as item>  
	                	<#if item.title="园区简介">
	                		<dd><a href="/info/list/content/151?mid=11">${item.title!'' }</a></dd>
	                	<#else>	
							<dd><a href="/info/list/11?catId=${item.id?c!''}">${item.title!'' }</a></dd>
		            	</#if>
		            </#list>
                </#if> 	
            </dl>
        </li>
        <li>
            <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 2>          
			            <i class="i_3"></i>
			            <a href="${bar.linkUri}">${bar.title}</a>
                    </#if>
                </#list>
            </#if> 					            
            <dl>
            	<dt></dt>
                    <#if service_list??>
                  		<#list service_list as item>   	
                			<dd> <a href="/info/list/content/${item.id?c!''}?mid=10">${item.title!'' }</a></dd>
                        </#list>
                   </#if>    	 
            </dl>
        </li>
        <li>
     	     <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 3>       
			        	<i class="i_4"></i>
			            <a href="${bar.linkUri}">${bar.title}</a>
                    </#if>
                </#list>
            </#if>             
            <dl>
            	<dt></dt>
                    <#if news_list??>
                  		<#list news_list as item>   	
                			<dd> <a href="/info/list/8?catId=${item.id?c!''}">${item.title!'' }</a></dd>
                        </#list>
                   </#if>    
            </dl>
        </li>
        <li>
    	     <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 4>        
			        	<i class="i_5"></i>
			            <a href="${bar.linkUri}">${bar.title}</a>
                    </#if>
                </#list>
            </#if> 			            
            <dl>
            	<dt></dt>
                    <#if policy_list??>
                  		<#list policy_list as item>   	
                			<dd> <a href="/info/list/86?catId=${item.id?c!''}">${item.title!'' }</a></dd>
                        </#list>
                   </#if>    
            </dl>
        </li>
         <li>
    	     <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 10>        
			        	<i class="i_11"></i>
			            <a href="${bar.linkUri}">${bar.title}</a>
                    </#if>
                </#list>
            </#if> 			            
        </li>       
        <li>
    	     <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 5>        
			        	<i class="i_6"></i>
			            <a href="${bar.linkUri}">${bar.title}</a>
                    </#if>
                </#list>
            </#if> 			            
            <dl>
            	<dt></dt>
                <#if space_list??>
              		<#list space_list as item>              	
						<dd> <a href="/info/list/${item.menuId}?catId=${item.id?c!''}">${item.title!''}</a></dd>
	                </#list>
	            </#if> 
            </dl>
        </li>
        <li>
    	     <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 6>        
			        	<i class="i_7"></i>
			            <a href="${bar.linkUri}">${bar.title}</a>
                    </#if>
                </#list>
            </#if> 			            
            <dl>
            	<dt></dt>
                    <#if coo_list??>
                  		<#list coo_list as item>   	
                			<dd> <a href="/info/list/content/${item.id?c}?mid=10">${item.title!'' }</a></dd>
                        </#list>
                   </#if>    
                   <dd> <a href="/cooperation">在线申请</a></dd>
            </dl>
        </li>
        <li>
     	     <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 7>       
        				<i class="i_8"></i>
        			    <a href="${bar.linkUri}">${bar.title}</a>
                    </#if>
                </#list>
            </#if>         
        </li>
        <li>
     	     <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 8>       
			        	<i class="i_9"></i>
			            <a href="${bar.linkUri}">${bar.title}</a>
                     </#if>
                </#list>
            </#if>              
            <dl>
            	<dt></dt>
                    <#if work_list??>
                  		<#list work_list as item>   	
                			<dd>  <a href="/info/list/83?catId=${item.id?c!''}">${item.title!'' }</a></dd>
                        </#list>
                   </#if>    
            </dl>
        </li>
        <li>
    	     <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 9>  
			        	<i class="i_10"></i>
			            <a href="${bar.linkUri}">${bar.title}</a>
                    </#if>
                </#list>
            </#if>   			            
            <dl>
            	<dt></dt>
                    <#if contact_list??>
                  		<#list contact_list as item>   	
                			<dd> <a href="/info/list/89?catId=${item.id?c!''}">${item.title!'' }</a></dd>
                			<dd> <a href="/suggestion">留言板</a></dd>
                        </#list>
                   </#if>    
            </dl>
        </li>
    </ul>
</div>
<!--顶部导航结束-->

<!--右侧浮动导航开始-->
<div class="floatbox">
  <a id="BizQQWPA" href="http://wpa.qq.com/msgrd?v=3&uin=${site.qq1!''}&site=qq&menu=yes" target="_blank" title="在线咨询">
  	<img src="/client/images/float_ico02.png" width="42" height="42" alt="在线咨询">
  </a>
  <a href="javascript:loginWinOpen('weixin_win','myselfbox',200);" title="微信客服">
  	<img src="/client/images/float_ico01.png" width="42" height="42" alt="微信客服">
  	<span><img src="${site.wxQrCode!''}"></span>
  </a>
  <a href="${site.sinaWeibo!''}" title="新浪微博" target="_blank" rel="nofollow">
  	<img src="/client/images/float_ico03.png" width="42" height="42" alt="新浪微博">
  </a>
  <a title="服务热线">
  	<img src="/client/images/float_ico04.png" width="42" height="42" alt="服务热线">
  	<span><img src="${site.weiboQrCode!''}"></span>
  </a>
  <a href="#go_topnav" title="到顶部">
  	<img src="/client/images/float_ico05.png" width="42" height="42" alt="到顶部">
  </a>
</div>
<!--右侧浮动导航结束-->