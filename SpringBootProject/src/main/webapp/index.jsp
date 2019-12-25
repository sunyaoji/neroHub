<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	String path=request.getContextPath();
	String basepath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basepath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="script/jquery-1.8.1.min.js"></script>
<script type="text/javascript">
function tuichu() {
	location="http://localhost:8080/Springmvc/Tuichu.do";
}
$(function() {
	if($("#quanerror").html()=="失败"){
		alert("您不是管理员没有该权限!");
	}
})

function showuser() {
	if($("#uname").html()=="admin"){
		location="http://localhost:8080/Springmvc/ShowUser.do";
	}else if($("#uname").html()==undefined){
		location="http://localhost:8080/Springmvc/Tuichu.do";
	}
	else{
		var userid=$("#userid").html();
		var username=$("#username").html();
		var password=$("#password").html();
		location="http://localhost:8080/Springmvc/Change.do?userid="+userid+"&username="+username+"&password="+password;
	}
}
</script>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/public.css">
</head>
<body>
<div class="public-header-warrp">
	<div class="public-header">
		<div class="content">
			<div class="public-header-logo"><a href=""><i>LOGO</i>
			<h3>aowin</h3></a></div>
			<span style="display: none" id="quanerror">${quanerror}</span>
			<span style="display: none" id="userid">${user.userid}</span>
			<span style="display: none" id="username">${user.username}</span>
			<span style="display: none" id="password">${user.password}</span>
			<div class="public-header-admin fr">
			访问人数是:${visit}
			<c:if test="${user!=null}">
				<p class="admin-name" id="uname">${user.username}</p> 您好！</c:if>
			<c:if test="${user==null}">
				<p class="admin-name">您还未登录</p></c:if>
				<div class="public-header-fun fr">
					<a href="temp.htm" class="public-header-man">管理</a>
					<a class="public-header-loginout" onclick="tuichu()">安全退出</a>	
				</div>
			</div>
		</div>
	</div>
</div>
<div class="clearfix"></div>
<!-- 内容展示 -->
<div class="public-ifame mt20">
	<div class="content">
	<!-- 内容模块头 -->
		
		<div class="clearfix"></div>
		<!-- 左侧导航栏 -->
		<div class="public-ifame-leftnav">
			<div class="public-title-warrp">
				<div class="public-ifame-title ">
					<a href="">首页</a>
				</div>
			</div>
			<ul class="left-nav-list">
				<li class="public-ifame-item">
					<a href="javascript:;">用户操作</a>
					<div class="ifame-item-sub">
						<ul>
							<li class="active"><a href="ShowBook.do" target="content">借书</a></li>
							<li><a href="ShowBorrow.do?userid=${user.userid}" target="content"">还书</a></li>
							<li><a href="ShowOwing.do?userid=${user.userid}" target="content">逾期缴费</a></li>
						</ul>
					</div>
				</li>	
			</ul>
		</div>
		<!-- 右侧内容展示部分 -->
		<div class="public-ifame-content">
		<iframe name="content" src="main.jsp" frameborder="0" id="mainframe" scrolling="yes" marginheight="0" marginwidth="0" width="100%" style="height: 700px;"></iframe>
		</div>
	</div>
</div>
<script src="js/jquery.min.js"></script>
<script>
$().ready(function(){
	var item = $(".public-ifame-item");

	for(var i=0; i < item.length; i++){
		$(item[i]).on('click',function(){
			$(".ifame-item-sub").hide();
			if($(this.lastElementChild).css('display') == 'block'){
				$(this.lastElementChild).hide()
				$(".ifame-item-sub li").removeClass("active");
			}else{
				$(this.lastElementChild).show();
				$(".ifame-item-sub li").on('click',function(){
					$(".ifame-item-sub li").removeClass("active");
					$(this).addClass("active");
				});
			}
		});
	}
});
</script>
</body>
</html>