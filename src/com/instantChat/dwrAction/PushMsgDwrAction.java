package com.instantChat.dwrAction;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.stereotype.Service;

import com.instantChat.model.Tuser;
import com.instantChat.serviceI.AnswerDwrServiceI;

/*
 * 处理匹配好用户的提示,dwr
 */
@Service("pushMsgDwrAction")
public class PushMsgDwrAction {

	private AnswerDwrServiceI answerDwrService;
	public static WebContext wctx = null;
	Logger logger = Logger.getLogger("");
	
	public AnswerDwrServiceI getAnswerDwrService() {
		return answerDwrService;
	}
	@Resource
	public void setAnswerDwrService(AnswerDwrServiceI answerDwrService) {
		this.answerDwrService = answerDwrService;
	}

	// 提示用户接受回答请求
	public void pushMsgToUserForAnswer(final String userId, String tag1, String tag2,
			String tag3, final String title) {
		// 推送目标用户
logger.info(" D w r ");
		Tuser tuser = this.searchGoalUser(userId, tag1, tag2, tag3);
		// 推送目标用户的id
		if(tuser != null){
			final String tuserReceiverId = tuser.getId();
logger.info("zhaodaoUser " + tuser.getId());
			Browser.withAllSessionsFiltered(new ScriptSessionFilter() {
				@Override
				public boolean match(ScriptSession scriptSession) {
					if (scriptSession.getAttribute("tuserId") == null) {
						return false;
					} else {
						return (scriptSession.getAttribute("tuserId")
								.equals(tuserReceiverId));
					}
				}
			}, new Runnable() {
				@Override
				public void run() {
logger.info("daozhe" + userId);
					Collection<ScriptSession> colls = Browser
							.getTargetSessions();
					ScriptBuffer script = new ScriptBuffer();
					script.appendCall("alertReceiverUser", title, userId, 0); // 接收者弹出框提示接受问题
					for (ScriptSession scriptSessions : colls) {
						scriptSessions.addScript(script);
					}
				}
			});
		}
	}

	//推送接受到邀请好友
	public void pushMsgToUserFriend(final String publishUid, final String friendId, final String title){
		Browser.withAllSessionsFiltered(new ScriptSessionFilter() {
			@Override
			public boolean match(ScriptSession scriptSession) {
				if (scriptSession.getAttribute("tuserId") == null) {
					return false;
				} else {
					return (scriptSession.getAttribute("tuserId")
							.equals(friendId));
				}
			}
		}, new Runnable() {
			@Override
			public void run() {
				Collection<ScriptSession> colls = Browser.getTargetSessions();
				ScriptBuffer script = new ScriptBuffer();
				script.appendCall("alertReceiverUser", title, publishUid, 1); // 接收者弹出框提示接受问题1判断是好友的
				for (ScriptSession scriptSessions : colls) {
					scriptSessions.addScript(script);
				}
			}
		});
	}
	
	// 点对点聊天
	public void chatWithOne(final String receiverUserId, final String userName, final String msg, final String userId){
		WebContext wctx = WebContextFactory.get();
		// 推送的目标页面
		String currentPage = wctx.getContextPath()
				+ "/userPublicInstantQuestionAction!saveProblem";
		Browser.withPageFiltered(currentPage, new ScriptSessionFilter(){
			public boolean match(ScriptSession scriptSession) {
				if (scriptSession.getAttribute("tuserId") == null) {
					return false;
				} else {
					return (scriptSession.getAttribute("tuserId").equals(receiverUserId));
				}
			}
		},  new Runnable() {
			public void run() {
				Collection<ScriptSession> colls = Browser.getTargetSessions();
				ScriptBuffer script = new ScriptBuffer();
				script.appendCall("dialogical_msg", userName, msg, userId);
				for (ScriptSession scriptSessions : colls) {
					scriptSessions.addScript(script);
				}
			}
		});
	}

	// 点对点聊天
	public void chatWithFriend(final String receiverUserId,
			final String userName, final String msg, final String userId) {
		WebContext wctx = WebContextFactory.get();
		// 推送的目标页面
		String currentPage = wctx.getContextPath()
				+ "/userInviteFriendToChatAction!saveProblem";
		Browser.withPageFiltered(currentPage, new ScriptSessionFilter() {
			public boolean match(ScriptSession scriptSession) {
				if (scriptSession.getAttribute("tuserId") == null) {
					return false;
				} else {
					return (scriptSession.getAttribute("tuserId")
							.equals(receiverUserId));
				}
			}
		}, new Runnable() {
			public void run() {
				Collection<ScriptSession> colls = Browser.getTargetSessions();
				ScriptBuffer script = new ScriptBuffer();
				script.appendCall("dialogical_msg", userName, msg, userId);
				for (ScriptSession scriptSessions : colls) {
					scriptSessions.addScript(script);
				}
			}
		});
	}
		
	// 抽调出来算法匹配的方法，便于其他类调用
	public Tuser searchGoalUser(String userId, String tag1, String tag2, String tag3) {
		Tuser tuser = answerDwrService.getGoalUserToAnswer2(userId, tag1, tag2, tag3);
		if (tuser != null) {
			return tuser;
		}
		return null;
	}
	
}
