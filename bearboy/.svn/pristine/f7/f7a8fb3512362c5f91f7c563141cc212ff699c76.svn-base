package com.itheima.redbaby.ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.itheima.redbaby.GloableParameters;
import com.itheima.redbaby.R;
import com.itheima.redbaby.bean.UserInfo;
import com.itheima.redbaby.engine.LoginEngine;
import com.itheima.redbaby.ui.manager.BaseView;
import com.itheima.redbaby.ui.manager.MiddleManager;
import com.itheima.redbaby.utils.BeanFactory;
import com.itheima.redbaby.utils.PromptManager;

/**
 * 用户注册
 * 
 * @author zl
 * 
 */
public class RegisterView extends BaseView implements OnClickListener {

	private EditText username;
	private EditText password;
	private EditText password2;
	private Button register;

	private String userName;
	private String psw;
	private String psw2;
	private UserInfo userInfo;

	public RegisterView(Context context) {
		super(context);
	}

	/**
	 * 获取中间容器
	 */
	@Override
	protected void loadMiddleLayout() {
		middleView = View.inflate(context, R.layout.dl_count_register, null);
	}

	/**
	 * 获取对象
	 */
	@Override
	protected void findViewById() {

		username = (EditText) middleView
				.findViewById(R.id.dl_user_register_username);
		password = (EditText) middleView
				.findViewById(R.id.dl_user_register_password);
		password2 = (EditText) middleView
				.findViewById(R.id.dl_user_register_password2);
		register = (Button) middleView.findViewById(R.id.dl_user_regist);
	}

	/**
	 * 设置监听
	 */
	@Override
	public void setListener() {

		register.setOnClickListener(this);
	}

	/**
	 * 点击事件
	 */
	@Override
	public void onClick(View v) {
		// 用户输入信息
		if (checkUserInfo()) {
			// 注册
			userName = username.getText().toString();
			psw = password.getText().toString();
			psw2 = password2.getText().toString();
			if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userName)) {
				PromptManager.showToast(context, "用户名或密码不能为空！");
				// Toast.makeText(this, "用户名或密码不能为空", 0).show();
				return;
			}
			// 判断密码是否一致
			if (!psw.equals(psw2)) {
				PromptManager.showToast(context, "两次输入密码不一致！");
				return;
			}else{
				registerUser();
			}
		}
	}


	/**
	 * 注册
	 */
	private void registerUser(){

		new MyHttpTask<UserInfo>() {

			@Override
			protected Object doInBackground(UserInfo... params) {
				// TODO Auto-generated method stub

				LoginEngine registerUser = BeanFactory.getFactory().getInstance(LoginEngine.class);
				userInfo = registerUser
						.getServiceUserInfo(userName, psw);

				return userInfo;
			}

			protected void onPostExecute(Object result) {
				PromptManager.closeProgressDialog();
				username.setText("");
				password.setText("");
				password2.setText("");
				if (result != null) {
					// 界面跳转
					PromptManager.showToast(context, "注册成功！");
					// 界面跳转,保存userid
//					GloableParameters.USER_NAME = userName;
//					GloableParameters.USER_ID = userInfo.getUserId();
					MiddleManager.getInstance().changeView(LoginView.class, null);
				} else {
					PromptManager.showToast(context, "服务器忙！");
				}
				super.onPostExecute(result);
			}

		}.executeProxy(userInfo);

	}
	
	/**
	 * 访问网络
	 */
	@Override
	protected void processEngine() {

		// String proList = "";
		// ByteArrayOutputStream os = null;
		// try {
		// InputStream is = context.getAssets().open("myCount.txt");
		// os = new ByteArrayOutputStream();
		//
		// byte[] buffer = new byte[1024 * 1024];
		//
		// try {
		// while (is.read(buffer) != -1) {
		// os.write(buffer);
		//
		// }
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// } catch (IOException e1) {
		// e1.printStackTrace();
		// }

	}

	@Override
	protected int getID() {
		// TODO Auto-generated method stub
		return GloableParameters.USER_ID;
	}

	/**
	 * 用户信息判断
	 * 
	 * @return
	 */
	private boolean checkUserInfo() {

		return true;
	}

}
