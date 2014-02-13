package com.instantChat.listener;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.instantChat.model.Tuser;
import com.instantChat.serviceI.UserServiceI;
import com.instantChat.util.GetTuser;
import com.opensymphony.xwork2.ActionContext;

@Controller("onlineUserListener")
public class OnlineUserListener implements HttpSessionListener{

	//创建httpSession
	public void sessionCreated(HttpSessionEvent e) {
		System.out.println("创建了：" + e.getSession().getId());
	}

	//销毁httpSession
	public void sessionDestroyed(HttpSessionEvent e) {
		HttpSession httpSession = e.getSession();
		Tuser tuser = (Tuser)httpSession.getAttribute("user_httpSession");
		ApplicationContext springCtx = WebApplicationContextUtils.getWebApplicationContext(httpSession.getServletContext());
		 UserServiceI userService = (UserServiceI)(springCtx.getBean("userService")); 
		 if(tuser != null){
			 userService.updateUserIfOnline(tuser.getId(), 0);	//下线
			 System.out.println(tuser.getName() + "下线了！");
			 httpSession.removeAttribute("user_httpSession");
		 }
		//session.remove("user_session");
	}
	
}








