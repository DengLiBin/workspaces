package com.itheima.redbaby.ui.manager;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.redbaby.R;
import com.itheima.redbaby.utils.AnimationController;
import com.itheima.redbaby.utils.AppStackManager;

public class MiddleManager extends Observable {
	private static final String TAG = "MiddleManager";
	// 单例化MiddleManager
	private static MiddleManager middleManager = new MiddleManager();
	/**
	 * 任务栈
	 */
	private static AppStackManager appStack;

	private MiddleManager() {
	}

	public static MiddleManager getInstance() {
		/**
		 * 获取任务栈
		 */
		appStack = AppStackManager.getInstance();
		return middleManager;
	}

	/**
	 * 中间容器
	 */
	private ViewGroup ii_middle;
	private BaseView currentUI;

	public void init(Activity activity) {
		ii_middle = (ViewGroup) activity.findViewById(R.id.dl_rl_middle);
	}

	/**
	 * 获取上下文
	 * 
	 * @return
	 */
	public Context getContext() {
		return ii_middle.getContext();
	}

	// TODO 利用手机内存空间，换应用的运行速度
	private Map<String, BaseView> viewCache = new HashMap<String, BaseView>();

	/**
	 * 切换View
	 * 
	 * @param clazz
	 *            view类
	 * @param bundle
	 *            传递的参数,如果不需要用bundle，就传一个null
	 */
	public void changeView(Class<? extends BaseView> clazz, Bundle bundle) {
		if (currentUI != null && currentUI.getClass() == clazz) {
			return;
		}

		String key = clazz.getSimpleName();
		// 目标UI
		BaseView targetUI = null;
		if (viewCache.containsKey(key)) {
			targetUI = viewCache.get(key);
		} else {
			try {
				Constructor<? extends BaseView> constructor = clazz
						.getConstructor(Context.class);
				targetUI = constructor.newInstance(getContext());

				viewCache.put(key, targetUI);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("constructor new instance error");
			}
		}

		// Logger.i(TAG, targetUI.toString());

		// 目标界面关闭
		if (currentUI != null) {
			currentUI.onPause();
		}

		ii_middle.removeAllViews();

		if (targetUI != null) {
			if (bundle != null) {
				targetUI.setBundle(bundle);
			}
			View targetView = targetUI.getChild();
			ii_middle.addView(targetView);
			AnimationController.fadeIn(targetView, 500, 0);

			// 向view栈中增加view
			appStack.addStack(targetUI);
			// 将目标的view转化为当前view
			// 处理生命周期，目标界面开启
			targetUI.onResume();
			currentUI = targetUI;

			setNotifyTitleAndBottom();
		}
	}

	/**
	 * 通知标题栏和底部栏，中间容器内容变化了
	 */
	private void setNotifyTitleAndBottom() {
		setChanged();
		// 通知所有的观察者
		notifyObservers(currentUI.getID());
	}

	/**
	 * 处理返回键
	 * 
	 * @return
	 */
	public boolean goBack() {
		if (appStack.getAppStackSize() > 0) {
			// 当用户误操作返回键（不退出应用）
			if (appStack.getAppStackSize() == 1) {
				return false;
			}

			if (appStack.getAppStackSize() > 0) {
				appStack.removeCurrentView();
				// 设置生命周期
				currentUI.onPause();
				ii_middle.removeAllViews();
				View targetView = appStack.currentView().getChild();
				ii_middle.addView(targetView);
				// 设置生命周期
				appStack.currentView().onResume();
				currentUI = appStack.currentView();
				setNotifyTitleAndBottom();
				return true;
			}
		}
		return false;
	}

	/**
	 * 得到当前的View fun 2014-04-17 16:34:11
	 */
	public BaseView getCurrentUI() {
		return currentUI;
	}

}
