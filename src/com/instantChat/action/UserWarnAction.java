package com.instantChat.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.instantChat.model.TfriendsUserWarn;
import com.instantChat.model.Tuser;
import com.instantChat.pageModel.Json;
import com.instantChat.serviceI.FriendsUserWarnServiceI;
import com.opensymphony.xwork2.ActionContext;

public class UserWarnAction extends BaseAction{
	
	private FriendsUserWarnServiceI friendsUserWarnService;
	//接受下一页参数
	private int ifNext;
	//保存用户的未读提醒
	private List<TfriendsUserWarn> listWarnContext = new ArrayList<TfriendsUserWarn>();
	//保存用户的已读提提醒
	private List<TfriendsUserWarn> listUnScanWarnContext = new ArrayList<TfriendsUserWarn>();
	//保存提醒的总条数
	private Integer totalWarnsContext = new Integer(0);
	//保存判断是否是第一页
	private Integer ifFirstPage = new Integer(0);
	
	//分页得到用户的关注提醒
	public String getUserWarn(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Tuser tuser= (Tuser)session.get("user_session");
		int page = 1;
		int rows = 6;
		int status = 0;
		int statusScaned = 1;
		if(tuser != null){
			stutas = 0;
			List<TfriendsUserWarn> listWarnUnScan = friendsUserWarnService.getListFriendsWarn(tuser, status, page, rows);
			List<TfriendsUserWarn> listWarnScaned = 
					friendsUserWarnService.getListFriendsWarn(tuser, statusScaned, page, rows);
			listWarnContext = listWarnUnScan;
			listUnScanWarnContext = listWarnScaned;
			if(listWarnScaned != null && listWarnScaned.size() > 0){
				this.totalWarnsContext = listWarnScaned.size();
			}
			//更新提醒状态设为已经查看的状态
			if(listWarnUnScan != null && listWarnUnScan.size() > 0){
				friendsUserWarnService.updateFriendsUserWarn(tuser, statusScaned);
			}
			return "success";
		}
		return "false";
	}
	
	//ajax方式加载更多提醒，封装成json格式
	static int stutas = 0; 
	public void getAjaxWarns(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Tuser tuser = (Tuser)session.get("user_session");
		int page = 1;
		int rows = 2;
		int statusScaned = 1;	//查看过的提醒
		if(tuser != null){
			stutas++;
			page += stutas;
			List<TfriendsUserWarn> listWarnScaned = 
					friendsUserWarnService.getListFriendsWarn(tuser, statusScaned, page, rows);
			/*Json j = new Json();
			j.setFlag(true);
			j.setObj(listWarnScaned);
			super.writeJson(j);*/
			for(int i=0; i<listWarnScaned.size(); i++){
				Json j = new Json();
				//j.setFlag(true);
				j.setMsg(listWarnScaned.get(i).getCreatetime());
				j.setObj(listWarnScaned.get(i));
				super.writeJson(j);
			}
		}
	}
	
	//load用户的已读的提醒
	public String getWarns(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Tuser tuser = (Tuser)session.get("user_session");
		int page = 1;
		int rows = 6;
		int statusScaned = 1;	//查看过的提醒
		if(tuser != null && ifNext == 1){
			stutas++;
			page += stutas;
			List<TfriendsUserWarn> listWarnScaned = 
					friendsUserWarnService.getListFriendsWarn(tuser, statusScaned, page, rows);
			if(listWarnScaned != null && listWarnScaned.size() > 0){
				this.listUnScanWarnContext = listWarnScaned;
				this.totalWarnsContext = listWarnScaned.size();
			}
			return "success";
		}else if(tuser != null && ifNext == -1){
			stutas--;
			page += stutas;
			List<TfriendsUserWarn> listWarnScaned = 
					friendsUserWarnService.getListFriendsWarn(tuser, statusScaned, page, rows);
			if(listWarnScaned != null && listWarnScaned.size() > 0){
				this.listUnScanWarnContext = listWarnScaned;
				this.totalWarnsContext = listWarnScaned.size();
				if(page == 1){
					this.ifFirstPage = 1;
				}
			}
			return "success";
		}
		return null;
	}
	//get and set
	public Integer getIfFirstPage() {
		return ifFirstPage;
	}
	public void setIfFirstPage(Integer ifFirstPage) {
		this.ifFirstPage = ifFirstPage;
	}
	public int getIfNext() {
		return ifNext;
	}
	public void setIfNext(int ifNext) {
		this.ifNext = ifNext;
	}
	public FriendsUserWarnServiceI getFriendsUserWarnService() {
		return friendsUserWarnService;
	}
	@Resource
	public void setFriendsUserWarnService(
			FriendsUserWarnServiceI friendsUserWarnService) {
		this.friendsUserWarnService = friendsUserWarnService;
	}
	public Integer getTotalWarnsContext() {
		return totalWarnsContext;
	}

	public void setTotalWarnsContext(Integer totalWarnsContext) {
		this.totalWarnsContext = totalWarnsContext;
	}
	public List<TfriendsUserWarn> getListWarnContext() {
		return listWarnContext;
	}
	public void setListWarnContext(List<TfriendsUserWarn> listWarnContext) {
		this.listWarnContext = listWarnContext;
	}
	public List<TfriendsUserWarn> getListUnScanWarnContext() {
		return listUnScanWarnContext;
	}
	public void setListUnScanWarnContext(
			List<TfriendsUserWarn> listUnScanWarnContext) {
		this.listUnScanWarnContext = listUnScanWarnContext;
	}
}
