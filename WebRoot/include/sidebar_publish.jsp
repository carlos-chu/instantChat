<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!--右边侧导航栏-->
<div id="sidebar">
	<!--发表问答按钮-->
	<div class="publishQuestion">
		<a title="邀请您的好友解决您的问题！" href="<%=basePath%>userInviteFriendAction!getUserOnlineFriends">
			<button class="btn btn-large" id="yaoqingButton" type="button">邀请您的好友回答问题!</button>
		</a>
		<div class="clear"></div>
	</div>
	<div class="publishQuestion mainFont">
		<p>
			这是一个即时问答社区，记得关注回答您问题的好友哦~<br>
			这样您可以邀请您的在线好友为您解答问题！
		</p><p>
			我们是通过为您匹配适合回答您问题的达人，问您即时解决困扰您的问题，
			希望您尽量开启"接受问题"（在首页中设置），这样您也能为别人解决问题！
		</p>
	</div>
</div>







