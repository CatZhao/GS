package cn.zhaoxiaoxue.nsfw.action;

import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport {
	public String frame(){
		return "frame";
	}
	public String top(){
		return "top";
	}
	public String left(){
		return "left";
	}
}
