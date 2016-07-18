package com.itheima.redbaby.ui;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.itheima.redbaby.GloableParameters;
import com.itheima.redbaby.R;
import com.itheima.redbaby.bean.UserInfo;
import com.itheima.redbaby.engine.impl.UserInfoServiceImpl;
import com.itheima.redbaby.net.HttpClientUtil;
import com.itheima.redbaby.net.NetUtil;
import com.itheima.redbaby.ui.manager.BaseView;
import com.itheima.redbaby.ui.manager.MiddleManager;
import com.itheima.redbaby.utils.PromptManager;

/**
 * 我的账户中心
 * 
 * @author zl
 * 
 */
public class MyCountCenterView extends BaseView implements OnClickListener {

	private TextView loginOut_text;
	private TextView my_name_text;
	private TextView my_bonus_text;
	private TextView my_level_text;
	private UserInfo userInfo;

	public MyCountCenterView(Context context) {
		super(context);
	}

	@Override
	protected void loadMiddleLayout() {
		middleView = View.inflate(context, R.layout.dl_count_center, null);
	}

	@Override
	protected void findViewById() {
		loginOut_text = (TextView) middleView.findViewById(R.id.dl_logout_text);

		my_name_text = (TextView) middleView.findViewById(R.id.dl_my_name_text);
		my_bonus_text = (TextView) middleView.findViewById(R.id.dl_my_bonus_text);
		my_level_text = (TextView) middleView.findViewById(R.id.dl_my_level_text);

	}

	@Override
	public void onClick(View v) {
		MiddleManager.getInstance().changeView(HomeView.class, null);
	}

	@Override
	protected void setListener() {
		loginOut_text.setOnClickListener(this);
	}

	@Override
	protected void processEngine() {

		checkLogin();
	}

	private void checkLogin() {
		new MyHttpTask<UserInfo>() {

			@Override
			protected Object doInBackground(UserInfo... params) {

				UserInfoServiceImpl userInfoServiceImpl = new UserInfoServiceImpl();
				List<UserInfo> userInfos = userInfoServiceImpl.getServiceProdects();
				userInfo = userInfos.get(0);
				String username = userInfo.getUsername();
				int bonus = userInfo.getBonus();
				String level = userInfo.getLevel();
				// bundle = new Bundle();
				// bundle.putString("name", username);
				// bundle.putInt("bonus", bonus);
				// bundle.putString("level", level);

				Bundle result = new Bundle();
				result.putString("name", username);
				result.putInt("bonus", bonus);
				result.putString("level", level);

				return result;
			}

			protected void onPostExecute(Object result) {
				PromptManager.closeProgressDialog();
				if (result != null) {
					bundle = (Bundle) result;
					// 界面跳转
					PromptManager.showToast(context, "退出登录！");
					// MiddleManager.getInstance().goBack();
					MiddleManager.getInstance().changeView(HomeView.class, bundle);
				} else {
					PromptManager.showToast(context, "服务器忙！");
				}
				super.onPostExecute(result);
			}

		}.executeProxy(userInfo);
	}

	@Override
	protected int getID() {
		return GloableParameters.USER_ID;
	}

	// @Override
	// public void onResume() {
	// my_name_text.setText(bundle.getString("username"));
	// my_bonus_text.setText(bundle.getInt("bonus"));
	// my_level_text.setText(bundle.getString("level"));
	//
	// }

}
