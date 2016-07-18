package com.itheima.redbaby;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.itheima.redbaby.ui.MainActivity;
import com.itheima.redbaby.utils.AnimationController;

/**
 * 第一个加载的界面，是为了做一些准备工作
 * 
 * @author wmy
 * 
 */
public class FirstActivity extends Activity {

	protected static final int ISFIRST = 0;
	protected static final int ISNOTFIRST = 1;
	private ImageView iv_welcome;
	private ImageView iv_bear;
	private ImageView iv_bearboy;
	private SharedPreferences sp;
	private Intent intent;

	private Handler handler = new Handler() {

	};
	private Message msg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		sp = getSharedPreferences("config", MODE_PRIVATE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		findViewById();
		setAnimation();
		addShortCut();
		isShowingHome();
	}

	private void enterHome() {
		intent = new Intent(FirstActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}

	private void enterSplash() {
		intent = new Intent(FirstActivity.this, Splashctivity.class);
		startActivity(intent);
		finish();
	};

	/**
	 * 设置动画
	 */
	private void setAnimation() {
		// TODO Auto-generated method stub
		AnimationController.fadeIn(iv_bearboy, 1000, 0);
		AnimationController.scaleIn(iv_bear, 1000, 1000);
		AnimationController.fadeIn(iv_welcome, 1000, 2000);
	}

	/**
	 * 通过sp判断是否进入主界面
	 */
	private void isShowingHome() {
		boolean b = sp.getBoolean("isfirst", true);
		if (b) {
			Editor edit = sp.edit();
			edit.putBoolean("isfirst", false);
			edit.commit();
			handler.postDelayed(new Runnable() {

				@Override
				public void run() {
					enterSplash();
				}
			}, 4000);
		} else {
			handler.postDelayed(new Runnable() {

				@Override
				public void run() {
					enterHome();
				}
			}, 4000);
		}
	}

	/**
	 * 添加快捷方式
	 */
	private void addShortCut() {
		Editor editor = sp.edit();
		boolean b = sp.getBoolean("shortcut", false);
		if (b) {
			return;
		}
		Intent intent = new Intent();
		intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
		intent.putExtra(Intent.EXTRA_SHORTCUT_ICON, BitmapFactory.decodeResource(getResources(), R.drawable.icon));
		intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "熊孩子");
		Intent i = new Intent();
		i.setAction("android.intent.action.MAIN");
		i.addCategory("android.intent.category.LAUNCHER");
		i.setClassName(getPackageName(), "com.itheima.redbaby.FirstActivity");
		intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, i);
		sendBroadcast(intent);
		editor.putBoolean("shortcut", true);
		editor.commit();
	}

	private void findViewById() {
		iv_welcome = (ImageView) findViewById(R.id.dl_iv_first_welcome);
		iv_bear = (ImageView) findViewById(R.id.dl_iv_first_bear);
		iv_bearboy = (ImageView) findViewById(R.id.dl_iv_bearboy);
	}

}
