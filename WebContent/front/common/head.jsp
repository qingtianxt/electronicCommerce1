<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script
	src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<link
	href="${pageContext.request.contextPath }/static/typeList/typeList.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/typeList/typeList.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/js/bootstrap-spinner.min.js"></script>
<nav class="navbar navbar-default" role="navigation">
	<!-- Brand and toggle get grouped for better mobile display -->
	
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-ex1-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="#">Brand</a>
	</div>

	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse navbar-ex1-collapse">

		<form class="navbar-form navbar-left" role="search">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search">
			</div>
			<button type="submit" class="btn btn-default">提交</button>
		</form>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="${pageContext.request.contextPath }/front/productShow/list.jsp" target="_parent">首页 </a></li>
			<li><a>用户名: ${userBean.account}</a></li>
			<li><a href="${pageContext.request.contextPath}/order?method=listByPage&currPage=1" target="_top">订单中心</a></li>
			<li><a href="${pageContext.request.contextPath }/user?method=reg" target="_top">退出登录 </a></li>
			<li><a href="${pageContext.request.contextPath }/cart?method=toCart" target="_top">我的购物车 </a></li>
		</ul>
	</div>
	<!-- /.navbar-collapse -->

</nav>