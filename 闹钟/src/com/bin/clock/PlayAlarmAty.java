package com.bin.clock;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;

public class PlayAlarmAty extends Activity {
	private MediaPlayer mp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_alarm);
		mp=MediaPlayer.create(this, R.raw.abc);
		mp.start();
	}
	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mp.stop();
		mp.release();// Õ∑≈
	}
}
