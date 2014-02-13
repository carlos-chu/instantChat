package com.instantChat.serviceI;

import java.util.List;

import com.instantChat.model.Tuser;
import com.instantChat.pageModel.UserDto;

public interface UserServiceI {

	public Tuser saveUser(UserDto userDto);
	
	public Tuser login(UserDto userDto);
	
	public void updateUserIfOnline(String uid, int ifOnline);

	public Tuser getUserById(String tuserId);
	
	public Boolean updateUserIntro(String userId, String intro);
	
	public Boolean upadateUserAcceptAnswer(String userId);
	
	public Boolean updateUserCancelAccept(String userId);
	
	public void updateUserGrade(String uid, int grade);
	
	public void updateUserName(String uid, String name);
	
	public void updateUserPassword(String uid, String password);
	
	public int getUserGrade(String uid);
	
	public List<Tuser> getUserByPage(int page, int row);
	
	public Integer count();
}
