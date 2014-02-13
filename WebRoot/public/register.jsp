<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>chat--加入我们</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="keywords" content="">
<meta property="qc:admins" content="1103250777613011637571645074534711">
<!--点点css-->
<link href="<%=basePath%>css/DA232.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>css/rich-content.css" rel="stylesheet"
	type="text/css">
<link href="<%=basePath%>css/MyCSS/container.css" rel="stylesheet"
	type="text/css">
<!--第三方弹出提示-->
<link href="<%=basePath%>css/build/messenger.css" rel="stylesheet">
<link href="<%=basePath%>css/build/messenger-theme-future.css"
	rel="stylesheet">
<!-- Le styles -->
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>css/MyCSS/myChat-img.css" rel="stylesheet">
<!--点点js-->
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="http://x.libdd.com/farm1/c32ade/bf3254d2/DAF06.css" />
<![endif]-->
<script src="<%=basePath%>js/jquery-1.7-latest.js"
	type="text/javascript"></script>
<style>
body {
	background-color: #CCCCFF;
	padding-top: 50px;
}
</style>
</head>
<body>
	<div class="navbar  navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> </a> <a
					class="brand" href="#" name="top">LOGO</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class="divider-vertical"></li>
						<li class="divider-vertical"></li>
						<li class="divider-vertical"></li>
						<li class="active"><a href="<%=basePath%>"><i
								class="icon-home"></i><strong class="text-info titleFont">
									首页</strong> </a></li>
						<li class="divider-vertical"></li>
						<li id="questionsPage"><a
							href="<%=basePath%>questionsAction!initQuestions"> <i
								class="icon-question-sign"></i><strong
								class="text-info titleFont"> 问答广场</strong> </a>
						</li>
						<li class="divider-vertical"></li>
						<li id="usersPage"><a
							href="<%=basePath%>usersAction!initUsers"> <i
								class="icon-eye-open"></i><strong class="text-info titleFont">
									达人广场</strong> </a>
						</li>
						<li class="divider-vertical"></li>
						<li id="discussesPage"><a
							href="<%=basePath%>discussesAction!initDiscusses"> <i
								class="icon-th"></i><strong class="text-info titleFont">
									话题讨论</strong> </a>
						</li>
						<li class="divider-vertical"></li>
						<li class="divider-vertical"></li>
						<form class="navbar-search pull-left">
							<input type="text" class="search-query titleFont"
								placeholder="搜索想要的...">
						</form>
					</ul>
					<ul class="nav pull-right">
						<li><a class="titleFont"
							href="<%=basePath%>public/register.jsp">注册</a>
						</li>
						<li class="dropdown"><a class="dropdown-toggle titleFont"
							href="#" data-toggle="dropdown">登录 <strong class="caret"></strong>
						</a>
							<div class="dropdown-menu"
								style="padding: 15px; padding-bottom: 0px;">
								<form method="post"
									action="<%=basePath%>userLoginAction!loginUser"
									accept-charset="UTF-8">
									<input style="margin-bottom: 15px;" type="text" required
										placeholder="邮箱" id="username" name="email"> <input
										style="margin-bottom: 15px;" type="password" required
										placeholder="密码" id="password" name="password"> <input
										style="float: left; margin-right: 10px;" type="checkbox"
										name="info[remember]" id="remember-me" value="1"> <label
										class="string optional" for="user_remember_me">
										记住用户名密码</label> <input type="hidden" name="dosubmit" value="1">
									<input class="btn btn-primary btn-block" type="submit"
										id="sign-in" value="登录">
								</form>
								<span style="height:10px; width:10px; display:block"></span>
							</div></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container-fluid -->
		</div>
		<!--/.navbar-inner -->
	</div>
	<!--主内容开始-->
	<div id="container">
		<div class="row">
			<div class="span10">
				<div class="hero-unit">
					<div class="row register-title">
						<div class="span4 offset3">
							<h4>加入我们！尽情的释放您的智慧！</h4>
						</div>
					</div>
					<div class="row register-main">
						<div class="span8 offset2">
							<form class="form-horizontal" id="registerForm"
								action="<%=basePath%>userRegisterAction!register" method="post">
								<div class="control-group">
									<label class="control-label" for="email">请输入邮箱(必填)：</label>
									<div class="controls">
										<input type="email" id="email" name="email" required
											placeholder="请输入正确的邮箱...">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="name">请输入名字(必填)：</label>
									<div class="controls">
										<input type="text" id="name" name="name" required
											placeholder="请填写您的名称..."> <span id="nameWarn"
											class="help-inline check"></span>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="sex">请输入性别(必填)：</label>
									<div class="controls">
										<input type="text" id="sex" name="sex" required
											placeholder="文艺女,技术男..."> <span id="sexWarn"
											class="help-inline check"></span>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="password">请输入密码(必填)：</label>
									<div class="controls">
										<input type="password" id="password" name="password" required
											placeholder="6-25个字符..."> <span id="passwordWarn"
											class="help-inline check"></span>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="confirmPassword">确认密码：</label>
									<div class="controls">
										<input type="password" id="confirmPassword"
											name="confirmPassword" required placeholder="请确认您的密码...">
										<span id="comfirmPasswordWarn" class="help-inline check"></span>
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<s:token />
										<%--<a class="btn btn-primary" href="javascript:submitFrom();">加 入 我 们</a>
										--%>
										<button type="submit" class="btn btn-primary">加 入 我 们</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--页尾-->
	<footer class="footer">
		<div align="center">
			<p></p>
			<p>
				<abbr title="邮件:361497394@qq.com">联系我们</abbr> | © 即时问答社区 - i问 | <a
					href="#">关于我们</a>
			</p>
		</div>
	</footer>
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/bootstrap.min.js"></script>
	<script src="<%=basePath%>js/build/messenger.min.js"></script>
	<script src="<%=basePath%>js/build/MyMessenger.js"></script>
	<script src="<%=basePath%>js/build/backbone-0.9.10.js"></script>
	<script src="<%=basePath%>js/MyJS/check.js"></script>
</body>
</html>
