package com.shopping.redboy.ViewManager;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;

import com.shopping.redboy.util.AnnotationUtil;
import com.shopping.redboy.util.SoftMap;
import com.shopping.redboy.view.BaseView;

import android.R;
import android.R.integer;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

public class UIManager extends Observable {

	private static final String TAG = "UIManager";
	
	private Map<String, Object> map = new HashMap<String, Object>();
	public Map<String, Object> getMap() {
		return map;
	}

	private RelativeLayout middleContainer;
	public void setMiddleContainer(RelativeLayout middleContainer) {
		this.middleContainer = middleContainer;
	}

	private static UIManager manager = new UIManager();
	private UIManager() {}
	public static UIManager getInstance() {
		return manager;
	}

	private Context context;
	public void init(Context context) {
		this.context = context;
	}

	private BaseView currentview;
	public BaseView getCurrentview() {
		return currentview;
	}

	//private Map<Integer, BaseView> VIEWCACHE = new HashMap<Integer, BaseView>();
	private SoftMap<Integer, BaseView> VIEWCACHE = new SoftMap<Integer, BaseView>();
	private LinkedList<Integer> HISTORY = new LinkedList<Integer>();
	
	public void changeView(Class clazz) {
		if (currentview != null && currentview.getClass() == clazz) {
			return;
		}
		Integer id = AnnotationUtil.getClassResID(clazz);
		if(currentview != null){
			currentview.onPause();
		}
		try {
			BaseView tempview = VIEWCACHE.get(id);
			if (tempview != null) {
				currentview = tempview;
				Log.i(TAG, "复用历史缓存");
			} else {
				currentview = (BaseView) clazz.getConstructor(Context.class)
						.newInstance(context);
				VIEWCACHE.put(id, currentview);
				Log.i(TAG, "创建新对象");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		middleContainer.removeAllViews();
		middleContainer.addView(currentview.getShowview());
		changeTitle();
		currentview.onResume();
		currentview.getShowview().startAnimation(
				AnimationUtils.loadAnimation(context,R.anim.fade_in));
		HISTORY.addFirst(id);
	}

	private void changeTitle() {
		setChanged();
		notifyObservers();
	}

	public boolean goback() {
		if(HISTORY.size() > 0){
			if(HISTORY.size() == 1){
				return false;
			}
			HISTORY.removeFirst();
			
			Integer firstResID = HISTORY.getFirst();
			
			currentview.onPause();
			currentview = VIEWCACHE.get(firstResID);
			middleContainer.removeAllViews();
			
			isNull(firstResID);
			middleContainer.addView(currentview.getShowview());	//这里有几率爆空指针异常，加个判断
			currentview.onResume();
			currentview.getShowview().startAnimation(
					AnimationUtils.loadAnimation(context, R.anim.fade_in));
			changeTitle();
			return true;
		}
		return false;
	}
	
	public void isNull(int resID){
		if(currentview==null){
			VIEWCACHE.remove(resID);
			HISTORY.removeFirst();
			Integer firstId = HISTORY.getFirst();
			currentview = VIEWCACHE.get(firstId);
			isNull(firstId);
		}
	}
}
