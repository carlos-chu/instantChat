<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="navbar-inner">
	<div class="container-fluid">
		<a class="btn btn-navbar" data-toggle="collapse"
			data-target=".nav-collapse"> <span class="icon-bar"></span> <span
			class="icon-bar"></span> <span class="icon-bar"></span> </a> <a
			class="brand" href="#" name="top">LOGO</a>
		<div class="nav-collapse collapse">
			<ul class="nav">
				<li class="divider-vertical"></li>
				<li class="active"><a href="<%=basePath%>"> 
					<i class="icon-home"></i><strong 
							class="text-info titleFont">首页</strong> </a>
				</li>
				<li class="divider-vertical"></li>
				<li id="questionsPage"><a href="<%=basePath%>questionsAction!initQuestions">
					<i class="icon-question-sign"></i><strong
						class="text-info titleFont"> 问答广场</strong> </a>
				</li>
				<li class="divider-vertical"></li>
				<li id="usersPage"><a href="<%=basePath%>usersAction!initUsers">
					<i class="icon-eye-open"></i><strong
						class="text-info titleFont"> 达人广场</strong> </a>
				</li>
				<li class="divider-vertical"></li>
				<li id="discussesPage"><a href="<%=basePath%>discussesAction!initDiscusses">
					<i class="icon-th"></i><strong
						class="text-info titleFont"> 话题讨论</strong> </a>
				</li>
				<li class="divider-vertical"></li>
				<li class="divider-vertical"></li>
				<form class="navbar-search pull-left">
					<input type="text" class="search-query titleFont"
						placeholder="搜索想要的...">
				</form>
			</ul>
			<s:set name="userSession" value="#session.user_session.id"></s:set>
			<s:if test="#userSession == null">
				<ul class="nav pull-right">
					<li><a class="titleFont"
						href="<%=basePath%>public/register.jsp">注册</a></li>
					<li class="dropdown"><a class="dropdown-toggle titleFont"
						href="#" data-toggle="dropdown">登录 <strong class="caret"></strong>
					</a>
						<div class="dropdown-menu"
							style="padding: 15px; padding-bottom: 0px;">
							<form method="post"
								action="<%=basePath%>userLoginAction!loginUser"
								accept-charset="UTF-8">
								<input style="margin-bottom: 15px;" type="text" required placeholder="邮箱"
									id="username" name="email"> <input
									style="margin-bottom: 15px;" type="password" required placeholder="密码"
									id="password" name="password"> <input
									style="float: left; margin-right: 10px;" type="checkbox"
									name="info[remember]" id="remember-me" value="1"> <label
									class="string optional" for="user_remember_me"> 记住用户名密码</label>
								<input type="hidden" name="dosubmit" value="1"> <input
									class="btn btn-primary btn-block" type="submit" id="sign-in"
									value="登录">
							</form>
							<span style="height:10px; width:10px; display:block"></span>
						</div>
					</li>
				</ul>
			</s:if>
			<s:else>
				<ul class="nav pull-right">
					<li class="dropdown"><a class="dropdown-toggle" href="<%=basePath%>userWarnAction!getUserWarn">
							<i class="icon-envelope"></i> 消 息
							<s:if test="listSizeFriendsWarnContext != 0 && listSizeFriendsWarnContext != null">
								<span id="msgAlert"	class="badge badge-warning msg_num">
									<s:property value="listSizeFriendsWarnContext"/>
								</span></s:if></a>
					</li>
					<li class="dropdown"><a class="dropdown-toggle titleFont"
						data-toggle="dropdown" href="#"> <s:property
								value="#session.user_session.name" /> <b class="caret"></b> </a>
						<ul class="dropdown-menu">
							<li><a href="<%=basePath%>userInitUserHomeDataAction!getUserHomeDataByUserId?userId=<s:property value="#session.user_session.id"/>" >
								<i class="icon-home"></i> 个人主页</a>
							</li>
							<li><a href="<%=basePath%>userGetResetInfoAction!getTagsByUser">
							 <i class="icon-cog"></i> 个人设置 </a></li>
							<li class="divider">&nbsp;</li>
							<li><a href="#"> <i class="icon-off"></i> 退出</a>
							</li>
						</ul></li>
				</ul>
			</s:else>
		</div>
		<!--/.nav-collapse -->
	</div>
	<!--/.container-fluid -->
</div>