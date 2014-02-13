package com.instantChat.serviceI;

import com.instantChat.model.Tuser;

public interface GradeServiceI {

	public void saveGrade(Tuser tuser, Long grade);
	
	public void updateGrade(Tuser tuser, Long grade);
	
}
