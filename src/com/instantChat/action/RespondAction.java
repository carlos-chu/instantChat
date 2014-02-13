package com.instantChat.action;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.instantChat.model.Tquestion;
import com.instantChat.model.Tuser;
import com.instantChat.pageModel.Json;
import com.instantChat.serviceI.FriendsUserWarnServiceI;
import com.instantChat.serviceI.QuestionServiceI;
import com.instantChat.serviceI.RespondServiceI;
import com.opensymphony.xwork2.ActionContext;

@Controller("respondAction")
public class RespondAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private RespondServiceI respondService;
	private QuestionServiceI questionService;
	private FriendsUserWarnServiceI friendsUserWarnService;
	//接受前台传过来的question对象
	private Long questionId;
	//接受前台传过来的回复内容
	private String respondContent;

	public String getRespondContent() {
		return respondContent;
	}
	public void setRespondContent(String respondContent) {
		this.respondContent = respondContent;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public RespondServiceI getRespondService() {
		return respondService;
	}
	@Resource
	public void setRespondService(RespondServiceI respondService) {
		this.respondService = respondService;
	}
	public QuestionServiceI getQuestionService() {
		return questionService;
	}
	@Resource
	public void setQuestionService(QuestionServiceI questionService) {
		this.questionService = questionService;
	}
	public FriendsUserWarnServiceI getFriendsUserWarnService() {
		return friendsUserWarnService;
	}
	@Resource
	public void setFriendsUserWarnService(
			FriendsUserWarnServiceI friendsUserWarnService) {
		this.friendsUserWarnService = friendsUserWarnService;
	}

	//保存单人聊天模式下的对话
	public void saveInstantChat(){
		Json j = new Json();
		Map<String, Object> session = ActionContext.getContext().getSession();
		Tuser tuser = (Tuser)session.get("user_session");
		String respondContextUTF;
		if(tuser != null && respondContent != null && questionId != null){
			try {
				respondContextUTF = new String(respondContent.trim().getBytes("iso-8859-1"), "utf-8");
				Tquestion question = questionService.getQuestionById(questionId);
				this.respondService.saveRespond(tuser, respondContextUTF, question);
				
				j.setFlag(true);
				j.setMsg(respondContextUTF);
				j.setMsg1(tuser.getId());
				j.setUserName(tuser.getName());
				super.writeJson(j);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
	//保存问题讨论信息和保存提醒
	public void saveDiscussAndWarn(){
		Json j = new Json();
		Map<String, Object> session = ActionContext.getContext().getSession();
		Tuser tuser = (Tuser)session.get("user_session");
		String respondContextUTF;
		if(tuser != null && respondContent != null && questionId != null){
			try {
				respondContextUTF = new String(respondContent.trim().getBytes("iso-8859-1"), "utf-8");
				Tquestion question = questionService.getQuestionById(questionId);
				this.respondService.saveRespond(tuser, respondContextUTF, question);
				
				j.setFlag(true);
				j.setMsg(respondContextUTF);
				j.setMsg1(tuser.getId());
				j.setUserName(tuser.getName());
				super.writeJson(j);
				//保存提醒
				Tuser userWarn = question.getTuser();
					if(!userWarn.getId().equals(tuser.getId())){
						friendsUserWarnService.saveFriendsUserWarn(userWarn, tuser, question);
					}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
	
}













