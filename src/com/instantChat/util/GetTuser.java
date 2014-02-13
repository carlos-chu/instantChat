package com.instantChat.util;

import java.util.Map;

import com.instantChat.model.Tuser;
import com.opensymphony.xwork2.ActionContext;

public class GetTuser {

	public static Tuser getTuser(){
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		Tuser tuser = (Tuser) session.get("user_session");
		if(tuser != null){
			return tuser;
		}
		return null;
	}
}
