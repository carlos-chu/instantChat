package com.instantChat.serviceI.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.instantChat.daoI.UserFriendsDaoI;
import com.instantChat.model.TuserFriends;
import com.instantChat.serviceI.UserFriendsServiceI;

@Service("userFriendsService")
public class UserFriendsServiceImpl implements UserFriendsServiceI{
	
	private UserFriendsDaoI userFriendsDao;
	
	//保存用户的关注人
	public void saveUserFriends(String uid, String fid) {
		TuserFriends tuserFriend = new TuserFriends();
		tuserFriend.setUserId(uid);
		tuserFriend.setFriendId(fid);
		userFriendsDao.save(tuserFriend);
	}
	
	//通过uid得到用户的好友
	public List<TuserFriends> getListUserFriendsByUid(String uid) {
		if(uid != null){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("uid", uid);
			List<TuserFriends> listUserFriends = userFriendsDao.find("from TuserFriends tf where tf.userId = :uid", params);
			if(listUserFriends != null && listUserFriends.size() > 0){
				return listUserFriends;
			}
		}
		return null;
	}
	
	//分页得到用户的好友
	public List<TuserFriends> getListUserFriendsByUidAndPage(String uid , int page, int rows) {
		if(uid != null){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("uid", uid);
			List<TuserFriends> listUserFriends = userFriendsDao.find(
					"from TuserFriends tf where tf.userId = :uid", params, page, rows);
			if(listUserFriends != null && listUserFriends.size() > 0){
				return listUserFriends;
			}
		}
		return null;
	}
	
	//通过fid得到关注该用户的用户群
	public List<TuserFriends> getListFriendsToUserByFid(String fid) {
		if(fid != null){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("fid", fid);
			List<TuserFriends> listFriendsToUser = this.userFriendsDao.find(
					"from TuserFriends tf where tf.friendId = :fid", params);
			if(listFriendsToUser != null && listFriendsToUser.size() > 0){
				return listFriendsToUser;
			}
		}
		return null;
	}
	
	//删除用户的某个关注的人
	public void deleteUserFriendByFid(String uid, String fid) {
		if(uid != null && fid != null){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("uid", uid);
			params.put("fid", fid);
			userFriendsDao.delete("delete TuserFriends tf where tf.userId = :uid and friendId = :fid", params);
		}
	}
	
	//get and set
	public UserFriendsDaoI getUserFriendsDao() {
		return userFriendsDao;
	}
	@Resource
	public void setUserFriendsDao(UserFriendsDaoI userFriendsDao) {
		this.userFriendsDao = userFriendsDao;
	}

}











