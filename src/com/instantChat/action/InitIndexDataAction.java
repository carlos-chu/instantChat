package com.instantChat.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.instantChat.model.TfriendsUserWarn;
import com.instantChat.model.Tquestion;
import com.instantChat.model.Tuser;
import com.instantChat.serviceI.FriendsUserWarnServiceI;
import com.instantChat.serviceI.QuestionServiceI;
import com.instantChat.serviceI.UserServiceI;
import com.opensymphony.xwork2.ActionContext;

public class InitIndexDataAction extends BaseAction {

	private UserServiceI userService;
	private QuestionServiceI questionService;
	private FriendsUserWarnServiceI friendsUserWarnService;
	// 保存User
	private Set<Tuser> usersContext = new HashSet<Tuser>();
	// 保存question
	private List<Tquestion> questionsContext = new ArrayList<Tquestion>();
	// 保存用户的添加他为好友的提醒
	private Integer listSizeFriendsWarnContext = new Integer(0);
	//保存总页数
	private Integer totalPageContext = new Integer(0);
	//保存当前页
	private Integer currentPageContext = new Integer(0);
	private int page;

	// 初始化首页（index.jsp）的数据
	public String initIndexPage() {
		int page = 1;
		int rowUser = 12;
		int rowQuestion = 15;
		//解决问题状态
		int isSolve = 1;
		List<Tuser> listUsers = userService.getUserByPage(page, rowUser);
		if (listUsers != null && listUsers.size() > 0) {
			List<Integer> listGrades = new ArrayList<Integer>();
			for (Tuser listU : listUsers) {
				int grade = listU.getGrade();
				listGrades.add(grade);
			}
			Collections.sort(listGrades, Collections.reverseOrder());
			if (listGrades != null) {
				Set<Tuser> listUsersFinal = new HashSet<Tuser>();
				// 通过分数的高低为用户排名
				for (int i = listGrades.size() - 1; i >= 0; i--) {
					for (int j = 0; j < listUsers.size(); j++) {
						if (listUsers.get(j).getGrade()
								.equals(listGrades.get(i))){
							listUsersFinal.add(listUsers.get(i));
						}
					}
				}
				this.usersContext = listUsersFinal;
			}
		}
		List<Tquestion> listQuestion = questionService.getQuestionByPage(page, rowQuestion, isSolve);
		if (listQuestion != null && listQuestion.size() > 0) {
			this.questionsContext = listQuestion;
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

	// 初始化首页（questions.jsp）的数据
	public String initQuestions() {
		int rows = 10;
		int isSolve = 1;
		//分页代码
		int totalRows = questionService.count(isSolve);
		if(totalRows > 0){
			int temp = totalRows % rows;
			if(temp > 0){
				Integer totalPages = totalRows / rows + 1;
				if(totalPages > 0){
					this.totalPageContext = totalPages;
					currentPageContext = page;
				}
			}else{
				Integer totalPages = totalRows / rows;
				if (totalPages > 0) {
					this.totalPageContext = totalPages;
					currentPageContext = page;
				}
			}
		}
		if(page != 0){
			List<Tquestion> listQuestion = questionService.getQuestionByPage(page, rows, isSolve);
			if (listQuestion != null && listQuestion.size() > 0) {
				questionsContext = listQuestion;
			}
		}else{
			page = 1;
			List<Tquestion> listQuestion = questionService.getQuestionByPage(page, rows, isSolve);
			if (listQuestion != null && listQuestion.size() > 0) {
				questionsContext = listQuestion;
			}
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

	// 初始化首页（users.jsp）的数据
	public String initUsers() {
		int rows = 20;
		//分页代码
		int totalRows = userService.count();
		if(totalRows > 0){
			int temp = totalRows % rows;
			if(temp > 0){
				Integer totalPages = totalRows / rows + 1;
				if(totalPages > 0){
					this.totalPageContext = totalPages;
					currentPageContext = page;
				}
			}else{
				Integer totalPages = totalRows / rows;
				if (totalPages > 0) {
					this.totalPageContext = totalPages;
					currentPageContext = page;
				}
			}
		}
		if(page != 0){
			List<Tuser> listUsers = userService.getUserByPage(page, rows);
			if (listUsers != null && listUsers.size() > 0) {
				List<Integer> listGrades = new ArrayList<Integer>();
				for (Tuser listU : listUsers) {
					int grade = listU.getGrade();
					listGrades.add(grade);
				}
				Collections.sort(listGrades, Collections.reverseOrder());
				if (listGrades != null) {
					Set<Tuser> listUsersFinal = new HashSet<Tuser>();
					// 通过分数的高低为用户排名
					for (int i = listGrades.size() - 1; i >= 0; i--) {
						for (int j = 0; j < listUsers.size(); j++) {
							if (listUsers.get(j).getGrade()
									.equals(listGrades.get(i))) {
								listUsersFinal.add(listUsers.get(i));
							}
						}
					}
					this.usersContext = listUsersFinal;
				}
			}
		}else{
			page = 1;
			List<Tuser> listUsers = userService.getUserByPage(page, rows);
			if (listUsers != null && listUsers.size() > 0) {
				List<Integer> listGrades = new ArrayList<Integer>();
				for (Tuser listU : listUsers) {
					int grade = listU.getGrade();
					listGrades.add(grade);
				}
				Collections.sort(listGrades, Collections.reverseOrder());
				if (listGrades != null) {
					Set<Tuser> listUsersFinal = new HashSet<Tuser>();
					// 通过分数的高低为用户排名
					for (int i = listGrades.size() - 1; i >= 0; i--) {
						for (int j = 0; j < listUsers.size(); j++) {
							if (listUsers.get(j).getGrade()
									.equals(listGrades.get(i))) {
								listUsersFinal.add(listUsers.get(i));
							}
						}
					}
					this.usersContext = listUsersFinal;
				}
			}
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

	// 初始化首页（discusses.jsp）的数据
	public String initDiscusses() {
		int rows = 10;
		int noSolve = 0;
		//分页代码
		int totalRows = questionService.count(noSolve);
		if (totalRows > 0) {
			int temp = totalRows % rows;
			if (temp > 0) {
				Integer totalPages = totalRows / rows + 1;
				if (totalPages > 0) {
					this.totalPageContext = totalPages;
					currentPageContext = page;
				}
			} else {
				Integer totalPages = totalRows / rows;
				if (totalPages > 0) {
					this.totalPageContext = totalPages;
					currentPageContext = page;
				}
			}
		}
		if(page != 0){
			List<Tquestion> listQuestion = questionService.getQuestionByPage(page, rows, noSolve);
			if (listQuestion != null && listQuestion.size() > 0) {
				questionsContext = listQuestion;
			}
		}else{
			page = 1;
			List<Tquestion> listQuestion = questionService.getQuestionByPage(page, rows, noSolve);
			if (listQuestion != null && listQuestion.size() > 0) {
				questionsContext = listQuestion;
			}
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
	
	// get and set
	public Integer getCurrentPageContext() {
		return currentPageContext;
	}

	public void setCurrentPageContext(Integer currentPageContext) {
		this.currentPageContext = currentPageContext;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public Integer getTotalPageContext() {
		return totalPageContext;
	}

	public void setTotalPageContext(Integer totalPageContext) {
		this.totalPageContext = totalPageContext;
	}
	public List<Tquestion> getQuestionsContext() {
		return questionsContext;
	}

	public void setQuestionsContext(List<Tquestion> questionsContext) {
		this.questionsContext = questionsContext;
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
	public Set<Tuser> getUsersContext() {
		return usersContext;
	}
	public void setUsersContext(Set<Tuser> usersContext) {
		this.usersContext = usersContext;
	}
	public FriendsUserWarnServiceI getFriendsUserWarnService() {
		return friendsUserWarnService;
	}

	@Resource
	public void setFriendsUserWarnService(
			FriendsUserWarnServiceI friendsUserWarnService) {
		this.friendsUserWarnService = friendsUserWarnService;
	}

	public Integer getListSizeFriendsWarnContext() {
		return listSizeFriendsWarnContext;
	}

	public void setListSizeFriendsWarnContext(Integer listSizeFriendsWarnContext) {
		this.listSizeFriendsWarnContext = listSizeFriendsWarnContext;
	}

}
