<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>编辑信息</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.handlers.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/zh_CN.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<link href="/mag/style/WdatePicker.css" rel="stylesheet" type="text/css">
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<link href="/mag/style/default.css" rel="stylesheet">
<#--
<script type="text/javascript" charset="utf-8" src="/mag/js/TQEditor/TQEditor.js"></script>
-->
<script type="text/javascript">
    $(function () {
        //初始化表单验证
        $("#form1").initValidform();

         
        //初始化编辑器
        var editor = KindEditor.create('.editor', {
            width: '98%',
            height: '350px',
            resizeType: 1,
            uploadJson: '/Verwalter/editor/upload?action=EditorFile',
            fileManagerJson: '/Verwalter/editor/upload?action=EditorFile',
            allowFileManager: true
        });
        
        //初始化上传控件
        $(".upload-img").each(function () {
            $(this).InitSWFUpload({ 
                sendurl: "/Verwalter/upload", 
                flashurl: "/mag/js/swfupload.swf",
				use_query_string:true
            });
        });
        
        //初始化上传控件
        $(".upload-file").each(function () {
            $(this).InitSWFUpload({ 
                sendurl: "/Verwalter/uploadfile", 
                flashurl: "/mag/js/swfupload.swf",
                use_query_string:true
            });
        });
        //批量上传 zhangji
        $(".upload-show360").each(function () {
            $(this).InitSWFUpload_show360({ 
                btntext: "批量上传", 
                btnwidth: 66, 
                single: false, 
                water: true, 
                thumbnail: true, 
                filesize: "5120", 
                sendurl: "/Verwalter/upload", 
                flashurl: "/mag/js/swfupload.swf", 
                filetypes: "*.jpg;*.jpge;*.png;*.gif;" 
            });
        });

        //（缩略图）
        var txtPic = $("#txtImgUrl").val();
        if (txtPic == "" || txtPic == null) {
            $(".thumb_ImgUrl_show").hide();
        }
        else {
            $(".thumb_ImgUrl_show").html("<ul><li><div class='img-box1'><img src='" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
            $(".thumb_ImgUrl_show").show();
        }

        $("#txtImgUrl").blur(function () {
            var txtPic = $("#txtImgUrl").val();
            if (txtPic == "" || txtPic == null) {
                $(".thumb_ImgUrl_show").hide();
            }
            else {
                $(".thumb_ImgUrl_show").html("<ul><li><div class='img-box1'><img src='" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
                $(".thumb_ImgUrl_show").show();
            }
        });
        
        var logo1Pic = $("#logo1ImgUrl").val();
      //  if (logo1Pic == "" || logo1Pic == null) {
      //      $(".thumb_Logo1ImgUrl_show").hide();
      //  }
      //  else {
      //      $(".thumb_Logo1ImgUrl_show").html("<ul><li><div class='img-box1'><img src='" + logo1Pic + "' bigsrc='" + logo1Pic + "' /></div></li></ul>");
      //      $(".thumb_Logo1ImgUrl_show").show();
      //  }
        
        //设置封面图片的样式
        $(".photo-list ul li .img-box img").each(function () {
            if ($(this).attr("src") == $("#hidFocusPhoto").val()) {
                $(this).parent().addClass("selected");
            }
        });
    });
</script>
</head>
<body class="mainbody">
<form method="post" action="/Verwalter/article/save" id="form1">
<div>
<input type="hidden" name="__EVENTTARGET" id="__EVENTTARGET" value="${__EVENTTARGET!""}" />
<input type="hidden" name="__EVENTARGUMENT" id="__EVENTARGUMENT" value="${__EVENTARGUMENT!""}" />
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" />
</div>
<input name="menuId" type="text" value='${mid!""}' style="display:none;">
<input name="channelId" type="text" value='${cid!""}' style="display:none">
<input name="catId" type="text" value='${categoryId!""}' style="display:none">
<input name="id" type="text" value='<#if article??>${article.id!""}</#if>' style="display:none">
    <!--导航栏-->
    <div class="location">
        <a href="/Verwalter/content/list?cid=${cid!""}&mid=${mid!""}&categoryId=${categoryId!''}" class="back"><i></i><span>
            返回列表页</span></a> 
        <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
        <i class="arrow"></i>
        <a href="/Verwalter/content/list?cid=${cid!""}&mid=${mid!""}"><span>
            内容管理</span></a> <i class="arrow"></i><span>编辑信息</span>
    </div>
    <div class="line10">
    </div>
    <!--/导航栏-->
    <!--内容-->
    <div class="content-tab-wrap">
        <div id="floatHead" class="content-tab">
            <div class="content-tab-ul-wrap">
                <ul>
                    <li><a href="javascript:;" onclick="tabs(this);" class="selected">基本信息</a></li>
                    <li><a href="javascript:;" onclick="tabs(this);" class="">详细描述</a></li>
                    <li><a href="javascript:;" onclick="tabs(this);" class="">SEO选项</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="tab-content" style="display: block;">
        <dl>
            <dt>所属类别</dt>
            <dd>
                <div class="rule-single-select">
                    <select name="categoryId" id="ddlCategoryId" datatype="*" sucmsg=" " nullmsg="请选择！" class="Validform_error" style="display: none;">
                    	<#if article??>
                    	<#else>
                    	<option value="">请选择类别...</option>
                    	</#if>
                        <#if category_list??>
                            <#list category_list as c>
                                <option value="${c.id!""}" <#if article?? && article.categoryId==c.id>selected="selected"</#if>><#if c.layerCount?? && c.layerCount gt 1><#list 1..(c.layerCount-1) as a>　</#list>├ </#if>${c.title!""}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
                <#if article??>
                    <span class="Validform_checktip Validform_right"></span>
                <#else>
                    <span class="Validform_checktip Validform_wrong">请选择！</span>
                </#if>
            </dd>
        </dl>
        <dl>
            <dt>显示状态</dt>
            <dd>
                <div class="rule-multi-radio multi-radio">
                    <span id="rblStatus" style="display: none;">
                        <input type="radio" name="statusId" value="0" <#if !article?? || article?? && article.statusId?? && article.statusId==0>checked="checked"</#if> ><label>正常</label>
                       <#--> <input type="radio" name="statusId" value="1" <#if article?? && article.statusId?? && article.statusId==1>checked="checked"</#if>><label>待审核</label>-->
                        <input type="radio" name="statusId" value="2" <#if article?? && article.statusId?? && article.statusId==2>checked="checked"</#if>><label>不显示</label>
                    </span>
                </div>
            </dd>
        </dl>
        <#--
        <dl>
            <dt>推荐类型</dt>
            <dd>
                <div class="rule-multi-checkbox multi-checkbox">
                    <span id="cblItem" style="display: none;">
                        <input id="cblItem_0" type="checkbox" name="recommendId"><label for="cblItem_0">普通课程</label>
                        <input id="cblItem_1" type="checkbox" name="recommendId"><label for="cblItem_1">金牌课程</label>
                        <input id="cblItem_2" type="checkbox" name="recommendId"><label for="cblItem_2">推荐课程</label>
                        <input id="cblItem_3" type="checkbox" name="recommendId"><label for="cblItem_3">热门</label>
                        <input id="cblItem_4" type="checkbox" name="recommendId"><label for="cblItem_4">幻灯片</label>
                    </span>
                </div>
            </dd>
        </dl>

        <#if mid=12>
        <dl>
            <dt>推荐类型</dt>
            <dd>
                <div class="rule-multi-radio multi-radio">
                    <span id="rblRecommendId" style="display: none;">
                        <input type="radio" name="recommendId" value="0" <#if !article?? || article?? && article.recommendId?? && article.recommendId==0>checked="checked"</#if> ><label>普通课程</label>
                        <input type="radio" name="recommendId" value="1" <#if article?? && article.recommendId?? && article.recommendId==1>checked="checked"</#if>><label>金牌课程</label>
                        <input type="radio" name="recommendId" value="2" <#if article?? && article.recommendId?? && article.recommendId==2>checked="checked"</#if>><label>热门课程</label>
                    </span>
                </div>
            </dd>
        </dl>
        </#if>
                -->
        <dl>
            <dt>内容标题</dt>
            <dd>
                <input name="title" type="text" value="<#if article??>${article.title!""}</#if>" id="txtTitle" class="input normal" datatype="*2-100" sucmsg=" ">
                <span class="Validform_checktip">*标题最多100个字符</span>
            </dd>
        </dl>
        
        <dl>
            <dt>封面图片</dt>
            <dd>
                <input name="imgUrl" type="text" id="txtImgUrl" value="<#if article??>${article.imgUrl!""}</#if>" class="input normal upload-path">
                <div class="upload-box upload-img"></div>
                <div class="photo-list thumb_ImgUrl_show" style="display: none;">
                    <ul>
                        <li>
                            <div class="img-box1"></div>
                        </li>
                    </ul>
                </div>
                <span class="Validform_checktip">图片最佳尺寸690*540</span>
            </dd>
        </dl>
        <dl>
            <dt>排序数字</dt>
            <dd>
                <input name="sortId" type="text" value="<#if article??>${article.sortId!""}<#else>99</#if>" id="txtSortId" class="input txt100" datatype="n" sucmsg=" ">
                <span class="Validform_checktip">*数字，越小越向前</span>
            </dd>
        </dl>
        <dl>
            <dt>浏览次数</dt>
            <dd>
                <input name="viewCount" type="text"  value="<#if article??>${article.viewCount?c!""}<#else>0</#if>" id="txtClick" class="input txt100" datatype="n" sucmsg=" ">
                <span class="Validform_checktip">点击浏览该信息自动+1</span>
            </dd>
        </dl>
        <dl>
            <dt>发布时间</dt>
            <dd>
                <div class="input-date">
                    <input name="createTime" type="text" id="txtAddTime" value="<#if article??>${article.createTime!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
                    <i>日期</i>
                </div>
                <span class="Validform_checktip">不选择默认当前发布时间</span>
            </dd>
        </dl>
    </div>
    
    <div class="tab-content" style="display: none;">
        <#-- 批量上传 zhangji-->
        <#if mid = 11 || mid = 10 || mid = 12|| mid = 83 ||mid == 94>
        <dl id="div_show360_container">
            <dt>展示图片</dt>
            <dd>
                <div class="upload-box upload-show360"></div>
                <div class="photo-list_show360">
                    <ul>
                        <#if article?? && article.showPictures??>
                            <#list article.showPictures?split(",") as uri>
                                <#if uri != "">
                                <li>
                                    <input type="hidden" name="hid_photo_name_show360" value="0|${uri!""}|${uri!""}">
                                    <div class="img-box">
                                        <img src="${uri!""}" bigsrc="${uri!""}">
                                    </div>
                                    <a href="javascript:;" onclick="delImg(this);">删除</a>
                                </li>
                                </#if>
                            </#list>
                        </#if>
                    </ul>
                </div>
            </dd>
        </dl>
        </#if>
        <#-- 批量上传 zhangji  end-->
        <dl id="div_source">
            <dt>
                <span id="div_source_title">
                <#if mid == 89 ||mid ==13>
                	联系人
	            </span></dt>
	            <dd>
	                <input name="source" type="text" datatype="*" value="<#if article??>${article.source!""}</#if>" id="field_control_source" class="input normal">
	            </dd>
                <#elseif mid == 10>
                	副标题
	             </span></dt>
	            <dd>
	                <input name="source" type="text" value="<#if article??>${article.source!""}<#else>本站</#if>" id="field_control_source" class="input normal">
	                <span id="div_source_tip" class="Validform_checktip">非必填，最多50个字符</span>
	            </dd>	
                <#elseif mid == 11||mid == 94>
                	楼层
	             </span></dt>
	            <dd>
	                <input name="source" type="text" value="<#if article??>${article.source!""}<#else>3F</#if>" id="field_control_source" class="input normal">
	                <span id="div_source_tip" class="Validform_checktip">非必填，最多50个字符</span>
	            </dd>		            
                <#else>		
                	信息来源
                </span></dt>
	            <dd>
	                <input name="source" type="text" value="<#if article??>${article.source!""}<#else>本站</#if>" id="field_control_source" class="input normal">
	                <span id="div_source_tip" class="Validform_checktip">非必填，最多50个字符</span>
	            </dd>
	            </#if>	
        </dl>
        
        <dl>
            <dt>
            <#if mid==89 || mid ==13>
	            	电话</dt>
	            <dd>
	                <input name="brief" id="txtZhaiyao" class="input" datatype="m|/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/" errormsg="请输入正确格式的电话号码" value="<#if article??>${article.brief!""}</#if>" />
	            </dd>	
            <#else>
	            内容摘要</dt>
	            <dd>
	                <textarea name="brief" rows="2" cols="20" id="txtZhaiyao" class="input"  sucmsg=" "><#if article??>${article.brief!""}</#if></textarea>
	                <span class="Validform_checktip">不填写则自动截取内容前512字符</span>
	            </dd>
            </#if>
        </dl>
        <dl>
            <dt>文件地址</dt>
            <dd>
                <input id="logo1ImgUrl" name="linkUrl" type="text" datatype="*0-255" value="<#if article??>${article.linkUrl!""}</#if>" class="input normal upload-path">
                <div class="upload-box upload-file"></div>
                <span class="Validform_checktip"></span>
               
            </dd>
        </dl>
        <#if mid==89>
        <dl>
            <dt>邮箱</dt>
            <dd>
                <input name="content" datatype="e" value="<#if article??>${article.content!""}</#if>"/>
            </dd>
        </dl>
        <#else>
        <dl>
            <dt>内容描述</dt>
            <dd>
                <textarea name="content" class="editor" style="visibility:hidden;"><#if article??>${article.content!""}</#if></textarea>
                <#--<textarea id="e1" rows="20" cols="130" name="content"><#if article??>${article.content!""}</#if></textarea>-->
            </dd>
        </dl>
        </#if>
        <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
        <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
        <#--
         <script type="text/javascript" charset="utf-8" src="/mag/js/ueditor-1.4.3.3/ueditor.config.js"></script>
        <script type="text/javascript" charset="utf-8" src="/mag/js/ueditor-1.4.3.3/_examples/editor_api.js"> </script>
        <script type="text/javascript" charset="utf-8" src="/mag/js/ueditor-1.4.3.3/lang/zh-cn/zh-cn.js"></script>
        <script type="text/javascript" defer="true">
            new  tqEditor('e1',{toolbar:'admin'});
        </script>
        -->
    </div>
    <div class="tab-content" style="display: none;">
        <dl>
            <dt>SEO标题</dt>
            <dd>
                <input name="seoTitle" type="text" maxlength="255" id="txtSeoTitle" value="<#if article??>${article.seoTitle!""}</#if>" class="input normal" datatype="*0-100" sucmsg=" ">
                <span class="Validform_checktip">255个字符以内</span>
            </dd>
        </dl>
        <dl>
            <dt>SEO关健字</dt>
            <dd>
                <textarea name="seoKeywords" rows="2" cols="20" id="txtSeoKeywords" class="input" datatype="*0-255" sucmsg=" "><#if article??>${article.seoKeywords!""}</#if></textarea>
                <span class="Validform_checktip">以“,”逗号区分开，255个字符以内</span>
            </dd>
        </dl>
        <dl>
            <dt>SEO描述</dt>
            <dd>
                <textarea name="seoDescription" rows="2" cols="20" id="txtSeoDescription" class="input" datatype="*0-255" sucmsg=" "><#if article??>${article.seoDescription!""}</#if></textarea>
                <span class="Validform_checktip">255个字符以内</span>
            </dd>
        </dl>
    </div>
    
    <!--/内容-->
    <!--工具栏-->
    <div class="page-footer">
        <div class="btn-list">
            <input type="submit" name="btnSubmit" value="提交保存" id="btnSubmit" class="btn">
            <input name="btnReturn" type="button" value="返回上一页" class="btn yellow" onclick="javascript:history.back(-1);">
        </div>
        <div class="clear">
        </div>
    </div>
    <!--/工具栏-->
    </form>


</body></html>