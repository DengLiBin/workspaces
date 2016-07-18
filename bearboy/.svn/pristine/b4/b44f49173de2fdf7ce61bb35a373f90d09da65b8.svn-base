package com.itheima.redbaby.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.view.KeyEvent;

import com.itheima.redbaby.R;
import com.itheima.redbaby.ui.manager.BottomManager;
import com.itheima.redbaby.ui.manager.MiddleManager;
import com.itheima.redbaby.ui.manager.TitleManager;
import com.itheima.redbaby.utils.PromptManager;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		TitleManager.getinstance().init(this);

		BottomManager.getInstance().init(this);

		MiddleManager.getInstance().init(this);

		// 增加观察者
		MiddleManager.getInstance().addObserver(TitleManager.getinstance());
		MiddleManager.getInstance().addObserver(BottomManager.getInstance());
		//切换到主界面
		MiddleManager.getInstance().changeView(HomeView.class, null);
	}

	/**
	 * 重写back键
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			boolean result = MiddleManager.getInstance().goBack();
			if (!result) {
				PromptManager.showExitSystem(this);
			}
			return false;
		}
		return false;
	}

}

