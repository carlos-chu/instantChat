package com.instantChat.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.instantChat.model.Tuser;
import com.instantChat.pageModel.Json;
import com.instantChat.serviceI.FriendsUserWarnServiceI;
import com.instantChat.serviceI.UserFriendsServiceI;
import com.instantChat.serviceI.UserServiceI;
import com.opensymphony.xwork2.ActionContext;

@Controller("userFriendsAction")
public class UserFriendsAction extends BaseAction{

	private static final long serialVersionUID = 4203567827396330850L;
	private UserFriendsServiceI userFriendsServie;
	private FriendsUserWarnServiceI friendsUserWarnService;
	private UserServiceI userService;
	//接受前台传过来的fid的参数
	private String fid;
	
	//保存用户关注的人到tuserFriends表中，包括显示在通知栏
	public void saveUserFriends(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Tuser tuser = (Tuser)session.get("user_session");
		Json j = new Json();
		if(tuser != null && fid != null){
			this.userFriendsServie.saveUserFriends(tuser.getId(), fid);
			//保存到关注用户的提醒表中
			Tuser tuserOfWarn = userService.getUserById(fid);
			if(tuserOfWarn != null){
				this.friendsUserWarnService.saveFriendsUserWarn(tuserOfWarn, tuser);
			}
			j.setFlag(true);
			super.writeJson(j);
		}
	}
	
	//用户取消关注某用户时调用其方法
	public void deleteUserFriendsByFid(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Json j = new Json();
		if(session != null){
			Tuser tuser  = (Tuser)session.get("user_session");
			if(tuser != null && fid != null){
				this.userFriendsServie.deleteUserFriendByFid(tuser.getId(), fid);
				j.setFlag(true);
				super.writeJson(j);
			}
		}
	}
	
	//get and set
	public UserServiceI getUserService() {
		return userService;
	}
	@Resource
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}
	public UserFriendsServiceI getUserFriendsServie() {
		return userFriendsServie;
	}
	@Resource
	public void setUserFriendsServie(UserFriendsServiceI userFriendsServie) {
		this.userFriendsServie = userFriendsServie;
	}
	public FriendsUserWarnServiceI getFriendsUserWarnService() {
		return friendsUserWarnService;
	}
	@Resource
	public void setFriendsUserWarnService(
			FriendsUserWarnServiceI friendsUserWarnService) {
		this.friendsUserWarnService = friendsUserWarnService;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
}









