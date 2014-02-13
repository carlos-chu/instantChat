package com.instantChat.daoI.daoImpl;

import org.springframework.stereotype.Repository;

import com.instantChat.daoI.UserQuestionDaoI;
import com.instantChat.model.TuserQuestion;

@Repository("userQuestionDao")
public class UserQuestionDaoImpl extends BaseDaoImpl<TuserQuestion> implements UserQuestionDaoI{

}
