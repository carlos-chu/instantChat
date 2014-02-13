package com.instantChat.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.instantChat.serviceI.GradeServiceI;

@Controller("gradeAction")
public class GradeAction extends BaseAction{
	
	private GradeServiceI gradeService;

	public GradeServiceI getGradeService() {
		return gradeService;
	}
	@Resource
	public void setGradeService(GradeServiceI gradeService) {
		this.gradeService = gradeService;
	}
	
}







