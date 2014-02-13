package com.instantChat.serviceI.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.instantChat.daoI.UserQuestionDaoI;
import com.instantChat.model.Tuser;
import com.instantChat.model.TuserQuestion;
import com.instantChat.serviceI.UserQuestionServiceI;

@Service("userQuestionService")
public class UserQuestionServiceImpl implements UserQuestionServiceI{

	private UserQuestionDaoI userQuestionDao;
	
	//保存TsuerQuestion
	public void saveUserQuestion(Tuser tuser, Long questionId) {
		TuserQuestion tuserQuestion = new TuserQuestion();
		tuserQuestion.setIsfellow(1);	//1代表回答过的问题
		tuserQuestion.setTuser(tuser);
		tuserQuestion.setQuestionId(questionId);
		userQuestionDao.save(tuserQuestion);
	}

	//通过user得到全部的TuserQuestion
	public List<TuserQuestion> getListUserQuestionByUser(Tuser tuser, int isFellow) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tuser", tuser);
		params.put("isFellow", isFellow);
		if(tuser != null){
			List<TuserQuestion> listUserQuestions = userQuestionDao.find(
					"from TuserQuestion tq where tq.tuser = :tuser and isfellow = :isFellow", params);
			if(listUserQuestions != null && listUserQuestions.size() > 0){
				return listUserQuestions;
			}
		}
		return null;
	}

	//通过user分页得到全部的TuserQuestion
	public List<TuserQuestion> getListUserQuestionByUserAndPage(Tuser tuser, int isFellow, int page, int rows) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tuser", tuser);
		params.put("isFellow", isFellow);
		if(tuser != null){
			List<TuserQuestion> listUserQuestions = userQuestionDao.find(
					"from TuserQuestion tq where tq.tuser = :tuser and isfellow = :isFellow", params, page, rows);
			if(listUserQuestions != null && listUserQuestions.size() > 0){
				return listUserQuestions;
			}
		}
		return null;
	}
	
	//getter and setter
	public UserQuestionDaoI getUserQuestionDao() {
		return userQuestionDao;
	}
	@Resource
	public void setUserQuestionDao(UserQuestionDaoI userQuestionDao) {
		this.userQuestionDao = userQuestionDao;
	}

	
}











