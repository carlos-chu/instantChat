package com.instantChat.serviceI.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.instantChat.daoI.QuestionDaoI;
import com.instantChat.daoI.TagDaoI;
import com.instantChat.model.Tquestion;
import com.instantChat.model.Ttag;
import com.instantChat.model.Tuser;
import com.instantChat.pageModel.ProblemPageModel;
import com.instantChat.serviceI.QuestionServiceI;
import com.instantChat.serviceI.UserServiceI;

@Service("questionService")
public class QuestionServiceImpl implements QuestionServiceI {

	private QuestionDaoI questionDao;
	private TagDaoI tagDao;
	private UserServiceI userService;

	public UserServiceI getUserService() {
		return userService;
	}
	@Resource
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	public TagDaoI getTagDao() {
		return tagDao;
	}
	@Resource
	public void setTagDao(TagDaoI tagDao) {
		this.tagDao = tagDao;
	}

	public QuestionDaoI getQuestionDao() {
		return questionDao;
	}
	@Resource
	public void setQuestionDao(QuestionDaoI questionDao) {
		this.questionDao = questionDao;
	}

	// 保存问题
	public void saveQuestion(ProblemPageModel problemDto, Tuser tuser) {
		Tquestion tquestion = new Tquestion();
		Date createDate = new Date();
		// 转化时间格式
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateTime = dateFormat.format(createDate);
		tquestion.setCreatetime(dateTime);
		tquestion.setDeleteable(0);
		tquestion.setIswhat(1); 	// 1代表是即时问答的，0代表是共同讨论的
		tquestion.setTitle(problemDto.getTitle());
		tquestion.setTuser(tuser);
		tquestion.setIfSolve(0);	//1代表解决了，0代表没解决
		questionDao.save(tquestion);
		// 调用下面的方法
		this.saveQuestionTag(problemDto, tquestion);
	}

	// 保存问题
	public void saveQuestion2(ProblemPageModel problemDto, Tuser tuser) {
		Tquestion tquestion = new Tquestion();
		Date createDate = new Date();
		// 转化时间格式
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateTime = dateFormat.format(createDate);
		tquestion.setCreatetime(dateTime);
		tquestion.setDeleteable(0);
		tquestion.setIswhat(1); // 1代表是即时问答的，0代表是共同讨论的
		tquestion.setTitle(problemDto.getTitle());
		tquestion.setIfSolve(0);	//1代表解决了，0代表没解决
		tquestion.setTuser(tuser);
		questionDao.save(tquestion);
	}
	
	// 保存问题的标签
	public void saveQuestionTag(ProblemPageModel problemDto, Tquestion tquestion) {
		if (!problemDto.getTag3().trim().equals("") && problemDto.getTag3() != null) {
			Ttag tag1 = new Ttag();
			tag1.setId(UUID.randomUUID().toString());
			tag1.setUserorquestionflag(1);
			tag1.setTquestion(tquestion);
			tag1.setTitle(problemDto.getTag1());
			tagDao.save(tag1);
			Ttag tag2 = new Ttag();
			tag2.setId(UUID.randomUUID().toString());
			tag2.setUserorquestionflag(1);
			tag2.setTquestion(tquestion);
			tag2.setTitle(problemDto.getTag2());
			tagDao.save(tag2);
			Ttag tag3 = new Ttag();
			tag3.setId(UUID.randomUUID().toString());
			tag3.setUserorquestionflag(1);
			tag3.setTquestion(tquestion);
			tag3.setTitle(problemDto.getTag3());
			tagDao.save(tag3);
		}else{
			Ttag tag1 = new Ttag();
			tag1.setId(UUID.randomUUID().toString());
			tag1.setUserorquestionflag(1);
			tag1.setTquestion(tquestion);
			tag1.setTitle(problemDto.getTag1());
			tagDao.save(tag1);
			Ttag tag2 = new Ttag();
			tag2.setId(UUID.randomUUID().toString());
			tag2.setUserorquestionflag(1);
			tag2.setTquestion(tquestion);
			tag2.setTitle(problemDto.getTag2());
			tagDao.save(tag2);
		}
	}
	
