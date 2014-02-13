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
}
</style>
<script type="text/javascript" src="<%=basePath %>js/jquery-1.7-latest.js"></script>
<script type="text/javascript" src="<%=basePath %>js/MyJS/MyJS_resetInfo.js"></script>
<script type="text/javascript">
	$(function() {
		dwr.engine.setActiveReverseAjax(true);
		dwr.engine.setNotifyServerOnPageUnload(true);
	});
</script>
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
					<div class="row register-main">
						<div class="tabbable tabs-left">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#tab1" data-toggle="tab">更改资料</a>
								</li>
								<li><a href="#tab2" data-toggle="tab">更改密码</a></li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane active" id="tab1">
									<div class="span6 offset1">
										<div id="touxiangDiv">
											<img id="touxiangIcon" class="uploadedUserImg"
												src="<%=basePath%><s:property value="userContext.iconUrl"/>">
										</div>
										<a href="#uploadTXImg" role="button" class="btn btn-primary"
											data-toggle="modal">更 改 头 像</a> (上传头像很有必要!有头像,有礼貌!)<br>
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
												<input type="hidden" name="useId"
													value="<s:property value="#session.user_session.id"/> "
													id="userId"> <input type="file" name="fileInput"
													id="fileInput" /> <br /> <a
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
												<h5>修改您的标签!</h5>
												<input name="tags_user" id="tags_user" class="tagsinput"
													value="<s:iterator value='listTagsOfUserContext'>
													<s:property value='title'/>,
													</s:iterator>" />
											</div>
											<div class="span5">
												<h5>修改您的昵称！</h5>
												<input class="input-large" type="text" id="resetName"
													value="<s:property value="userContext.name"/>">
											</div>
											<div class="span5">
												<h5>修改您的签名！</h5>
												<!--填写自己的介绍-->
												<textarea name="intro" class="input-block-level" rows="2" id="resetIntro">
												</textarea><br> 
												<!-- 成功提示框 -->
												<div id="alertDiv"></div>
												<input id="saveResetInfoButton"
													class="btn btn-large btn-primary" type="button"	value="保 存">
											</div>
										</div>
										<!-- /row -->
									</div>
								</div>
								<div class="tab-pane" id="tab2">
									<form class="form-horizontal">
										<div class="control-group">
											<label class="control-label" for="inputEmail">原密码：</label>
											<div class="controls">
												<input type="password" required id="oldPassword" placeholder="原密码">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="inputPassword">新密码：</label>
											<div class="controls">
												<input type="password" required id="newPassword" placeholder="新密码">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="inputPassword">确认密码：</label>
											<div class="controls">
												<input type="password" required id="confirmPassword" placeholder="确认密码">
											</div>
										</div>
										<div class="control-group">
										 <div class="row">
										 	<div class="span3 offset1"><div id="alertDiv2"></div></div>
										 </div>
											<div class="controls">
												<button type="button" id="resetPasswordButton" class="btn btn-primary">
													确  认</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--页尾-->
	<s:include value="%{#request.getContextPath()}/include/footer.jsp"></s:include>

	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/bootstrap.min.js"></script>
	<script src="<%=basePath%>js/build/backbone-0.9.10.js"></script>
	<script src="<%=basePath%>js/uploadPic/jquery.uploadify.v2.1.4.js"></script>
	<script src="<%=basePath%>js/uploadPic/swfobject.js"></script>
	<script src="<%=basePath%>js/uploadPic/uploadTouxiangJs.js"></script>
	<script src="<%=basePath%>js/Flat-UI/bootstrap-select.js"></script>
	<script src="<%=basePath%>js/Flat-UI/jquery.tagsinput.js"></script>
	<script src="<%=basePath%>js/Flat-UI/application.js"></script>
	<script src="<%=basePath%>js/MyJS/MyJS_uploadModal.js"></script>
	<script src="<%=basePath%>js/jquery.bootstrap.teninedialog.min.js"></script>
	<script src="<%=basePath%>js/build/MyMessenger.js"></script>
</body>
</html>
