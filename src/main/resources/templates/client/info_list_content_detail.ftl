
   <div class="right_content newsBox">
    	<!--面包屑导航-->
    	<div class="crumb">
        	<a href="/">首页</a>
            <i>></i>
            <#if menu_id??&&menu_id != 10><a href="/info/list/${menu_id}<#if catId?? && (catId==30 || catId==65)>?catId=${catId}</#if>">${menu_name}</a><i>></i></#if>
            <a <#if menu_id??&menu_id!=10> href="javascript:selectCat(${menu_id},${catId})" </#if>>${info.title!'' }</a>
        </div>
        <!--新闻列表-->
        <ul class="news_details">
        	<li>
            	<a href="javascript;"><h3>${info.title!'' }</h3></a>
            	<#if menu_name == "最新资讯">
	                <div class="information_home">
	                	<span class="date">${info.createTime?string("yyyy-MM-dd HH:mm")!'' }</span>
	                    <span class="user">${info.source!'' }</span>
	                </div>
	            </#if>
                <div class="news_summary">
                	<p>${info.brief!'' }</p>
                	<#if menu_name == "园区概况"&&info_name??&&(info_name.title == "企业展示"||info_name.title == "配套设施")||menu_name=="合作机构">
    					 <#if info.showPictures??>
               				 <#list info.showPictures?split(",") as uri>
               				 	<p class="photo">
	                    			<img src="${uri}"/>
	                    		</p>
		                    </#list>
		                </#if> 	
					</#if>                
                	<#if menu_name == "最新资讯"><p class="photo"><img src="${info.imgUrl!'' }" /></p></#if>
                    <p>${info.content!'' }</p>
                    <#if info_name??&&info_name.title == "交通路线">
                        <input class="tj"  type="button" onclick="window.location.href='/info/map'" value="地图导航" style="
                        																																											 display: block;
																																																	  width: 108px;
																																																	  cursor: pointer;
																																																	  _height: 30px;
																																																	  line-height: 30px;
																																																	  margin: 50px auto;
																																																	  background-color: #00a1e9;
																																																	  border: solid 1px #ffffff;
																																																	  color: #ffffff;"> 
                    </#if>
 					<#if info_name??&&info_name.title == "入驻申请">
                        <input class="tj"  type="button" onclick="window.location.href='/cooperation'" value="在线申请" style="
                        																																											 display: block;
																																																	  width: 108px;
																																																	  cursor: pointer;
																																																	  _height: 30px;
																																																	  line-height: 30px;
																																																	  margin: 50px auto;
																																																	  background-color: #00a1e9;
																																																	  border: solid 1px #ffffff;
																																																	  color: #ffffff;"> 
                    </#if>
                    <#if info.linkUrl?? && info.linkUrl !="">
                    <a href="/downloads/info/${info.id?c}" style="color: #00a1e9;">附件下载</a>
                    </#if>                    
                </div>
            </li>
        </ul>
        <!--上篇、下篇-->
        <#if menu_name == "最新资讯">
	 		<dl>
	 		    <#if prev_info??>
	 				<dt class="dt1"><a href="javascript:selectContent(${prev_info.id },${prev_info.menuId })">上一篇：${prev_info.title}</a></dt>
	 			<#else >	
	 				<dt class="dt1"><a>上一篇：没有了</a></dt>
	 			</#if>
	 			<#if next_info??>
	            	<dt class="dt2"><a href="javascript:selectContent(${next_info.id },${next_info.menuId })">下一篇：${next_info.title}</a></dt>
	            <#else >	
	 				<dt class="dt1"><a>下一篇：没有了</a></dt>	
	            </#if>
	        </dl>
	    </#if>    
    </div>

