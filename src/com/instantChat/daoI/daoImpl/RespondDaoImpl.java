package com.instantChat.daoI.daoImpl;

import org.springframework.stereotype.Repository;

import com.instantChat.daoI.RespondDaoI;
import com.instantChat.model.Trespond;

@Repository("respondDao")
public class RespondDaoImpl extends BaseDaoImpl<Trespond> implements RespondDaoI{

}
