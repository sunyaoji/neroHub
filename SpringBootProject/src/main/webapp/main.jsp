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
<title>Insert title here</title>
</head>
<body>
您好!欢迎!<br/><br/><br/>
您的名字是:${user.username }<br/><br/><br/>
您的卡号是:${user.idcard }<br/><br/><br/>
您的电话号码:${user.phone}<br/><br/><br/>
您的状态:
<c:if test="${user.status==1 }">
正常
</c:if>
<c:if test="${user.status==-1 }">
锁定
</c:if>
<br/><br/><br/>
您的欠费金额为:${user.owing_money }<br/><br/><br/>
</body>
</html>