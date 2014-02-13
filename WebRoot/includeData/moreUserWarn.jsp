<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<s:iterator value="listUnScanWarnContext" status="s">
	<span class="showInfoContent" id="showWarnsSpan">
		<p>
			<s:property value="#s.index+1" />
			.&nbsp; <a class="warnUserStyle titleFont questionStyle"
				target="_blank"
				href="<%=basePath%>userInitUserHomeDataAction!getUserHomeDataByUserId?userId=<s:property value="friendtouserId.id"/>">
				<s:property value="friendtouserId.name" /> </a> &nbsp;
			<s:if test="grade != null">
					给了你<strong><s:property value="grade" /> </strong>个星！作为感谢您的回答！
			</s:if><s:elseif test="questionId != null">
					评论了您的:<strong> <a
					href="<%=basePath%>userDiscussAction!initQuestionById?questionId=<s:property value="questionId.id"/>">
						<s:property value="questionId.title" /> </a> </strong>的问题！
			</s:elseif><s:else>关注了你！</s:else>
				<span class="titleFont timeStyle"> <s:property
					value="createtime" /> </span>
		</p> </span>
	<hr>
</s:iterator>
<span id="buttonSpan"> 
		<ul class="pager">
			<s:if test="ifFirstPage == 1">
				<li class="previous disabled"><a href="#">&larr; 前页</a></li>
			</s:if><s:else>
				<li class="previous"><a href="javascript:getPreviousWarns();">&larr; 前页</a></li>
			</s:else>
			<s:if test="totalWarnsContext > 5">
				<li class="next"><a href="javascript:getNextWarns();">后页 &rarr;</a></li>
			</s:if><s:else>
				<li class="next disabled"><a href="#">后页 &rarr;</a></li>
			</s:else>
		</ul>
</span>
	