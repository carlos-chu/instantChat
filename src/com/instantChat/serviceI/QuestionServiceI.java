package com.instantChat.serviceI;

import java.util.List;
import java.util.Map;

import com.instantChat.model.Tquestion;
import com.instantChat.model.Ttag;
import com.instantChat.model.Tuser;
import com.instantChat.pageModel.ProblemPageModel;


public interface QuestionServiceI {

	public void saveQuestion(ProblemPageModel problemDto, Tuser tuser);
	
	public void saveQuestion2(ProblemPageModel problemDto, Tuser tuser);
	
	public void saveQuestionTag(ProblemPageModel problemDto, Tquestion tquestion);
	
	//public Tquestion getQuestionByUserIdAndQuestionIdOfMax(String userId);
	
	public Map<String, List<Ttag>> getQuestionAndTagByUserId_QuestionIdOfMax(String userId);
	
	public Tquestion getMaxIdQuestionByUserId(Tuser tuser);
	
	public Tquestion getQuestionById(Long questionId);
	
	public List<Tquestion> getQuestionListByUser(Tuser tuser);
	
	public List<Tquestion> getQuestionListByUserAndPage(Tuser tuser, int page, int rows);
	
	public void saveQuestion(String title, Tuser tuser);
	
	public List<Tquestion> getQuestionByPage(int page, int rows, int ifSolve);
	
	public void updateQuestionIsSolved(Long questionId, int status);
	
	public Integer count(int ifSolve);
}
