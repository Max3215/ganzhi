<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>查看订单信息</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
        $(function () {
            $("#btnConfirm").click(function () { OrderConfirm(); });   //确认订单
            $("#btnPayment").click(function () { OrderPayment(); });   //确认付款

            $("#btnHDFKPayment").click(function () { OrderHDFKPayment(); });   //确认货到付款已付款
            $("#btnOrderExpress").click(function () { OrderExpress(); });   //确认发货
            $("#btnOrderComplete").click(function () { OrderComplete(); }); //完成订单
            $("#btnCancel").click(function () { OrderCancel(); });     //取消订单
            // $("#btnInvalid").click(function () { OrderInvalid(); });   //作废订单
            $("#btnPrint").click(function () { OrderPrint(); });       //打印订单
            $("#btnOrderReceive").click(function () { OrderReceive(); }); //确认收货
            $("#btnHDFKReceive").click(function () { OrderHDFKReceive(); }); //货到付款确认收货
            $("#btnEditAcceptInfo").click(function () { EditAcceptInfo(); }); //修改收货信息
            $("#btnEditRemark").click(function () { EditOrderRemark(); });    //修改订单备注
            $("#btnEditRealAmount").click(function () { EditRealAmount(); }); //修改商品总金额
            $("#btnEditExpressFee").click(function () { EditExpressFee(); }); //修改配送费用
            $("#btnEditPaymentFee").click(function () { EditPaymentFee(); }); //修改支付手续费
        });

        //确认收货
        function OrderReceive() {
            var dialog = $.dialog.confirm('该步骤将确认送餐，确认要继续吗？', function () {
                var orderNumber = $.trim($("#spanOrderNumber").text());
                var postData = { "orderNumber": orderNumber, "type": "orderReceive" };
                //发送AJAX请求
                sendAjaxUrl(dialog, postData, "/Verwalter/order/param/edit");
                return false;
            });

        }
        //货到付款 确认收货
        function OrderHDFKReceive() {
            var dialog = $.dialog.confirm('该步骤将确认收货，确认要继续吗？', function () {
                var orderNumber = $.trim($("#spanOrderNumber").text());
                var postData = { "orderNumber": orderNumber, "type": "orderReceive" };
                //发送AJAX请求
                sendAjaxUrl(dialog, postData, "/Verwalter/order/param/edit");
                return false;
            });

        }

        //确认订单
        function OrderConfirm() {
            var dialog = $.dialog.confirm('确认要继续吗？', function () {
                var orderNumber = $.trim($("#spanOrderNumber").text());
                var postData = { "id": id, "type": "orderConfirm" };
                //发送AJAX请求
                sendAjaxUrl(dialog, postData, "/Verwalter/order/param/edit");
                return false;
            });
        }
        
        //确认付款
        function OrderPayment() {
            var dialog = $.dialog.confirm('操作提示信息：<br />1、该订单使用在线支付方式，付款成功后自动确认；<br />2、如客户确实已打款而没有自动确认可使用该功能；<br />3、确认付款后无法修改金额，确认要继续吗？', function () {
                var orderNumber = $.trim($("#spanOrderNumber").text());
                var postData = { "orderNumber": orderNumber, "type": "orderPay" };
                //发送AJAX请求
                sendAjaxUrl(dialog, postData, "/Verwalter/order/param/edit");
                return false;
            });
        }

        //修改订单备注
        function EditOrderRemark() {
            var dialog = $.dialog({
                title: '订单备注',
                content: '<textarea id="orderRemark" name="txtOrderRemark" rows="2" cols="20" class="input">${course.remarkInfo!''}</textarea>',
                min: false,
                max: false,
                lock: true,
                ok: function () {
                    var remark = $("#orderRemark", parent.document).val();
                    if (remark == "") {
                        $.dialog.alert('对不起，请输入订单备注内容！', function () { }, dialog);
                        return false;
                    }
                    var id = $.trim($("#spanOrderNumber").text());
                    var postData = { "id": id, "type": "editMark", "data": remark };
                    //发送AJAX请求
                    sendAjaxUrl(dialog, postData, "/Verwalter/order/param/edit");
                    return false;
                },
                cancel: true
            });
        }
        //确认货到付款已付款
        function OrderHDFKPayment() {
            var dialog = $.dialog.confirm('操作提示信息：<br />1、该订单使用货到付款方式，确定后，将完成订单；<br />确认要继续吗？', function () {
                var orderNumber = $.trim($("#spanOrderNumber").text());
                var postData = { "orderNumber": orderNumber, "type": "orderPayOffline" };
                //发送AJAX请求
                sendAjaxUrl(dialog, postData, "/Verwalter/order/param/edit");
                return false;
            });
        }
        //确认发货
        function OrderExpress() {
            var orderNumber = $.trim($("#spanOrderNumber").text());
            var dialog = $.dialog({
                title: '确认发货',
                content: 'url:/Verwalter/order/dialog/delivery?orderNumber=' + orderNumber,
                min: false,
                max: false,
                lock: true,
                width: 450,
                height:350
            });
        }
        
        //确认完成
        function OrderComplete() {
             var dialog = $.dialog.confirm('确认已完成？', function () {
                var id = $.trim($("#spanOrderNumber").text());
                var postData = { "id": id, "type": "orderFinish" };
                //发送AJAX请求
                sendAjaxUrl(dialog, postData, "/Verwalter/order/param/edit");
                return false;
            });
        }

        //取消订单
        function OrderCancel() {
            var dialog = $.dialog({
                title: '取消订单',
                content: '确认取消吗？',
                min: false,
                max: false,
                lock: true,
                icon: 'confirm.gif',
                button: [{
                    name: '直接取消',
                    callback: function () {
                        var id = $.trim($("#spanOrderNumber").text());
                        var postData = { "id": id, "type": "orderCancel" };
                        //发送AJAX请求
                        sendAjaxUrl(dialog, postData, "/Verwalter/order/param/edit");
                        return false;
                    },
                    focus: true
                }, {
                    name: '关闭'
                }]
            });

        }
        //作废订单
        function OrderInvalid() {
            var dialog = $.dialog({
                title: '取消订单',
                content: '操作提示信息：<br />1、设为作废订单，将不可逆转；<br />2、会员用户，自动检测退还金额或积分到账户；<br />3、请单击相应按钮继续下一步操作！',
                min: false,
                max: false,
                lock: true,
                icon: 'confirm.gif',
                button: [{
                    name: '检测退还',
                    callback: function () {
                        var order_no = $.trim($("#spanOrderNumber").text());
                        var postData = { "order_no": order_no, "edit_type": "order_invalid", "check_revert": 1 };
                        //发送AJAX请求
                        sendAjaxUrl(dialog, postData, "../../tools/Verwalter_ajax.ashx?action=edit_order_status");
                        return false;
                    },
                    focus: true
                }, {
                    name: '直接作废',
                    callback: function () {
                        var order_no = $.trim($("#spanOrderNumber").text());
                        var postData = { "order_no": order_no, "edit_type": "order_invalid", "check_revert": 0 };
                        //发送AJAX请求
                        sendAjaxUrl(dialog, postData, "../../tools/Verwalter_ajax.ashx?action=edit_order_status");
                        return false;
                    }
                }, {
                    name: '关闭'
                }]
            });
        }
        //打印订单
        function OrderPrint() {
            var dialog = $.dialog({
                title: '打印订单',
                content: 'url:/Verwalter/order/dialog/print?orderNumber=' + $.trim($("#spanOrderNumber").text()),
                min: false,
                max: false,
                lock: true,
                width: 850//,
                // height: 500
            });
        }
        //修改收货信息
        function EditAcceptInfo() {
            var dialog = $.dialog({
                title: '修改收货信息',
                content: 'url:/Verwalter/order/dialog/contact',
                min: false,
                max: false,
                lock: true,
                width: 550,
                height: 320
            });
        }
        //=================================工具类的JS函数====================================
        //检查是否货币格式
        function checkIsMoney(val) {
            var regtxt = /^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/;
            if (!regtxt.test(val)) {
                return false;
            }
            return true;
        }
        //发送AJAX请求
        function sendAjaxUrl(winObj, postData, sendUrl) {
            $.ajax({
                type: "post",
                url: sendUrl,
                data: postData,
                dataType: "json",
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    $.dialog.alert('尝试发送失败，错误信息：' + errorThrown, function () { }, winObj);
                },
                success: function (data) {
                    if (data.code == 0) {
                        winObj.close();
                        $.dialog.tips(data.msg, 2, '32X32/succ.png', function () { location.reload(); }); //刷新页面
                    } else {
                        $.dialog.alert('错误提示：' + data.message, function () { }, winObj);
                    }
                }
            });
        }
    </script>
