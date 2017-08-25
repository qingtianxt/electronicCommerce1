<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPEhtmlPUBLIC"-//W3C//DTD HTML 4.01
Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
<script
	src="${pageContext.request.contextPath}/static/js/jquery.validate.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/myValidate.js"
	type="text/javascript"></script>
</head>
<body>
	<div class="container">
		<div class="row-fluid">
			<div class="col-md-12">
				<h2>欢迎注册</h2>
			</div>
		</div>
	</div>
	<hr />
	<div class="container">
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<form enctype="multipart/form-data"
					action="${pageContext.request.contextPath}/user?method=add"
					method="post" id="checkForm">
					<div class="form-group">
						<label for="account">用户名： </label><input type="text"
							class="form-control" name="account" id="account"
							placeholder="请输入您的手机号">
					</div>
					<div class="form-group">
						<label for="inputPassword">密码： </label><input type="password"
							class="form-control" name="password" id="inputPassword"
							placeholder="password">
					</div>
					<div class="form-group">
						<label for="password2">重新输入密码：</label><input type="password"
							class="form-control" name="password2" id="password2"
							placeholder="password">
					</div>
					<div class="form-group">
						<label for="nickname">昵称： </label><input type="text"
							class="form-control" name="nickname" id="nickname"
							placeholder="nickname">
					</div>
					<div class="form-group">
						<label for="truename">真实姓名：</label><input type="text"
							class="form-control" name="truename" id="truename"
							placeholder="truename">
					</div>
					<div class="form-group">
						<label>性别： </label><input name="sex" type="radio" value="男" checked />男
						<input name="sex" type="radio" value="女" />女
					</div>
					<div class="form-group">
						<label for="pic">头像： </label><input type="file"
							class="form-control" name="pic" id="pic" placeholder="pic" />
					</div>
					<div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">@</span>
					  <input type="text" name="email" id="email" class="form-control" placeholder="email" aria-describedby="basic-addon1">
					</div>
					<div class="row-fluid">
						<div class="col-md-3"></div>
						<div class="col-md-3">
							<button type="submit" class="btn btn-default">提交</button>
						</div>
						<div class="col-md-3">
							<a type="submit" class="btn btn-default" href="${pageContext.request.contextPath }/user?method=loginUI">返回登录</a>
						</div>
					</div>
					
					<div class="row-fluid">
						<div class="col-md-12">
								<div class="alert alert-success" role="alert">
									<span style="margin-left: 75px;">${msg }</span>
								</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>