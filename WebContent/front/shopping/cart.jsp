<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://localhost:8080//electronicCommerce1/util"
	prefix="util"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车</title>
<script
	src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"
	type="text/javascript"></script>
<script type="text/javascript">
	function changeSum(obj) {
		$(obj).parents(".aForm").find(".select").attr('checked', 'checked');
		$(obj).parents(".aForm").find(".price").attr("name", "price");
		$(obj).parents(".aForm").find(".oPrice").attr("name", "oPrice");
		var productId = $(obj).attr('for');
		var number = $(obj).parents(".aForm").find(".input-number").val();
		$(obj).parents(".aForm").find(".sub").val(productId + "_" + number);
		var oSum = 0.0;
		var oAll = document.getElementsByName("oPrice");
		for ( var oPrice in oAll) {
			var oPrice = parseFloat(oAll[oPrice].innerHTML);
			if (oPrice > 0.0) {
				oSum += oPrice;
			}
		}
		var sum = 0.0;
		var all = document.getElementsByName("price");
		for ( var price in all) {
			var price = parseFloat(all[price].innerHTML);
			if (price > 0.0) {
				sum += price;
			}
		}
		$("#oSum").html(oSum);
		$("#sum").html(sum);
	}
	function changeItem(productId, obj, oPrice, price) {
		var num = obj.value;
		$.get("cart?method=addItem", {
			num : num,
			productId : productId
		}, function(data) {
		}, "text");
		$(obj).parents(".aForm").find(".price").html(num * price);
		$(obj).parents(".aForm").find(".oPrice").html(num * oPrice);
		changeSum(obj);
	}
	$().ready(
			function() {
				changeSum();
				$('.select').click(
						function() {
							if ($(this).attr('checked')) {
								$(this).removeAttr('checked');
								$(this).parents(".aForm").find(".price").attr(
										"name", "priceNull");
								$(this).parents(".aForm").find(".oPrice").attr(
										"name", "oPriceNull");
								$(this).parents(".aForm").find(".sub").val("");
							} else {
								$(this).attr('checked', 'checked');
								$(this).parents(".aForm").find(".price").attr(
										"name", "price");
								$(this).parents(".aForm").find(".oPrice").attr(
										"name", "oPrice");
								var productId = $(this).attr('for');
								var number = $(this).parents(".aForm").find(
										".input-number").val();
								$(this).parents(".aForm").find(".sub").val(
										productId + "_" + number);
							}
							changeSum();
						});
			});
</script>
<style>
form {
	width: 1009px;
	margin: 50px auto;
	border: solid 2px #f0f0f0;
}

.aForm .aForm0 {
	height: 200px;
	width: 200px;
	margin-left: 50px;
	float: left;
}

ul {
	matgin: auto;
}

li {
	font-size: 15px;
	line-height: 60px;
}

li div {
	float: left;
}

li input {
	height: 20px;
	width: 100px;
}

.spinner {
	width: 130px;
	float: right;
	position: relative;
	top: 10px;
}

.cSum {
	font-size: 20px;
}
</style>
</head>
<body>
	<%@include file="../top.jsp"%>
	<c:if test="${empty cart.map }">
		<h1 al style="color: red">购物车空空如也，赶紧去逛逛</h1>
	</c:if>
	<c:if test="${not empty cart.map }">
		<form
			action="${pageContext.request.contextPath}/order?method=makeOrder"
			method="post">
			<div>
				<c:forEach items="${cart.items}" var="pItem">
					<div class="aForm" name="aForm">
						<img class="aForm0" alt=""
							src="${pageContext.request.contextPath}/${pItem.product.pic}">
						<ul class="aForm0">
							<li>商品名称： ${pItem.product.name}</li>
							<li><span>数量： </span>
								<div class="input-group spinner">
									<span class="input-group-btn">
										<button type="button" class="btn" data-value="decrease"
											data-target="#items_${pItem.product.id}"
											data-toggle="spinner">
											<span class="glyphiconglyphicon-minus"></span>
										</button>
									</span> <input type="text" for="${pItem.product.id}"
										data-ride="spinner" id="items_${pItem.product.id}"
										name="number" class="form-control input-number"
										value="${pItem.num}"
										onchange="changeItem('${pItem.product.id}',this,'${pItem.product.original_price}','${pItem.product.price}')">
									<span class="input-group-btn">
										<button type="button" class="btn" data-value="increase"
											data-target="#items_${pItem.product.id}"
											data-toggle="spinner">
											<span class="glyphiconglyphicon-plus"></span>
										</button> <!-- 存储该商品的id和最终要购买的数量 -->
									</span> <input type="hidden" name="sub" class="sub"
										value="${pItem.product.id}_${pItem.num}">
								</div></li>
						</ul>
						<ul class="aForm0">
							<li>原价： <span type="text" name="oPrice" class="oPrice">${pItem.product.original_price*pItem.num}</span>
								元
							</li>
							<li>现价： <span type="text" name="price" class="price">${pItem.product.price*pItem.num}</span>元
							</li>
						</ul>
						<ul class="aForm0">
							<li><input type="checkbox" name="select" class="select"
								for="${pItem.product.id}" checked><span>选择商品 </span></li>
							<li><a href="javascript:void(0)"
								onclick="del('${pItem.product.id}')"><button
										class="btn btn-danger" type="button">删除商品</button> </a></li>
						</ul>
					</div>
					<div>&nbsp;&nbsp;</div>
					<hr>
				</c:forEach>
			</div>
			<div class="cSum col-md-4 col-md-offset-8">
				<div>
					原总价： <span class="oSum" id="oSum" name="oSum"></span> 元
				</div>
				<div>
					现总价： <span class="sum" id="sum" name="sum"></span>元
				</div>
				<div align="center">
					<a href="${pageContext.request.contextPath}/cart?method=clear"><button
							class="btn btn-danger" type="button">清空购物车</button></a>
				</div>
			</div>
			<button class="btn btn-default col-md-offset-9" type="submit">确认订单</button>
		</form>

	</c:if>
	<script type="text/javascript">
		function del(id) {
			if (confirm("你确定要删除吗")) {
				location.href = "${pageContext.request.contextPath}/cart?method=del";
			}
		}
	</script>
</body>
</html>