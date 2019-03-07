/*$(document).ready(function(){

    var toggleSelect = function()
    {
        $.ajax({
            type:"post",
            url:"/cart/toggleSelect",
            data:{"id":$(this).attr("cgid")},
            success:function(data){
                $(".page-main").html(data);
                
                initClicks();
            }
        });
    }
    
    var toggleAll = function()
    {
        $.ajax({
            type:"post",
            url:"/cart/toggleAll",
            data:{"sid":$(this).attr("sid")},
            success:function(data){
                $(".page-main").html(data);
                
                initClicks();
            }
        });
    }
    
    var numIncrease = function()
    {
        $.ajax({
            type:"post",
            url:"/cart/numberAdd",
            data:{"id":$(this).attr("cgid")},
            success:function(data){
                $(".page-main").html(data);
                
                initClicks();
            }
        });
    }
    
    var numDecrease = function()
    {
        $.ajax({
            type:"post",
            url:"/cart/numberMinus",
            data:{"id":$(this).attr("cgid")},
            success:function(data){
                $(".page-main").html(data);
                
                initClicks();
            }
        });
    }
    
    var delGoods = function()
    {
        $.ajax({
            type:"post",
            url:"/cart/del",
            data:{"id":$(this).attr("cgid")},
            success:function(data){
                $(".page-main").html(data);
                
                initClicks();
            }
        });
    }
    
    var initClicks = function(){
        // 点击商品选择框
        $(".duoxuank").click(toggleSelect);
        // 点击全选
        $("#all-select").click(toggleAll);
        // 数量加1
        $(".num_add").click(numIncrease);
        // 数量减1
        $(".num_minus").click(numDecrease);
        // 点击删除
        $(".gwc_delete").click(delGoods);
    }
    
    initClicks();
});
*/

// 点击复选框
function toggleSelect(id)
{
    $.ajax({
        type:"post",
        url:"/touch/cart/toggleSelect",
        data:{"id":id},
        success:function(data){
            $(".page-main").html(data);
        }
    });
}

// 点击全选框
function toggleAllSelect(sid)
{
    $.ajax({
        type:"post",
        url:"/touch/cart/toggleAll",
        data:{"sid":sid},
        success:function(data){
            $(".page-main").html(data);
        }
    });
}

// 商品数量加1
function addNum(id)
{
    $.ajax({
        type:"post",
        url:"/touch/cart/numberAdd",
        data:{"id":id},
        success:function(data){
            $(".page-main").html(data);
        }
    });
}

// 商品数量减1
function minusNum(id)
{
    $.ajax({
        type:"post",
        url:"/touch/cart/numberMinus",
        data:{"id":id},
        success:function(data){
            $(".page-main").html(data);
        }
    });
}

function delCartItem(id)
{
    if (null == id)
    {
        return;
    }
    
    $.ajax({
        type:"post",
        url:"/touch/cart/del",
        data:{"id": id},
        success:function(data){
            $(".page-main").html(data);
        }
    });
}

function goNext(goodsNum)
{
    if (0==goodsNum)
    {
        alert("请至少选择一种商品!");
        return false;
    }
    window.location.href="/touch/order/info";
}