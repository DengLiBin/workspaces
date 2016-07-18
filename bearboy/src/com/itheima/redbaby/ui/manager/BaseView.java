package com.itheima.redbaby.ui.manager;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.itheima.redbaby.R;
import com.itheima.redbaby.net.NetUtil;
import com.itheima.redbaby.utils.PromptManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * 中间的View的基类
 * 
 * @author zhangyun
 * 
 */
public abstract class BaseView {
	protected Context context;
	/**
	 * 如果要用此bundle，要在初始化的时候new一个
	 */
	protected Bundle bundle;

	/**
	 * 初始化ImageLoader
	 */
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	protected DisplayImageOptions options;

	public BaseView(Context context) {
		this.context = context;
		bundle = new Bundle();
		// 初始化DisplayImageOptions,用于设置图片展示的风格
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.dd_progress) // 设置图片下载期间显示的图片
				.showImageForEmptyUri(R.drawable.dd_unconnectioned) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.dd_unconnectioned) // 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
				.build();

		init();
	}

	public Bundle getBundle() {
		return bundle;
	}

	public void setBundle(Bundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * 初始化中间容器
	 */
	protected View middleView;

	/**
	 * 获取需要在中间容器加载的内容
	 * 
	 * @return
	 */
	public View getChild() {
		// 设置layout参数

		// root=null
		// showInMiddle.getLayoutParams()=null
		// root!=null
		// return root

		// 当LayoutParams类型转换异常，向父容器看齐
		if (middleView.getLayoutParams() == null) {
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.MATCH_PARENT,
					RelativeLayout.LayoutParams.MATCH_PARENT);
			middleView.setLayoutParams(params);
		}

		return middleView;
	}

	/**
	 * 处理中间容器的业务逻辑，在acitvity中必须调用
	 */
	private void init() {
		loadMiddleLayout();// 1
		findViewById();// 2
		setListener();// 3
		processEngine();// 4
	}

	/**
	 * 1.加载中间容器的布局
	 * 
	 * @return 获取的中间容器
	 */
	protected abstract void loadMiddleLayout();

	/**
	 * 2.初始化控件
	 */
	protected abstract void findViewById();

	/**
	 * 3.设置控件的监听，如果没有需要点击的事件就不点击了
	 */
	protected abstract void setListener();

	/**
	 * 4.调用业务逻辑层，处理业务逻辑 访问网络。。获取数据等。。。
	 */
	protected abstract void processEngine();

	/**
	 * 为每个页面设置一个ID
	 * 
	 * @return
	 */
	protected abstract int getID();

	/**
	 * 当中间view开启的时候
	 */
	public void onResume() {

	}

	/**
	 * 当中间容器关闭的时候
	 */
	public void onPause() {

	}

	// TODO Object 是否需要改为其它
	/**
	 * 访问网络的工具
	 * 
	 * @author Administrator
	 * 
	 * @param <Params>
	 * 
	 */
	protected abstract class MyHttpTask<Params> extends
			AsyncTask<Params, Void, Object> {
		/**
		 * 
		 * @param params
		 * 
		 * @return
		 */
		public final AsyncTask<Params, Void, Object> executeProxy(
				Params... params) {
			if (NetUtil.checkNetWork(context)) {
				return super.execute(params);
			} else {
				PromptManager.showNoNetWork(context);
			}
			return null;
		}

	}

	public Context getContext() {
		return context;
	}
}
