package com.instantChat.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.instantChat.model.TfriendsUserWarn;
import com.instantChat.model.Tquestion;
import com.instantChat.model.Ttag;
import com.instantChat.model.Tuser;
import com.instantChat.model.TuserFriends;
import com.instantChat.model.TuserQuestion;
import com.instantChat.serviceI.FriendsUserWarnServiceI;
import com.instantChat.serviceI.QuestionServiceI;
import com.instantChat.serviceI.TagServiceI;
import com.instantChat.serviceI.UserFriendsServiceI;
import com.instantChat.serviceI.UserQuestionServiceI;
import com.instantChat.serviceI.UserServiceI;
import com.opensymphony.xwork2.ActionContext;

/*
 * 初始化用户数据，用于前台显示
 */
@Controller("initUserDataAction")
public class InitUserDataAction extends BaseAction{
	
	private static final long serialVersionUID = 2587109208659550883L;
	private UserServiceI userService;
	private TagServiceI tagService;
	private QuestionServiceI questionService;
	private UserQuestionServiceI userQuestionService;
	private UserFriendsServiceI userFriendsService;
	private FriendsUserWarnServiceI friendsUserWarnService;
	private String userId;
	private Integer ifNext = new Integer(0);
	//保存是否是第一页
	private Integer ifFirst = new Integer(0);
	//存放user的数据到actioncontext中，用于前台显示
	private Tuser tuserContext = new Tuser();
	//存放用户的标签到actioncontext中，用于前台显示
	private List<Ttag> tagListContext = new ArrayList<Ttag>();
	//存放用户的回答过的问题
	private List<Tquestion> listQuestionAnswersContext = new ArrayList<Tquestion>();
	//存放用户跟随的问题
	private List<Tquestion> listQuestionFellowsContext = new ArrayList<Tquestion>();
	//存放用户发表的问题
	private List<Tquestion> listQuestionContext = new ArrayList<Tquestion>();
	//保存用户的关注的人
	private List<Tuser> listUserFriendsContext = new ArrayList<Tuser>();
	//保存关注用户的人
	private List<Tuser> listFriendsToUserContext = new ArrayList<Tuser>();
	//保存用户查看某个用户时判断是否关注该用户的字段1表示关注0表示没有关注
	private Integer ifFellowPeople = new Integer(0);
	//保存查看用户拥有的分数
	private Integer userGradeContext = new Integer(0);
	//保存用户的添加他为好友的提醒
	private Integer listSizeFriendsWarnContext = new Integer(0);
	//保存用户的个数据的总数
	private Integer totalDataContext = new Integer(0);

	//通过session得到当前登录用户的信息
	public String getUserDataBySession(){
		Map<String,Object> session = ActionContext.getContext().getSession();
		Tuser tuser = (Tuser)session.get("user_session");		//持久化的对象，数据没更新
		if(tuser != null){
			Tuser user = userService.getUserById(tuser.getId());	//更新后的对象，有数据
			if(tuser != null && user != null){
				this.tuserContext = user;
				return "success";
			}
		}
		return "false";
	}

	//通过id得到用户的信息
	public String getUserDataById(){
		if(userId != null){
			Tuser tuser = userService.getUserById(userId);
			this.tuserContext = tuser;
			return "success";
		}
		return null;
	}
	
	//通过session得到当前用户的标签和用户信息
	public String getUserDataAndTagsBySession(){
		Map<String,Object> session = ActionContext.getContext().getSession();
		Tuser tuser = (Tuser)session.get("user_session");		//持久化的对象，数据没更新
		if(tuser != null){
			Tuser user = userService.getUserById(tuser.getId());	//更新后的对象，有数据
			if(user != null){
				this.tuserContext = user;
				List<Ttag> tags = tagService.getListTagsByUserId(tuser);
				this.tagListContext = tags;
				return "success";
			}
		}
		return null;
	}
	
	//通过用户的id得到用户信息和用户标签
	public String getUserDataAndTagsByUserId(){
		if(userId != null){
			Tuser tuser = userService.getUserById(userId);
			if(tuser != null){
				this.tuserContext = tuser;
				List<Ttag> tags = tagService.getListTagsByUserId(tuser);
				this.tagListContext = tags;
				return "success";
			}
		}
		return "false";
	}

