package com.instantChat.serviceI.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.instantChat.daoI.UserDaoI;
import com.instantChat.serviceI.UploadServiceI;

@Service("uploadService")
public class UploadServiceImpl implements UploadServiceI{

	private UserDaoI userDao;
	
	public UserDaoI getUserDao() {
		return userDao;
	}
	@Resource
	public void setUserDao(UserDaoI userDao) {
		this.userDao = userDao;
	}

	@Override
	public void uploadTxImg(String touxiangSrc, String uid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("txSrc", touxiangSrc);
		params.put("uid", uid);
		userDao.update("update Tuser tu set tu.iconUrl = :txSrc where tu.id = :uid", params);
	}

}






