<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!--右边侧导航栏-->
<div id="sidebar">
	<s:set name="userSession" value="#session.user_session.id"></s:set>
	<s:if test="#userSession == null">
		<div class="publishQuestion">
			<a title="加入我们，即刻解决您的问题！" href="<%=basePath%>public/register.jsp">
				<button class="btn btn-block btn-info btn-large" type="button">加
					入 i问</button> </a>
			<div class="clear"></div>
		</div>
	</s:if>
	<s:else>
		<!--发表问答按钮-->
		<div class="publishQuestion">
			<a title="发表您的问题，马上解除烦恼！"
				href="<%=basePath%>userPublicAnswerAction!getUserDataBySession">
				<button class="btn btn-large btn-primary" type="button">发表问题</button>
			</a>
			<s:set name="isAccept" value="#session.user_session.ifaccept"></s:set>
			<s:if test="#isAccept == 0">
				<a title="开启接受问题模式" target="_blank" id="starButtonSpan">
					<button id="startAnswer" class="btn btn-large" type="button">接受问题</button>
				</a>
			</s:if>
			<s:else>
				<a title="关闭接受问题模式" target="_blank" id="starButtonSpan">
					<button id="cancelAnswer" class="btn btn-large" type="button">取消接受</button>
				</a>
			</s:else>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
		<div class="publishQuestion">您还可以邀请您的好友回答：</div>
		<div class="publishQuestion">
			<a title="邀请您的好友解决您的问题！"
				href="<%=basePath%>userInviteFriendAction!getUserOnlineFriends">
				<button class="btn btn-large" id="yaoqingButton" type="button">邀请您的好友回答问题!</button>
			</a>
			<div class="clear"></div>
		</div>
	</s:else>
	<div class="publishQuestion mainFont">
		<p>
			这是一个即时问答社区，记得关注回答您问题的好友哦~<br> 这样您可以邀请您的在线好友为您解答问题！
		</p>
		<p>我们是通过为您匹配适合回答您问题的达人，问您即时解决困扰您的问题，
			希望您尽量开启"接受问题"（在首页中设置），这样您也能为别人解决问题！</p>
	</div>
</div>