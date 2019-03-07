 <div class="right_title">
    <a>首页&nbsp;&gt;&nbsp;</a>   
    <a>课程设置</a>
    <a><#if info_name??> > ${info_name.title!'' }</#if></a>
</div>    
<div class="right_crouse">
    <#if course_page??>
        <#list course_page.content as item> 
            <dl class="crouse">
                <dt><img src="${item.imgUrl!''}" /></dt>
                <dd>
                    <a href="javascript:courseTake(${item.id },${item.menuId })">${item.title!''}</a>
                    <#if item.content?length lt 400>
                        <p>${item.content!''}</p>
                    <#else>
                        <p>${item.content[0..400] }...</p>
                    </#if>
                </dd>
            </dl>
        </#list>
    </#if>        
</div>
<!--内容底部-->
 <div class="page">
 <#if course_page??>
     <#assign continueEnter=false>
     
     <#if course_page.totalPages gt 0>
         <#list 1..course_page.totalPages as page>
             <#if page <= 3 || (course_page.totalPages-page) < 3 || (course_page.number+1-page)?abs<3 >
                 <#if page == course_page.number+1>
                     <input type="button" value="${page }" />
                 <#else>
                     <input onclick="javascript:searchPage('${keywords}','btnPage','${page-1}')" class="" type="button" value="${page}" /> 
                 </#if>
                 <#assign continueEnter=false>
             <#else>
                 <#if !continueEnter>
                     ...
                     <#assign continueEnter=true>
                 </#if>
             </#if>
         </#list>
     </#if>
     
     <#if course_page.number+1 == 1>
         <input class="block" type="button" value="上一页" />
     <#else>
         <input onclick="javascript:searchPage('${keywords}','btnPage','${course_page.number-1}')" class="block" type="button" value="上一页" />                
     </#if>
     

     
     <#if course_page.number+1 == course_page.totalPages || course_page.totalPages==0>
         <input class="block" type="button" value="下一页" />
     <#else>
         <input onclick="javascript:searchPage('${keywords}','btnPage','${course_page.number+1}')" class="block" type="button" value="下一页" /> 
     </#if>
 </#if>

    <input id="jump-page" class="page_text" type="" value="${course_page.number+1 }" />
    <input class= "block" onclick="javascript:searchPageJump('${keywords}','btnPage')" type="button" value="确定" />
    
    <a>共${course_page.totalPages}页</a>
</div>
 <!--内容底部 end-->      