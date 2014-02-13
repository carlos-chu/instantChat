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
<link rel="stylesheet" href="<%=basePath%>css/MyCSS/myCSS.css" type="text/css"></link>
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
						src="<s:property value="#session.user_session.iconUrl"/>"></span> <br>
				<hr />
				<div class="entry rich-content titleInput">
					<s:if test="ifHaveOnlineFriends == 1">
						<div class="alert">
  							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<h4>您当前没有在线的好友！您还可以匹配其他的达人回答您的问题！</h4>
						</div>
					</s:if><s:else>
						<div class="hero-unit">
						<div class="row">
							<h4 class="titleFont">在线好友:</h4>
							第一步选择在线好友(点击选择)：
							<div class="span5">
								<s:iterator value="userOnlineFriendsContext" status="s">
									<div class="span1">
										<a href="<%=basePath%>userInviteFriendAction!chooseFriend?friendId=<s:property value="id"/>">
											<img class="img-circle indexImg"
												src="<s:property value="iconUrl"/>"><s:property value="name" /></a>
									</div>
								</s:iterator>
							</div>
							<span class="changeFriends" id="changeFriends">
								<a href="#">换一组</a>
							</span>
						</div>
					</div>
					</s:else>
					<form action="<%=basePath%>
					userInviteFriendToChatAction!saveProblem?friendId=<s:property value="friendContext.id"/>" method="post">
					 <span class="date mainFont"> <input id="user_id"
						type="hidden" value="<s:property value="#session.user_session.id"/>">
						第二步填写您问题的标题(必填)： <textarea name="title" id="title" class="input-block-level"
							required rows="2" placeholder="问题标题......"></textarea><br />
					 </span>
					 <span class="date mainFont" id="showQueding">
						<!-- 保存邀请用户的id -->
						<input id="friend_id" type="hidden" value="<s:property value="friendContext.id"/>">
						<s:if test="friendContext.id != null">
							<h4>您邀请的是：</h4><br>
							<span class="chooseOk">
							  <img class="img-rounded imgSizeInviteUser" 
							  	src="<%=basePath%><s:property value="friendContext.iconUrl"/>">
							  <s:property value="friendContext.name"/>
							</span><br>
							<s:token></s:token>
							<button id="OkButton" class="btn btn-large btn-block btn-success"
								data-loading-text="正在邀请中..." type="submit">确 定</button>
						</s:if>
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
		<s:include value="%{#request.getContextPath()}/include/sidebar_inviteFriend.jsp"></s:include>
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
