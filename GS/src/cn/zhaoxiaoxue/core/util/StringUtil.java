package cn.zhaoxiaoxue.core.util;

public class StringUtil {
	public static boolean isBlank(String str){
		if(str != null && !"".equals(str)){
			return false;
		}else{
			return true;
		}
	}
}
