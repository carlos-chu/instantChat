<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
				</h6> </span><br> <br>
		</div>
	</div>
</s:iterator>
<span id="buttonSpan">
<s:property value="totalDataContext" />
	<ul class="pager">
		<s:if test="ifFirstPage == 1">
			<li class="previous disabled"><a href="#">&larr; 前页</a></li>
		</s:if>
		<s:else>
			<li class="previous"><a
				href="javascript:getPreviousQuestions();">&larr; 前页</a></li>
		</s:else>
		<s:if test="totalDataContext > 1">
			<li class="next"><a href="javascript:getNextQuestions();">后页
					&rarr;</a></li>
		</s:if>
		<s:else>
			<li class="next disabled"><a href="#">后页 &rarr;</a></li>
		</s:else>
	</ul> </span>