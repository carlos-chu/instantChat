package com.instantChat.serviceI;

import java.util.List;

import com.instantChat.model.TuserFriends;

public interface UserFriendsServiceI {

	public void saveUserFriends(String uid, String fid);
	
	public List<TuserFriends> getListUserFriendsByUid(String uid);
	
	public List<TuserFriends> getListFriendsToUserByFid(String fid);
	
	public void deleteUserFriendByFid(String uid, String fid);
	
	public List<TuserFriends> getListUserFriendsByUidAndPage(String uid, int page, int rows);
}
