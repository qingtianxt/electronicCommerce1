<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://localhost:8080//electronicCommerce1/util" prefix="util"%>
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

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<a class="navbar-brand" target="_parent"
				href="${pageContext.request.contextPath}/product?method=sort&id=0">EShop</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a
					href="${pageContext.request.contextPath}/admin/front/shopping/shoppingServlet?method=toCart">预留连接
						<span class="sr-only">(current)</span>
				</a></li>
			</ul>
			<form class="navbar-form navbar-left"
				action="${pageContext.request.contextPath}/product?method=search"
				method="post" role="search"><!-- 一个小的表单 -->
				<div class="form-group">
					<input type="text" name="key" class="form-control"
						placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">搜索</button>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li><a>用户名： ${userBean.account}</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">设置<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a target="_parent"
							href="${pageContext.request.contextPath}/front/main.jsp">账
								户信息</a></li>
						<li><a target="_parent"
							href="${pageContext.request.contextPath}/cart?method=toCart">购物车</a></li>
						<li><a href="#">Something else here</a></li>
						<li role="separator" class="divider"></li>
						<li><a target="_parent" href="user?method=reg">注销</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>
	<util:type path="${pageContext.request.contextPath}"/>
