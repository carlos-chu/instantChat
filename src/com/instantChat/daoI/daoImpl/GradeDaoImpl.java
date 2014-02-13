package com.instantChat.daoI.daoImpl;

import org.springframework.stereotype.Repository;

import com.instantChat.daoI.GradeDaoI;
import com.instantChat.model.Tgrade;

@Repository("gradeDao")
public class GradeDaoImpl extends BaseDaoImpl<Tgrade> implements GradeDaoI{

}
