package com.instantChat.dwrAction;


import java.util.Collection;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.stereotype.Service;

@Service("chatWithOneDwrAction")
public class ChatWithOneDwrAction {
	
	//点对点聊天
	public void chatWithOne(String msg){
System.out.println(msg);
		WebContext wctx = WebContextFactory.get();
		//推送的目标页面
		String currentPage = wctx.getContextPath() + "/userPublicInstantQuestionAction!saveProblem";
System.out.println(msg);
		Browser.withPage(currentPage, new Runnable(){
			public void run() {
				Collection<ScriptSession> colls = Browser.getTargetSessions();
				ScriptBuffer script = new ScriptBuffer();
				script.appendCall("alertReceiverUser", "sjjsjsjjsjs");
				for(ScriptSession scriptSessions : colls){
					scriptSessions.addScript(script);
				}
			}
			
		});
	}
	
}









