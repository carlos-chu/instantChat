package com.instantChat.serviceI;

import java.util.List;

import com.instantChat.model.Tquestion;
import com.instantChat.model.Ttag;
import com.instantChat.model.Tuser;

public interface TagServiceI {

	public Ttag addUserTags(String tag, Tuser tuser);
	
	public Boolean deleteUserTags(Tuser tuser, String value);
	
	public List<Ttag> getListTagsByUserId(Tuser tuser);
	
	public List<Ttag> getListTagsByQuestionId(Tquestion question);
}
