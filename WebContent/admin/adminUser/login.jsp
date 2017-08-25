<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 导入css -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
<style type="text/css">  
        .code   
        {   
            background-image:url(code.jpg);   
            font-family:Arial;   
            font-style:italic;   
            color:Red;   
            border:0;   
            padding:2px 3px;   
            letter-spacing:3px;   
            font-weight:bolder;   
        }   
        .unchanged   
        {   
            border:0;   
        }   
    </style>
<script type="text/javascript">
var code ; //在全局 定义验证码   
function createCode()   
{    
  code = "";   //用code来接受循环时随机产生的
  var codeLength = 6;//验证码的长度   
  var checkCode = document.getElementById("checkCode");   
  var selectChar = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');//所有候选组成验证码的字符，当然也可以用中文的   
      
  for(var i=0;i<codeLength;i++)   
  {   
    
      
  var charIndex = Math.floor(Math.random()*36);  
  
  //math.floor()此方法返回最大的（最接近正无穷大）浮点值小于或等于参数，并等于某个整数。
  code +=selectChar[charIndex];   
     
     
  }   
//  alert(code);   
  if(checkCode)   
  {   
    checkCode.className="code";//   用来定义验证码的格式
    checkCode.value = code;   
  }   
     
}
</script>
</head>
<body onload="createCode()"><!--当进入到这个页面的时候，运行createCode（）产生验证码-->
<!-- 通过validate验证，获取id值，和表单中的name属性值进行比对 -->
	<div class="row-fluid" style="margin-top: 200px;">
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<form role="form" class="form-horizontal" action="${pageContext.request.contextPath}/adminUser?method=login"
				method="post" id="checkForm">
				<div class="form-group">
					<label class="col-md-3 control-label" for="username">用户名</label>
					<div class="col-md-9">
						<input class="form-control" name="username" type="text" id="username"
							placeholder="Username" value="" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label" for="inputPassword">密码</label>
					<div class="col-md-9">
						<input type="password" name="password" class="form-control"
							id="inputPassword" placeholder="Password">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label" for="checkCode">验证码</label>
					<!-- <div class="col-md-4">
						<input type="text" name="codeCheck" class="form-control"
							id="codeCheck" placeholder="Code">
					</div> -->
					<div class="col-md-4">
						<input type="text" onclick="createCode()" readOnly="true" id="checkCode" class="unchanged" style="width: 80px"  />
					</div>
					
					<div class="col-md-1"></div>
					
				</div>
				<div class="form-group">
				</div>
				<div class="form-group">
					<div class="col-md-offset-3 col-md-9">
						<button type="submit" class="btn btn-primary btn-block">
							登录</button>
					</div>
				</div>
				<c:if test="${param.status.equals('1')}">
					<div class="alert alert-danger" role="alert">用户名不存在</div>
				</c:if>
				<c:if test="${param.status.equals('2')}">
					<div class="alert alert-danger" role="alert">用户名或密码错误</div>
				</c:if>
				<c:if test="${param.status.equals('3')}">
					<div class="alert alert-danger" role="alert">退出成功</div>
				</c:if>
			</form>
		</div>
		<div class="col-md-4"></div>
	</div>
	<script src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.validate.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/myValidate.js" type="text/javascript"></script>
	<!-- 需要把路径写成这种形式，这样的可利用性会大大提高 -->
</body>
</html>