package com.instantChat.util;

import java.util.Comparator;

import com.instantChat.model.Tuser;

public class ComparatorGrade implements Comparator<Tuser>{

	@Override
	public int compare(Tuser o1, Tuser o2) {
		if(o1.getGrade() > o2.getGrade()){
			return -1;
		}else if(o1.getGrade() == o2.getGrade()){
			return 0;
		}else{
			return 1;
		}
	}

}
