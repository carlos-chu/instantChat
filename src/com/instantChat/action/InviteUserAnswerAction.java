package com.instantChat.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.instantChat.model.Tquestion;
import com.instantChat.model.Ttag;
import com.instantChat.model.Tuser;
import com.instantChat.model.TuserFriends;
import com.instantChat.pageModel.ProblemPageModel;
import com.instantChat.serviceI.QuestionServiceI;
import com.instantChat.serviceI.UserFriendsServiceI;
import com.instantChat.serviceI.UserQuestionServiceI;
import com.instantChat.serviceI.UserServiceI;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Controller("inviteUserAnswerAction")
public class InviteUserAnswerAction extends BaseAction implements ModelDriven<ProblemPageModel>{

	private static final long serialVersionUID = -806207401654181328L;
	ProblemPageModel problemDto = new ProblemPageModel();
	private UserFriendsServiceI userFriendsService;
	private UserServiceI userService;
	private QuestionServiceI questionService;
	private UserQuestionServiceI userQuestionService;
	//接受前台传过来的好友的id
	private String friendId;
	//接受前台传过来的问题的title
	private String title;
	//保存用户的在线好友
	private List<Tuser> userOnlineFriendsContext = new ArrayList<Tuser>();
	//保存邀请好友的信息对象
	private Tuser friendContext = new Tuser();
	//点击接受按钮从链接接受问问题者的id
	private String publishUserId;
	// 保存的回答者的id
	private String receiverUserId = new String();
	// 保存map类型的参数到前台，存放title和tags
	private Map<String, List<Ttag>> titleTagsContext = new HashMap<String, List<Ttag>>();
	// 保存question的id放到前台用于保存回复时用到question
	private Long questionIdContext = new Long(0);
	// 保存通过dwr聊天的用户的名字
	private String userNameContext = new String();
	// 保存判断是回答者的页面还是问问题者的页面的字段1是回答者0是问问题者
	private Integer isAnswerPageContext = new Integer(0);
	// 保存交流者互相的基本信息
	private Tuser otherUserContext = new Tuser();
	//保存判断字段（好友和普通）
	private Integer isFriendChat = new Integer(0);
	//保存判断是否有在线好友，没有给出提示
	private Integer ifHaveOnlineFriends = new Integer(0);

	//得到用户的在线好友（分页查询）
	public String getUserOnlineFriends(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Tuser tuser = (Tuser)session.get("user_session");
		int page = 1;
		int row = 10;
		if(tuser != null){
			List<TuserFriends> listFriendsOnline = userFriendsService.
							getListUserFriendsByUidAndPage(tuser.getId(), page, row);
			if(listFriendsOnline != null){
				List<Tuser> listTuserFriends = new ArrayList<Tuser>();
				for(TuserFriends listTF : listFriendsOnline){
					Tuser t = userService.getUserById(listTF.getFriendId());
					if(t != null){
						if(t.getIfOnline() == 1){
							listTuserFriends.add(t);
						}
					}
				}
				if(listTuserFriends != null && listTuserFriends.size() > 0){
					this.userOnlineFriendsContext = listTuserFriends;
					return "success";
				}
			}
		}
		this.ifHaveOnlineFriends = 1;
 		return "false";
	}
	
	//选择邀请回答的好友
	public String chooseFriend(){
		if(friendId != null){
			Map<String, Object> session = ActionContext.getContext().getSession();
			Tuser tuser = (Tuser)session.get("user_session");
			int page = 1;
			int row = 10;
			Tuser friend = userService.getUserById(friendId);
			if(friend != null && tuser != null){
				List<TuserFriends> listFriendsOnline = userFriendsService.
						getListUserFriendsByUidAndPage(tuser.getId(), page, row);
				if(listFriendsOnline != null){
					List<Tuser> listTuserFriends = new ArrayList<Tuser>();
					for(TuserFriends listTF : listFriendsOnline){
						Tuser t = userService.getUserById(listTF.getFriendId());
						if(t != null){
							if(t.getIfOnline() == 1){
								listTuserFriends.add(t);
							}
						}
					}
					if(listTuserFriends != null && listTuserFriends.size() > 0){
						this.userOnlineFriendsContext = listTuserFriends;
					}
				}
				this.friendContext = friend;
				return "success";
			}
		}
		return "false";
	}
	
