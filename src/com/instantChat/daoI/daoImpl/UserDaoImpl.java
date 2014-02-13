package com.instantChat.daoI.daoImpl;

import org.springframework.stereotype.Repository;

import com.instantChat.daoI.UserDaoI;
import com.instantChat.model.Tuser;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<Tuser> implements UserDaoI {

}
