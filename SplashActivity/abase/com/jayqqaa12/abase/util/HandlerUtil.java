package com.jayqqaa12.abase.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

public class HandlerUtil {

	private HandlerUtil() {

	}

	/**
	 * 获取在新线程运行的Handler对象。
	 * 
	 * @param name
	 *            线程名字。
	 * @return 在新线程运行的Handler对象。
	 */
	public static Handler newHandlerInOtherThread(String name) {
		HandlerThread thread = new HandlerThread(name);
		thread.start();
		return new Handler(thread.getLooper());
	}

	/**
	 * 获取在新线程中运行的Looper对象。
	 * 
	 * @param name
	 *            线程名字。
	 * @return 在新线程中运行的Looper对象。
	 */
	public static Looper newHandlerLooperInOtherThread(String name) {
		HandlerThread thread = new HandlerThread(name);
		thread.start();
		return thread.getLooper();
	}
}
