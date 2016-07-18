package com.itheima.redbaby.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.opengl.Visibility;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.GloableParameters;
import com.itheima.redbaby.R;
import com.itheima.redbaby.bean.Version;
import com.itheima.redbaby.bean.VersionItem;
import com.itheima.redbaby.engine.CheckVersionEngine;
import com.itheima.redbaby.ui.manager.BaseView;
import com.itheima.redbaby.ui.manager.MiddleManager;
import com.itheima.redbaby.utils.BeanFactory;
import com.itheima.redbaby.utils.Logger;
import com.itheima.redbaby.utils.PromptManager;

/**
 * 账户中心
 * 
 * @author zl 用户登录、注册、订单管理、地址管理、检查升级和收藏夹的入口
 */

public class MyCountView extends BaseView implements OnClickListener {
	
	private static final String TAG = "MyCountView";
	private RelativeLayout login;
	private RelativeLayout register;
	private RelativeLayout order_list;
	private RelativeLayout address_manage;
	private RelativeLayout favorites;
	private RelativeLayout rl_update;

	private RelativeLayout login_title;
	private RelativeLayout logout_title;
	private RelativeLayout dl_logout;
	private RelativeLayout logout;
	
	private TextView my_name;
	private TextView my_bonus;
	private TextView my_level;

	public MyCountView(Context context) {
		super(context);
	}

	@Override
	protected void loadMiddleLayout() {
		middleView = View.inflate(context, R.layout.dl_mycount, null);
		
	}

	@Override
	protected void findViewById() {
		getView();
		
	}

	private void getView() {
		login = (RelativeLayout) middleView.findViewById(R.id.dl_my_login_manage);
		register = (RelativeLayout) middleView.findViewById(R.id.dl_my_register_manage);
		order_list = (RelativeLayout) middleView.findViewById(R.id.dl_my_count_center);
		address_manage = (RelativeLayout) middleView.findViewById(R.id.dl_my_address_manage);
		favorites = (RelativeLayout) middleView.findViewById(R.id.dl_my_favorites_manage);

		rl_update = (RelativeLayout) middleView.findViewById(R.id.dl_check_update);
		
		dl_logout = (RelativeLayout) middleView.findViewById(R.id.dl_logout);
	}
	

