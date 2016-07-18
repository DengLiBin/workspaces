package com.jikexueyuan.oauth;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.api.AsyncBaiduRunner;
import com.baidu.api.AsyncBaiduRunner.RequestListener;
import com.baidu.api.Baidu;
import com.baidu.api.BaiduDialog.BaiduDialogListener;
import com.baidu.api.BaiduDialogError;
import com.baidu.api.BaiduException;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class MainActivity extends Activity {
	
	private TextView mTvAccessToken = null;
	private TextView mTvResult = null;
	private TextView mTvResultUser = null;
	
	private Baidu mBaidu = null;
	private Gson mGson = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBaidu = new Baidu("V3VW2ulGTGbTM32pXyAs7fvY", this);
		mGson = new Gson();
		setContentView(R.layout.activity_main);
		mTvAccessToken = (TextView)findViewById(R.id.tv_access_token);
		mTvResult= (TextView)findViewById(R.id.tv_result);
		mTvResultUser= (TextView)findViewById(R.id.tv_result_user);
	}
	
	public void OnClickOAuth(View v) {
		mBaidu.authorize(this, true, true, new BaiduDialogListener() {
			
			@Override
			public void onError(BaiduDialogError arg0) {
				refreshUI("error");
			}
			
			@Override
			public void onComplete(Bundle arg0) {
				refreshUI(mBaidu.getAccessToken());
			}
			
			@Override
			public void onCancel() {
				refreshUI("cancel");
			}
			
			@Override
			public void onBaiduException(BaiduException arg0) {
				refreshUI("exception");
			}
		});
	}
	
	public void OnClickGetUserInfo(View v) {
		String token = mBaidu.getAccessToken();
		if(TextUtils.isEmpty(token)) {
			Toast.makeText(this, "Token is null", Toast.LENGTH_SHORT).show();
		} else {
			AsyncBaiduRunner runner = new AsyncBaiduRunner(mBaidu);
			String url = "https://openapi.baidu.com/rest/2.0/passport/users/getInfo";
			runner.request(url, null, "GET", new RequestListener() {
				
				@Override
				public void onIOException(IOException arg0) {
					refreshResultUI("onIOException");
				}
				
				@Override
				public void onComplete(String json) {
					refreshResultUI(json);
				}
				
				@Override
				public void onBaiduException(BaiduException arg0) {
					refreshResultUI("onBaiduException");
				}
			});
			/**
			new Thread(){
				 public void run() {
					 String url = "https://openapi.baidu.com/rest/2.0/passport/users/getInfo";
						try {
							final String jsonText = mBaidu.request(url, null, "GET");
//							final UserEntity user = mGson.fromJson(jsonText, UserEntity.class);
							final UserEntity user = mGson.fromJson(jsonText, new TypeToken<UserEntity>(){}.getType());
							runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									mTvResult.setText(jsonText);
									mTvResultUser.setText(mGson.toJson(user));
								}
							});
						} catch (IOException e) {
							e.printStackTrace();
						} catch (BaiduException e) {
							e.printStackTrace();
						}
				 }
			}.start();
			**/
		}
	}
	

	private void refreshResultUI(final String msg) {
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				mTvResultUser.setText(msg);
			}
		});
	}
	private void refreshUI(final String msg) {
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				mTvAccessToken.setText(msg);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	class UserEntity {
		private String userid;
		private String blood;
		@SerializedName("username")
		private String name;
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		public String getBlood() {
			return blood;
		}
		public void setBlood(String blood) {
			this.blood = blood;
		}
		public String getName() {
			return name;
		}
		public void setName(String username) {
			this.name = username;
		}
		
	}
}
