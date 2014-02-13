package com.instantChat.serviceI.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.instantChat.daoI.TagDaoI;
import com.instantChat.daoI.UserDaoI;
import com.instantChat.model.Ttag;
import com.instantChat.model.Tuser;
import com.instantChat.serviceI.AnswerDwrServiceI;
import com.instantChat.util.ComparatorGrade;

@Service("answerDwrService")
public class AnswerDwrServiceImpl implements AnswerDwrServiceI {

	private UserDaoI userDao;
	private TagDaoI tagDao;
	Logger logger = Logger.getLogger("");
	@Override
	// 得到目标回答用户，算法匹配
	public Tuser getGoalUserToAnswer(String userId, String tag1, String tag2, String tag3) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tag1", "%" + tag1 + "%");
		params.put("userorquestionflag", 0);
		//params.put("ifOnline", 1);
		/*
		 * params.put("tag2", "%" + tag2 + "%"); params.put("tag3", "%" + tag3 +
		 * "%");
		 */
		//params.put("isAccept", 1); // 1代表接受模式开启，0代表关闭，3代表正在进行回答
		// 查找所有符合tag1的所有tag
		List<Ttag> listTag = tagDao.find(
				"from Ttag tag where tag.title like :tag1 and tag.userorquestionflag = :userorquestionflag", params);
		// 查找目标用户（解决不包含自己和用户在线的问题）
		if (listTag != null && listTag.size() > 0) {
			List<Tuser> listUser1 = new ArrayList<Tuser>();
			for (int i = 0; i < listTag.size(); i++) {
				Tuser tuser1 = listTag.get(i).getTuser();
				if (!tuser1.getId().equals(userId) && tuser1.getIfaccept() == 1) {
					listUser1.add(tuser1);
				}
			}
			if(listUser1 != null && listUser1.size() > 0){
				return listUser1.get(0);
			}
		}
		return null;
	}
	
	public Tuser getGoalUserToAnswer2(String userId, String tag1, String tag2, String tag3) {
logger.info("1 " + tag1 + tag2 + tag3 + "logwer");
		//只填写了两个标签
		if(!userId.equals("") && !tag1.equals("") && ! tag2.equals("") /*&& tag3.equals("")*/){
logger.info("2 " + tag1);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tag1", tag1);
			params.put("userorquestionflag", 0);
			//params.put("ifOnline", 1);
			List<Ttag> listTag;
			// 查找所有符合tag1的所有tag
			listTag = tagDao.find(
					"from Ttag tag where tag.title like :tag1 and tag.userorquestionflag = :userorquestionflag", params);
			if(listTag != null && listTag.size() > 0){
				params.remove("userorquestionflag");
				params.remove("tag1");
logger.info("3 " + listTag.size());
				//再找符合这个标签的在线的人
				List<Tuser> listUser_tag1_online_isAccept = new ArrayList<Tuser>();
				for (int i = 0; i < listTag.size(); i++) {
					Tuser tuser1 = listTag.get(i).getTuser();
					//排除用户自己匹配和条件是开启接受和在线
					if (!tuser1.getId().equals(userId) && tuser1.getIfaccept() == 1 && tuser1.getIfOnline() == 1) {
logger.info("4 " + i);
						listUser_tag1_online_isAccept.add(tuser1);
					}
				}
				if(listUser_tag1_online_isAccept != null && listUser_tag1_online_isAccept.size() > 0){
logger.info("5 " + listUser_tag1_online_isAccept.get(0));
					//在这里面查找符合tag2的用户
					List<Tuser> listUser_tag1_online_isAccept_tag2 = new ArrayList<Tuser>();
					for(Tuser listUser : listUser_tag1_online_isAccept){
						params.put("tag2", tag2);
						params.put("tuser", listUser);
						Ttag tag2OfU = tagDao.get("from Ttag t where t.title like :tag2 and t.tuser = :tuser", params);
						if(tag2OfU != null){
logger.info("6 " + tag2OfU);
							listUser_tag1_online_isAccept_tag2.add(listUser);
						}
					}
					//返回分数最高的
					if(listUser_tag1_online_isAccept_tag2 != null && listUser_tag1_online_isAccept_tag2.size() > 0){
logger.info("7 " + listUser_tag1_online_isAccept_tag2.get(0));
						if(listUser_tag1_online_isAccept_tag2.size() == 1){
logger.info("7.1" + listUser_tag1_online_isAccept_tag2.get(0).getId());
							return listUser_tag1_online_isAccept_tag2.get(0);
						}else{
						//分数排序,降序
							Collections.sort(listUser_tag1_online_isAccept_tag2, new ComparatorGrade());
							Tuser finalUser = listUser_tag1_online_isAccept_tag2.get(0); 
								if(finalUser != null){
logger.info("8 " + finalUser);
									return finalUser;
								}
						}	
					//没有符合tag2的用户，直接返回分数最高的符合tag1的
					}else{
logger.info("9 ");
						List<Integer> listGrades = new ArrayList<Integer>();
						for(Tuser listU : listUser_tag1_online_isAccept){
							int grade = listU.getGrade();
							listGrades.add(grade);
						}
						Collections.sort(listGrades, Collections.reverseOrder());
						if(listGrades != null){
							Tuser finalUser = null;
							for(Tuser t : listUser_tag1_online_isAccept){
								if(t.getGrade().equals(listGrades.get(0))){
									finalUser = t;
								}
							}
logger.info("10 " + finalUser);
							return finalUser;
						}
					}
				}
			//没有符合tag1,在tag2中找分数最高的
			}else if(listTag ==null || listTag.size() == 0){
logger.info("11 ");
				params.remove("tag1");
				params.put("tag2", tag2);
				listTag = tagDao.find(
					"from Ttag tag where tag.title like :tag2 and tag.userorquestionflag = :userorquestionflag", params);
				if(listTag != null && listTag.size() > 0){
logger.info("12 " + listTag.get(0));
					//再找符合这个标签的在线的人
					List<Tuser> listUser_tag2_online_isAccept = new ArrayList<Tuser>();
					for (int i = 0; i < listTag.size(); i++) {
						Tuser tuser1 = listTag.get(i).getTuser();
						//排除用户自己匹配和条件是开启接受和在线
						if (!tuser1.getId().equals(userId) && tuser1.getIfaccept() == 1 && tuser1.getIfOnline() == 1) {
							listUser_tag2_online_isAccept.add(tuser1);
						}
					}
					if(listUser_tag2_online_isAccept != null && listUser_tag2_online_isAccept.size() > 0){
logger.info("13 " + listUser_tag2_online_isAccept.get(0));
						List<Integer> listGrades = new ArrayList<Integer>();
						for(Tuser listU : listUser_tag2_online_isAccept){
							int grade = listU.getGrade();
							listGrades.add(grade);
						}
						Collections.sort(listGrades, Collections.reverseOrder());
						if(listGrades != null){
							Tuser finalUser = null;
							for(Tuser t : listUser_tag2_online_isAccept){
								if(t.getGrade() == listGrades.get(0)){
									finalUser = t;
								}
							}
							return finalUser;
						}
					}
				}
			}else{
				return null;
			}
		//填写标签tag3的
		}else{
			
		}
		return null;
	}

	public UserDaoI getUserDao() {
		return userDao;
	}

	@Resource
	public void setUserDao(UserDaoI userDao) {
		this.userDao = userDao;
	}

	public TagDaoI getTagDao() {
		return tagDao;
	}

	@Resource
	public void setTagDao(TagDaoI tagDao) {
		this.tagDao = tagDao;
	}

}
