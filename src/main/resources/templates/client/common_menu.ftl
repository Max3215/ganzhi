	<!--左侧菜单导航开始-->
	<div class="left_nav">
    	<!-- sideMenu Beigin -->
        <div class="sideMenu">
            <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 1>              
						<a href="${bar.linkUri}"><h3 <#if menu_id == 11>class="on"</#if>>${bar.title}</h3></a>
                    </#if>
                </#list>
            </#if> 							
            <!-- 假设当前菜单为“园区概况”，手动或后台程序添加titOnClassName类名（默认是'on'），相当于设置参数defaultIndex:1。若设置参数returnDefault:true，则鼠标移出.sideMen0.3秒可以返回当前频道 -->
			<ul>
			    <#if profile_list??>
	                <#list profile_list as item>  
	                	<#if item.title =="园区简介">
	                		<li id="list151" <#if catId?? && catId== item.id>class="sel menu_list" <#else>class="menu_list"</#if>><a href="/info/list/content/cat/${item.id?c}?mid=11">${item.title!''}</a></li>
						<#elseif item.title == "交通路线">
						 	<li id="list152" <#if catId?? && catId== item.id>class="sel menu_list" <#else>class="menu_list"</#if>><a href="/info/list/content/cat/${item.id?c}?mid=11">${item.title!''}</a></li>
						<#else>
							<li id="list${item.id}" <#if catId?? && catId== item.id>class="sel menu_list" <#else>class="menu_list"</#if>><a href="/info/list/${item.menuId}?catId=${item.id?c}">${item.title!''}</a></li>
		            	</#if>
		            </#list>
                </#if> 			
                <#--
				<li><a href="javascript:selectEntry(43,10);">园区简介</a></li>
                <li><a href="/info/list/11?catId=30">企业展示</a></li>
                <li><a href="/info/list/11?catId=31">配套设施</a></li>
                <li><a href="javascript:selectEntry(44,10);">交通路线</a></li>
				-->
			</ul>
            <p></p>
            <#-- 服务体系-->
            <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 2>              
						<a href="${bar.linkUri}"><h3 <#if menu_id == 10 &&catId == 52>class="on"</#if> >${bar.title}</h3></a>
                    </#if>
                </#list>
            </#if> 		
			<ul>
                <#if service_list??>
              		<#list service_list as item>   	
						<li <#if id??&&id == item.id>  class=" sel menu_detail" <#else>class="menu_list"</#if> ><a href="/info/list/content/${item.id?c}?mid=10">${item.title!''}</a></li>
                    </#list>
               </#if>    							
			</ul>
            <p></p>
            <#-- 最新资讯-->
            <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 3>              
						<a href="${bar.linkUri}"><h3 <#if menu_id == 8>class="on"</#if> >${bar.title}</h3></a>
                    </#if>
                </#list>
            </#if> 		
			<ul>
			    <#if news_list??>
	                <#list news_list as item>  
						<li id="list${item.id}" <#if catId?? && catId== item.id>class="sel menu_list" <#else>class="menu_list"</#if>><a href="/info/list/${item.menuId}?catId=${item.id?c}">${item.title!''}</a></li>
		            </#list>
                </#if> 
			</ul>
			<p></p>
            <#-- 优惠政策 -->
             <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 4>              
						<a href="${bar.linkUri}"><h3 <#if menu_id == 86>class="on"</#if>>${bar.title}</h3></a>
                    </#if>
                </#list>
            </#if> 		
			<ul>
				<#if policy_list??>
		            <#list policy_list as item>  
						<li id="list${item.id}" <#if catId?? && catId== item.id>class="sel menu_list" <#else>class="menu_list"</#if>><a href="/info/list/${item.menuId}?catId=${item.id?c}">${item.title!''}</a></li>
		            </#list>
	            </#if>
			</ul>
			<p></p>
			<#--  创业导师-->
            <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 10>              
						<a href="${bar.linkUri}"><h3 <#if menu_id == 94>class="on"</#if>>${bar.title}</h3></a>
                    </#if>
                </#list>
            </#if> 		
			<ul>
				<li ></li>
			</ul>
			<p></p>			
            <#-- 创客空间 -->
            <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 5>              
						<a href="${bar.linkUri}"><h3 <#if menu_id == 12>class="on"</#if>>${bar.title}</h3></a>
                    </#if>
                </#list>
            </#if> 		
			<ul>
				<#if space_list??>
		            <#list space_list as item>  
						<li <#if catId?? && catId== item.id>class="sel menu_list" <#else>class="menu_list"</#if>><a href="/info/list/${item.menuId}?catId=${item.id?c}">${item.title!''}</a></li>
	                </#list>
	            </#if> 		
			</ul>
			<p></p>
            <#-- 入驻申请-->
            <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 6>              
						<a href="${bar.linkUri}"><h3 <#if menu_id == 10&&catId == 56>class="on"</#if>>${bar.title}</h3></a>
                    </#if>
                </#list>
            </#if> 		
			<ul>
                <#if coo_list??>
              		<#list coo_list as item> 
						<li id="list${item.id}" <#if catId?? && catId== item.categoryId&&info_name??&&menu_name=="功能信息">class="sel menu_list" <#else>class="menu_list"</#if>><a href="/info/list/content/${item.id?c}?mid=10">${item.title!'' }</a></li>
                    </#list>
               </#if>   
                <li class="<#if info_name??&&info_name=="在线申请">sel </#if> menu_list"><a href="/cooperation">在线申请</a></li>
			</ul>
			<p></p>
            
            <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 7>              
						<a href="${bar.linkUri}"><h3 <#if menu_id == 13>class="on"</#if>>${bar.title}</h3></a>
                    </#if>
                </#list>
            </#if> 		
			<ul id="rczp">
				<li ></li>
			</ul>
			<p></p>
            <#-- 合作机构-->
            <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 8>              
						<a href="${bar.linkUri}"><h3 <#if menu_id == 83>class="on"</#if>>${bar.title}</h3></a>
                    </#if>
                </#list>
            </#if> 		
			<ul>
				<#if work_list??>
		            <#list work_list as item>  
						<li id="list${item.id}" <#if catId?? && catId== item.id>class="sel menu_list" <#else>class="menu_list"</#if>><a href="/info/list/${item.menuId}?catId=${item.id?c}">${item.title!''}</a></li>		            
		            </#list>
	            </#if>
			</ul>
			<p></p>
            <#-- 联系我们 -->
            <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 9>              
						<a href="${bar.linkUri}"><h3 <#if menu_id == 89>class="on"</#if>>${bar.title}</h3></a>
                    </#if>
                </#list>
            </#if> 		
			<ul>
				<#if contact_list??>
		            <#list contact_list as item>  
						<li id="list${item.id}" <#if catId?? && catId== item.id>class="sel menu_list" <#else>class="menu_list"</#if>><a href="/info/list/89?catId=${item.id?c!''}">${item.title!''}</a></li>
		            </#list>
	            </#if>
                <li class="<#if !catId??>sel </#if> menu_list"><a href="/suggestion">留言板</a></li>
			</ul>
			<p></p>

		</div><!-- sideMenu End -->

		<script type="text/javascript">
			jQuery(".sideMenu").slide({
				titCell:"h3", //鼠标触发对象
				targetCell:"ul", //与titCell一一对应，第n个titCell控制第n个targetCell的显示隐藏
				effect:"slideDown", //targetCell下拉效果
				delayTime:300 , //效果时间
				triggerTime:150, //鼠标延迟触发时间（默认150）
				trigger:"click",//点击是否执行效果（默认true）
				returnDefault:false, //鼠标从.sideMen移走后返回默认状态（默认false）
				});
		</script>
    </div>
    <!--左侧菜单导航结束-->