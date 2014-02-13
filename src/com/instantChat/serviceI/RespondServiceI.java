package com.instantChat.serviceI;

import java.util.List;

import com.instantChat.model.Tquestion;
import com.instantChat.model.Trespond;
import com.instantChat.model.Tuser;

public interface RespondServiceI {

	public void saveRespond(Tuser tuser, String content, Tquestion question);
	
	public List<Trespond> getRespondsByQuestionId(Tquestion questionId);
	
}
