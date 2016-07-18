package com.itheima.redbaby.test;

import com.itheima.redbaby.bean.UserInfo;
import com.itheima.redbaby.engine.LoginEngine;
import com.itheima.redbaby.engine.UserInfoEngine;
import com.itheima.redbaby.utils.BeanFactory;

import android.test.AndroidTestCase;

public class ReginsterTest extends AndroidTestCase{

	public void test(){
		LoginEngine registerService = BeanFactory.getInstance(LoginEngine.class);
		UserInfo serviceUserInfo = registerService.getServiceUserInfo("zl", "123");
		System.out.println(serviceUserInfo);
	}
}
