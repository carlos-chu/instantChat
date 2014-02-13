<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<!-- saved from url=(0032)http://www.ycpai.com/index/index -->
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>chat--发表问题</title>
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
<!--lightbox-->
<script src="<%=basePath%>js/190D9.js" type="text/javascript"></script>
<!--core-->
<script src="<%=basePath%>js/F0E08.js" type="text/javascript"></script>
<style>
body {
	background-color: #CCCCFF;
	padding-top: 50px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>
<script type="text/javascript">
	$(function() {
		dwr.engine.setActiveReverseAjax(true);
		dwr.engine.setNotifyServerOnPageUnload(true);
	});
</script>
</head>
<body>
	<div class="navbar  navbar-fixed-top">
		<s:include value="%{#request.getContextPath()}/include/head.jsp"></s:include>
		<!--页头 -->
	</div>
	<!--主内容开始-->
	<div id="container">
		<!--发表问题-->
		<div id="main">
			<div class="post photo">
				<span class="icon"><img class="img-rounded imgSizePublishUser"
						src="<s:property value="tuserContext.iconUrl"/>"></span> <br>
				<hr />
				<div class="entry rich-content titleInput">
					<form action="<%=basePath%>userPublicInstantQuestionAction!saveProblem" method="post">
						<span class="date mainFont"> <input id="user_id"
							type="hidden"
							value="<s:property value="#session.user_session.id"/>">
							第一步填写您问题的标题(必填)： <textarea name="title" id="title" class="input-block-level"
								rows="3" required placeholder="问题标题......"></textarea><br />
							第二步(可以填写2~3个标签来使你的问题更加精确找到回答人，必填)：<br /> <input
							class="input-medium" id="tag1" type="text" required name="tag1"
							placeholder="标签1"> <input class="input-medium" id="tag2" name="tag2"
							type="text" required placeholder="标签2"> 
							<input name="tag3"class="input-medium" id="tag3" type="text"
								 placeholder="标签3(选填)">
							<%--<span name="tag3" id="tag3" class="input-medium uneditable-input">标签3(暂时不可填)</span>
							--%><br /><br /> 
							<s:if test="ifAlert == 1">
								<div class="alert">
  									<button type="button" class="close" data-dismiss="alert">&times;</button>
									<h4>很抱歉！根据您的标签没有匹配到人，您还可以邀请好友回答！</h4>
								</div>
							</s:if>
							第三步:<br />
							<s:token></s:token>
							<button class="btn btn-large btn-block"	data-loading-text="正在匹配中..." 
								type="submit" id="startLoad">开始匹配达人</button> 
						</span>
					</form>
				</div>
				<div class="clear"></div>
				<div class="post_meta">
					<!--显示信息-->
					<div class="showInformation">
						<span class="showInfoContent"> 
							<p><h5>即时问答提示：</h5></p>
							<p>1.问题标签关键词要尽量写完整，这样您才会匹配到适合回答您问题的达人。
							</p><p>
								2.记得解答完毕后给达人评星啊！！！这样您会帮到其他与您有同样问题的人更精确的
							   找到该达人！
							</p>
							<p>
								3.如果您没有匹配到达人，您还可以邀请您在线的好友回答问题！
							</p>
						</span>
					</div>
					<hr />
				</div>
			</div>
		</div>
		<%--右边导航框 --%>
		<s:include value="%{#request.getContextPath()}/include/sidebar_publish.jsp"></s:include>
	</div>
	<%--页尾 --%>
	<s:include value="%{#request.getContextPath()}/include/footer.jsp"></s:include>
	
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/bootstrap.min.js"></script>
	<script src="<%=basePath%>js/build/messenger.min.js"></script>
	<script src="<%=basePath%>js/build/MyMessenger.js"></script>
	<script src="<%=basePath%>js/build/backbone-0.9.10.js"></script>
	<script src="<%=basePath%>js/MyJS/MyJS.js"></script>
	<script src="<%=basePath%>dwr/util.js"></script>
	<script src="<%=basePath%>dwr/engine.js"></script>
	<script src="<%=basePath%>dwr/interface/PushMsgDwrToUser.js"></script>
</body>
</html>
