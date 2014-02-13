package com.instantChat.serviceI.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.instantChat.daoI.UserDaoI;
import com.instantChat.model.Tuser;
import com.instantChat.pageModel.UserDto;
import com.instantChat.serviceI.UserServiceI;
import com.instantChat.util.Encrypt;

@Service("userService")
public class UserServiceImpl implements UserServiceI {
	
	private UserDaoI userDao;

	public UserDaoI getUserDao() {
		return userDao;
	}
	@Resource
	public void setUserDao(UserDaoI userDao) {
		this.userDao = userDao;
	}

	//用户注册
	@Override
	public Tuser saveUser(UserDto userDto) {
		Tuser tuser = new Tuser();
		Date createDate = new Date();
		
		BeanUtils.copyProperties(userDto, tuser, new String[]{"password"});
		tuser.setId(UUID.randomUUID().toString());
		tuser.setPassword(Encrypt.e(userDto.getPassword()));
		tuser.setCreatetime(createDate);
		tuser.setIfaccept(0);
		tuser.setIconUrl("images/default_user_touxiang.jpg");
		tuser.setGrade(0);
		tuser.setIfOnline(1);
		userDao.save(tuser);
		return tuser;
	}
	
	//用户登陆
	public Tuser login(UserDto userDto){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", userDto.getEmail());
		params.put("password", Encrypt.e(userDto.getPassword()));
		Tuser tuser = userDao.get("from Tuser tuser where tuser.email = :email and tuser.password = :password", params);
		if(null != tuser){
			return tuser;
		}
		return null; 
	}
	
	//更新用户的是否在线状态
	public void updateUserIfOnline(String uid, int ifOnline){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uid", uid);
		params.put("online", ifOnline);	//1代表在线，0代表离线
		this.userDao.update("update Tuser tu set tu.ifOnline = :online where tu.id = :uid", params);
	}
	
	@Override
	//通过id得到用户
	public Tuser getUserById(String tuserId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uid", tuserId);
		Tuser tuser = userDao.get("from Tuser tuser where tuser.id = :uid", params);
		if(null != tuser){
			return tuser;
		}
		return null;
	}
	
	@Override
	//更新用户介绍
	public Boolean updateUserIntro(String userId, String intro) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uid", userId);
		params.put("intro", intro);
		if(null != userId){
			userDao.update("update Tuser tu set tu.intro = :intro where tu.id = :uid", params);
			return true;
		}
		return false;
	}
	
	@Override
	//更新用户的接受状态
	public Boolean upadateUserAcceptAnswer(String userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("accept", 1);
		if(userId != null){
			userDao.update("update Tuser tu set tu.ifaccept = :accept where tu.id = :userId", params);
			return true;
		}
		return false;
	}
	
	@Override
	//更新用户的取消接受状态
	public Boolean updateUserCancelAccept(String userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("cancelAccept", 0);
		if(userId != null){
			userDao.update("update Tuser tu set tu.ifaccept = :cancelAccept where tu.id = :userId", params);
			return true;
		}
		return false;
	}
	
	//更新用户的分数
	public void updateUserGrade(String uid, int grade) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uid", uid);
		params.put("grade", grade);
		userDao.update("update Tuser tu set tu.grade = :grade where tu.id = :uid", params);
	}
	
	//更新用户的名字
	public void updateUserName(String uid, String resetName){
		if(uid != null && resetName != null){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("uid", uid);
			params.put("name", resetName);
			userDao.update("update Tuser t set t.name = :name where t.id = :uid", params);
		}
		
	}
	
	//更新用户的密码
	public void updateUserPassword(String uid, String password){
		if(uid != null && password != null){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("uid", uid);
			params.put("pass", Encrypt.e(password));
			userDao.update("update Tuser t set t.password = :pass where t.id = :uid", params);
		}
	}
	
	//得到用户原先的分数
	public int getUserGrade(String uid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uid", uid);
		if(uid != null){
			Tuser tuser = userDao.get("from Tuser tu where tu.id = :uid", params);
			if(tuser != null){
				int grade = tuser.getGrade();
				return grade;
			}
		}
		return (Integer) null;
	}
	
	@Override
	//分页得到用户用于前台的显示
	public List<Tuser> getUserByPage(int page, int rows) {
		if(page > 0 && rows > 0){
			List<Tuser> listUser = userDao.find("from Tuser t", page, rows);
			if(listUser != null && listUser.size() > 0){
				return listUser;
			}
		}
		return null;
	}
	
	//查询出总数
	public Integer count() {
		int nums = userDao.countInt("select count(*) from Tuser tu");
		if(nums > 0){
			return nums;
		}
		return 0;
	}
	
	
}












