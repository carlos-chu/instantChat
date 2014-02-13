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
	<div class="clear"></div>
	<s:if test="isAnswerPageContext == 0">
		<div class="publishQuestion">
			<i class="icon-ok-sign"></i>这个问题是否解决：<br>
				<select id="ifSolve" name="pingfenGoal">
  					<option value=1>解决了！</option>
  					<option value=0>还没解决！</option>
				</select>
			<i class=" icon-gift"></i>请为这次回答评分：<br>
			<div class="clear"></div>
				<select id="pingfenGoal" name="pingfenGoal">
  					<option value=5>辛苦分-->5个星</option>
  					<option value=10>心情分-->10个星</option>
  					<option value=15>诚意分-->15个星</option>
  					<option value=25>收获分-->25个星</option>
  					<option value=50>满意分-->50个星</option>
				</select>
				<div class="pingfen">
					<button id="pingfenButton" class="btn btn-warning" type="button">确 定</button>
				</div>
			<div class="clear"></div>
		</div>
	</s:if>
	<div class="clear"></div>
	<s:if test="otherUserContext != null">
		<div class="publishQuestion mainFont">
		<i class="icon-user"></i>与您交流的是：<br><br>
		<a target="_blank" href="">
			<img class="img-rounded onlineUserImg" 
				src="<%=basePath%><s:property value="otherUserContext.iconUrl"/>">&nbsp;&nbsp;
				<s:property value="otherUserContext.name"/></a><br>
		<i class="icon-star-empty"></i>他拥有的星数：
		<span class="label label-warning"><s:property value="otherUserContext.grade"/></span><br>
		<i class="icon-pencil"></i>他的签名是：<br>
		<p><s:property value="otherUserContext.intro"/></p>
	</div>
	</s:if><s:else>
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
		<div class="publishQuestion mainFont">
		<p>
			这是一个即时问答社区，记得关注回答您问题的好友哦~<br> 这样您可以邀请您的在线好友为您解答问题！
		</p>
		<p>我们是通过为您匹配适合回答您问题的达人，问您即时解决困扰您的问题，
			希望您尽量开启"接受问题"（在首页中设置），这样您也能为别人解决问题！</p>
	</div>
	</s:else>
</div>







