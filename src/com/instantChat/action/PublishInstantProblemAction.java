package com.instantChat.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.instantChat.dwrAction.PushMsgDwrAction;
import com.instantChat.model.Tquestion;
import com.instantChat.model.Ttag;
import com.instantChat.model.Tuser;
import com.instantChat.pageModel.ProblemPageModel;
import com.instantChat.serviceI.QuestionServiceI;
import com.instantChat.serviceI.UserQuestionServiceI;
import com.instantChat.serviceI.UserServiceI;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class PublishInstantProblemAction extends BaseAction implements ModelDriven<ProblemPageModel> {
	/**
	 * 处理用户发表即时回答的问题,寻求匹配
	 */
	private static final long serialVersionUID = 1L;
	ProblemPageModel problemDto = new ProblemPageModel();
	private QuestionServiceI questionService;
	private UserServiceI userService;
	private UserQuestionServiceI userQuestionService;
	private PushMsgDwrAction pushMsgDwrAction;
	//点击接受按钮从链接接受问问题者的id
	private String publishUserId;
	//保存的回答者的id
	private String receiverUserId = new String();
	//保存map类型的参数到前台，存放title和tags
	private Map<String, List<Ttag>> titleTagsContext = new HashMap<String, List<Ttag>>();
	//保存question的id放到前台用于保存回复时用到question
	private Long questionIdContext = new Long(0);
	//保存通过dwr聊天的用户的名字
	private String userNameContext = new String();
	//保存判断是回答者的页面还是问问题者的页面的字段1是回答者0是问问题者
	private Integer isAnswerPageContext = new Integer(0);
	//保存交流者互相的基本信息
	private Tuser otherUserContext =  new Tuser();
	
	//保存判断弹出警告框的字段
	private Integer ifAlert = new Integer(0);

	//用户发表问题保存问题到数据库（判断下是谁的跳转分回答者会问问题者）
	public String saveProblem(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Tuser tuser = (Tuser)session.get("user_session");
		Tuser goalUser = null;
		if(problemDto.getTag1() != null){
			goalUser = pushMsgDwrAction.searchGoalUser(tuser.getId(), problemDto.getTag1(), 
					problemDto.getTag2(), problemDto.getTag3());
		}
		if(tuser != null && goalUser != null){ 			//这是响应匹配时的处理
			this.receiverUserId = goalUser.getId();		//把回答者的id放到前台
			questionService.saveQuestion(problemDto, tuser);
			//查询出来回答者的对象
			this.otherUserContext = userService.getUserById(goalUser.getId());
			//查询刚刚保存的question
			Tquestion tquestion = questionService.getMaxIdQuestionByUserId(tuser);
			//同样查询出来刚刚保存的问题
			String userId = tuser.getId();
			Map<String, List<Ttag>> mapQuestion = questionService.getQuestionAndTagByUserId_QuestionIdOfMax(userId);
			if(mapQuestion != null && tquestion != null){
				this.titleTagsContext = mapQuestion;
				this.questionIdContext = tquestion.getId();
				this.userNameContext = tuser.getName();
				this.isAnswerPageContext = 0;
				return "success";
			}
		}else if(tuser != null && goalUser == null){   	//这是判断是点击接受按钮时的处理
			this.receiverUserId = publishUserId; 		//把问问题者的id放到前台
			Tuser publishUser = userService.getUserById(publishUserId); 
			this.otherUserContext = publishUser;
			//查询刚刚保存的question
			Tquestion tquestion = questionService.getMaxIdQuestionByUserId(publishUser);
			Map<String, List<Ttag>> mapQuestion = 
					questionService.getQuestionAndTagByUserId_QuestionIdOfMax(publishUserId);
			if(mapQuestion != null && tquestion != null){
				//当回答者点击接受按钮时，保存问题的ID到（用户问题）表（tuser_question）
				this.userQuestionService.saveUserQuestion(tuser, tquestion.getId());
				this.titleTagsContext = mapQuestion;
				this.questionIdContext = tquestion.getId();
				this.userNameContext = tuser.getName();
				this.isAnswerPageContext = 1;
				return "success";
			}
		}
		this.ifAlert = 1;
		return "false";
	}
	
	//getter和setter方法
	public Integer getIfAlert() {
		return ifAlert;
	}
	public void setIfAlert(Integer ifAlert) {
		this.ifAlert = ifAlert;
	}

	public Long getQuestionIdContext() {
		return questionIdContext;
	}
	public void setQuestionIdContext(Long questionIdContext) {
		this.questionIdContext = questionIdContext;
	}
	public Map<String, List<Ttag>> getTitleTagsContext() {
		return titleTagsContext;
	}
	public void setTitleTagsContext(Map<String, List<Ttag>> titleTagsContext) {
		this.titleTagsContext = titleTagsContext;
	}
	public String getReceiverUserId() {
		return receiverUserId;
	}
	public void setReceiverUserId(String receiverUserId) {
		this.receiverUserId = receiverUserId;
	}
	public String getPublishUserId() {
		return publishUserId;
	}
	public void setPublishUserId(String publishUserId) {
		this.publishUserId = publishUserId;
	}
	public PushMsgDwrAction getPushMsgDwrAction() {
		return pushMsgDwrAction;
	}
	@Resource
	public void setPushMsgDwrAction(PushMsgDwrAction pushMsgDwrAction) {
		this.pushMsgDwrAction = pushMsgDwrAction;
	}
	public UserServiceI getUserService() {
		return userService;
	}
	@Resource
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}
	public QuestionServiceI getQuestionService() {
		return questionService;
	}
	@Resource
	public void setQuestionService(QuestionServiceI questionService) {
		this.questionService = questionService;
	}
	@Override
	public ProblemPageModel getModel() {
		return problemDto;
	}
	public String getUserNameContext() {
		return userNameContext;
	}
	public void setUserNameContext(String userNameContext) {
		this.userNameContext = userNameContext;
	}
	public UserQuestionServiceI getUserQuestionService() {
		return userQuestionService;
	}
	@Resource
	public void setUserQuestionService(UserQuestionServiceI userQuestionService) {
		this.userQuestionService = userQuestionService;
	}
	public Integer getIsAnswerPageContext() {
		return isAnswerPageContext;
	}
	public void setIsAnswerPageContext(Integer isAnswerPageContext) {
		this.isAnswerPageContext = isAnswerPageContext;
	}
	public Tuser getOtherUserContext() {
		return otherUserContext;
	}

	public void setOtherUserContext(Tuser otherUserContext) {
		this.otherUserContext = otherUserContext;
	}

}








