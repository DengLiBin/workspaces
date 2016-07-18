package com.itheima.redbaby.engine;

import com.itheima.redbaby.bean.UserInfo;
/**
 * 登录接口
 * @author zl
 *
 */
public interface LoginEngine {
	
	/**
	 * 注册和登录
	 * @param userName
	 * @param password
	 * @return
	 */
	UserInfo getServiceUserInfo(String userName,String password);
	
	
}
