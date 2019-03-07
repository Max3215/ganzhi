 <div class="page_number">
 <#if PAGE_DATA??>
     <#assign continueEnter=false>
     <#if PAGE_DATA.number+1 == 1>
         <a class="first"></a>
     <#else>
         <a href="javascript:page(${mid},${catId },'btnPage','${PAGE_DATA.number-1}')" class="first"></a>                
     </#if>
     <#if PAGE_DATA.totalPages gt 0>
         <#list 1..PAGE_DATA.totalPages as page>
             <#if page <= 1 || (PAGE_DATA.totalPages-page) < 1 || (PAGE_DATA.number+1-page)?abs<3 >
                 <#if page == PAGE_DATA.number+1>
                    <a class="sel">${page } </a>
                 <#else>
                     <a href ="javascript:page(${mid},${catId },'btnPage','${page-1}')" >${page}</a> 
                 </#if>
                 <#assign continueEnter=false>
             <#else>
                 <#if !continueEnter>
                      <span> ...</span>
                     <#assign continueEnter=true>
                 </#if>
             </#if>
         </#list>
     </#if>

     <#if PAGE_DATA.number+1 == PAGE_DATA.totalPages || PAGE_DATA.totalPages==0>
         <a class="last"></a>
     <#else>
         <a href="javascript:page(${mid},${catId },'btnPage','${PAGE_DATA.number+1}')" class="last" ></a>
     </#if>
 </#if>
</div>