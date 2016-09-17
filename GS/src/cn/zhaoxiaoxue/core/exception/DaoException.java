package cn.zhaoxiaoxue.core.exception;

public class DaoException extends SysException {
	public DaoException(){
		
		
	}
	public DaoException(String message,Throwable cause){
		super(message,cause);	
		
	}
	public DaoException(String message){
		super(message);
	}
	public DaoException(Throwable cause){
		super(cause);
	}
}
