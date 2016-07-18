package com.jayqqaa12.abase.core;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.jayqqaa12.abase.util.ConfigSpUtil;

/**
 * 如果 不想 每次都 传递 Context 请继承这个类 然后在 清单文件中配置 如果 直接 通过 getContext 获得 application
 * Context 对象 当然 application Context 不能 成为 生命周期 短的 类的 属性 否则 会使那个类 得不到 销毁。。
 * 
 * 如果不想 继承 AbaseApp 又想 使用 Abase （工具类也一样） 记得 在你 当前的 application 类 里面 设置
 * Abase.setContext(context) 并且 传递 一个 context 给 他，
 * 
 * 如果 发现 getContext  NullPonintException @see setContext()
 * 
 * @author jayqqaa12
 * @date 2013-5-14
 */
public class AbaseApp extends Application
{
	protected static SharedPreferences sp;
	private static Context applicationContext;

	@Override
	public void onCreate()
	{
		super.onCreate();
		applicationContext = getApplicationContext();
		Abase.setContext(applicationContext);
		sp = ConfigSpUtil.getConfigSp();
		Abase.init(this);
	}

	/**
	 * 
	 * 这个 方法的 意义 在于   Test 时候的  context 会先于 application 创建 前 创建 
	 * 所以 在此之前 调用一些 方法 可能会出错
	 * 
	 * @param context
	 * @return
	 */
	public static void setContext(Context context)
	{
		applicationContext = context;
	}

	public static Context getContext()
	{
		return applicationContext;
	}

}
