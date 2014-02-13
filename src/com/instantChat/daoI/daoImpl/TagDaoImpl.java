package com.instantChat.daoI.daoImpl;

import org.springframework.stereotype.Repository;

import com.instantChat.daoI.TagDaoI;
import com.instantChat.model.Ttag;

@Repository("tagDao")
public class TagDaoImpl extends BaseDaoImpl<Ttag> implements TagDaoI{

}
