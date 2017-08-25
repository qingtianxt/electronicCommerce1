<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 在收货的状态显示和操作时 注意后台可以操作 -->
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/Style1.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/layer/layer.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css"
	type="text/css" />
<script
	src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js"
	type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->

<script type="text/javascript">
	function showDetail(id) {
		$
				.post(
						"${pageContext.request.contextPath}/order",
						{
							"method" : "getDetailByid",
							"id" : id
						},
						function(data) {
							var s = "<table width='99%' border='1'><tr><th>商品名称</th><th>购买数量</th></tr>";
							$(data).each(
									function() {
										s += "<tr><td>" + this.product.name
												+ "</td><td>" + this.number
												+ "</td><tr>";
									});
							s += "</table>";
							layer.open({
								type : 1,//0:信息框; 1:页面; 2:iframe层;	3:加载层;	4:tip层
								title : "订单详情",//标题
								area : [ '450px', '300px' ],//大小
								shadeClose : true, //点击弹层外区域 遮罩关闭
								content : s
							//内容
							});

						}, "json")
	}
</script>
</HEAD>
<body>
	<div class="container">
		<br>
		<div class="row-fluid">
			<div class="col-md-4">
				<h1>用户订单</h1>
			</div>
			<div class="col-md-6">
				<form class="navbar-form navbar-left" role="search"
					action="${pageContext.request.contextPath}/adminOrder?method=listUser"
					method="post">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="用户账
号"
							name="search">
					</div>
					<button type="submit" class="btn btn-primery">提交</button>
				</form>
			</div>
		</div>
		<c:if test="${not empty orderBeans }">
			<form id="Form1" name="Form1"
				action="${pageContext.request.contextPath}/user/list.jsp"
				method="post">
				<table cellSpacing="1" cellPadding="0" width="100%" align="center"
					bgColor="#f5fafe" border="0">
					<TBODY>
						<tr>
							<td class="ta_01" align="center" bgColor="#afd1f3"><strong>订单列表</strong>
							</TD>
						</tr>

						<tr>
							<td class="ta_01" align="center" bgColor="#f5fafe">
								<table cellspacing="0" cellpadding="1" rules="all"
									bordercolor="gray" border="1" id="DataGrid1"
									style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
									<tr
										style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

										<td align="center" width="10%">序号</td>
										<td align="center" width="10%">订单编号</td>
										<td align="center" width="10%">订单金额</td>
										<td align="center" width="10%">收货人</td>
										<td align="center" width="10%">订单状态</td>

										<td align="center" width="50%">订单日期</td>
										<td align="center" width="50%">订单详情</td>

									</tr>
									<c:forEach items="${orderBeans }" var="o" varStatus="vs">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="18%">${vs.count }</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">${o.code }</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">${o.price }</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">${o.addressBean.name }</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%"><c:if test="${o.status==0 }">未付款</c:if> <c:if
													test="${o.status==1 }">
													<a
														href='${pageContext.request.contextPath }/order?method=updateState&id=${o.id}&status=2'>发货</a>
												</c:if> <c:if test="${o.status==2 }">等待确认收货</c:if> <c:if
													test="${o.status==3 }">已完成</c:if></td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">${o.create_date }</td>
											<td align="center" style="HEIGHT: 22px"><input
												type="button" value="订单详情" onclick="showDetail('${o.id}')" />
											</td>
										</tr>
									</c:forEach>
								</table>
							</td>
						</tr>
						<tr align="center">
							<td colspan="7"></td>
						</tr>
					</TBODY>
				</table>
			</form>
		</c:if>
	</div>
</body>
</HTML>

