package com.instantChat.serviceI.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.instantChat.daoI.FriendsUserWarnDaoI;
import com.instantChat.model.TfriendsUserWarn;
import com.instantChat.model.Tquestion;
import com.instantChat.model.Tuser;
import com.instantChat.serviceI.FriendsUserWarnServiceI;

@Service("friendsUserWarnService")
public class FriendsUserWarnServiceImpl implements FriendsUserWarnServiceI{

	private FriendsUserWarnDaoI friendsUserWarnDao;
	//保存添加好友提醒表
	public void saveFriendsUserWarn(Tuser tuser, Tuser friendsId) {
		TfriendsUserWarn friendsUserWarn = new TfriendsUserWarn();
		Date createDate = new Date();
		// 转化时间格式
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateTime = dateFormat.format(createDate);
		friendsUserWarn.setTuser(tuser);
		friendsUserWarn.setFriendtouserId(friendsId);
		friendsUserWarn.setIfScan(0);				//0代表没有查看，1代表查看了
		friendsUserWarn.setCreatetime(dateTime);
		friendsUserWarnDao.save(friendsUserWarn);
	} 
	
	//保存评分提醒表
	public void saveFriendsUserWarn(Tuser tuser, Tuser friend, Long grade) {
		TfriendsUserWarn friendsUserWarn = new TfriendsUserWarn();
		Date createDate = new Date();
		// 转化时间格式
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateTime = dateFormat.format(createDate);
		friendsUserWarn.setTuser(tuser);
		friendsUserWarn.setFriendtouserId(friend);
		friendsUserWarn.setIfScan(0);				//0代表没有查看，1代表查看了
		friendsUserWarn.setGrade(grade);
		friendsUserWarn.setCreatetime(dateTime);
		friendsUserWarnDao.save(friendsUserWarn);
	}
	
	//保存用户的回答问题提醒
	public void saveFriendsUserWarn(Tuser tuser, Tuser friend, Tquestion question) {
		TfriendsUserWarn friendsUserWarn = new TfriendsUserWarn();
		Date createDate = new Date();
		// 转化时间格式
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateTime = dateFormat.format(createDate);
		friendsUserWarn.setTuser(tuser);
		friendsUserWarn.setFriendtouserId(friend);
		friendsUserWarn.setIfScan(0);				//0代表没有查看，1代表查看了
		friendsUserWarn.setCreatetime(dateTime);
		friendsUserWarn.setQuestionId(question);
		friendsUserWarnDao.save(friendsUserWarn);
	}
	
	//更新提醒表表示已经查看了
	public void updateFriendsUserWarn(Tuser tuser, int haveScaned){
		if(tuser != null){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tuser", tuser);
			params.put("haveScaned", haveScaned);
			params.put("s", 0);
			friendsUserWarnDao.update(
				"update TfriendsUserWarn tw set tw.ifScan = :haveScaned where tw.tuser = :tuser and tw.ifScan = :s", params);
		}
	}
	
	//分页得到没查看的提醒
	public List<TfriendsUserWarn> getListFriendsWarn(Tuser tuser, int status, int page, int rows){
		if(tuser != null){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tuser", tuser);
			params.put("status", status);
			List<TfriendsUserWarn> listFriendsWarn = friendsUserWarnDao.find(
					"from TfriendsUserWarn tw where tw.tuser = :tuser and tw.ifScan = :status order by tw.id desc",
					params, page, rows);
			if(listFriendsWarn != null && listFriendsWarn.size() > 0){
				return listFriendsWarn;
			}
		}
		return null;
	}
	
	//得到全部的提醒
	public List<TfriendsUserWarn> getListFriendsWarn(Tuser tuser, int status) {
		if(tuser != null){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tuser", tuser);
			params.put("status", status);
			List<TfriendsUserWarn> listFriendsWarn = friendsUserWarnDao.find(
					"from TfriendsUserWarn tw where tw.tuser = :tuser and tw.ifScan = :status", params);
			if(listFriendsWarn != null && listFriendsWarn.size() > 0){
				return listFriendsWarn;
			}
		}
		return null;
	}
	
	public FriendsUserWarnDaoI getFriendsUserWarnDao() {
		return friendsUserWarnDao;
	}
	@Resource
	public void setFriendsUserWarnDao(FriendsUserWarnDaoI friendsUserWarnDao) {
		this.friendsUserWarnDao = friendsUserWarnDao;
	}

}









