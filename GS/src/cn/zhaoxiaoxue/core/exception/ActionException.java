package cn.zhaoxiaoxue.core.exception;

public class ActionException extends SysException {
	public ActionException(){
		
		
	}
	public ActionException(String message,Throwable cause){
		super(message,cause);	
		
	}
	public ActionException(String message){
		super(message);
	}
	public ActionException(Throwable cause){
		super(cause);
	}
}
