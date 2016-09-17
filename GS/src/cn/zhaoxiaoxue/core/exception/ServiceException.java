package cn.zhaoxiaoxue.core.exception;

public class ServiceException extends SysException {
	public ServiceException(){
		
		
	}
	public ServiceException(String message,Throwable cause){
		super(message,cause);	
		
	}
	public ServiceException(String message){
		super(message);
	}
	public ServiceException(Throwable cause){
		super(cause);
	}
}
