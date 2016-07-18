//package com.jayqqaa12.news.ui;
//
//import java.util.HashMap;
//
//import org.androidannotations.annotations.Click;
//import org.androidannotations.annotations.EActivity;
//
//import android.app.Activity;
//import android.os.Handler.Callback;
//import android.os.Message;
//import android.view.View;
//import android.widget.Toast;
//import cn.sharesdk.framework.Platform;
//import cn.sharesdk.framework.PlatformActionListener;
//import cn.sharesdk.framework.utils.UIHandler;
//import cn.sharesdk.sina.weibo.SinaWeibo;
//import cn.sharesdk.tencent.qzone.QZone;
//
//import com.jayqqaa12.abase.kit.MsgKit;
//import com.jayqqaa12.abase.kit.common.L;
//import com.jayqqaa12.news.R;
//
//@EActivity(R.layout.third_party_login_page)
//public class LoginActivity extends Activity implements Callback, PlatformActionListener
//{
//	// private static final int MSG_USERID_FOUND = 1;
//	// private static final int MSG_AUTH_ERROR = 4;
//	private static final int MSG_LOGIN = 2;
//	private static final int MSG_AUTH_CANCEL = 3;
//	private static final int MSG_AUTH_COMPLETE = 5;
//
//	private Platform platform;
//
//	@Click({ R.id.tvWeibo, R.id.tvQq })
//	public void onClick(View v)
//	{
//		switch (v.getId())
//		{
//		case R.id.tvWeibo:
//			authorize(new SinaWeibo(this));
//			break;
//		case R.id.tvQq:
//			authorize(new QZone(this));
//			break;
//		}
//	}
//
//	private void authorize(Platform plat)
//	{
//		platform = plat;
//		if (plat.isValid())
//		{
//			String userId = plat.getDb().getUserId();
//			if (userId != null)
//			{
//				L.i("user id = " + userId);
//				MsgKit.sendMessage(this, MSG_LOGIN, null);
//				return;
//			}
//		}
//		// 没有就去授权
//		plat.setPlatformActionListener(this);
//		plat.SSOSetting(true);
//		plat.showUser(null);
//	}
//
//	public void onComplete(Platform platform, int action, HashMap<String, Object> res)
//	{
//		if (action == Platform.ACTION_USER_INFOR)
//		{
//			UIHandler.sendEmptyMessage(MSG_AUTH_COMPLETE, this);
//			MsgKit.sendMessage(this, MSG_LOGIN, res);
//		}
//	}
//
//	public void onError(Platform platform, int action, Throwable t)
//	{
//		if (action == Platform.ACTION_USER_INFOR) platform.removeAccount();
//
//		L.i(" error = " + t.getMessage());
//	}
//
//	public void onCancel(Platform platform, int action)
//	{
//		if (action == Platform.ACTION_USER_INFOR) UIHandler.sendEmptyMessage(MSG_AUTH_CANCEL, this);
//	}
//
//	public boolean handleMessage(Message msg)
//	{
//		switch (msg.what)
//		{
//		case MSG_LOGIN:
//
//			L.i(" user info =" + msg.obj);
//			platform.getDb().getUserId();
//			// TODO
//			/***
//			 * 把 userid 发给 服务器 看看有没有注册 有的话直接登录 否则 要注册 把userInfo 发给服务器 信息发给服务器注册
//			 * 
//			 */
//
//			break;
//		case MSG_AUTH_CANCEL:
//			Toast.makeText(this, "canel", Toast.LENGTH_SHORT).show();
//			break;
//		case MSG_AUTH_COMPLETE:
//			Toast.makeText(this, "auth complete", Toast.LENGTH_SHORT).show();
//			break;
//		}
//		return false;
//	}
//
//}