	//得到最新的问题和属于这个问题的标签
	public Map<String, List<Ttag>> getQuestionAndTagByUserId_QuestionIdOfMax(String userId){
		Map<String, Object> params = new HashMap<String, Object>();
		Tuser tuser = userService.getUserById(userId);
		Tquestion question = null;
		
		if(tuser != null){
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("tuser", tuser);
			question = questionDao.get("from Tquestion q where q.id= " +
					"(select max(q.id) from Tquestion q where q.tuser = :tuser)", param);
		}
		
		params.put("tquestion", question);
		String title = null;
		if(question != null){
			title = question.getTitle();
		}
		List<Ttag> listTags = tagDao.find("from Ttag tag where tag.tquestion = :tquestion", params);
		if(title != null ){
			Map<String, List<Ttag>> maps = new HashMap<String, List<Ttag>>();
			maps.put(title, listTags);
			return maps;
		}
		return null;
	}
	
	//通过tuser得到最新发表的问题
	public Tquestion getMaxIdQuestionByUserId(Tuser tuser) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tuser", tuser);
		Tquestion tquestion = questionDao.get("from Tquestion q where q.id=" +
				"(select max(q.id) from Tquestion q where q.tuser = :tuser)", params);
		if(tquestion != null){
			return tquestion;
		}
		return null;
	}

	//通过question的id得到question
	public Tquestion getQuestionById(Long questionId) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", questionId);
		if(questionId != null){
			return questionDao.get("from Tquestion q where q.id = :id", params);
		}
		return null;
	}
	
	//通过user得到用户的question
	public List<Tquestion> getQuestionListByUser(Tuser tuser) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tuser", tuser);
		if(tuser != null){
			List<Tquestion> listQuestions = questionDao.find(
					"from Tquestion tq where tq.tuser = :tuser", params);
			if(listQuestions != null && listQuestions.size() > 0){
				return listQuestions;
			}
		}
		return null;
	}
	
	//通过user得到用户的question
	public List<Tquestion> getQuestionListByUserAndPage(Tuser tuser, int page, int rows) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tuser", tuser);
		if (tuser != null) {
			List<Tquestion> listQuestions = questionDao.find(
					"from Tquestion tq where tq.tuser = :tuser", params, page, rows);
			if (listQuestions != null && listQuestions.size() > 0) {
				return listQuestions;
			}
		}
		return null;
	}
		
	
	//保存没有tag的问题
	public void saveQuestion(String title, Tuser tuser) {
		Tquestion tquestion = new Tquestion();
		Date createDate = new Date();
		// 转化时间格式
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateTime = dateFormat.format(createDate);
		tquestion.setCreatetime(dateTime);
		tquestion.setDeleteable(0);
		tquestion.setIswhat(1); // 1代表是即时问答的，0代表是共同讨论的
		tquestion.setTitle(title);
		tquestion.setTuser(tuser);
		questionDao.save(tquestion);
	}
	
	//分页得到解决的问题用于前台显示
	public List<Tquestion> getQuestionByPage(int page, int rows, int ifSolve) {
		if(page > 0 && rows > 0){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("isSolve", ifSolve);
			List<Tquestion> listQuestion = questionDao.find(
					"from Tquestion q where q.ifSolve = :isSolve order by q.id desc", params, page, rows);
			if(listQuestion != null && listQuestion.size() > 0){
				return listQuestion;
			}
		}
		return null;
	}

	//更新问题为解决状态
	public void updateQuestionIsSolved(Long questionId, int status) {
		if(questionId != null){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("qid", questionId);
			params.put("isSolve", status);
			questionDao.update("update Tquestion q set q.ifSolve = :isSolve where q.id = :qid", params);
		}
	}
	
	//查询出记录数
	public Integer count(int ifSolve) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ifSolve", ifSolve);
		int totalNum = questionDao.countInt("select count(*) from Tquestion q where q.ifSolve = :ifSolve", params);
		if(totalNum != 0){
			return totalNum;
		}
		return 0;
	}
	
}








