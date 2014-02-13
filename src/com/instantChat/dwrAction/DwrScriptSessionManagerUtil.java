package com.instantChat.dwrAction;

import java.util.Map;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.impl.DefaultScriptSessionManager;

import com.instantChat.model.Tuser;
import com.opensymphony.xwork2.ActionContext;
//管理自己的scriptsession
public class DwrScriptSessionManagerUtil extends DefaultScriptSessionManager{

	//初始化方法--无参数的构造方法
	public DwrScriptSessionManagerUtil(){
		try{
			this.addScriptSessionListener(new ScriptSessionListener(){
				@Override
				//scriptsession创建的方法
				public void sessionCreated(ScriptSessionEvent ev) {
					ScriptSession scriptSession = ev.getSession(); //得到scriptsession
					Map<String, Object> httpSession = ActionContext.getContext().getSession();
					Tuser tuserLogined = (Tuser)httpSession.get("user_session");
					if(tuserLogined != null){
						scriptSession.setAttribute("tuserId", tuserLogined.getId());
					}else{
						scriptSession.invalidate();
						return;
					}
				}
				
				@Override
				//scriptsession销毁的方法
				public void sessionDestroyed(ScriptSessionEvent ev) {
					
				}
				
			});
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}