</head>
<body class="mainbody"><div style="position: absolute; left: -9999em; top: 236px; visibility: visible; width: auto; z-index: 1976;"><table class="ui_border ui_state_visible ui_state_focus"><tbody><tr><td class="ui_lt"></td><td class="ui_t"></td><td class="ui_rt"></td></tr><tr><td class="ui_l"></td><td class="ui_c"><div class="ui_inner"><table class="ui_dialog"><tbody><tr><td colspan="2"><div class="ui_title_bar"><div class="ui_title" unselectable="on" style="cursor: move;">视窗 </div><div class="ui_title_buttons"><a class="ui_min" href="javascript:void(0);" title="最小化" style="display: inline-block;"><b class="ui_min_b"></b></a><a class="ui_max" href="javascript:void(0);" title="最大化" style="display: inline-block;"><b class="ui_max_b"></b></a><a class="ui_res" href="javascript:void(0);" title="还原"><b class="ui_res_b"></b><b class="ui_res_t"></b></a><a class="ui_close" href="javascript:void(0);" title="关闭(esc键)" style="display: inline-block;">×</a></div></div></td></tr><tr><td class="ui_icon" style="display: none;"></td><td class="ui_main" style="width: auto; height: auto;"><div class="ui_content" style="padding: 10px;"><div class="ui_loading"><span>loading...</span></div></div></td></tr><tr><td colspan="2"><div class="ui_buttons" style="display: none;"></div></td></tr></tbody></table></div></td><td class="ui_r"></td></tr><tr><td class="ui_lb"></td><td class="ui_b"></td><td class="ui_rb" style="cursor: se-resize;"></td></tr></tbody></table></div>
<form name="form1" method="post" action="/Verwalter/order/save" id="form1">
    <!--导航栏-->
    <div class="location" style="position: fixed; top: 0px;">
        <a href="/Verwalter/order/list/${statusId!"0"}" class="back"><i></i><span>返回列表页</span></a>
        <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
        <i class="arrow"></i>
        <a href="/Verwalter/order/list/${statusId!"0"}"><span>订单管理</span></a>
        <i class="arrow"></i><span>订单详细</span>
    </div>
    <div class="line10">
    </div>
    <!--/导航栏-->
    <!--内容-->
    <div class="content-tab-wrap">
        <div id="floatHead" class="content-tab" style="position: static; top: 52px;">
            <div class="content-tab-ul-wrap">

            </div>
        </div>
    </div>
    <div class="tab-content">   
    <span style="display:none;" id="spanOrderNumber">${course.id!'' }</span> 
        <dl>
            <dt>课程</dt>
            <dd>
                <table border="0" cellspacing="0" cellpadding="0" class="border-table" width="50%">
                    <thead>
                        <tr>
                            <th width="8%">
                                课程ID
                            </th>
                            <th width="15%">
                                课程名称
                            </th>                          
                        </tr>
                    </thead>
                    <tbody>                       
                        <tr class="td_c">
                            <td>${course.courseId!""}</td>
                            <td style="text-align: center; white-space: normal;">
                                ${course.courseName!""} 
                            </td>                                    
                        </tr>
                    </tbody> 
                </table> 
            </dd> 
        </dl>
        
        <dl>
            <dt>预约客户信息</dt>
            <dd>
                <table border="0" cellspacing="0" cellpadding="0" class="border-table" width="98%">
                    <tbody>
                    
                    
                    <tr>
                        <th width="20%">
                            姓名
                        </th>
                        <td>
                            <div class="position">
                                <span id="spanRealAmountValue">
                                    ${course.name!""} 
                                </span> 
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th>
                           联系电话
                        </th>
                        <td>
                            <div class="position">
                                <span id="spanExpressFeeValue">
                                     ${course.mobile!""} 
                                </span> 
                            </div>
                        </td>
                    </tr>
                
                    <tr>
                        <th>
         QQ
                        </th>
                        <td>
                            <div class="position">
                                <span id="spanPaymentFeeValue">
                                 ${course.qq!""} 
                                </span> 

                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th>
                           邮箱
                        </th>
                        <td>
                            <div class="position">
                                ${course.mail!""} 
                            </div>
                        </td>
                    </tr>            
                    <tr>
                        <th>
                           预约时间
                        </th>
                        <td>
                            <div class="position">
                                ${course.time!""} 
                            </div>
                        </td>
                    </tr>       
                </tbody>
                </table>
            </dd>
        </dl>    
        <dl>
            <dt>备注</dt>
            <dd>
                
                <table border="0" cellspacing="0" cellpadding="0" class="border-table" width="98%">
                    <tbody>
                    
                    
                    <tr>
                        <th  width="20%">
                            用户留言
                        </th>
                        <td>${course.content!""}</td>
                    </tr>
                    <tr>
                        <th valign="top">
                            订单备注
                        </th>
                        <td>
                            <div class="position">
                                <div>${course.remarkInfo!""}</div>
                                <input name="btnEditRemark" type="button" id="btnEditRemark" class="ibtn" value="修改" style="margin-top: -3px;">
                            </div>
                        </td>
                    </tr>
                </tbody></table>
            </dd>
        </dl>
        
    </div>
    <!--/内容-->
    <!--工具栏-->
    <div class="page-footer">
        <div class="btn-list">
            <#if course.statusId==1>
                <input type="button" id="btnOrderComplete" value="确认处理" class="btn">
                <input type="button" id="btnCancel" value="取消订单" class="btn green">
            </#if>
            <input type="button" value="返回上一页" class="btn yellow" onclick="javascript:history.back(-1);">
        </div>
        <div class="clear">
        </div>
    </div>
    <!--/工具栏-->
    </form>


</body></html>