package cn.zhaoxiaoxue.core.exception;

public abstract class SysException extends Exception {
	private String errorMsg;
	
	public SysException(){
		
	}
	public SysException(String message,Throwable cause){
		super(message,cause);
		this.errorMsg = message;
	}
	public SysException(String message){
		super(message);
		this.errorMsg = message;
	}
	public SysException(Throwable cause){
		super(cause);
		
	}
}
