package com.itheima.redbaby.engine.impl;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.bean.Version;
import com.itheima.redbaby.engine.CheckVersionEngine;

public class CheckVersionEngineimpl extends BaseEngine implements CheckVersionEngine {

	@Override
	public Version getVersion() {
		String sendGet = httpClient.sendGet(ConstantValue.URI + "/x_z_version.php");
		Version version = JSON.parseObject(sendGet, Version.class);
		return version;
	}

}
