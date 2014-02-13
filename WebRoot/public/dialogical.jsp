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
<title>chat--主页</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="keywords" content="">
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
<script type="text/javascript" src="<%=basePath %>js/jquery-1.7-latest.js"></script>
<script type="text/javascript" src="<%=basePath%>js/MyJS/MyJS_grade.js"></script>
</head>
<body>
	<div class="navbar  navbar-fixed-top">
		<s:include value="%{#request.getContextPath()}/include/head.jsp"></s:include>
		<!--页头 -->
	</div>
	<!--主内容开始-->
	<div id="container">
		<!--话题实时共享，媒体新闻实时评论，问题共享回答模块模板-->
		<div id="main">
			<div class="post photo">
			<s:iterator value="titleTagsContext" id="maptitletag" status="s">
				<span class="icon"><a href="#"> <img class="img-rounded"
						src="<%=basePath%>images/Koala.jpg"
						style="height:45px; width:45px"> </a> 
				</span>
				<!--图片-->
				<div class="entry rich-content">
					<h5 class="titleFont">
					<s:property value="key"/>
					</h5>
					<hr />
					<span class="date"><i class="icon-tags"></i>
						问题的标签：
						<s:iterator value="#maptitletag.value" id="listTags">
							<a href="#">
								<span class="label label-info">
						 			<s:property value="#listTags.title"/></span></a>
						</s:iterator>
					</span> <br />
				</div>
			</s:iterator>
				<hr />
				<div class="post_meta">
					<hr style="FILTER: alpha(opacity=100,finishopacity=0,style=1)"
						width="100%" color=#987cb9 size=3 />
					<!--显示信息-->
					<div id="chatDiv" class="showInformation pre-scrollable">
						
					</div>
					<hr />
					<!--添加输入框-->
					<hr style="FILTER: alpha(opacity=100,finishopacity=0,style=1)"
						width="100%" color=#6600CC size=2 />
					<div>
						<!-- 保存接受者的id -->
						<input type="hidden" id="receiverId" value="<s:property value="receiverUserId"/>">
						<!-- 保存问题对象 -->
						<input type="hidden" id="tquestionId" value="<s:property value="questionIdContext"/>">
						<!-- 保存当前用户的名字 -->
						<input type="hidden" id="tuserName" value="<s:property value="userNameContext"/>">
						<!-- 保存当前用户的id -->
						<input type="hidden" id="tuserId" value="<s:property value="#session.user_session.id"/>">
						<textarea rows="5" id="msgTextsOfUser" class="mainTextarea"></textarea>
						<s:if test="isFriendChat == 1">
							<button class="btn btn-primary mainTextareaButton" id="sendMsgToFriendButton" type="button">
								发 表</button>
						</s:if><s:else>
							<button class="btn btn-primary mainTextareaButton" id="sendMsgButton" type="button">
								发 表</button>
						</s:else>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
		<%--右边导航框 --%>
		<s:include value="%{#request.getContextPath()}/include/sidebar_all.jsp"></s:include>
	</div>
	<!--页尾-->
	<s:include value="%{#request.getContextPath()}/include/footer.jsp"></s:include>
	
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/bootstrap.min.js"></script>
	<script src="<%=basePath%>js/build/messenger.min.js"></script>
	<script src="<%=basePath%>js/build/MyMessenger.js"></script>
	<script src="<%=basePath%>js/build/backbone-0.9.10.js"></script>
	<script src="<%=basePath%>js/MyJS/MyJS.js"></script>
	<script src="<%=basePath%>js/MyJS/MyJS_dialogical.js"></script>
	<script src="<%=basePath%>dwr/util.js"></script>
	<script src="<%=basePath%>dwr/engine.js"></script>
	<script src="<%=basePath%>dwr/interface/PushMsgDwrToUser.js"></script>
</body>
</html>
