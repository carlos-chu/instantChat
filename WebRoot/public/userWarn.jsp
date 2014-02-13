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
<title>chat--您的提醒</title>
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
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="http://x.libdd.com/farm1/c32ade/bf3254d2/DAF06.css" />
<![endif]-->
<style>
body {
	background-color: #CCCCFF;
	padding-top: 50px;
}
</style>
<script type="text/javascript">
function getPreviousWarns() {
	$('#showWarnsDiv').html(
			$('#buttonSpan').load("getMoreWarnsAction2!getWarns?ifNext=" + -1));
}
function getNextWarns() {
	$('#showWarnsDiv').html(
			$('#buttonSpan').load("getMoreWarnsAction2!getWarns?ifNext=" + 1));
}
</script>
</head>
<body>
	<div class="navbar  navbar-fixed-top">
		<s:include value="%{#request.getContextPath()}/include/head.jsp"></s:include>
		<!--页头 -->
	</div>
	<!--主内容开始-->
	<div id="container">
		<div class="row">
			<div class="span10">
				<div class="hero-unit">
					<div class="row">
						<div class="span6">
							<div class="tabbable">
								<ul class="nav nav-tabs">
									<li class="active"><a href="#tab1" data-toggle="tab">
											<button class="btn btn-link" type="button">您的新提醒</button> </a></li>
									<li><a href="#tab2" data-toggle="tab">
											<button class="btn btn-link" type="button">全部的提醒</button> </a></li>
								</ul>
								<div class="tab-content">
									<!--显示信息-->
									<div class="tab-pane active" id="tab1">
										<div class="showInformation1">
											<s:iterator value="listWarnContext" status="s">
												<span class="showInfoContent">
													<p>
														<s:property value="#s.index+1" />
														.&nbsp; <a class="warnUserStyle titleFont questionStyle"
															target="_blank"
															href="<%=basePath%>userInitUserHomeDataAction!getUserHomeDataByUserId?userId=<s:property value="friendtouserId.id"/>">
															<s:property value="friendtouserId.name" /> </a> &nbsp;
															<s:if test="grade != null">
																给了你<strong><s:property value="grade" /></strong>个星！作为感谢您的回答！
															</s:if><s:elseif test="questionId != null">
																评论了您的:<strong>
																	<a href="<%=basePath%>userDiscussAction!initQuestionById?questionId=<s:property value="questionId.id"/>">
																<s:property value="questionId.title" /></a></strong>的问题！
															</s:elseif><s:else>关注了你！</s:else>									
														<span class="titleFont timeStyle"> <s:property
																value="createtime" /> </span>
													</p> </span>
												<hr>
											</s:iterator>
										</div>
									</div>
									<div class="tab-pane" id="tab2">
										<div class="showInformation1" id="showWarnsDiv">
											<s:iterator value="listUnScanWarnContext" status="s">
												<span class="showInfoContent" id="showWarnsSpan">
													<p>
														<s:property value="#s.index+1" />
														.&nbsp; <a class="warnUserStyle titleFont questionStyle"
															target="_blank"
															href="<%=basePath%>userInitUserHomeDataAction!getUserHomeDataByUserId?userId=<s:property value="friendtouserId.id"/>">
															<s:property value="friendtouserId.name" /> </a> &nbsp;
															<s:if test="grade != null">
																给了你<strong><s:property value="grade" /></strong>个星！作为感谢您的回答！
															</s:if><s:elseif test="questionId != null">
																评论了您的:<strong>
																	<a href="<%=basePath%>userDiscussAction!initQuestionById?questionId=<s:property value="questionId.id"/>">
																<s:property value="questionId.title" /></a></strong>的问题！
															</s:elseif><s:else>关注了你！</s:else>
														<span class="titleFont timeStyle"> <s:property
																value="createtime" /> </span>
													</p></span>
												<hr>
											</s:iterator>
											<span id="buttonSpan">
												<ul class="pager">
													<s:if test="totalWarnsContext > 5">
														<li class="next"><a
															href="javascript:getNextWarns();">后页 &rarr;</a>
														</li>
													</s:if>
													<s:else>
														<li class="next disabled"><a href="#">后页 &rarr;</a>
														</li>
													</s:else>
												</ul> </span>
										</div>
									</div>
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
	<script src="<%=basePath%>js/Flat-UI/bootstrap-select.js"></script>
	<script src="<%=basePath%>js/Flat-UI/application.js"></script>
	<script src="<%=basePath%>js/build/MyMessenger.js"></script>
</body>
</html>
