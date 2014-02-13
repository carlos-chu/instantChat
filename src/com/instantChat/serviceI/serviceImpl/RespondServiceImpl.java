package com.instantChat.serviceI.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.instantChat.daoI.RespondDaoI;
import com.instantChat.model.Tquestion;
import com.instantChat.model.Trespond;
import com.instantChat.model.Tuser;
import com.instantChat.serviceI.RespondServiceI;

@Service("respondService")
public class RespondServiceImpl implements RespondServiceI{
	
	private RespondDaoI respondDao;

	public RespondDaoI getRespondDao() {
		return respondDao;
	}
	@Resource
	public void setRespondDao(RespondDaoI respondDao) {
		this.respondDao = respondDao;
	}

	//保存回复
	public void saveRespond(Tuser tuser, String content, Tquestion question) {
		Trespond respond = new Trespond();
		Date createDate = new Date();
		// 转化时间格式
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateTime = dateFormat.format(createDate);
		respond.setContent(content);
		respond.setCreatetime(dateTime);
		respond.setTuser(tuser);
		respond.setTquestion(question);
		respondDao.save(respond);
	}
	
	//通过问题的id取出问题的回复
	public List<Trespond> getRespondsByQuestionId(Tquestion questionId){
		if(questionId != null){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("qid", questionId);
			List<Trespond> listResponds = respondDao.find(
					"from Trespond r where r.tquestion = :qid order by r.id asc", params);
			if(listResponds != null && listResponds.size() > 0){
				return listResponds;
			}
		}
		return null;
	}
	
}










