package com.instantChat.serviceI;

import java.util.List;

import com.instantChat.model.TfriendsUserWarn;
import com.instantChat.model.Tquestion;
import com.instantChat.model.Tuser;

public interface FriendsUserWarnServiceI {
	
	public void  saveFriendsUserWarn(Tuser tuser, Tuser friend);
	
	public void  saveFriendsUserWarn(Tuser tuser, Tuser friend, Long grade);
	
	public void  saveFriendsUserWarn(Tuser tuser, Tuser friend, Tquestion question);
	
	public void updateFriendsUserWarn(Tuser tuser, int haveScaned);

	public List<TfriendsUserWarn> getListFriendsWarn(Tuser tuser, int status, int page, int rows);
	
	public List<TfriendsUserWarn> getListFriendsWarn(Tuser tuser, int status);
}