	//通过用户的id得到用户的问题，回答的问题，粉丝，关注的人等信息
	public String getUserHomeDataByUserId(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Tuser tuserOfMy = (Tuser)session.get("user_session");
		//0通过id得到tuser
		Tuser tuser = userService.getUserById(userId);
		this.tuserContext = tuser;
		int page = 1;
		int rows = 1;
		//1得到回答过的问题
		List<TuserQuestion> listUserQuestionsAnswered = userQuestionService.
															getListUserQuestionByUserAndPage(tuser, 1, page, rows);
		if(listUserQuestionsAnswered != null && listUserQuestionsAnswered.size() > 0){
			List<Tquestion> listQuestionAnswered = new ArrayList<Tquestion>();
			for(TuserQuestion listUq : listUserQuestionsAnswered){
				Long questionId = listUq.getQuestionId();
				Tquestion question = questionService.getQuestionById(questionId);
				listQuestionAnswered.add(question);
			}
			this.listQuestionAnswersContext = listQuestionAnswered;
		}
		//2得到跟随的问题
		List<TuserQuestion> listUserQuestionsFellowed = userQuestionService.
															getListUserQuestionByUserAndPage(tuser, 0, page, rows);
		if(listUserQuestionsFellowed != null && listUserQuestionsFellowed.size() > 0){
			List<Tquestion> listQuestionFellowed = new ArrayList<Tquestion>();
			for(TuserQuestion listUq : listUserQuestionsFellowed){
				Long questionId = listUq.getQuestionId();
				Tquestion question = questionService.getQuestionById(questionId);
				listQuestionFellowed.add(question);
			}
			this.listQuestionFellowsContext = listQuestionFellowed;
		}
		//3判断当前用户是否关注查看用户
		if(tuserOfMy != null){
			List<TuserFriends> listMyUserFriends = this.userFriendsService.getListUserFriendsByUid(tuserOfMy.getId());
			if(listMyUserFriends != null && listMyUserFriends.size() > 0){
				for(int i=0; i<listMyUserFriends.size(); i++){
					if(userId.equals(listMyUserFriends.get(i).getFriendId()) == true){
						this.ifFellowPeople = 1;
					}else if(!userId.equals(listMyUserFriends.get(i).getFriendId()) == false){
						this.ifFellowPeople = 0;
					}
				}
			}
		}
		//4得到用户的标签
		List<Ttag> listTags = tagService.getListTagsByUserId(tuser);
		if(listTags != null && listTags.size() > 0){
			this.tagListContext = listTags;
		}
		//5得到用户关注的人，判断关注的人防止重复关注
		List<TuserFriends> listUserFriends = this.userFriendsService.getListUserFriendsByUid(tuser.getId());
		if(listUserFriends != null){
			List<Tuser> listUser = new ArrayList<Tuser>();
 			for(TuserFriends listUF : listUserFriends){
				Tuser userFriend = userService.getUserById(listUF.getFriendId());
				if(userFriend != null){
					listUser.add(userFriend);
				}
			}
 			if(listUser != null && listUser.size() > 0){
 				this.listUserFriendsContext = listUser;
 			}
		}
		//6得到关注用户的人
		List<TuserFriends> listFriendsToUser = this.userFriendsService.getListFriendsToUserByFid(tuser.getId());
		if(listFriendsToUser != null){
			List<Tuser> listUser = new ArrayList<Tuser>();
 			for(TuserFriends listUF : listFriendsToUser){
				Tuser friendUser = userService.getUserById(listUF.getUserId());
				if(friendUser != null){
					listUser.add(friendUser);
				}
			}
 			if(listUser != null && listUser.size() > 0){
 				this.listFriendsToUserContext = listUser;
 			}
		}
		//7得到用户拥有的分数
		if(userId != null){
			this.userGradeContext = tuser.getGrade();
		}
		//8得到用户发表的问题
		List<Tquestion> listQuestions = this.questionService.getQuestionListByUserAndPage(tuser, page, rows);
		if(listQuestions != null && listQuestions.size() > 0){
			this.listQuestionContext = listQuestions;
			this.totalDataContext = listQuestions.size();
		}
		//9当前用户的提醒
		List<TfriendsUserWarn> listWarns = friendsUserWarnService.getListFriendsWarn(tuserOfMy, 0);
		if(listWarns != null && listWarns.size() > 0){
			this.listSizeFriendsWarnContext = listWarns.size();
		}
		if(tuser != null){
			return "success";
		}
		return "false";
	}
	
	static int stutas = 0;
	//load用户的发表的问题
	public String getUserQuestions(){
		Tuser tuser = userService.getUserById(userId);
		this.tuserContext = tuser;
		int page = 1;
		int rows = 1;
System.out.println(page + " sda " + userId );
		if(tuser != null && ifNext == 1){
			stutas ++;
			page += stutas;
			List<Tquestion> listQuestion = this.questionService.getQuestionListByUserAndPage(tuser, page, rows);
			if(listQuestion != null && listQuestion.size() > 0){
System.out.println(page + " " + listQuestion.size());
				this.listQuestionContext = listQuestion;
				this.totalDataContext = listQuestion.size();
			}
			return "success";
		}else if(tuser != null && ifNext == -1){
			stutas --;
			page += stutas;
			List<Tquestion> listQuestion = this.questionService.getQuestionListByUserAndPage(tuser, page, rows);
			if(listQuestion != null && listQuestion.size() > 0){
				this.listQuestionContext = listQuestion;
				this.totalDataContext = listQuestion.size();
System.out.println(page + " " + ifNext );
			}
			if(page == 1){
				this.ifFirst = 1;
			}
			return "success";
		}
		return null;
	}
	
