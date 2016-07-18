package com.bin.clock;

import java.util.Timer;
import java.util.TimerTask;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class TimerView extends LinearLayout{
	private Button mBtnStart,mBtnPause,mBtnContinue,mBtnRestart;
	private EditText mEditHour,mEditMin,mEditSec;
	private Timer timer=new Timer();
	private TimerTask timerTask=null;
	private int allTimerCount=0;
	private static final int MSG_WHAT_TIMEISUP=1;
	private static final int MSG_WHAT_TIMEPIC=2;
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MSG_WHAT_TIMEISUP:
				new AlertDialog.Builder(getContext()).setTitle("Time is up").setMessage("时间到").
				setNegativeButton("取消",null).show();
				break;
			case MSG_WHAT_TIMEPIC:
				int hour=allTimerCount/60/60;
				int min=(allTimerCount/60)%60;
				int sec=allTimerCount%60;
				mEditHour.setText(hour+"");
				mEditMin.setText(min+"");
				mEditSec.setText(sec+"");
				break;
			default:
				break;
			}
		}
	};
	public TimerView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public TimerView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TimerView(Context context) {
		super(context);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		
		mBtnStart=(Button) findViewById(R.id.btn_start);
//		mBtnStart.setVisibility(View.VISIBLE);
		mBtnStart.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				startTimer();//开始倒计时
			}
		});
		
		mBtnPause=(Button) findViewById(R.id.btn_pause);
//		mBtnPause.setEnabled(false);
		mBtnPause.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				stopTimer();//停止倒计时
			}
		});
		mBtnContinue=(Button) findViewById(R.id.btn_continue);
//		mBtnContinue.setVisibility(View.GONE);
		mBtnContinue.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				startTimer();//开始倒计时
			}
		});
		
		mBtnRestart=(Button) findViewById(R.id.btn_restart);
//		mBtnRestart.setVisibility(View.GONE);
		mBtnRestart.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				stopTimer();//停止倒计时
				allTimerCount=0;
				mEditHour.setText("00");
				mEditMin.setText("00");
				mEditSec.setText("00");
			}
		});
		
		mEditHour=(EditText) findViewById(R.id.et_hour);
		mEditHour.setText("00");
			
		mEditMin=(EditText) findViewById(R.id.et_min);
		mEditMin.setText("00");
		
		mEditSec=(EditText) findViewById(R.id.et_sec);
		mEditSec.setText("00");
		
		mEditHour.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
				
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
		mEditMin.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				if(!TextUtils.isEmpty(s)){
					int value=Integer.parseInt(s.toString());
					if(value>59){
						mEditMin.setText("59");
					}else if(value<0){
						mEditMin.setText("00");
					}
				}
			}
		});
		mEditSec.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {			
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				if(!TextUtils.isEmpty(s)){
					int value=Integer.parseInt(s.toString());
					if(value>59){
						mEditSec.setText("59");
					}else if(value<0){
						mEditSec.setText("00");
					}
				}
			}
		});
	}
	
	private void startTimer(){
		if(timerTask==null){
			allTimerCount=Integer.parseInt(mEditHour.getText().toString())*60*60+
							Integer.parseInt(mEditMin.getText().toString())*60+
							Integer.parseInt(mEditSec.getText().toString());
			timerTask=new TimerTask(){				
				@Override
				public void run() {//子线程中运行的
				
					while(true){
						
						try {
							Thread.sleep(1000L);
							allTimerCount--;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(allTimerCount<=0){
							//new AlertDialog.Builder(getContext()).setTitle("Time is up").setMessage("时间到").
							//setNegativeButton("取消",null).show();
							Message msg=handler.obtainMessage();
							msg.what=MSG_WHAT_TIMEISUP;
							handler.sendMessage(msg);
							stopTimer();
							break;
						}else{
							Message msg=handler.obtainMessage();
							msg.what=MSG_WHAT_TIMEPIC;
							handler.sendMessage(msg);
						}
					}
					
				}
				
			};
			timer.schedule(timerTask, 1000,1000);
		}
	}
	private void stopTimer(){
		if(timerTask!=null){
			timerTask.cancel();
			timerTask=null;
		}
	}
}
