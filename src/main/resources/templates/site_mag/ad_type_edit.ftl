<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑广告位</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<link href="/mag/style/WdatePicker.css" rel="stylesheet" type="text/css">
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<link href="/mag/style/default.css" rel="stylesheet">
<script type="text/javascript">
$(function () {
    //初始化表单验证
    $("#form1").initValidform();
});
</script>
</head>
<body class="mainbody"><div class="" style="left: 0px; top: 0px; visibility: hidden; position: absolute;"><table class="ui_border"><tbody><tr><td class="ui_lt"></td><td class="ui_t"></td><td class="ui_rt"></td></tr><tr><td class="ui_l"></td><td class="ui_c"><div class="ui_inner"><table class="ui_dialog"><tbody><tr><td colspan="2"><div class="ui_title_bar"><div class="ui_title" unselectable="on" style="cursor: move;"></div><div class="ui_title_buttons"><a class="ui_min" href="javascript:void(0);" title="最小化" style="display: inline-block;"><b class="ui_min_b"></b></a><a class="ui_max" href="javascript:void(0);" title="最大化" style="display: inline-block;"><b class="ui_max_b"></b></a><a class="ui_res" href="javascript:void(0);" title="还原"><b class="ui_res_b"></b><b class="ui_res_t"></b></a><a class="ui_close" href="javascript:void(0);" title="关闭(esc键)" style="display: inline-block;">×</a></div></div></td></tr><tr><td class="ui_icon" style="display: none;"></td><td class="ui_main" style="width: auto; height: auto;"><div class="ui_content" style="padding: 10px;"></div></td></tr><tr><td colspan="2"><div class="ui_buttons" style="display: none;"></div></td></tr></tbody></table></div></td><td class="ui_r"></td></tr><tr><td class="ui_lb"></td><td class="ui_b"></td><td class="ui_rb" style="cursor: se-resize;"></td></tr></tbody></table></div>
<form method="post" action="/Verwalter/ad/type/save" id="form1">
<div>
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" >
<input type="hidden" name="id" value="<#if ad_type??>${ad_type.id!""}</#if>" >
</div>
<!--导航栏-->
<div class="location" style="position: static; top: 0px;">
  <a href="/Verwalter/ad/type/list"><i></i><span>返回列表页</span></a>
  <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
  <i class="arrow"></i>
  <span>广告位管理</span>
  <i class="arrow"></i>
  <span>编辑广告位</span>
