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
<title>chat--话题讨论</title>
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
		<!--页头 -->
		<s:include
			value="%{#request.getContextPath()}/include/head_discusses.jsp"></s:include>
	</div>
	<!--主内容开始-->
	<div id="container">
		<!--话题实时共享，媒体新闻实时评论，问题共享回答模块模板-->
		<div id="main">
			<div class="post photo">
				<span class="date"><h4 class="indexTitle titleFont">问题讨论	</h4>
				</span>
				<hr style="FILTER: alpha(opacity=100,finishopacity=0,style=1)"
					width="100%" color=#987cb9 size=3 />
				<!--显示信息-->
				<div class="hero-unit">
					<div class="showInformation1">
						<s:iterator value="questionsContext" status="s">
							<span class="showInfoContent"> <a target="_blank"
								href="<%=basePath%>userDiscussAction!initQuestionById?questionId=<s:property value="id"/>">
									<h5>
										<p class="titleFont questionStyle">
											<s:property value="#s.index+1" />
											.&nbsp;
											<s:property value="title" />
										</p>
									</h5> </a>
								<h6 class="titleFont timeStyle">
									<s:property value="createtime" />
								</h6> </span>
							<br>
							<hr>
						</s:iterator>
					</div>
				</div>
				<div class="pagination pagination-centered">
					<ul>
						<s:if test="currentPageContext != null && currentPageContext > 1">
							<li><a href="<%=basePath%>discussesAction!initDiscusses?page=1">
									首页</a>
							</li>
						</s:if><s:else>
							<li class="disabled"><a href="#">首页</a>
							</li>
						</s:else>
						<s:if test="totalPageContext < 3">
							<s:iterator value="new int[totalPageContext]" status="s">
						 		<li><a href="<%=basePath%>discussesAction!initDiscusses?page=<s:property value="#s.index+1" />">
										<s:property value="#s.index+1" /></a>
								</li>
							</s:iterator>
						</s:if><s:elseif test="currentPageContext == 0 || currentPageContext == 1">
						 	<li><a href="<%=basePath%>discussesAction!initDiscusses?page=1">1</a></li>
						 	<li><a href="<%=basePath%>discussesAction!initDiscusses?page=2">2</a></li>
						 	<li><a href="<%=basePath%>discussesAction!initDiscusses?page=3">3</a></li>
						 	<s:if test="totalPageContext > 3">
						 		<li><a href="<%=basePath%>discussesAction!initDiscusses?page=4">...</a></li>
						 	</s:if>
						 </s:elseif><s:elseif test="currentPageContext != 1 && currentPageContext != 0">
						 	<li><a href="<%=basePath%>discussesAction!initDiscusses?page=<s:property value="currentPageContext-1"/>">
										<s:property value="currentPageContext-1"/></a></li>
						 	<li><a href="<%=basePath%>discussesAction!initDiscusses?page=<s:property value="currentPageContext"/>">
										<s:property value="currentPageContext"/></a></li>
							<s:if test="totalPageContext > currentPageContext">
								<li><a href="<%=basePath%>discussesAction!initDiscusses?page=<s:property value="currentPageContext+1"/>">
										<s:property value="currentPageContext+1"/></a></li>
							</s:if>
						 </s:elseif>
						<s:if test="currentPageContext != 0 && currentPageContext < totalPageContext">
							<li><a href="<%=basePath%>discussesAction!initDiscusses?page=<s:property value="totalPageContext"/>">末页</a>
							</li>
						</s:if><s:else>
							<li class="disabled"><a href="#">末页</a>
							</li>
						</s:else>
					</ul>
				</div>
				<hr />
				<div class="clear"></div>
			</div>
		</div>
		<%--右边导航框 --%>
		<s:include
			value="%{#request.getContextPath()}/include/sidebar_index.jsp"></s:include>
	</div>
	<!--页尾-->
	<s:include value="%{#request.getContextPath()}/include/footer.jsp"></s:include>

	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/bootstrap.min.js"></script>
	<script src="<%=basePath%>js/build/messenger.min.js"></script>
	<script src="<%=basePath%>js/build/MyMessenger.js"></script>
	<script src="<%=basePath%>js/build/backbone-0.9.10.js"></script>
	<script src="<%=basePath%>js/MyJS/MyJS.js"></script>
	<script src="<%=basePath%>dwr/util.js"></script>
	<script src="<%=basePath%>dwr/engine.js"></script>
</body>
</html>
