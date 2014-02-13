package com.instantChat.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.instantChat.model.Ttag;
import com.instantChat.model.Tuser;
import com.instantChat.pageModel.Json;
import com.instantChat.serviceI.TagServiceI;
import com.instantChat.serviceI.UserServiceI;
import com.opensymphony.xwork2.ActionContext;

@Controller("resetAction")
public class ResetInfoAction extends BaseAction{

	private TagServiceI tagService;
	private UserServiceI userService;
	private List<Ttag> listTagsOfUserContext = new ArrayList<Ttag>();
	//持久化后的对象
	private Tuser userContext = new Tuser();
	//接受前台的intro,username参数
	private String resetIntro;
	private String resetName;
	private String resetPassword;

	//修改信息先得到用户的标签
	public String getTagsByUser(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		//游离的对象
		Tuser tuser = (Tuser)session.get("user_session");
		if(tuser != null){
			Tuser user = userService.getUserById(tuser.getId());
			List<Ttag> listTags = tagService.getListTagsByUserId(tuser);
			if(user != null){
				this.listTagsOfUserContext = listTags;
				this.userContext = user;
				return "success";
			}
		}
		return "false";
	}

	//修改用户信息名字和签名方式
	public void updateUserInfo(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Tuser tuser = (Tuser)session.get("user_session");
		if(tuser != null){
			Json j = new Json();
			try {
				String resetIntroUTF = new String(resetIntro.trim().getBytes("iso-8859-1"), "utf-8");
				String nameUTF = new String(resetName.trim().getBytes("iso-8859-1"), "utf-8");
				userService.updateUserIntro(tuser.getId(), resetIntroUTF);
				userService.updateUserName(tuser.getId(), nameUTF);
				j.setFlag(true);
				super.writeJson(j);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
	
	//修改用户的密码
	public void updateUserPassword(){
		if(resetPassword != null){
			Map<String, Object> session = ActionContext.getContext().getSession();
			Tuser tuser = (Tuser)session.get("user_session");
			Json j = new Json();
			try {
				String passwordUTF = new String(resetPassword.trim().getBytes("iso-8859-1"), "utf-8");
				this.userService.updateUserPassword(tuser.getId(), passwordUTF);
				j.setFlag(true);
				super.writeJson(j);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
	
	//get and set
	public List<Ttag> getListTagsOfUserContext() {
		return listTagsOfUserContext;
	}
	public void setListTagsOfUserContext(List<Ttag> listTagsOfUserContext) {
		this.listTagsOfUserContext = listTagsOfUserContext;
	}
	public TagServiceI getTagService() {
		return tagService;
	}
	@Resource
	public void setTagService(TagServiceI tagService) {
		this.tagService = tagService;
	}
	public UserServiceI getUserService() {
		return userService;
	}
	@Resource
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}
	public String getResetIntro() {
		return resetIntro;
	}
	public void setResetIntro(String resetIntro) {
		this.resetIntro = resetIntro;
	}
	public String getResetName() {
		return resetName;
	}
	public void setResetName(String resetName) {
		this.resetName = resetName;
	}
	public String getResetPassword() {
		return resetPassword;
	}
	public void setResetPassword(String resetPassword) {
		this.resetPassword = resetPassword;
	}
	public Tuser getUserContext() {
		return userContext;
	}
	public void setUserContext(Tuser userContext) {
		this.userContext = userContext;
	}
}