	@Override
	protected void setListener() {

		login.setOnClickListener(this);
		register.setOnClickListener(this);
		order_list.setOnClickListener(this);
		address_manage.setOnClickListener(this);
		favorites.setOnClickListener(this);
		rl_update.setOnClickListener(this);
		
		dl_logout.setOnClickListener(this);//退出登录点击监听
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		//退出登录
		case R.id.dl_logout:
			Logger.i(TAG,"退出登录!");
			GloableParameters.USER_ID = 0;
			MiddleManager.getInstance().changeView(HomeView.class, null);
			PromptManager.showToast(context, "退出登录！");
			break;
		
		// 进入登录页面
		case R.id.dl_my_login_manage:
			MiddleManager.getInstance().changeView(LoginView.class, null);
			break;
		// 进入注册页面
		case R.id.dl_my_register_manage:
			MiddleManager.getInstance().changeView(RegisterView.class, null);
			break;
		// 进入 订单中心
		case R.id.dl_my_count_center:
			if (GloableParameters.USER_ID > 0) {

			MiddleManager.getInstance().changeView(OrderView.class, null);
			} else {
				PromptManager.showToast(context, "您还未登录，请登录！");
				MiddleManager.getInstance().changeView(LoginView.class, null);
			}
			break;
		// 进入地址管理
		case R.id.dl_my_address_manage:
			if (GloableParameters.USER_ID > 0) {
			MiddleManager.getInstance().changeView(MyAddressList.class, null);
			} else {
				PromptManager.showToast(context, "您还未登录，请登录！");
				MiddleManager.getInstance().changeView(LoginView.class, null);
			}
			break;
		// 进入收藏夹
		case R.id.dl_my_favorites_manage:
			if (GloableParameters.USER_ID > 0) {
			MiddleManager.getInstance().changeView(FavoriteView.class, null);
			} else {
				PromptManager.showToast(context, "您还未登录，请登录！");
				MiddleManager.getInstance().changeView(LoginView.class, null);
			}
			break;
		// 进入检查升级
		case R.id.dl_check_update:

//		if (GloableParameters.USER_ID > 0) {
//			 MiddleManager.getInstance().changeView(CheckUpdate.class, null);

			String currentVersion = GloableParameters.VERSION;
			Logger.i(TAG, "当前的版本号是："+currentVersion);
			new MyHttpTask<Void>() {
				protected void onPreExecute() {
					PromptManager.showToast(context, "正在检查版本更新...");
				};

				@Override
				protected Object doInBackground(Void... params) {
					CheckVersionEngine checkVersionEngine = BeanFactory.getFactory().getInstance(CheckVersionEngine.class);
					return checkVersionEngine.getVersion();
				}

				protected void onPostExecute(Object result) {
					Version version = (Version) result;
					VersionItem versionItem = version.getVersion();
					if ("true".equals(versionItem.getIsnew())) {
						// 是有新版本
						downNewVersion(versionItem.getUrl());
					} else {
						// 没有新版本
						PromptManager.showToast(context, "当前是最新版本");
					}
				}

			}.executeProxy();

//		} else {
//			PromptManager.showToast(context, "您还未登录，请登录！");
//			MiddleManager.getInstance().changeView(LoginView.class, null);
//		}

		break;
		}
	}

	@Override
	protected void processEngine() {
		// TODO Auto-generated method stub
	}

	@Override
	protected int getID() {
		return ConstantValue.MYCOUNTVIEW;
	}

	/*
	 * @Override public void onClick(View v) { switch (v.getId()) { case
	 * R.id.tv_my_address_in: //点击进入我的地址
	 * MiddleManager.getInstance().changeView(MyNewAddress.class, null);
	 * 
	 * break;
	 * 
	 * default: break; }
	 * 
	 * }
	 */

	private void downNewVersion(String url) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new Builder(context);
		builder.setTitle("更新提示");
		builder.setCancelable(false);

		builder.setMessage("有新版本是否更新？");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		builder.setNegativeButton("取消", null);

		builder.show();

	};
	
	/**
	 * 登录后，显示用户信息
	 */
	@Override
	public void onResume() {
		Logger.i(TAG,GloableParameters.USER_ID+"");
		
		login_title = (RelativeLayout) middleView.findViewById(R.id.dl_my_account_login_title);
		logout_title = (RelativeLayout) middleView.findViewById(R.id.dl_my_account_logout_title);
		dl_logout = (RelativeLayout) middleView.findViewById(R.id.dl_logout);
		
		my_name = (TextView) middleView.findViewById(R.id.dl_my_name_text);
		my_name = (TextView) middleView.findViewById(R.id.dl_my_name_text);
		my_bonus = (TextView) middleView.findViewById(R.id.dl_my_bonus_text);
		my_level = (TextView) middleView.findViewById(R.id.dl_my_level_text);
		
		if(GloableParameters.USER_ID>0){
			Logger.i(TAG,"登录成功，跳转至主页！");
			
//			middleView = View.inflate(context, R.layout.dl_mycount, null);
			login_title.setVisibility(View.VISIBLE);
			logout_title.setVisibility(View.GONE);
			dl_logout.setVisibility(View.VISIBLE);
			
			my_name.setText(GloableParameters.USER_NAME);
			my_bonus.setText(String.valueOf(GloableParameters.BONUS));
			my_level.setText(String.valueOf(GloableParameters.LEVEL));
			
		}else{
			login_title.setVisibility(View.GONE);
			logout_title.setVisibility(View.VISIBLE);
			dl_logout.setVisibility(View.GONE);
		}
		super.onResume();
	}

}
