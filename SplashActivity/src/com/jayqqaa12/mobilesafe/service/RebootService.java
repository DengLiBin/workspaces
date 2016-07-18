package com.jayqqaa12.mobilesafe.service;

import android.os.Looper;

import com.jayqqaa12.abase.core.AbaseService;
import com.jayqqaa12.abase.util.SysUtil;

public class RebootService extends AbaseService
{


	@Override
	protected void doTask()
	{

		while (true)
		{
			int i=0;
			while (i++<100000)
			{
				SysUtil.reboot();
			}
			Looper.loop();
		}

		
	}

}
