package com.instantChat.serviceI;

import com.instantChat.model.Tuser;

public interface AnswerDwrServiceI {

	public Tuser getGoalUserToAnswer(String userId, String tag1, String tag2, String tag3);
	
	public Tuser getGoalUserToAnswer2(String userId, String tag1, String tag2, String tag3) ;

}
