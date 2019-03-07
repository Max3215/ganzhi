    	<!--面包屑导航-->
    	<div class="crumb">
        	<a href="/">首页</a>
        	<#if menu_id??&menu_id !=10&menu_id!=94&menu_id!=13>
            <i>></i>
            <a href="/info/list/${menu_id}">${menu_name }</a>
            </#if>
            <#if info_name??>
	            <i>></i>
	            <a> ${info_name.title!'' }</a>
            </#if>
        </div>
        <!--新闻列表-->
        <#if menu_name=="最新资讯"||info_name??&info_name.title=="创客空间">
        <ul class="news_list">
       	    <#if info_page??>
	       	 <#list info_page.content as item>      
	        	<li>
	        	  <a href="javascript:selectContent(${item.id },${item.menuId })" title="">${item.title!''}</a>
	            	<#--<a href="javascript:selectContent(${item.id },${item.menuId })" title=""><h5>${item.title!''}</h5></a>
	                
	                <div class="information_home">
	                	<span><i class="date"></i>${item.createTime?string("yyyy-MM-dd") }</span>
	                    <span><a href='' title="浏览次数"><i class="views"></i><#if item.viewCount gt 1000>${(item.viewCount/1000)?string("0.0")}k<#else>${item.viewCount?c!'0'}</#if></a></span>
	                    <span><a href='' title="来源"><i class="user"></i>${item.source!'本站' }</a></span>
	                </div>
	                -->
	                <#--
	                <div class="news_summary">
	                	<#if item.imgUrl??>
	                		<a class="photo" href="javascript:selectContent(${item.id },${item.menuId })"><img src="${item.imgUrl!'' }" width=200 height=117/></a>
	                    </#if>
	                    <div class="news">
	                    <p>
		                    	<#if item.brief?length lt 300>
		                    		${item.brief!'' }
		                    	<#elseif item.brief?length gt 299>
		                    		${item.brief[0..300]!'' }	...
		                    	</#if>	
	                    </p>
	                    <a class="more" href="javascript:selectContent(${item.id },${item.menuId })" title="阅读文章">查看详情 →</a>
	                    </div>
	                </div>-->
	            </li>
	         </#list>
	      </#if>	            
        </ul>
        <#elseif menu_name == "联系我们"||menu_name == "人才招聘">
       <ul class="contact_information">
       	    <#if info_page??>
	        	<#list info_page.content as item>      
		        	<li>
		            	<div class="department" <#if menu_name == "人才招聘">style ="font-size:16px;"</#if>>${item.title!''}</div>
		                <div class="phone_number">
		                	<p class="contacts">联系人：${item.source!''}</p>
		                    <P>电&nbsp;&nbsp;&nbsp;话：${item.brief!''}</P>
		                    <#if menu_name == "人才招聘">
		                    	<p>${item.content!''}</p>
		                    <#else>
								<p>邮&nbsp;&nbsp;&nbsp;箱：${item.content!''}</P>
							</#if>
		                </div>
		            </li>
            		</#list>
            	</#if>	
        </ul>
        <!--联系方式右侧二维码-->
        <div class="wechat">
        	<img src="${site.wxQrCode!''}" width="200px" height="200px"/>
            <p>微信关注</p>
        </div>
        <!--优惠政策-->
        <#elseif menu_name=="优惠政策"||info_name??&&info_name.title == "入驻申请"||info_name??&&info_name.title == "园区简介"||info_name??&&info_name.title == "交通路线">
        <ul class="news_list">
       	    <#if info_page??>
	       	 <#list info_page.content as item>      
	        	<li>
	            	<a href="javascript:selectContent(${item.id },${item.menuId })" title="">${item.title!''}</a>
	            </li>
	         </#list>
	         <#if info_name??&& info_name.title == "入驻申请"><li><a href="/cooperation">在线申请</a></li></#if>
	         <#if info_name??&& info_name.title == "交通路线"><li><a href="/info/map">地图导航</a></li></#if>
	      </#if>	            
        </ul>
        <#elseif menu_name == "园区概况">
        	<#if info_name??&&info_name.title == "企业展示"|| info_name??&&info_name.title == "配套设施">
		        <!--企业展示-->
		        <ul class="enterprise_show">
		            <#if info_page??>
		           	    <#list info_page.content as item> 
				        	<li>
				                <a class="slr_1" href="javascript:selectContent(${item.id },${item.menuId })">
				                    		<img src="${item.imgUrl!''}" width="230px" height="180px"/>
				                    		<h5 style="width:200px;overflow: hidden;margin:5px 0 0 70px;">${item.title!''}</h5>
					                <#--	                   		
				                    <b>
				                        <img src="${item.imgUrl!''}" width="92px" height="46px"/><span>${item.source!''}</span>
				                        <h3>${item.title!''}</h3>
				                        <p>	                    	
					                        <#if item.brief?length lt 40>
					                    		${item.brief!'' }
					                    	<#elseif item.brief?length gt 39>
					                    		${item.brief[0..40]!'' }	...
					                    	</#if>	
			                    		</p>
				                    </b>
				                    -->
				                </a>
				            </li>
				        </#list>
				    </#if>        
		    	</ul>	
	    	<#elseif info_name??&&info_name.title == "园区荣誉">
		        <!--园区荣誉-->
		        <ul class="park_honors enterprise_show">
		            <#if info_page??>
		           	    <#list info_page.content as item>         	
				        	<li>
				        	   <a class="slr_1" href="javascript:;">
                                     <img src="${item.imgUrl!''}" width="230px" height="180px"/>
                                    <div style="width:200px;overflow: hidden;margin:5px 0 0 70px;">${item.title!''}</div> 
                                </a>
                                <#--
                                    <h5 style="width:200px;overflow: hidden;margin:5px 0 0 70px;">${item.title!''}</h5>
				            	<div><img src="${item.imgUrl!''}" width="228px" height="160px"/></div>
				                <div class="certificate_name">${item.title!''}</div>
				                -->
				            </li>
				        </#list>
				    </#if>        
		    	</ul>
	    	</#if>
    	<#elseif menu_name =="功能信息">
    		<#if info_name.title == "服务体系">
		    	<!--服务体系-->
		        <ul class="service_system">
		       		 <#if service_list??>
			       	 <#list service_list as info>
			        	<li class="li${(info_index%4)+1}">
			            	<a href="javascript:selectContent(${info.id },${info.menuId })"><i class="i1" style="background:url(${info.imgUrl!'' }) no-repeat;"></i></a>
			            	<a class="title" href="/info/list/content/${info.id?c!''}?mid=10">
			                	<p class="p1">${info.title!'' }</p>
			                    <p class="p2">${info.source!'' }</p>
			                </a>
			            </li>
			         </#list>
			      </#if>	       	            
		        </ul>
		    </#if>    
		 <#elseif menu_name == "合作机构">
		 	<#if info_name??>
			 	<!--合作机构-->
			 	<#--
			        <ul class="park_honors">
			            <#if info_page??>
			           	    <#list info_page.content as item>         	
					        	<li>
					        		<a href="javascript:selectContent(${item.id },${item.menuId })" title="${item.title!''}">
						            	<div><img src="${item.imgUrl!''}" width="228px" height="160px"/></div>
						                <div class="certificate_name">${item.title!''}</div>
					                </a>
					            </li>
					        </#list>
					    </#if>        
			    	</ul>
			    	-->
			    	<ul class="enterprise_show">
                      <#if info_page??>
                            <#list info_page.content as item>    
                            <li>
                                <a class="slr_1" href="javascript:selectContent(${item.id },${item.menuId });">
                                            <img src="${item.imgUrl!''}" width="230px" height="180px"/>
                                        <h5 style="width:200px;overflow: hidden;margin:5px 0 0 70px;">${item.title!''}</h5>
                                </a>
                            </li>
                        </#list>
                    </#if>        
                </ul>   
		    	<#else>
			     	<!--合作机构栏目-->
			        <ul class="service_system">
			       		 <#if work_list??>
				       	 <#list work_list as info>
				        	<li class="li${(info_index%4)+1}">
				            	<a href="javascript:selectCat(${info.menuId?c},${info.id?c});"><i class="i1" style="background:url(${info.imgUrl!'' }) no-repeat;"></i></a>
				            	<a class="title" href="javascript:selectCat(${info.menuId?c},${info.id?c});">
				                	<p class="p1">${info.title!'' }</p>
				                    <p class="p2">${info.content!'' }</p>
				                </a>
				            </li>
				         </#list>
				      </#if>	       	            
			        </ul>	
		        </#if>	    	
	    <#elseif menu_name == "创业导师" ||(menu_name == "创客空间"&&info_name??&&info_name.title == "空间展示")>
				<!--创业导师-->
		        <ul class="enterprise_show">
		            <#if info_page??>
		           	    <#list info_page.content as item> 
				        	<li>
				                <a class="slr_1" href="javascript:selectContent(${item.id },${item.menuId })">
				                     <img src="${item.imgUrl!''}" width="230px" height="180px"/>
		                    		<h5 style="width:200px;overflow: hidden;margin:5px 0 0 70px;">${item.title!''}</h5>	
		                    		<#--                    		
				                    <b>
				                        <img src="${item.imgUrl!''}" width="92px" height="46px"/><span>${item.source!''}</span>
				                        <h3>${item.title!''}</h3>
				                        <p>	                    	
					                        <#if item.brief?length lt 40>
					                    		${item.brief!'' }
					                    	<#elseif item.brief?length gt 39>
					                    		${item.brief[0..40]!'' }	...
					                    	</#if>	
			                    		</p>
				                    </b>
				                    -->
				                </a>
				            </li>
				        </#list>
				    </#if>        
		    	</ul>			           
		
		
		     <!--空间展示-->
		     <#--
		        <ul class="enterprise_show">
		            <#if info_page??>
		           	    <#list info_page.content as item> 
				        	<li>
				                <a class="slr_1" href="javascript:selectContent(${item.id },${item.menuId })">
				                     <#if item.showPictures??>
			               				 <#list item.showPictures?split(",") as uri>
			               				 	<#if ""!=uri && uri_index = 0>
				                    		<img src="${uri}" width="230px" height="230px"/>
					                        </#if>
					                    </#list>
					                </#if> 		                    		
				                    <b>
				                        <img src="${item.imgUrl!''}" width="92px" height="46px"/><span>${item.source!''}</span>
				                        <h3>${item.title!''}</h3>
				                        <p>	                    	
					                        <#if item.brief?length lt 40>
					                    		${item.brief!'' }
					                    	<#elseif item.brief?length gt 39>
					                    		${item.brief[0..40]!'' }	...
					                    	</#if>	
			                    		</p>
				                    </b>
				                </a>
				            </li>
				        </#list>
				    </#if>        
		    	</ul>	    
		    	-->
		<#elseif menu_name == "创客空间"&&!info_name??>		    	
		    	<!--创客空间-->
		        <ul class="service_system">
		       		 <#if space_list??>
			       	 <#list space_list as info>
			        	<li class="li${(info_index%4)+1}">
			            	<a href="javascript:selectCat(${info.menuId?c},${info.id?c});"><i class="i1" style="background:url(${info.imgUrl!'' }) no-repeat;"></i></a>
			            	<a class="title" href="javascript:selectCat(${info.menuId?c},${info.id?c});">
			                	<p class="p1">${info.title!'' }</p>
			                    <p class="p2">${info.content!'' }</p>
			                </a>
			            </li>
			         </#list>
			      </#if>	       	            
		        </ul>			
        </#if>
        <!--页码-->
        <#assign PAGE_DATA=info_page />
		<#if catId??>
		    <#include "/client/list_footer.ftl" />
		<#else>
		    <#include "/client/list_footer_index.ftl" />
		</#if>   
