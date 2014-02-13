package com.instantChat.daoI.daoImpl;

import org.springframework.stereotype.Repository;

import com.instantChat.daoI.QuestionDaoI;
import com.instantChat.model.Tquestion;

@Repository("questionDao")
public class QuestionDaoImpl extends BaseDaoImpl<Tquestion> implements QuestionDaoI{

}
