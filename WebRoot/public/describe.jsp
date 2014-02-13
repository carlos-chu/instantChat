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
<script type='text/javascript'
	src='https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.13/jquery-ui.min.js'></script>
<link rel="stylesheet" type="text/css"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.13/themes/start/jquery-ui.css" />
<!--点点css-->
<link href="<%=basePath%>css/MyCSS/container2.css" rel="stylesheet"
	type="text/css">
<link href="<%=basePath%>css/rich-content.css" rel="stylesheet"
	type="text/css">
<link href="<%=basePath%>css/MyCSS/container.css" rel="stylesheet"
	type="text/css">
<!-- Le styles -->
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>css/MyCSS/myChat-img.css" rel="stylesheet">
<!-- 上传的 -->
<link rel="stylesheet" href="<%=basePath%>css/uploadPic/uploadify.css"
	type="text/css">
</link>
<link rel="stylesheet"
	href="<%=basePath%>css/uploadPic/uploadTouxiang.css" type="text/css">
</link>
<!-- Loading Flat UI -->
<link href="<%=basePath%>css/Flat-UI/flat-ui.css" rel="stylesheet">
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="http://x.libdd.com/farm1/c32ade/bf3254d2/DAF06.css" />
<![endif]-->
<style>
body {
	background-color: #CCCCFF;
	padding-top: 50px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>
</head>
<body>
	<div class="navbar  navbar-fixed-top">
		<s:include value="../include/head.jsp"></s:include>
		<!--页头 -->
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
							<div id="touxiangDiv">
								<s:set name="sessionUserIcon" value="tuserContext.iconUrl"></s:set>
								<s:if test="#sessionUserIcon == null">
									<img id="touxiangIcon"
										src="<%=basePath%>images/default_user_touxiang.jpg">
								</s:if>
								<s:else>
									<img id="touxiangIcon" class="uploadedUserImg"
										src="<%=basePath%><s:property value="tuserContext.iconUrl"/> ">
								</s:else>
							</div>
							<a href="#uploadTXImg" role="button" class="btn btn-primary"
								data-toggle="modal">上 传 头 像</a> (上传头像很有必要!有头像,有礼貌!)<br>
							<!-- 上传头像对话框 -->
							<div id="uploadTXImg" class="modal hide fade" tabindex="-1"
								role="dialog" aria-hidden="true">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 id="myModalLabel">请上传您的头像</h4>
								</div>
								<div class="modal-body">
									<!--显示结果-->
									<div id="result"></div>
									<div id="fileQueue"></div>
									<input type="hidden" name="useId" value="<s:property value="#session.user_session.id"/> "
										id="userId"> 
									<input type="file" name="fileInput"	id="fileInput" /> <br /> <a
										href="javascript:$('#fileInput').uploadifySettings('scriptData',
										{'useId':$('#userId').val()});$('#fileInput').uploadifyUpload();">
										开 始</a> &nbsp;&nbsp;&nbsp; &nbsp;&nbsp; <a
										href="javascript:$('#fileInput').uploadifyClearQueue();">取
										消</a>
								</div>
								<div class="modal-footer">
									<button id="okButton" class="btn btn-primary"
										data-dismiss="modal">确定</button>
								</div>
							</div>
							<!-- 上传头像对话框结束 -->
							<!--填写自己的标签-->
							<div class="row">
								<div class="span5">
									<h5>添加您擅长的标签，例如心理咨询或IT技术，最少添加1个（必填）</h5>
									<input name="tags_user" id="tags_user" class="tagsinput" value="" />
								</div>
								<div class="span5">
									<h5>填写介绍自己的话吧！让别人更能了解您！</h5>
									<form
										action="<%=basePath%>userUpdateIntroAction!updateUserIntro.action"
										method="post">
										<!--填写自己的介绍-->
										<textarea name="intro" class="input-block-level" rows="3"
											placeholder="简介......"></textarea>
										<br> <input class="btn btn-large btn-block btn-primary"
											type="submit" value="开 始 i问">
									</form>
								</div>
							</div>
							<!-- /row -->

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
				<abbr title="邮件:361497394@qq.com">联系我们</abbr> | © 即时问答社区 - i问 |
				<a href="#">关于我们</a>
			</p>
		</div>
	</footer>
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/bootstrap.min.js"></script>
	<script src="<%=basePath%>js/build/backbone-0.9.10.js"></script>
	<script src="<%=basePath%>js/uploadPic/jquery.uploadify.v2.1.4.js"></script>
	<script src="<%=basePath%>js/uploadPic/swfobject.js"></script>
	<script src="<%=basePath%>js/uploadPic/uploadTouxiangJs.js"></script>
	<script src="<%=basePath%>js/Flat-UI/bootstrap-select.js"></script>
	<script src="<%=basePath%>js/Flat-UI/jquery.tagsinput.js"></script>
	<script src="<%=basePath%>js/Flat-UI/application.js"></script>
</body>
</html>
