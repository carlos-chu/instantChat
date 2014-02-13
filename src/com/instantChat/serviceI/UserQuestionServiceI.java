package com.instantChat.serviceI;

import java.util.List;

import com.instantChat.model.Tuser;
import com.instantChat.model.TuserQuestion;

public interface UserQuestionServiceI {

	public void saveUserQuestion(Tuser tuser, Long questionId);
	
	public List<TuserQuestion> getListUserQuestionByUser(Tuser tuser, int isFellow);
	
	public List<TuserQuestion> getListUserQuestionByUserAndPage(Tuser tuser, int isFellow, int page, int rows);
}
