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
		alert("新增失败!");
	}
	else if(error=="成功"){
		alert("还书成功!");
	}else{}
})

$(function() {
	var error=$("#error2").html();
	if(error=="失败"){
		alert("该用户还有新闻存在，修改失败!");
	}
	else if(error=="成功"){
		alert("修改成功!");
	}else{}
})

$(function() {
	var error=$("#error3").html();
	if(error=="失败"){
		alert("该用户还有新闻存在,删除失败!");
	}
	else if(error=="成功"){
		alert("删除成功!");
	}else{}
})

function returnbook() {
	var d1=new Date();
	var returntime=d1.getFullYear()+"-0"+(parseInt(d1.getMonth())+1)+"-"+d1.getDate()+" "+d1.getHours()+":"+d1.getMinutes()+":"+d1.getSeconds();
	var checked=$("input:checked");
	var userid=$("#account").html();
	if(checked.size()!=0){
	var Str="http://localhost:8080/Library/Return.do?userid="+userid+"&returntime="+returntime+"&changetime="+returntime+"&type=1&";
	checked.each(function() {
		var bookid=$(this).parent().parent().children().eq(1).html();
		Str=Str+"bookid="+bookid+"&";
	})
	location=Str;}else{}
}
</script>
	<title>后台欢迎页</title>
	<link rel="stylesheet" href="css/reset.css" />
	<link rel="stylesheet" href="css/content.css" />
</head>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">您当前的位置：<a href="">图书管理</a>><a href="">图书归还</a></div>
		<div class="public-content">
			<div class="public-content-header">
				<h3>图书归还</h3>
			</div>
			<span id="userid" style="display: none"></span>
			<span id="account" style="display: none">${user.userid }</span>
			<div class="public-content-cont" id="list">
				<table class="public-cont-table">
					<tr>
						<th style="width:5%">选择</th>
						<th style="width:15%">图书编号</th>
						<th style="width:15%">图书名称</th>
						<th style="width:15%">图书作者</th>
						<th style="width:15%">图书价格</th>
						<th style="width:15%">出版社</th>
						<th style="width:15%">剩余免费天数</th>
					</tr>
					<c:forEach items="${pagelist}" var="n">
					<tr>
					<td><input type="checkbox"/></td>
					<td style="display: none">${n.bookid}</td>
					<td>${n.bookcode}</td>
						<td>${n.bookname}</td>
						<td>${n.author}</td>
						<td>${n.price}</td>
						<td>${n.publisher}</td>
						<td>${n.freeday}</td>
					</tr>
					</c:forEach>
				</table>
				<div align="right">
				<input type="button" value="还书" onclick="returnbook()"/> 
				</div>
				<span style="display: none" id="msg">${msg}</span>
				<span style="display: none" id="error2">${error2}</span>
				<span style="display: none" id="error3">${error3}</span>
				<div class="page">
					<form action="" method="get">
					<c:if test="${page!=0}">
					共${booklist.size()}本图书
						<a	 href="ShowBorrow.do?page=1">首页</a>				
						<c:if test="${page!=1 }">
						<a href="ShowBorrow.do?page=${page-1 }">上一页</a>
						</c:if>
						<c:if test="${page!=totalpage}">
						<a href="ShowBorrow.do?page=${page+1 }">下一页</a>
						</c:if>
						第<span style="color:red;font-weight:600">${page}</span>页
						共<span style="color:red;font-weight:600">${totalpage}</span>页
							</c:if>
					</form>
				</div>
			</div>
			<div class="public-content-cont" style="display: none" id="updata">
			<form action="" method="post">
			<div class="form-group">用户姓名
				  <input class="form-input-txt" type="text" name="Dream_SiteName" value="" id="username"/>
				</div>
				<div class="form-group">用户密码
				  <input class="form-input-txt" type="text" name="Dream_SiteName" value="" id="password"/>
				</div>
				<div class="clearfix"></div>
				<div class="form-group" style="margin-left:150px;">
					<input type="button" class="sub-btn" value="提  交" onclick="send()"/>
					<input type="reset" class="sub-btn" value="重  置" />
				</div>
				</form>
			</div>
			
		</div>
	</div>
</body>
</html>