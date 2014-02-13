package com.instantChat.action;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.instantChat.model.Tuser;
import com.instantChat.serviceI.TagServiceI;
import com.opensymphony.xwork2.ActionContext;

@Controller("tagAction")
public class TagAction extends BaseAction {

	private TagServiceI tagService;
	private String tagValue;
	
	//接受ajax传过来的标签参数
	public String getTagValue() {
		return tagValue;
	}
	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}
	
	public TagServiceI getTagService() {
		return tagService;
	}
	@Resource
	public void setTagService(TagServiceI tagService) {
		this.tagService = tagService;
	}

	//用户添加标签
	public void addUserTags(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Tuser tuser = (Tuser)session.get("user_session");
		//解决浏览器的乱码问题，兼容性没有测试
		String tagValueUTF8;
		if(null != tuser){
			try {
				tagValueUTF8 = new String(tagValue.trim().getBytes("iso-8859-1"), "utf-8");
				tagService.addUserTags(tagValueUTF8, tuser);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
	
	//用户删除标签
	public void deleteUserTags(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Tuser tuser = (Tuser)session.get("user_session");
		String tagValueUTF8;
		if(null != tuser){
			try {
				tagValueUTF8 = new String(tagValue.trim().getBytes("iso-8859-1"), "utf-8");
				tagService.deleteUserTags(tuser, tagValueUTF8);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
	
}








