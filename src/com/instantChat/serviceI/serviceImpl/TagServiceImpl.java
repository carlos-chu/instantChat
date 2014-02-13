package com.instantChat.serviceI.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.instantChat.daoI.TagDaoI;
import com.instantChat.model.Tquestion;
import com.instantChat.model.Ttag;
import com.instantChat.model.Tuser;
import com.instantChat.serviceI.TagServiceI;

@Service("tagService")
public class TagServiceImpl implements TagServiceI{
	
	private TagDaoI tagDao;
	
	public TagDaoI getTagDao() {
		return tagDao;
	}
	@Resource
	public void setTagDao(TagDaoI tagDao) {
		this.tagDao = tagDao;
	}


	@Override
	//添加用户标签
	public Ttag addUserTags(String tag, Tuser tuser) {
		Ttag ttag = new Ttag();
		ttag.setId(UUID.randomUUID().toString());
		ttag.setTuser(tuser);
		ttag.setTitle(tag);
		ttag.setUserorquestionflag(0);
		ttag.setIsDelete(0);
		tagDao.save(ttag);
		if(tuser != null && null != tag){
			return ttag;
		}
		return null;
	}
	
	@Override
	//删除用户标签
	public Boolean deleteUserTags(Tuser tuser, String value) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tuser", tuser);
		params.put("tagValue", value);
		params.put("userorquestionflag", 0);
		params.put("isdelete", 1);
		if(null != tuser && value != null){
			tagDao.update("update Ttag t set t.isDelete = :isdelete where t.tuser = :tuser and" +
					" t.userorquestionflag = :userorquestionflag and t.title = :tagValue", params);
			return true;
		}
		return false;
	}
	
	@Override
	//通过用户的id得到所有的标签
	public List<Ttag> getListTagsByUserId(Tuser tuser) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tuser", tuser);
		params.put("ifdelete", 0);
		List<Ttag> listTags = tagDao.find("from Ttag tag where tag.tuser = :tuser and tag.isDelete =:ifdelete", params);
		if(listTags != null && listTags.size() > 0){
			return listTags;
		}
		return null;
	}
	
	@Override
	//通过question得到标签
	public List<Ttag> getListTagsByQuestionId(Tquestion question) {
		if(question != null){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("question", question);
			List<Ttag> listTag  = tagDao.find("from Ttag t where t.tquestion = :question", params);
			if(listTag != null && listTag.size() > 0){
				return listTag;
			}
		}
		return null;
	}

}






