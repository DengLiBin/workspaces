package com.shopping.redboy ;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SplashActivity extends Activity{
	
	private TextView tv_splash_version;
	private RelativeLayout rl_splash_root;
	protected static final int LOAD_MAINUI = 1;
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case LOAD_MAINUI:
				loadMainUI();
				break;
			}
		};
	};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash);
		rl_splash_root = (RelativeLayout) findViewById(R.id.rl_splash_root);
		tv_splash_version = (TextView) findViewById(R.id.tv_splash_version);

		tv_splash_version.setText("欢迎光临红孩子电子商场");
		tv_splash_version.setTextColor(Color.RED);
		
		AlphaAnimation aa = new AlphaAnimation(0.2f, 1.0f);
		aa.setDuration(5000);
		rl_splash_root.startAnimation(aa);
		
		handler.postDelayed(new Runnable() {
			public void run() {
				loadMainUI();
			}
		}, 2000);
		
	}
	private void loadMainUI() {
		Intent intent = new Intent(SplashActivity.this,MainActivity.class);
		startActivity(intent);
		finish();
	}
}
