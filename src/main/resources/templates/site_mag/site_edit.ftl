<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑站点</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<link href="/mag/style/default.css" rel="stylesheet">
<script type="text/javascript">
$(function () {
    //初始化表单验证
    $("#form1").initValidform();
});
</script>
</head>
<body class="mainbody">
<form method="post" action="/Verwalter/site/save" id="form1">
<div>
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" >
<input type="hidden" name="id" value="<#if site??>${site.id!""}</#if>" >
</div>
<!--导航栏-->
<div class="location" style="position: static; top: 0px;">
  <a href="/Verwalter/site/list"><i></i><span>返回列表页</span></a>
  <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
  <i class="arrow"></i>
  <span>站点管理</span>
  <i class="arrow"></i>
  <span>编辑站点</span>
</div>
<div class="line10"></div>
<!--导航栏-->
<!--内容-->
<div class="content-tab-wrap">
    <div id="floatHead" class="content-tab">
        <div class="content-tab-ul-wrap" >
            <ul>
                <li><a href="javascript:;" onclick="tabs(this);" class="selected">编辑站点</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="tab-content" style="display: block;">
    <dl>
        <dt>站点名称</dt>
        <dd>
            <input name="title" type="text" value="<#if site??>${site.title!""}</#if>" class="input normal" ajaxurl="/Verwalter/site/check<#if site??>?id=${site.id}</#if>" datatype="s" sucmsg=" ">
            <span class="Validform_checktip">*站点名称</span>
        </dd>
    </dl>
    <dl>
        <dt>域名</dt>
        <dd>
            <input name="linkUri" type="text" value="<#if site??>${site.linkUri!""}<#else>http://</#if>" class="input normal" datatype="url" sucmsg=" ">
            <span class="Validform_checktip">站点域名</span>
        </dd>
    </dl>
    <dl>
        <dt>固定电话</dt>
        <dd>
            <input name="telephone" type="text" value="<#if site??>${site.telephone!""}</#if>" class="input normal" datatype="*0-255" sucmsg=" ">
            <span class="Validform_checktip">固定电话</span>
        </dd>
    </dl>
    <dl>
        <dt>QQ号码</dt>
        <dd>
            <input name="qq" type="text" value="<#if site??>${site.qq!""}</#if>" class="input normal" datatype="n0-255" sucmsg=" ">
            <span class="Validform_checktip">QQ号码</span>
        </dd>
    </dl>
    <dl>
        <dt>状态</dt>
        <dd>
            <div class="rule-multi-radio">
                <span>
                    <input type="radio" name="isEnable" value="1" <#if site?? && site.isEnable?? && site.isEnable==true>checked="checked"</#if>>
                    <label>正常</label>
                    <input type="radio" name="isEnable" value="0" <#if !site?? || !site.isEnable?? || !site.isEnable>checked="checked"</#if>>
                    <label>禁用</label>
                </span>
            </div>
        </dd>
    </dl>
    <dl>
        <dt>排序数字</dt>
        <dd>
            <input name="sortId" type="text" value="<#if site??>${site.sortId!""}<#else>99</#if>" class="input txt100" datatype="n" sucmsg=" ">
            <span class="Validform_checktip">*数字，越小越向前</span>
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
</body>
</html>