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
<title>chat--用户主页</title>
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
<!--第三方弹出提示-->
<link href="<%=basePath%>css/build/messenger.css" rel="stylesheet">
<link href="<%=basePath%>css/build/messenger-theme-future.css" rel="stylesheet">
<!-- Le styles -->
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=basePath%>css/MyCSS/myChat-img.css" type="text/css"></link>
<link rel="stylesheet" href="<%=basePath%>css/MyCSS/myCSS.css" type="text/css"></link>
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="http://x.libdd.com/farm1/c32ade/bf3254d2/DAF06.css" />
<![endif]-->
<style>
body {
	background-color: #CCCCFF;
	padding-top: 50px;
}
</style>
<script type="text/javascript"
	src="<%=basePath%>js/jquery-1.7-latest.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/MyJS/MyJS_fellowPeople.js"></script>
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
		<div class="hero-unit">
			<div class="row">
				<div class="span10">
					<div class="row register-main">
						<input type="hidden" id="userId" value="<s:property value="tuserContext.id"/> ">
						<div class="span2">
							<img class="img-rounded" 
								src="<%=basePath%><s:property value="tuserContext.iconUrl"/>">
						</div>
						<h4>
							&nbsp;&nbsp;
							<s:property value="tuserContext.name" />
							&nbsp;&nbsp;
							<s:if test="#session.user_session.id != tuserContext.id">
								<span id="fellowPeopleSpan"> <input type="hidden"
									id="friendId" value=<s:property value="tuserContext.id"/>>
									<s:if test="ifFellowPeople == 1">
										<button id='cancelfellowPeopleButton' class='btn btn-small'
											type='button'>我已关注</button>
									</s:if> <s:else>
										<button id="fellowPeopleButton" data-loading-text="处理中..."
											class="btn btn-small btn-success" type="button">关注此人</button>
									</s:else> </span>
							</s:if>
						</h4>
						<s:if test="#session.user_session.id == tuserContext.id">
							<a href="<%=basePath%>userGetResetInfoAction!getTagsByUser">
								<button class="btn btn-link" type="button">编辑资料</button> </a>
						</s:if>
						<div class="span5">
							<h5>
								星数：<span class="label label-important"> <s:property
										value="userGradeContext" /> </span> &nbsp;&nbsp;&nbsp;&nbsp;
								属于：<span class="label"> <s:property
										value="tuserContext.sex" /> </span>
							</h5>
							<s:iterator value="tagListContext" status="s">
								<span class="label label-info"> <s:property value="title" />
								</span>
							</s:iterator>
							<h5>
								<s:property value="tuserContext.intro" />
							</h5>
						</div>
					</div>
					<br>
				</div>
			</div>
			<hr />
			<div class="row">
				<div class="span10">
					<div class="tabbable">
						<!-- Only required for left/right tabs -->
						<ul class="nav nav-tabs">
							<li class="active"><a href="#tab1" data-toggle="tab">
									<button class="btn" type="button">发表的问题</button> </a>
							</li>
							<li><a href="#tab2" data-toggle="tab">
									<button class="btn btn-inverse" type="button">回答的问题</button> </a>
							</li>
							<li><a href="#tab3" data-toggle="tab">
									<button class="btn btn-info" type="button">达人评价</button> </a>
							</li>
							<li><a href="#tab4" data-toggle="tab">
									<button class="btn btn-warning" type="button">关注的人</button> </a>
							</li>
							<li><a href="#tab5" data-toggle="tab">
									<button class="btn btn-success" type="button">粉丝</button> </a>
							</li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="tab1">
							  <div class="span6" id="showQuestionsDiv">
								<s:iterator value="listQuestionContext" status="s">
									<div class="row">
										<div class="span6">
											<span class="showInfoContent"> <a target="_blank"
												href="<%=basePath%>userScanQuestionAction!initQuestionById?questionId=<s:property value="id"/>">
													<h5>
														<p class="titleFont questionStyle">
															<s:property value="title" />
														</p>
													</h5> </a>
											<h6 class="titleFont timeStyle">
													<s:property value="createtime" />
												</h6> </span><br>
											<br>
										</div>
									</div>
								</s:iterator>
								<%--<span id="ssssa"></span>
								<span id="buttonSpan">
									<ul class="pager">
										<s:if test="totalDataContext > 0 ">
											<li class="next"><a href="javascript:getNextQuestions();">后页
													&rarr;</a></li>
										</s:if>
										<s:else>
											<li class="next disabled"><a href="#">后页 &rarr;</a></li>
										</s:else>
									</ul> </span>
							  --%></div>
							</div>
							<div class="tab-pane" id="tab2">
								<s:iterator value="listQuestionAnswersContext" status="s">
									<div class="row">
										<div class="span6">
											<span class="showInfoContent"> <a target="_blank"
												href="<%=basePath%>userScanQuestionAction!initQuestionById?questionId=<s:property value="id"/>">
													<h5>
														<p class="titleFont questionStyle">
															<s:property value="title" />
														</p>
													</h5> </a>
											<h6 class="titleFont timeStyle">
													<s:property value="createtime" />
												</h6> </span><br>
											<br>
										</div>
									</div>
								</s:iterator>
							</div>
							<div class="tab-pane" id="tab3">
								<s:iterator value="listQuestionFellowsContext" status="s">
									<div class="row">
										<div class="span6">
											<span class="showInfoContent"> <a target="_blank"
												href="<%=basePath%>userScanQuestionAction!initQuestionById?questionId=<s:property value="id"/>">
													<h5>
														<p class="titleFont questionStyle">
															<s:property value="title" />
														</p>
													</h5> </a>
											<h6 class="titleFont timeStyle">
													<s:property value="createtime" />
												</h6> </span><br>
											<br>
										</div>
									</div>
								</s:iterator>
							</div>
							<div class="tab-pane" id="tab4">
								<div class="row">
									<div class="span7">
									<s:iterator value="listUserFriendsContext" status="s">
										<div class="span1">
											<a target="_blank"
												href="<%=basePath%>userInitUserHomeDataAction!getUserHomeDataByUserId?userId=<s:property value="id"/>">
												<img class="img-circle indexImg"
													src="<%=basePath%><s:property value="iconUrl"/>"> <s:property value="name" />
											</a>
										</div>
									</s:iterator></div></div>
							</div>
							<div class="tab-pane" id="tab5">
								<div class="row">
									<div class="span7">
									<s:iterator value="listFriendsToUserContext" status="s">
										<div class="span1">
											<a target="_blank"
												href="<%=basePath%>userInitUserHomeDataAction!getUserHomeDataByUserId?userId=<s:property value="id"/>">
												<img class="img-circle indexImg"
													src="<%=basePath%><s:property value="iconUrl"/>"> <s:property value="name" />
											</a>
										</div>
									</s:iterator></div></div>
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
	<script src="<%=basePath%>js/build/messenger.min.js"></script>
	<script src="<%=basePath%>js/build/MyMessenger.js"></script>
	<script src="<%=basePath%>js/build/backbone-0.9.10.js"></script>
	<script src="<%=basePath%>dwr/util.js"></script>
	<script src="<%=basePath%>dwr/engine.js"></script>
	<script src="<%=basePath%>dwr/interface/PushMsgDwrToUser.js"></script>
</body>
</html>
