<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>结算页</title>
</head>
<body>
	<%@include file="../top.jsp"%>
	<form action="order?method=addOrder&originalPrice=${oSum }&price=${sum}" role="form"
		class="form-horizontal" method="post">
		<div class="container-fluid"
			style="border: solid 1px #f0f0f0; margin: 0 160px;">
			<div class="row-fluid">
				<div class="col-md-11 col-md-offset-1"
					style="border-bottom: solid 1px #f0f0f0; margin: 20px; padding: 16px;">
					<p class="text-danger">收货人信息</p>
					<select name='addr' class='form-control'>
						<c:forEach var="addr" items="${addressBeans}">
							<option value="${addr.id}">${addr.name}&nbsp;&nbsp;${addr.provincename}${addr.cityname }${addr.areaname }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="row-fluid">
				<div class="col-md-11 col-md-offset-1"
					style="border-bottom: solid 1px #f0f0f0; margin: 20px; padding: 16px;">
					<p class="text-danger">支付方式</p>
					<div class="radio">
						<label><input type="radio" checked="checked" name="payway"
							value="0" />在线支付</label>
					</div>
					<div class="radio">
						<label><input type="radio" name="payway" value="1" />货到付款</label>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="col-md-11 col-md-offset-1"
					style="border-bottom: solid 1px #f0f0f0; margin: 20px; padding: 16px;">
					<div class="row-fluid">
						<p class="text-danger">送货清单</p>
					</div>
					<div class="row-fluid">
						<c:forEach items="${cart.items}" var="pItem">
							<table cellpadding="0" cellspacing="0" class="table">
								<tr>
									<td><a
										href="${pageContext.request.contextPath}/product?method=info&id=${pItem.product.id }">
											<img width="70px" height="70px"
											src="${pageContext.request.contextPath}/${pItem.product.pic}" />
									</a></td>
									<td><a
										href="${pageContext.request.contextPath}/product?method=info&id=${pItem.product.id}">${pItem.product.name}</a></td>
									<td>共${pItem.num}件</td>
									<td><label id="total1" class="tot"
										style="color: #ff5500; font-size: 14px; font-weight: bold;">原价：
											￥<del>${pItem.product.original_price*pItem.num}</del>
									</label></td>
									<td><label id="total1" class="tot"
										style="color: #ff5500; font-size: 14px; font-weight: bold;">现价：￥${pItem.product.price*pItem.num}</label></td>
								</tr>
							</table>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="col-md-11 col-md-offset-1"
					style="margin: 20px; padding: 16px;">
					<p class="text-danger">发票信息</p>
				</div>
			</div>
		</div>
		<div class="container-fluid" style="margin: 36px 0;">
			<div class="row-fluid">
				<div class="col-md-3 col-md-offset-9" style="margin-bottom: 16px;">
					<a class="text-danger">原价：￥${oSum}</a><br> <a
						class="text-danger">现价：￥${sum}</a> <input type="hidden"
						name="originalPrice" value="${oSum}" /> <input type="hidden"
						name="price" value="${sum}" />
				</div>
			</div>
			<div class="row-fluid">
				<div class="col-md-1 "></div>
				<div class="col-md-9"
					style="background-color: #F4F4F4; margin: 16px 30px;">
					<div class="row-fluid">
						<div class="col-md-2 col-md-offset-8">
							<span class="text-danger">应付金额： ￥${sum}</span>
						</div>
						<div class="col-md-2 "></div>
					</div>
					<div class="row-fluid">
						<div class="col-md-11 col-md-offset-1">
							<span class="text-danger"></span>
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="col-md-3 col-md-offset-9" style="margin-bottom: 16px;">
					<button type="submit" class="btn btn-danger">提交订单</button>
				</div>
			</div>
		</div>
	</form>
</body>
</html>