	//跳转到好友交流页面（保存数据）
	public String saveProblem(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Tuser tuser = (Tuser)session.get("user_session");
		if(tuser != null && friendId != null){
			Tuser userFriend = userService.getUserById(friendId);
			//这是提问者的跳转
			if(userFriend != null && userFriend != null){
				this.receiverUserId = userFriend.getId();
				questionService.saveQuestion2(problemDto, tuser);
				//查询出来回答者的对象
				this.otherUserContext = userService.getUserById(userFriend.getId());
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
					this.isFriendChat = 1;
				}
				return "success";
			//这是回答者的跳转
			}
		}else if(tuser != null && publishUserId != null && friendId == null){
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
				this.isFriendChat = 1;
			}
			return "success";
		}
		return "false";
	}
	
	//get and set
	public Integer getIsFriendChat() {
		return isFriendChat;
	}
	public void setIsFriendChat(Integer isFriendChat) {
		this.isFriendChat = isFriendChat;
	}
	public UserQuestionServiceI getUserQuestionService() {
		return userQuestionService;
	}
	@Resource
	public void setUserQuestionService(UserQuestionServiceI userQuestionService) {
		this.userQuestionService = userQuestionService;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public QuestionServiceI getQuestionService() {
		return questionService;
	}
	@Resource
	public void setQuestionService(QuestionServiceI questionService) {
		this.questionService = questionService;
	}
	public String getPublishUserId() {
		return publishUserId;
	}

	public void setPublishUserId(String publishUserId) {
		this.publishUserId = publishUserId;
	}

	public String getReceiverUserId() {
		return receiverUserId;
	}

	public void setReceiverUserId(String receiverUserId) {
		this.receiverUserId = receiverUserId;
	}

	public Map<String, List<Ttag>> getTitleTagsContext() {
		return titleTagsContext;
	}

	public void setTitleTagsContext(Map<String, List<Ttag>> titleTagsContext) {
		this.titleTagsContext = titleTagsContext;
	}

	public Long getQuestionIdContext() {
		return questionIdContext;
	}

	public void setQuestionIdContext(Long questionIdContext) {
		this.questionIdContext = questionIdContext;
	}

	public String getUserNameContext() {
		return userNameContext;
	}

	public void setUserNameContext(String userNameContext) {
		this.userNameContext = userNameContext;
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
	public Tuser getFriendContext() {
		return friendContext;
	}
	public void setFriendContext(Tuser friendContext) {
		this.friendContext = friendContext;
	}
	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	public List<Tuser> getUserOnlineFriendsContext() {
		return userOnlineFriendsContext;
	}
	public void setUserOnlineFriendsContext(List<Tuser> userOnlineFriendsContext) {
		this.userOnlineFriendsContext = userOnlineFriendsContext;
	}

	public UserServiceI getUserService() {
		return userService;
	}
	@Resource
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}
	public UserFriendsServiceI getUserFriendsService() {
		return userFriendsService;
	}
	@Resource
	public void setUserFriendsService(UserFriendsServiceI userFriendsService) {
		this.userFriendsService = userFriendsService;
	}

	@Override
	public ProblemPageModel getModel() {
		return problemDto;
	} 
	public Integer getIfHaveOnlineFriends() {
		return ifHaveOnlineFriends;
	}
	public void setIfHaveOnlineFriends(Integer ifHaveOnlineFriends) {
		this.ifHaveOnlineFriends = ifHaveOnlineFriends;
	}

}
