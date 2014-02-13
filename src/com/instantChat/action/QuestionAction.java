package com.instantChat.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.instantChat.model.TfriendsUserWarn;
import com.instantChat.model.Tquestion;
import com.instantChat.model.Trespond;
import com.instantChat.model.Ttag;
import com.instantChat.model.Tuser;
import com.instantChat.serviceI.FriendsUserWarnServiceI;
import com.instantChat.serviceI.QuestionServiceI;
import com.instantChat.serviceI.RespondServiceI;
import com.instantChat.serviceI.TagServiceI;
import com.instantChat.serviceI.UserServiceI;
import com.opensymphony.xwork2.ActionContext;

@Controller("questionAction")
public class QuestionAction extends BaseAction{

	private static final long serialVersionUID = 6022155378579655408L;
	private QuestionServiceI questionService;
	private TagServiceI tagService;
	private UserServiceI userService;
	private RespondServiceI respondService;
	private FriendsUserWarnServiceI friendsUserWarnService;
	// 保存用户的添加他为好友的提醒
	private Integer listSizeFriendsWarnContext = new Integer(0);
	//接受前台传过来的问题的id
	private Long questionId;
	//保存参数到valueStock
	private Tquestion questionContext = new Tquestion();
	private Tuser publishUserContext = new Tuser();
	private List<Ttag> tagsContext = new ArrayList<Ttag>();
	private List<Trespond> respondsContext = new ArrayList<Trespond>();
	
	//通过questionId初始化问题的数据
	public String initQuestionById(){
		if(questionId != null){
			//1问题本身对象
			Tquestion question = questionService.getQuestionById(questionId);
			if(question != null){
				this.questionContext = question;
			}
			//2提问者
			Tuser publishUser = question.getTuser();
			if(publishUser != null){
				this.publishUserContext = publishUser;
			}
			//3问题的标签
			List<Ttag> listTagsOfQuestion = tagService.getListTagsByQuestionId(question);
			if(listTagsOfQuestion != null){
				this.tagsContext = listTagsOfQuestion;
			}
			//4问题的回复
			List<Trespond> listResponds = respondService.getRespondsByQuestionId(question);
			if(listResponds != null){
				this.respondsContext = listResponds;
			}
			// 用户的提醒
			Map<String, Object> session = ActionContext.getContext().getSession();
			Tuser tuser = (Tuser) session.get("user_session");
			List<TfriendsUserWarn> listWarns = friendsUserWarnService
					.getListFriendsWarn(tuser, 0);
			if (listWarns != null && listWarns.size() > 0) {
				this.listSizeFriendsWarnContext = listWarns.size();
			}
			return "success";
		}
		return "false";
	}
	
	//get and set
	public QuestionServiceI getQuestionService() {
		return questionService;
	}
	@Resource
	public void setQuestionService(QuestionServiceI questionService) {
		this.questionService = questionService;
	}
	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
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
	public RespondServiceI getRespondService() {
		return respondService;
	}
	@Resource
	public void setRespondService(RespondServiceI respondService) {
		this.respondService = respondService;
	}
	public Tquestion getQuestionContext() {
		return questionContext;
	}

	public void setQuestionContext(Tquestion questionContext) {
		this.questionContext = questionContext;
	}

	public Tuser getPublishUserContext() {
		return publishUserContext;
	}

	public void setPublishUserContext(Tuser publishUserContext) {
		this.publishUserContext = publishUserContext;
	}

	public List<Ttag> getTagsContext() {
		return tagsContext;
	}

	public void setTagsContext(List<Ttag> tagsContext) {
		this.tagsContext = tagsContext;
	}

	public List<Trespond> getRespondsContext() {
		return respondsContext;
	}

	public void setRespondsContext(List<Trespond> respondsContext) {
		this.respondsContext = respondsContext;
	}
	public Integer getListSizeFriendsWarnContext() {
		return listSizeFriendsWarnContext;
	}
	public void setListSizeFriendsWarnContext(Integer listSizeFriendsWarnContext) {
		this.listSizeFriendsWarnContext = listSizeFriendsWarnContext;
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
