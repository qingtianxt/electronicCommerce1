<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC"-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>XXX后台管理系统</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
</head>
<frameset frameborder="no" rows="82px, *"><!-- frameborder是设置有无边框，0表示没有边框，1表示有 -->
    <frame src="${pageContext.request.contextPath}/admin/head.jsp">
	<frameset  cols="285px, *"> 
		<frame src="${pageContext.request.contextPath}/admin/left.jsp">
		<frame  name="mainAction">
	</frameset>
</frameset>
<!-- 使用frameset将页面分成三个页面， -->
</html>    