</div>
<div class="line10"></div>
<!--导航栏-->
<!--内容-->
<div class="content-tab-wrap">
    <div id="floatHead" class="content-tab">
        <div class="content-tab-ul-wrap" >
            <ul>
                <li><a href="javascript:;" onclick="tabs(this);" class="selected">编辑广告位</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="tab-content" style="display: block;">
    <dl>
        <dt>名称</dt>
        <dd>
            <input name="title" type="text"<#if ad_type??> value="${ad_type.title!""}" disabled="disabled" style="background: #EEE;"</#if> class="input normal" datatype="s" sucmsg=" " >
            <span class="Validform_checktip">*广告位名称，添加后不可修改</span>
        </dd>
    </dl>
    <dl>
        <dt>打开位置</dt>
        <dd>
            <div class="rule-multi-radio multi-radio">
                <span>
                    <input type="radio" name="isNewWindow" value="1" <#if !ad_type?? || ad_type?? && ad_type.isNewWindow?? && ad_type.isNewWindow>checked="checked"</#if>>
                    <label>新窗口</label>
                    <input type="radio" name="isNewWindow" value="0" <#if ad_type?? && ad_type.isNewWindow?? && !ad_type.isNewWindow>checked="checked"</#if>>
                    <label>原窗口</label>
                </span>
            </div>
        </dd>
    </dl>
    <dl>
        <dt>效果样式选择</dt>
        <dd>
            <div class="rule-single-select">
                <select id="tssel" name="effect">
			        <option value="">选择切页效果</option>
			        <option value="fade" <#if ad_type.effect?? && ad_type.effect == "fade">selected="selected"</#if>>fade</option>
			        <optgroup label="滚动效果">
			            <option value="scroll" <#if ad_type.effect?? && ad_type.effect == "scroll">selected="selected"</#if>>scroll</option>
			            <option value="scroll3d" <#if ad_type.effect?? && ad_type.effect == "scroll3d">selected="selected"</#if>>scroll3d</option>
			            <option value="scrollCover" <#if ad_type.effect?? && ad_type.effect == "scrollCover">selected="selected"</#if>>scrollCover</option>
			            <option value="scrollCoverReverse" <#if ad_type.effect?? && ad_type.effect == "scrollCoverReverse">selected="selected"</#if>>scrollCoverReverse</option>
			            <option value="scrollCoverIn" <#if ad_type.effect?? && ad_type.effect == "scrollCoverIn">selected="selected"</#if>>scrollCoverIn</option>
			            <option value="scrollCoverOut" <#if ad_type.effect?? && ad_type.effect == "scrollCoverOut">selected="selected"</#if>>scrollCoverOut</option>
			            <option value="scrollX" <#if ad_type.effect?? && ad_type.effect == "scrollX">selected="selected"</#if>>scrollX</option>
			            <option value="scroll3dX" <#if ad_type.effect?? && ad_type.effect == "scroll3dX">selected="selected"</#if>>scroll3dX</option>
			            <option value="scrollCoverX" <#if ad_type.effect?? && ad_type.effect == "scrollCoverX">selected="selected"</#if>>scrollCoverX</option>
			            <option value="scrollCoverReverseX" <#if ad_type.effect?? && ad_type.effect == "scrollCoverReverseX">selected="selected"</#if>>scrollCoverReverseX</option>
			            <option value="scrollCoverInX" <#if ad_type.effect?? && ad_type.effect == "scrollCoverInX">selected="selected"</#if>>scrollCoverInX</option>
			            <option value="scrollCoverOutX" <#if ad_type.effect?? && ad_type.effect == "scrollCoverOutX">selected="selected"</#if>>scrollCoverOutX</option>
			            <option value="scrollY" <#if ad_type.effect?? && ad_type.effect == "scrollY">selected="selected"</#if>>scrollY</option>
			            <option value="scroll3dY" <#if ad_type.effect?? && ad_type.effect == "scroll3dY">selected="selected"</#if>>scroll3dY</option>
			            <option value="scrollCoverY" <#if ad_type.effect?? && ad_type.effect == "scrollCoverY">selected="selected"</#if>>scrollCoverY</option>
			            <option value="scrollCoverReverseY" <#if ad_type.effect?? && ad_type.effect == "scrollCoverReverseY">selected="selected"</#if>>scrollCoverReverseY</option>
			            <option value="scrollCoverInY" <#if ad_type.effect?? && ad_type.effect == "scrollCoverInY">selected="selected"</#if>>scrollCoverInY</option>
			            <option value="scrollCoverOutY" <#if ad_type.effect?? && ad_type.effect == "scrollCoverOutY">selected="selected"</#if>>scrollCoverOutY</option>
                    </optgroup>
			        <optgroup label="滑动效果">
			            <option value="slide" <#if ad_type.effect?? && ad_type.effect == "slide">selected="selected"</#if>>slide</option>
			            <option value="slideCover" <#if ad_type.effect?? && ad_type.effect == "slideCover">selected="selected"</#if>>slideCover</option>
			            <option value="slideCoverReverse" <#if ad_type.effect?? && ad_type.effect == "slideCoverReverse">selected="selected"</#if>>slideCoverReverse</option>
			            <option value="slideCoverIn" <#if ad_type.effect?? && ad_type.effect == "slideCoverIn">selected="selected"</#if>>slideCoverIn</option>
			            <option value="slideCoverOut" <#if ad_type.effect?? && ad_type.effect == "slideCoverOut">selected="selected"</#if>>slideCoverOut</option>
			            <option value="slideX" <#if ad_type.effect?? && ad_type.effect == "slideX">selected="selected"</#if>>slideX</option>
			            <option value="slideCoverX" <#if ad_type.effect?? && ad_type.effect == "slideCoverX">selected="selected"</#if>>slideCoverX</option>
			            <option value="slideCoverReverseX" <#if ad_type.effect?? && ad_type.effect == "slideCoverReverseX">selected="selected"</#if>>slideCoverReverseX</option>
			            <option value="slideCoverInX" <#if ad_type.effect?? && ad_type.effect == "slideCoverInX">selected="selected"</#if>>slideCoverInX</option>
			            <option value="slideCoverOutX" <#if ad_type.effect?? && ad_type.effect == "slideCoverOutX">selected="selected"</#if>>slideCoverOutX</option>
			            <option value="slideY" <#if ad_type.effect?? && ad_type.effect == "slideY">selected="selected"</#if>>slideY</option>
			            <option value="slideCoverY" <#if ad_type.effect?? && ad_type.effect == "slideCoverY">selected="selected"</#if>>slideCoverY</option>
			            <option value="slideCoverReverseY" <#if ad_type.effect?? && ad_type.effect == "slideCoverReverseY">selected="selected"</#if>>slideCoverReverseY</option>
			            <option value="slideCoverInY" <#if ad_type.effect?? && ad_type.effect == "slideCoverInY">selected="selected"</#if>>slideCoverInY</option>
			            <option value="slideCoverOutY" <#if ad_type.effect?? && ad_type.effect == "slideCoverOutY">selected="selected"</#if>>slideCoverOutY</option>
			        </optgroup>
			        <optgroup label="裁切效果">
			            <option value="slice" <#if ad_type.effect?? && ad_type.effect == "slice">selected="selected"</#if>>slice</option>
			            <option value="sliceX" <#if ad_type.effect?? && ad_type.effect == "sliceX">selected="selected"</#if>>sliceX</option>
			            <option value="sliceY" <#if ad_type.effect?? && ad_type.effect == "sliceY">selected="selected"</#if>>sliceY</option>
			        </optgroup>
			        <optgroup label="缩放效果">
			            <option value="zoom" <#if ad_type.effect?? && ad_type.effect == "zoom">selected="selected"</#if>>zoom</option>
			            <option value="zoomCover" <#if ad_type.effect?? && ad_type.effect == "zoomCover">selected="selected"</#if>>zoomCover</option>
			            <option value="zoomCoverReverse" <#if ad_type.effect?? && ad_type.effect == "zoomCoverReverse">selected="selected"</#if>>zoomCoverReverse</option>
			            <option value="zoomCoverIn" <#if ad_type.effect?? && ad_type.effect == "zoomCoverIn">selected="selected"</#if>>zoomCoverIn</option>
			            <option value="zoomCoverOut" <#if ad_type.effect?? && ad_type.effect == "zoomCoverOut">selected="selected"</#if>>zoomCoverOut</option>
			            <option value="zoomX" <#if ad_type.effect?? && ad_type.effect == "zoomX">selected="selected"</#if>>zoomX</option>
			            <option value="zoomCoverX" <#if ad_type.effect?? && ad_type.effect == "zoomCoverX">selected="selected"</#if>>zoomCoverX</option>
			            <option value="zoomCoverReverseX" <#if ad_type.effect?? && ad_type.effect == "zoomCoverReverseX">selected="selected"</#if>>zoomCoverReverseX</option>
			            <option value="zoomCoverInX" <#if ad_type.effect?? && ad_type.effect == "zoomCoverInX">selected="selected"</#if>>zoomCoverInX</option>
			            <option value="zoomCoverOutX" <#if ad_type.effect?? && ad_type.effect == "zoomCoverOutX">selected="selected"</#if>>zoomCoverOutX</option>
			            <option value="zoomY" <#if ad_type.effect?? && ad_type.effect == "zoomY">selected="selected"</#if>>zoomY</option>
			            <option value="zoomCoverY" <#if ad_type.effect?? && ad_type.effect == "zoomCoverY">selected="selected"</#if>>zoomCoverY</option>
			            <option value="zoomCoverReverseY" <#if ad_type.effect?? && ad_type.effect == "zoomCoverReverseY">selected="selected"</#if>>zoomCoverReverseY</option>
			            <option value="zoomCoverInY" <#if ad_type.effect?? && ad_type.effect == "zoomCoverInY">selected="selected"</#if>>zoomCoverInY</option>
			            <option value="zoomCoverOutY" <#if ad_type.effect?? && ad_type.effect == "zoomCoverOutY">selected="selected"</#if>>zoomCoverOutY</option>
			        </optgroup>
			        <optgroup label="扭曲效果">
			            <option value="skew" <#if ad_type.effect?? && ad_type.effect == "skew">selected="selected"</#if>>skew</option>
			            <option value="skewCover" <#if ad_type.effect?? && ad_type.effect == "skewCover">selected="selected"</#if>>skewCover</option>
			            <option value="skewCoverReverse" <#if ad_type.effect?? && ad_type.effect == "skewCoverReverse">selected="selected"</#if>>skewCoverReverse</option>
			            <option value="skewCoverIn" <#if ad_type.effect?? && ad_type.effect == "skewCoverIn">selected="selected"</#if>>skewCoverIn</option>
			            <option value="skewCoverOut" <#if ad_type.effect?? && ad_type.effect == "skewCoverOut">selected="selected"</#if>>skewCoverOut</option>
			            <option value="skew" <#if ad_type.effect?? && ad_type.effect == "skew">selected="selected"</#if>>skewX</option>
			            <option value="skewCoverX" <#if ad_type.effect?? && ad_type.effect == "skewCoverX">selected="selected"</#if>>skewCoverX</option>
			            <option value="skewCoverReverseX" <#if ad_type.effect?? && ad_type.effect == "skewCoverReverseX">selected="selected"</#if>>skewCoverReverseX</option>
			            <option value="skewCoverInX" <#if ad_type.effect?? && ad_type.effect == "skewCoverInX">selected="selected"</#if>>skewCoverInX</option>
			            <option value="skewCoverOutX" <#if ad_type.effect?? && ad_type.effect == "skewCoverOutX">selected="selected"</#if>>skewCoverOutX</option>
			            <option value="skewY" <#if ad_type.effect?? && ad_type.effect == "skewY">selected="selected"</#if>>skewY</option>
			            <option value="skewCoverY" <#if ad_type.effect?? && ad_type.effect == "skewCoverY">selected="selected"</#if>>skewCoverY</option>
			            <option value="skewCoverReverseY" <#if ad_type.effect?? && ad_type.effect == "skewCoverReverseY">selected="selected"</#if>>skewCoverReverseY</option>
			            <option value="skewCoverInY" <#if ad_type.effect?? && ad_type.effect == "skewCoverInY">selected="selected"</#if>>skewCoverInY</option>
			            <option value="skewCoverOutY" <#if ad_type.effect?? && ad_type.effect == "skewCoverOutY">selected="selected"</#if>>skewCoverOutY</option>
			        </optgroup>
			        <optgroup label="翻转效果">
			            <option value="flip" <#if ad_type.effect?? && ad_type.effect == "flip">selected="selected"</#if>>flip</option>
			            <option value="flip3d" <#if ad_type.effect?? && ad_type.effect == "flip3d">selected="selected"</#if>>flip3d</option>
			            <option value="flipClock" <#if ad_type.effect?? && ad_type.effect == "flipClock">selected="selected"</#if>>flipClock</option>
			            <option value="flipPaper" <#if ad_type.effect?? && ad_type.effect == "flipPaper">selected="selected"</#if>>flipPaper</option>
			            <option value="flipCover" <#if ad_type.effect?? && ad_type.effect == "flipCover">selected="selected"</#if>>flipCover</option>
			            <option value="flipCoverReverse" <#if ad_type.effect?? && ad_type.effect == "flipCoverReverse">selected="selected"</#if>>flipCoverReverse</option>
			            <option value="flipCoverIn" <#if ad_type.effect?? && ad_type.effect == "flipCoverIn">selected="selected"</#if>>flipCoverIn</option>
			            <option value="flipCoverOut" <#if ad_type.effect?? && ad_type.effect == "flipCoverOut">selected="selected"</#if>>flipCoverOut</option>
			            <option value="flipX" <#if ad_type.effect?? && ad_type.effect == "flipX">selected="selected"</#if>>flipX</option>
			            <option value="flip3dX" <#if ad_type.effect?? && ad_type.effect == "flip3dX">selected="selected"</#if>>flip3dX</option>
			            <option value="flipClockX" <#if ad_type.effect?? && ad_type.effect == "flipClockX">selected="selected"</#if>>flipClockX</option>
			            <option value="flipPaperX" <#if ad_type.effect?? && ad_type.effect == "flipPaperX">selected="selected"</#if>>flipPaperX</option>
			            <option value="flipCoverX" <#if ad_type.effect?? && ad_type.effect == "flipCoverX">selected="selected"</#if>>flipCoverX</option>
			            <option value="flipCoverReverseX" <#if ad_type.effect?? && ad_type.effect == "flipCoverReverseX">selected="selected"</#if>>flipCoverReverseX</option>
			            <option value="flipCoverInX" <#if ad_type.effect?? && ad_type.effect == "flipCoverInX">selected="selected"</#if>>flipCoverInX</option>
			            <option value="flipCoverOutX" <#if ad_type.effect?? && ad_type.effect == "flipCoverOutX">selected="selected"</#if>>flipCoverOutX</option>
			            <option value="flipY" <#if ad_type.effect?? && ad_type.effect == "flipY">selected="selected"</#if>>flipY</option>
			            <option value="flip3dY" <#if ad_type.effect?? && ad_type.effect == "flip3dY">selected="selected"</#if>>flip3dY</option>
			            <option value="flipClockY" <#if ad_type.effect?? && ad_type.effect == "flipClockY">selected="selected"</#if>>flipClockY</option>
			            <option value="flipPaperY" <#if ad_type.effect?? && ad_type.effect == "flipPaperY">selected="selected"</#if>>flipPaperY</option>
			            <option value="flipCoverY" <#if ad_type.effect?? && ad_type.effect == "flipCoverY">selected="selected"</#if>>flipCoverY</option>
			            <option value="flipCoverReverseY" <#if ad_type.effect?? && ad_type.effect == "flipCoverReverseY">selected="selected"</#if>>flipCoverReverseY</option>
			            <option value="flipCoverInY" <#if ad_type.effect?? && ad_type.effect == "flipCoverInY">selected="selected"</#if>>flipCoverInY</option>
			            <option value="flipCoverOutY" <#if ad_type.effect?? && ad_type.effect == "flipCoverOutY">selected="selected"</#if>>flipCoverOutY</option>
			        </optgroup>
			        <optgroup label="爆炸效果">
			            <option value="bomb" <#if ad_type.effect?? && ad_type.effect == "bomb">selected="selected"</#if>>bomb</option>
			            <option value="bombCover" <#if ad_type.effect?? && ad_type.effect == "bombCover">selected="selected"</#if>>bombCover</option>
			            <option value="bombCoverReverse" <#if ad_type.effect?? && ad_type.effect == "bombCoverReverse">selected="selected"</#if>>bombCoverReverse</option>
			            <option value="bombCoverIn" <#if ad_type.effect?? && ad_type.effect == "bombCoverIn">selected="selected"</#if>>bombCoverIn</option>
			            <option value="bombCoverOut" <#if ad_type.effect?? && ad_type.effect == "bombCoverOut">selected="selected"</#if>>bombCoverOut</option>
			            <option value="bombX" <#if ad_type.effect?? && ad_type.effect == "bombX">selected="selected"</#if>>bombX</option>
			            <option value="bombCoverX" <#if ad_type.effect?? && ad_type.effect == "bombCoverX">selected="selected"</#if>>bombCoverX</option>
			            <option value="bombCoverReverseX" <#if ad_type.effect?? && ad_type.effect == "bombCoverReverseX">selected="selected"</#if>>bombCoverReverseX</option>
			            <option value="bombCoverInX" <#if ad_type.effect?? && ad_type.effect == "bombCoverInX">selected="selected"</#if>>bombCoverInX</option>
			            <option value="bombCoverOutX" <#if ad_type.effect?? && ad_type.effect == "bombCoverOutX">selected="selected"</#if>>bombCoverOutX</option>
			            <option value="bombY" <#if ad_type.effect?? && ad_type.effect == "bombY">selected="selected"</#if>>bombY</option>
			            <option value="bombCoverY" <#if ad_type.effect?? && ad_type.effect == "bombCoverY">selected="selected"</#if>>bombCoverY</option>
			            <option value="bombCoverReverseY" <#if ad_type.effect?? && ad_type.effect == "bombCoverReverseY">selected="selected"</#if>>bombCoverReverseY</option>
			            <option value="bombCoverInY" <#if ad_type.effect?? && ad_type.effect == "bombCoverInY">selected="selected"</#if>>bombCoverInY</option>
			            <option value="bombCoverOutY" <#if ad_type.effect?? && ad_type.effect == "bombCoverOutY">selected="selected"</#if>>bombCoverOutY</option>
			        </optgroup>
                </select>
             </div>
             <span class="Validform_checktip">*显示的广告切换效果</span>
        </dd>
    </dl>
       <#--
    <dl>
        <dt>显示数量</dt>
        <dd>
            <input name="totalShows" type="text" value="<#if ad_type??>${ad_type.totalShows!""}</#if>" class="input txt100" datatype="n1-10" sucmsg=" ">
            <span class="Validform_checktip">*显示的广告数量</span>
        </dd>
    </dl>
 
    <dl>
        <dt>价格</dt>
        <dd>
            <input name="price" type="text" value="<#if ad_type?? && ad_type.price??>${ad_type.price?string("0.00")}</#if>" class="input txt100" datatype="n0-100|." sucmsg=" ">
            <span class="Validform_checktip">元/月</span>
        </dd>
    </dl>
   -->
    <dl>
        <dt>宽度</dt>
        <dd>
            <input name="width" type="text" value="<#if ad_type??>${ad_type.width?c!''}</#if>" class="input txt100" datatype="n1-100" sucmsg=" ">
            <span class="Validform_checktip">*广告宽度</span>
        </dd>
    </dl>
    <dl>
        <dt>高度</dt>
        <dd>
            <input name="height" type="text" value="<#if ad_type??>${ad_type.height?c!''}</#if>" class="input txt100" datatype="n1-100" sucmsg=" ">
            <span class="Validform_checktip">*广告高度</span>
        </dd>
    </dl>
   
    <dl>
        <dt>排序数字</dt>
        <dd>
            <input name="sortId" type="text" value="<#if ad_type??>${ad_type.sortId!""}<#else>99</#if>" class="input txt100" datatype="n" sucmsg=" ">
            <span class="Validform_checktip">*数字，越小越向前</span>
        </dd>
    </dl>
    <dl>
        <dt>备注</dt>
        <dd>
            <textarea name="mark" rows="2" cols="20" class="input" datatype="*0-255" sucmsg=""><#if ad_type??>${ad_type.mark!""}</#if></textarea>
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
</body>
</html>