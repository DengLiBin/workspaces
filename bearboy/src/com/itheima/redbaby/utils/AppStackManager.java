package com.itheima.redbaby.utils;

import java.util.LinkedList;

import com.itheima.redbaby.ui.manager.BaseView;

/**
 * 用来管理应用中MiddleView的生命周期
 * 
 * @author zhangyun
 * 
 */
public class AppStackManager {

	private LinkedList<BaseView> appStack;

	private static AppStackManager appStackManager = new AppStackManager();

	private AppStackManager() {
	}

	public static AppStackManager getInstance() {
		return appStackManager;
	}


	/**
	 * 获取应用栈的内容的大小
	 * @return
	 */
	public int getAppStackSize(){
		if(appStack!=null){
			return appStack.size();
		}else{
			return 0;
		}
	}
	
	/**
	 * 添加view到堆栈中
	 * @param baseView 要加入到应用栈的view对象
	 */
	public void addStack(BaseView baseView) {
		if (appStack == null) {
			appStack = new LinkedList<BaseView>();
		}
		appStack.addFirst(baseView);
	}

	/**
	 * 获取当前正在显示的UI
	 * @return
	 */
	public BaseView currentView() {
		if (appStack != null && appStack.size() > 0) {
			return appStack.getFirst();
		} else {
			return null;
		}
	}

	/**
	 * 删除当前的view
	 */
	public void removeCurrentView() {
		BaseView baseView = appStack.getFirst();
		removeView(baseView);
	}

	/**
	 * 结束指定的View
	 * 
	 * @param View
	 *            要结束的View
	 */
	public void removeView(BaseView baseView) {
		if (baseView != null) {
			appStack.remove(baseView);
			baseView = null;
		}
	}

	/**
	 * 删除所有的栈内容
	 * @param clazz
	 */
	public void removeAllView(Class<? extends BaseView> clazz) {
		for (BaseView baseView : appStack) {
			if (baseView.getClass() == clazz) {
				removeView(baseView);
			}
		}
	}

	/**
	 * 结束当前堆栈中所有的View
	 */
	public void finishAllView() {
		appStack.clear();
	}

}
