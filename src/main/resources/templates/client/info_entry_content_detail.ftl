<div class="right_title">
<a>首页&nbsp;&gt;&nbsp;</a>
<a title="${info_name.content }">${info_name.title!'' }&nbsp;&gt;&nbsp;</a>
<a title="${info.title }">${info.title!'' }</a>
</div>

<div class="name">${info.title!'' }</div>
<div class="right_box">
    <img src="${info.imgUrl!'' }"/>
    <p>${info.content!'' }</p>
</div>
<div class = "none">
              <#if info.showPictures??>
                  <#list info.showPictures?split(",") as uri>
                      <#if ""!=uri && uri_index < 4>
                          <img class = "cover" src="${uri!''}" style="margin-top:2%;"/><br />
                      </#if>
                  </#list>
              </#if>  
          </div>