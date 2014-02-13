package com.instantChat.daoI.daoImpl;

import org.springframework.stereotype.Repository;

import com.instantChat.daoI.FriendsUserWarnDaoI;
import com.instantChat.model.TfriendsUserWarn;

@Repository("friendsUserWarnDao")
public class FriendsUserWarnDaoImpl extends BaseDaoImpl<TfriendsUserWarn> implements FriendsUserWarnDaoI{

}
