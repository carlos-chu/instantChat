package com.instantChat.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.instantChat.model.Tuser;
import com.instantChat.pageModel.Json;
import com.instantChat.pageModel.UserDto;
import com.instantChat.serviceI.FriendsUserWarnServiceI;
import com.instantChat.serviceI.QuestionServiceI;
import com.instantChat.serviceI.UserServiceI;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.Element;

@Controller("userAction")
public class UserAction extends BaseAction implements ModelDriven<UserDto>{
	
	UserDto userDto = new UserDto();
	private UserServiceI userService;
	private FriendsUserWarnServiceI friendsUserWarnService;
	private QuestionServiceI questionService;
	//接受前台的参数
	private String userId;
	private int grade;
	private String friendId;	//这是相对于提醒用户谁给评分的
	private int ifSolve;
	private Long questionId;

	//用户注册
	public String register(){
		Tuser tuser = userService.saveUser(userDto);
		//把新注册的用户放入session中，注意这是游离的对象，没有数据的
		if(tuser != null){
			Map<String, Object> session = ActionContext.getContext().getSession();
			HttpSession httpSession = ServletActionContext.getRequest().getSession();
			session.put("user_session", tuser);
			httpSession.setAttribute("user_httpSession", tuser);
			return "success";
		}
		return null;
	}
	
	//用户登录
	public String loginUser(){
		Tuser tuser = userService.login(userDto);
		//把登陆的用户放到session中
		if(tuser != null){
			Map<String, Object> session = ActionContext.getContext().getSession();
			HttpSession httpSession = ServletActionContext.getRequest().getSession();
			this.userService.updateUserIfOnline(tuser.getId(), 1);
			session.put("user_session", tuser);
			httpSession.setAttribute("user_httpSession", tuser);
			return "success";
		}
		return "false";
	}
	
	//得到用户的当前头像地址
	public void getUserTxIcon(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		//得到当前的session用户,这个session中是没有数据的，是游离的对象，不可取
		Tuser tuser_null = (Tuser)session.get("user_session");
		//得到持久化的对象
		Tuser tuser = userService.getUserById(tuser_null.getId());
		//使用阿里巴巴的JSON插件交互数据
		Json j = new Json();
		j.setFlag(true);
		j.setMsg(tuser.getIconUrl());
		super.writeJson(j);
	}
	
	//更新用户的介绍
	public String updateUserIntro(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Tuser tuser = (Tuser)session.get("user_session");
		if(userDto.getIntro().trim() != null){
			Boolean b = userService.updateUserIntro(tuser.getId(), userDto.getIntro());
			if(true == b){
				return "success";
			}
		}
		return null;
	}
	
	//更新用户的分数和提醒friendId用户有人给他评分,和更新问题解决状态
	public String updateUserGrade(){
		if(userId != null){
			int gradeBefore = this.userService.getUserGrade(userId);
			int gradeAfter = gradeBefore + grade;
			this.userService.updateUserGrade(userId, gradeAfter);
			
			if(friendId != "" && friendId != null){
				Tuser tuser_warn = userService.getUserById(friendId);
				Tuser tuser_my = userService.getUserById(userId);
				friendsUserWarnService.saveFriendsUserWarn(tuser_my, tuser_warn, (long) grade);
				//更新问题状态
				questionService.updateQuestionIsSolved(questionId, ifSolve);
			}
			return "success";
		}
		return "false"; 
	}
	
	//更新用户的接受问题状态
	public void updateUserAcceptAnswer(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Json j = new Json();
		Tuser tuser = (Tuser)session.get("user_session");
		if(tuser != null){
			Boolean b = userService.upadateUserAcceptAnswer(tuser.getId());
			j.setFlag(b);
			super.writeJson(j);
		}
	}
	
	//更新用户的接受问题状态
	public void updateUserCancelAcceptAnswer(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Json j = new Json();
		Tuser tuser = (Tuser)session.get("user_session");
		if(tuser != null){
			Boolean b = userService.updateUserCancelAccept(tuser.getId());
			j.setFlag(b);
			super.writeJson(j);
		}
	}
	
	//重写ModelDriven的方法
	@Override
	public UserDto getModel() {
		return userDto;
	}
/*	//重写SessionAware的方法
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}*/
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public QuestionServiceI getQuestionService() {
		return questionService;
	}
	@Resource
	public void setQuestionService(QuestionServiceI questionService) {
		this.questionService = questionService;
	}
	public int getIfSolve() {
		return ifSolve;
	}
	public void setIfSolve(int ifSolve) {
		this.ifSolve = ifSolve;
	}

	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public UserServiceI getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	public FriendsUserWarnServiceI getFriendsUserWarnService() {
		return friendsUserWarnService;
	}
	@Resource
	public void setFriendsUserWarnService(
			FriendsUserWarnServiceI friendsUserWarnService) {
		this.friendsUserWarnService = friendsUserWarnService;
	}
}













