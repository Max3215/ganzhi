//文章内容 zhangji
function selectContent(id,mid)
{ 
    $(".menu_list").removeClass("sel");
    $("#list"+id).addClass("sel");
    
    $.ajax({
        type:"post",
        url:"/info/list/content/select",
        data:{"id":id,"mid":mid},
        success:function(data){            	
            $(".right_content").html(data);
        }       
    });
}

//选择栏目 zhangji
function selectEntry(id,mid)
{ 
    $(".menu_list").removeClass("leftbar_color");
    $("#list"+id).addClass("leftbar_color");
    
    $.ajax({
        type:"post",
        url:"/info/entry/select",
        data:{"id":id,"mid":mid},
        success:function(data){             
            $(".right_content").html(data);
        }       
    });
}

//选择栏目 zhangji
function selectCat(mid,catId)
{ 
    $(".menu_list").removeClass("sel");
    $("#list"+catId).addClass("sel");
	
    $.ajax({
        type:"post",
        url:"/info/list/select",
        data:{"mid":mid,"catId":catId},
        success:function(data){            	
            $(".right_content").html(data);
        }       
    });
}
//点击文章进入课程报名     ->mdj 2015-8-27 09:59:45<-
function courseTake(id,mid)
{
	$.ajax({
        type:"post",
        url:"/info/coursechoose",
        data:{"id":id,"mid":mid},
        success:function(data){       	
            $(".right_content").html(data);
        }       
    });
}

//翻页 zhangji
function page(mid,catId,eventTarget, eventArgument)
{ 
    $(".menu_list").removeClass("leftbar_color");
    $("#list"+catId).addClass("leftbar_color");
    $('html,body').animate({scrollTop:146},400);
    $.ajax({
        type:"post",
        url:"/info/list/select",
        data:{"mid":mid,
        	  "catId":catId,
        	  "__EVENTTARGET":eventTarget,
        	  "__EVENTARGUMENT":eventArgument},
        success:function(data){             
            $(".right_content").html(data);
        }       
    });
}

//跳页 zhangji
function pageJump(mid,catId,eventTarget)
{ 
    $(".menu_list").removeClass("leftbar_color");
    $("#list"+catId).addClass("leftbar_color");
    $('html,body').animate({scrollTop:146},400);
    var eventArgument = $("#jump-page").val() - 1;
    $.ajax({
        type:"post",
        url:"/info/list/select",
        data:{"mid":mid,
        	  "catId":catId,
        	  "__EVENTTARGET":eventTarget,
        	  "__EVENTARGUMENT":eventArgument},
        success:function(data){             
            $(".right_content").html(data);
        }       
    });
}
//[全部]分类跳页 zhangji
function pageJump2(mid,eventTarget)
{ 
	$('html,body').animate({scrollTop:146},400);
    var eventArgument = $("#jump-page").val() - 1;
    $.ajax({
        type:"post",
        url:"/info/list/select",
        data:{"mid":mid,
        	  "__EVENTTARGET":eventTarget,
        	  "__EVENTARGUMENT":eventArgument},
        success:function(data){             
            $(".right_content").html(data);
        }       
    });
}

//[全部]分类翻页
function page2(mid,eventTarget, eventArgument)
{   
    $('html,body').animate({scrollTop:146},400);
    $.ajax({
        type:"post",
        url:"/info/list/select",
        data:{"mid":mid,
              "__EVENTTARGET":eventTarget,
              "__EVENTARGUMENT":eventArgument},
        success:function(data){             
            $(".right_content").html(data);
        }       
    });
    
}