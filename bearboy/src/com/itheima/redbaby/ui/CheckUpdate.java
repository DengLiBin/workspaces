package com.itheima.redbaby.ui;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.SearchManager.OnCancelListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.itheima.redbaby.R;
import com.itheima.redbaby.ui.manager.BaseView;
import com.itheima.redbaby.utils.StreamTools;

public class CheckUpdate  {

//	public CheckUpdate(Context context) {
//		super(context);
//	}
//
//	/**
//	 * 获取中间容器
//	 */
//	@Override
//	protected void loadMiddleLayout() {
//		middleView = View.inflate(context, R.layout.dl_count_check_update, null);
//	}
//
//	@Override
//	protected void findViewById() {
//		
//	}
//
//	@Override
//	public void onClick(View v) {
//		
//	}
//
//	@Override
//	protected void setListener() {
//		long startTime = System.currentTimeMillis();
//		try {
//
//			URL url = new URL(getString(R.string.serverurl));
//			// 联网
//			HttpURLConnection conn = (HttpURLConnection) url
//					.openConnection();
//			conn.setRequestMethod("GET");
//			conn.setConnectTimeout(4000);
//			int code = conn.getResponseCode();
//			if (code == 200) {
//				// 联网成功
//				InputStream is = conn.getInputStream();
//				// 把流转成String
//				String result = StreamTools.readFromStream(is);
//				Log.i(TAG, "联网成功了" + result);
//				// json解析
//				JSONObject obj = new JSONObject(result);
//				// 得到服务器的版本信息
//				String version = (String) obj.get("version");
//
//				description = (String) obj.get("description");
//				apkurl = (String) obj.get("apkurl");
//
//				// 校验是否有新版本
//				if (getVersionName().equals(version)) {
//					// 版本一致，没有新版本，进入主页面
//				} else {
//					// 有新版本，弹出一升级对话框
//
//				}
//
//			}
//
//			long endTime = System.currentTimeMillis();
//			// 我们花了多少时间
//			long dTime = endTime - startTime;
//			// 2000
//			if (dTime < 2000) {
//				try {
//					Thread.sleep(2000 - dTime);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//
//	}
//
//	@Override
//	protected void processEngine() {
//		
//		
//		
//	}
//
//	@Override
//	protected int getID() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	
//	/**
//	 * 弹出升级对话框
//	 */
//	protected void showUpdateDialog() {
//		//this = Activity.this
//		AlertDialog.Builder builder = new Builder(SplashActivity.this);
//		builder.setTitle("提示升级");
////		builder.setCancelable(false);//强制升级
//		builder.setOnCancelListener(new OnCancelListener() {
//			
//			@Override
//			public void onCancel(DialogInterface dialog) {
//				// TODO Auto-generated method stub
//				//进入主页面
//				enterHome();
//				dialog.dismiss();
//				
//			}
//		});
//		builder.setMessage(description);
//		builder.setPositiveButton("立刻升级", new OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				// 下载APK，并且替换安装
//				if (Environment.getExternalStorageState().equals(
//						Environment.MEDIA_MOUNTED)) {
//					// sdcard存在
//					// afnal
//					FinalHttp finalhttp = new FinalHttp();
//					finalhttp.download(apkurl, Environment
//							.getExternalStorageDirectory().getAbsolutePath()+"/mobilesafe2.0.apk",
//							new AjaxCallBack<File>() {
//
//								@Override
//								public void onFailure(Throwable t, int errorNo,
//										String strMsg) {
//									t.printStackTrace();
//									Toast.makeText(getApplicationContext(), "下载失败", 1).show();
//									super.onFailure(t, errorNo, strMsg);
//								}
//
//								@Override
//								public void onLoading(long count, long current) {
//									// TODO Auto-generated method stub
//									super.onLoading(count, current);
//									tv_update_info.setVisibility(View.VISIBLE);
//									//当前下载百分比
//									int progress = (int) (current * 100 / count);
//									tv_update_info.setText("下载进度："+progress+"%");
//								}
//
//								@Override
//								public void onSuccess(File t) {
//									// TODO Auto-generated method stub
//									super.onSuccess(t);
//									installAPK(t);
//								}
//								/**
//								 * 安装APK
//								 * @param t
//								 */
//								private void installAPK(File t) {
//								  Intent intent = new Intent();
//								  intent.setAction("android.intent.action.VIEW");
//								  intent.addCategory("android.intent.category.DEFAULT");
//								  intent.setDataAndType(Uri.fromFile(t), "application/vnd.android.package-archive");
//								  startActivity(intent);
//								}
//							});
//				} else {
//					Toast.makeText(context, "没有sdcard，请安装上在试",
//							0).show();
//					return;
//				}
//			}
//		});
//		builder.setNegativeButton("下次再说", new OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				// TODO Auto-generated method stub
//				dialog.dismiss();
//				enterHome();// 进入主页面
//			}
//		});
//		builder.show();
//
//	}
//	
//	/**
//	 * 得到应用程序的版本名称
//	 */
//
//	private String getVersionName() {
//		// 用来管理手机的APK
//		PackageManager pm = getPackageManager();
//
//		try {
//			// 得到知道APK的功能清单文件
//			PackageInfo info = pm.getPackageInfo(getPackageName(), 0);
//			return info.versionName;
//		} catch (NameNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return "";
//		}
//
//	}
//	
}
