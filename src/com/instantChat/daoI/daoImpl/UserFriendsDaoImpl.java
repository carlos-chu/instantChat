package com.instantChat.daoI.daoImpl;

import org.springframework.stereotype.Repository;

import com.instantChat.daoI.UserFriendsDaoI;
import com.instantChat.model.TuserFriends;

@Repository("userFriendsDao")
public class UserFriendsDaoImpl extends BaseDaoImpl<TuserFriends> implements UserFriendsDaoI{

}
