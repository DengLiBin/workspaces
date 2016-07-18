package com.itheima.redbaby.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.GloableParameters;
import com.itheima.redbaby.R;
import com.itheima.redbaby.bean.UserInfo;
import com.itheima.redbaby.engine.LoginEngine;
import com.itheima.redbaby.ui.manager.BaseView;
import com.itheima.redbaby.ui.manager.MiddleManager;
import com.itheima.redbaby.utils.BeanFactory;
import com.itheima.redbaby.utils.Logger;
import com.itheima.redbaby.utils.PromptManager;

/**
 * 用户登录
 * 
 * @author zl
 * 
 */
public class LoginView extends BaseView implements OnClickListener {

	protected static final String TAG = "LoginView";
	private EditText username;
	private EditText password;
	private Button login;
	private Button register;

	private int tag;

//	private UserInfo userInfo;

	public LoginView(Context context) {
		super(context);
	}

	/**
	 * 获取中间容器
	 */
	@Override
	protected void loadMiddleLayout() {
		middleView = View.inflate(context, R.layout.dl_mycount, null);
	}

	/**
	 * 获取对象
	 */
	@Override
	protected void findViewById() {

		middleView = (ViewGroup) View.inflate(context, R.layout.dl_count_login,
				null);

		username = (EditText) middleView
				.findViewById(R.id.dl_user_login_username);
		password = (EditText) middleView
				.findViewById(R.id.dl_user_login_password);
		login = (Button) middleView.findViewById(R.id.dl_user_login);
		register = (Button) middleView.findViewById(R.id.dl_user_login_regist);
	}

	/**
	 * 设置监听
	 */
	@Override
	public void setListener() {

		login.setOnClickListener(this);
		register.setOnClickListener(this);
	}

	/**
	 * 点击事件
	 */
	@Override
	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.dl_user_login:
			// 登录
			String userName = username.getText().toString();
			String psw = password.getText().toString();
			if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userName)) {
				PromptManager.showToast(context, "用户名或密码不能为空！");
				// Toast.makeText(this, "用户名或密码不能为空", 0).show();
				return;
			}
			userLogin(userName,psw);
			break;

		case R.id.dl_user_login_regist:
			MiddleManager.getInstance().changeView(RegisterView.class, null);
			break;
		}

	}

	// private void register(View view) {
	// MiddleManager.getInstance().changeView(RegisterView.class, null);
	//
	// }
	//
	// private void login(View view) {
	//
	//
	// }

	private void userLogin(String username,String password) {
		
		new MyHttpTask<String>() {

			@Override
			protected Object doInBackground(String... params) {
				LoginEngine userLoginEngine = BeanFactory.getFactory().getInstance(LoginEngine.class);
				return userLoginEngine.getServiceUserInfo(params[0], params[1]);
			}

			@Override
			protected void onPostExecute(Object result) {
				
				PromptManager.closeProgressDialog();
				if (result != null) {
					
					// 界面跳转
					PromptManager.showToast(context, "登录成功！");
					UserInfo userInfo = (UserInfo) result;
					GloableParameters.USER_NAME = userInfo.getUsername();
					GloableParameters.USER_ID = userInfo.getUserId();
					GloableParameters.BONUS = userInfo.getBonus();
					GloableParameters.LEVEL = userInfo.getLevel();
					
					Logger.i(TAG,GloableParameters.USER_ID+"");
					
					LoginView.this.username.setText("");
					LoginView.this.password.setText("");
					MiddleManager.getInstance().changeView(MyCountView.class, null);
				} else {
					PromptManager.showToast(context, "服务器忙！");
				}
				
				super.onPostExecute(result);
			}
		
		}.executeProxy(username,password);
		
	}

	@Override
	protected void processEngine() {

	}

	@Override
	protected int getID() {
		// TODO Auto-generated method stub
		return GloableParameters.USER_ID;
	}


}
