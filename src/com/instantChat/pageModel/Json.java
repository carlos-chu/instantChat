package com.instantChat.pageModel;

public class Json {
	
	private boolean flag;
	
	private String msg;
	
	private String msg1;
	
	private String userName;
	
	private Object obj = null;

	public String getMsg() {
		return msg;
	}

	public Object getObj() {
		return obj;
	}
	
	public boolean isFlag() {
		return flag;
	};

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getMsg1() {
		return msg1;
	}

	public void setMsg1(String msg1) {
		this.msg1 = msg1;
	}
}
