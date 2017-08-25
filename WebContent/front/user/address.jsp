<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="util"
	uri="http://localhost:8080//electronicCommerce1/util"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>收货地址</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
<script
	src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js"
	type="text/javascript"></script>

<script type="text/javascript">
	window.pageConfig = {
		jdfVersion : '2.0.0'
	};
</script>
<script type="text/javascript">
	function chCity(obj) {
		$("#city0").empty();
		var id = obj.value;
		$.post("address", {
			method : "getCity",
			id : id
		}, function(data) {
			if (data != null && data.length > 0) {
				var content = "<option value='-1'>-- 请选择城市 --</option>";
				for ( var type in data) {
					content += "<option value='"+data[type].id+"'>"
							+ data[type].name + "</option>";
				}
				document.getElementById("city0").innerHTML = content;
				chRegion();
			}
		}, "json");

	}

	function chRegion(obj) {
		$("#region0").empty();
		var id = $('#city0').attr("value")
		var id = obj.value;
		$.post("address", {
			method : "getRegion",
			id : id
		}, function(data) {
			if (data != null && data.length > 0) {
				var content = "<option value='-1'>-- 请选择地区 --</option>";
				for ( var type in data) {
					content += "<option value='"+data[type].id+"'>"
							+ data[type].name + "</option>";
				}
				document.getElementById("region0").innerHTML = content;
			}
		}, "json");
	}
</script>
</head>
<body>
	<div id="container">
		<!-- Button trigger modal -->
		<button type="button" class="btn btn-primary btn-lg"
			data-toggle="modal" data-target="#myModal">新增收货地址</button>
		<span class="ftx-03">您已创建<span id="addressNum_top"
			class="ftx-02">1 </span>个收货地址，最多可创建<span class="ftx-02">20</span>个
		</span>
		<form action="${pageContext.request.contextPath }/address?method=add"
			method="post" id="checkForm">
			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Modal title</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label for="name">收货人</label> <input name="name"
									class="form-control" id="address_name" placeholder="收货人">
							</div>
							<div class="form-group col-md-12">
								<label id="label1" class="col-md-2 control-label" for="name">省份
									名： </label>
								<div class="col-md-10" id="province">
									<select name="provinceId" class="form-control"
										onchange="chCity(this)" id="province0">
										<option value="-1">-- 请选择省份 --</option>
										<c:forEach items="${province}" var="item">
											<option value="${item.id}">${item.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group col-md-12">
								<label id="label1" class="col-md-2 control-label" for="name">城市
									名： </label>
								<div class="col-md-10" id="city">
									<select name="cityId" class="form-control"
										onchange="chRegion(this)" id="city0">
									</select>
								</div>
							</div>
							<div class="form-group col-md-12">
								<label id="label1" class="col-md-2 control-label" for="name">地区
									名： </label>
								<div class="col-md-10" id="region">
									<select name="regionId" class="form-control" id="region0">
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">详细地址</label> <input type="text"
									name="addressinfo" class="form-control" placeholder="address">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">手机</label> <input type="text"
									name="account" class="form-control" id="cellphone"
									placeholder="手机">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">返回</button>
							<button type="submit" class="btn btn-primary">提交</button>
						</div>
					</div>
				</div>
			</div>
		</form>
		<div class="row-fluid">
			<div class="span12">
				<h1>收货地址列表</h1>
			</div>
		</div>
		<div class="row-fluid">
			<div class="col-md-1 "></div>
			<div class="col-md-10 ">
				<table class="table table-striped">
					<tr>
						<td>收货人</td>
						<td>地区</td>
						<td>详细地址</td>
						<td>电话</td>
						<td align="center">操作</td>
					</tr>
					<!-- forEach遍历出adminBeans -->
					<c:forEach items="${addressBeans}" var="item" varStatus="status">
						<c:if test="${item.status==1 }">
							<tr>
								<td>${item.name }</td>
								<td><a>${item.provincename }${item.cityname }${item.areaname }</a></td>
								<td>${item.address }</td>
								<td>${item.cellphone }</td>
								<td>默认</td>
							</tr>
						</c:if>
					</c:forEach>
					<c:forEach items="${addressBeans}" var="it" varStatus="status">

						<c:if test="${it.status==0 }">
							<tr>
								<td>${it.name }</td>
								<td><a>${it.provincename }${it.cityname }${it.areaname }</a></td>
								<td>${it.address }</td>
								<td>${it.cellphone }</td>
								<td><a
									href="${pageContext.request.contextPath}/address?method=updateStatus&id=${it.id}">设为默认
								</a></td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
			</div>
			<div class=" col-md-1"></div>
		</div>
	</div>
	<div align="center">
		<util:page pagingBean="${pagingBean }" />
	</div>
	<script
		src="${pageContext.request.contextPath}/static/js/jquery.validate.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/myValidate.js"
		type="text/javascript"></script>
</body>
</html>