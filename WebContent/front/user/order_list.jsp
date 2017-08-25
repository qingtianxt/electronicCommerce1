<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="util" uri="http://localhost:8080//electronicCommerce1/util" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员登录</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"></script>
			<script
		src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js" type="text/javascript"></script><!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/style.css" type="text/css" />

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
	</head>

	<body>
		<div class="container">
			<div class="row">
				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong>我的订单</strong>
					<table class="table table-bordered">
						<c:forEach items="${orderBeans }" var ="o">
							<tbody><!-- table的一部分，在以前table有问题时，tbody作为table的一部分，在页面加载的时候，先加载表格的头和尾，最后加载tbody，算是界面的优化，现在浏览器会帮我们做，每一个页面的每个表格都会有 -->
								<tr class="success">
									<th colspan="5">订单编号:${o.code }订单金额：${o.price }
										<c:if test="${o.status==0 }">
											<a href="${pageContext.request.contextPath }/order?method=getById&id=${o.id}">付款</a>
										</c:if>
										<c:if test="${o.status==1 }">
											已付款
										</c:if>
										<c:if test="${o.status==2 }">
											<a href="${pageContext.request.contextPath }/order?method=updateState&id=${o.id}">确认收货</a>
										</c:if>
										<c:if test="${o.status==3 }">
											已完成
										</c:if>
										
									 </th>
								</tr>
								<tr class="warning">
									<th>图片</th>
									<th>商品</th>
									<th>价格</th>
									<th>数量</th>
									<th>小计</th>
								</tr>
								<c:forEach items="${o.orderProductBeans }" var="oi">
									<tr class="active">
										<td width="60" width="40%">
											<input type="hidden" name="id" value="22">
											<img src="${pageContext.request.contextPath}/${oi.product.pic}" width="70" height="60">
										</td>
										<td width="30%">
											<a target="_top" href="${pageContext.request.contextPath }/product?method=info&id=${oi.product.id}"> ${oi.product.name }</a>
										</td>
										<td width="20%">
											￥${oi.product.price }
										</td>
										<td width="10%">
											${oi.number }
										</td>
										<td width="15%">
											<span class="subtotal">￥${oi.price* oi.number}</span>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</c:forEach>
					</table>
				</div>
			</div>
			<div style="text-align: center;">
				<util:page pagingBean="${order_pagingBean }"/>
			</div>
		</div>
		<div style="text-align: center;margin-top: 5px;">
			<ul class="list-inline">
				<li><a>关于我们</a></li>
				<li><a>联系我们</a></li>
				<li><a>招贤纳士</a></li>
				<li><a>法律声明</a></li>
				<li><a>友情链接</a></li>
				<li><a target="_blank">支付方式</a></li>
				<li><a target="_blank">配送方式</a></li>
				<li><a>服务声明</a></li>
				<li><a>广告声明</a></li>
			</ul>
		</div>
		<div style="text-align: center;margin-top: 5px;margin-bottom:20px;">
			Copyright &copy; 2005-2016 传智商城 版权所有
		</div>
	</body>

</html>