	//load用户的回答过的问题
	public String getUserAnsweredQuestions(){
		
		return null;
	}
	
	//load用户的关注的人
	public String getUserFellowsPeoples(){
		
		return null;
	}
	
	//load用户的粉丝
	public String getUserPeoplesToFellow(){
		
		return null;
	}
	
	//getter and setter
	public Integer getIfFirst() {
		return ifFirst;
	}
	public void setIfFirst(Integer ifFirst) {
		this.ifFirst = ifFirst;
	}
	public Integer getIfNext() {
		return ifNext;
	}

	public void setIfNext(Integer ifNext) {
		this.ifNext = ifNext;
	}
	public Integer getTotalDataContext() {
		return totalDataContext;
	}
	public void setTotalDataContext(Integer totalDataContext) {
		this.totalDataContext = totalDataContext;
	}
	public Integer getListSizeFriendsWarnContext() {
		return listSizeFriendsWarnContext;
	}
	public void setListSizeFriendsWarnContext(Integer listSizeFriendsWarnContext) {
		this.listSizeFriendsWarnContext = listSizeFriendsWarnContext;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Tuser getTuserContext() {
		return tuserContext;
	}
	public void setTuserContext(Tuser tuserContext) {
		this.tuserContext = tuserContext;
	}
	public List<Tuser> getListUserFriendsContext() {
		return listUserFriendsContext;
	}
	public void setListUserFriendsContext(List<Tuser> listUserFriendsContext) {
		this.listUserFriendsContext = listUserFriendsContext;
	}
	public List<Ttag> getTagListContext() {
		return tagListContext;
	}
	public void setTagListContext(List<Ttag> tagListContext) {
		this.tagListContext = tagListContext;
	}
	
	public UserServiceI getUserService() {
		return userService;
	}
	@Resource
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}
	
	public TagServiceI getTagService() {
		return tagService;
	}
	@Resource
	public void setTagService(TagServiceI tagService) {
		this.tagService = tagService;
	}
	public UserQuestionServiceI getUserQuestionService() {
		return userQuestionService;
	}
	@Resource
	public void setUserQuestionService(UserQuestionServiceI userQuestionService) {
		this.userQuestionService = userQuestionService;
	}
	public QuestionServiceI getQuestionService() {
		return questionService;
	}
	@Resource
	public void setQuestionService(QuestionServiceI questionService) {
		this.questionService = questionService;
	}
	
	public List<Tquestion> getListQuestionAnswersContext() {
		return listQuestionAnswersContext;
	}

	public void setListQuestionAnswersContext(
			List<Tquestion> listQuestionAnswersContext) {
		this.listQuestionAnswersContext = listQuestionAnswersContext;
	}

	public List<Tquestion> getListQuestionFellowsContext() {
		return listQuestionFellowsContext;
	}

	public void setListQuestionFellowsContext(
			List<Tquestion> listQuestionFellowsContext) {
		this.listQuestionFellowsContext = listQuestionFellowsContext;
	}
	public List<Tquestion> getListQuestionContext() {
		return listQuestionContext;
	}
	public void setListQuestionContext(List<Tquestion> listQuestionContext) {
		this.listQuestionContext = listQuestionContext;
	}
	public UserFriendsServiceI getUserFriendsService() {
		return userFriendsService;
	}
	@Resource
	public void setUserFriendsService(UserFriendsServiceI userFriendsService) {
		this.userFriendsService = userFriendsService;
	}
	public List<Tuser> getListFriendsToUserContext() {
		return listFriendsToUserContext;
	}
	public void setListFriendsToUserContext(List<Tuser> listFriendsToUserContext) {
		this.listFriendsToUserContext = listFriendsToUserContext;
	}
	public Integer getIfFellowPeople() {
		return ifFellowPeople;
	}
	public void setIfFellowPeople(Integer ifFellowPeople) {
		this.ifFellowPeople = ifFellowPeople;
	}
	public Integer getUserGradeContext() {
		return userGradeContext;
	}
	public void setUserGradeContext(Integer userGradeContext) {
		this.userGradeContext = userGradeContext;
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










