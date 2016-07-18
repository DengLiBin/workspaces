package com.jayqqaa12.abase.core;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;

import com.jayqqaa12.abase.util.ConfigSpUtil;

/**
 * 
 * 
 * 
 * 如果 不想 每次都 传递 Context 请继承AbaseApp这个类 然后在 清单文件中配置 然后 可以 直接 通过
 * AbaseApp。getContext 获得 application Context 对象 当然 application Context 不能 成为
 * 生命周期 短的 类的 属性 否则 会使那个类 得不到 销毁。。
 * 
 * @author jayqqaa12
 * @date 2013-5-14
 */
public class AbaseEngine
{
	protected SharedPreferences sp;
	protected Context context;

	public AbaseEngine()
	{
		this.sp = ConfigSpUtil.getConfigSp();
		Abase.init(this);
	}

	/**
	 * 这个 方法更多的时候 用 在 provide 的时候 因为 在 app 初始化前 初始化 provide 所以只能用这个了
	 * 
	 * @param context
	 */
	public AbaseEngine(Context context)
	{
		this.context = context.getApplicationContext();
		this.sp = ConfigSpUtil.getConfigSp();
		Abase.init(this);
	}
	
	
	protected Context getContext()
	{
		return context == null ? Abase.getContext() : context;
	}

	protected ContentResolver getResolver()
	{
		return getContext().getContentResolver();
	}

}
