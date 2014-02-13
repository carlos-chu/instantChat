package com.instantChat.serviceI.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.instantChat.daoI.GradeDaoI;
import com.instantChat.model.Tgrade;
import com.instantChat.model.Tuser;
import com.instantChat.serviceI.GradeServiceI;

@Service("gradeService")
public class GradeServiceImpl implements GradeServiceI{

	private GradeDaoI gradeDao;

	//baocun
	public void saveGrade(Tuser tuser, Long grade) {
		Tgrade tgrade = new Tgrade();
		tgrade.setTuser(tuser);
		tgrade.setGrade(grade);
		gradeDao.save(tgrade);
	}
	
	@Override
	public void updateGrade(Tuser tuser, Long grade) {
		
	}
	
	//get and set
	public GradeDaoI getGradeDao() {
		return gradeDao;
	}
	@Resource
	public void setGradeDao(GradeDaoI gradeDao) {
		this.gradeDao = gradeDao;
	}


	
}






