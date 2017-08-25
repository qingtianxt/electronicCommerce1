<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://localhost:8080//electronicCommerce1/util"
	prefix="util"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品展示</title>
<style>
.ima {
	width: 250px;
	height: 180px;
	background-repeat: no-repeat;
	background-size: cover;
}

.product {
	float: left;
	padding: 27px;
	font-size: 16px;
	color: #ff7f50;
	font-weight: bold;
}

.blank {
	width: 30px;
}
</style>
</head>
<body>
	<%@include file="../top.jsp"%><!--引用top内容 -->
	<div class="container">
		<c:forEach items="${productBeans}" var="item">
			<div class="product">
				<a href="product?method=info&id=${item.id }"> <!-- 点击图片或者点击商品名称都会跳到servlet 类型是info -->
					<div class="ima"
						style="background-image:url('${pageContext.request.contextPath}/${item.pic }')"></div>
				</a>
				<div>
					商品名称： <a href="product?method=info&id=${item.id }">${item.name}</a>
				</div>
				<div>价格： ${item.price}元！</div>
				<div>数量： ${item.number}份!</div>
			</div>
		</c:forEach>
	</div>
	<div>
		<c:if test="${not empty msg }">
			<div align="center" class="alert alert-info" role="alert">${msg }</div>
		</c:if>
	</div>
</body>
</html>