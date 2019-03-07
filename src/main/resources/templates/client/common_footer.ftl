<div class="foot">
	<div class="address">
        <div class="address_information">
            <div><img src="/client/images/bottom_logo.png" /></div>
            <div class="middle">
            	<p>联系电话：${site.telephone!''}</p>
				<p>公司地址：${site.address!''}</p>
				<ul class="friend_link">
	        	<li class="lk1">友情链接：</li>
	               	<#if site_link_list??>
	                    <#list site_link_list as link>
	                    	<#if link_has_next>
	                    		<#if link_index gt 7&& link_index%8 == 0> 
	                        		<li class="last"><a href="${link.linkUri}" target="_blank" title="${link.linkUri}">${link.title}</a></li></br>
	                        		<li class="last">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
	                        	<#else>
	                        		<li><a href="${link.linkUri}" target="_blank" title="${link.linkUri}">${link.title}</a></li>
	                        	</#if>	
	                        <#else>
	                        	<li class="last"><a href="${link.linkUri}" target="_blank" title="${link.linkUri}">${link.title}</a></li>
	                        </#if>	
	                    </#list>
	                </#if>  
        		</ul>
            </div>
            <div class="qr_code"><img src="${site.wx_qr_code!''}" /></div>
        </div>
    </div>
    <p class="footer">${site.copyright!''}</p>
</div>
