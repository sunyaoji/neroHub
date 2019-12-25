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
$(function() {
	var error=$("#msg").html();
	if(error=="失败"){
		alert("缴费失败!");
	}
	else if(error=="成功"){
		alert("缴费成功!");
	}else{}
})
function borrow(b) {
	 var d1=new Date();
	 var d2=new Date(d1);
	 d2.setDate(d1.getDate()+30);
	 var lendtime=d1.getFullYear()+"-0"+(parseInt(d1.getMonth())+1)+"-"+d1.getDate()+" "+d1.getHours()+":"+d1.getMinutes()+":"+d1.getSeconds();
	 var expiretime=d2.getFullYear()+"-0"+(parseInt(d2.getMonth())+1)+"-"+d2.getDate()+" "+d2.getHours()+":"+d2.getMinutes()+":"+d2.getSeconds();
	 var bookid=$(b).parent().parent().parent().children().eq(0).html();
	var userid=$("#account").html();
	location="http://localhost:8080/Library/Borrow.do?userid="+userid+"&bookid="+bookid+"&lendtime="+lendtime+"&expiretime="+expiretime;
}
function pay() {
	var owing=$("#owing").html();
	var d1=new Date();
	var changetime=d1.getFullYear()+"-0"+(parseInt(d1.getMonth())+1)+"-"+d1.getDate()+" "+d1.getHours()+":"+d1.getMinutes()+":"+d1.getSeconds();
	var userid=$("#account").html();
	location="http://localhost:8080/Library/Pay.do?userid="+userid+"&type=2&changetime="+changetime+"&money="+owing;
}
</script>
	<title>后台欢迎页</title>
	<link rel="stylesheet" href="css/reset.css" />
	<link rel="stylesheet" href="css/content.css" />
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">图书管理</a>><a href="">逾期缴费</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>逾期缴费</h3>
			</div>
			<span id="userid" style="display: none"></span>
			<span id="account" style="display: none">${user.userid }</span>
			<div class="public-content-cont" id="list">
				<table class="public-cont-table">
					<tr>
						<th style="width:60%">您的欠费金额</th>
						<th style="width:40%">操作</th>
					</tr>
					<tr>
					<td id="owing">${owing}</td>
						<td>
							<div class="table-fun">
								<a onclick="pay()">还款</a>
							</div>
						</td>
					</tr>
				</table>
				<span style="display: none" id="msg">${msg}</span>
			</div>
			
		</div>
	</div>
</body>
</html>