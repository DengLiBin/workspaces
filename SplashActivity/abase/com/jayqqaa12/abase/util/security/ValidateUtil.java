package com.jayqqaa12.abase.util.security;


import java.util.Collection;

/**
 * 校验工具类
 */
public class ValidateUtil {
	/**
	 * 验证字符串有效性
	 */
	public static boolean isValid(String str){
		if(str == null || "".equals(str)){
			return false ;
		}
		return true ;
	}
	
	/**
	 * 验证集合有效性
	 */
	public static boolean isValid(Collection col){
		if(col == null || col.isEmpty()){
			return false ;
		}
		return true ;
	}
	
	/**
	 * 验证数组有效性
	 */
	public static boolean isValid(Object[] arr){
		if(arr == null || arr.length == 0){
			return false ;
		}
		
		return true ;
	}
	
	
	
}