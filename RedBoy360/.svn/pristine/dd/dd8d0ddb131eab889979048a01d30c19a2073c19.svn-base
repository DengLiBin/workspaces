package com.shopping.redboy.view;

import com.shopping.redboy.util.AnnotationUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public abstract class BaseView implements OnClickListener {
	protected ViewGroup showview;

	public ViewGroup getShowview() {
		return showview;
	}

	protected Context context;

	public BaseView(Context context) {
		this.context = context;
		showview = AnnotationUtil.initField(this, showview, context);
		init();
		setListener();
	}

	/**
	 * 所有非注解成员变量初始化
	 */
	protected abstract void init();

	/**
	 * 设置监听器
	 */
	protected abstract void setListener();

	/**
	 * 中间容器控件的点击事件处理
	 */
	@Override
	public void onClick(View v) {
	}

	/**
	 * 退出界面需要做的工作
	 */
	public void onPause() {
	}

	/**
	 * 进入界面需要做的工作
	 */
	public void onResume() {
	}
}
