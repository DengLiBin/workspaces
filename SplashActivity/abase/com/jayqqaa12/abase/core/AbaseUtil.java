package com.jayqqaa12.abase.core;

import android.content.Context;

/**
 * 
 * 如果不想 继承 AbaseApp
 * 又想 使用 abase 里面的 工具类
 * 记得 在你 当前的 application 类 里面
 * 设置  Abase.setContext(context)
 * 并且 传递 一个 context 给 他，
 * 
 * @throws  NULLPaintException
 * @author jayqqaa12
 * @date 2013-5-14
 */
public class AbaseUtil
{
	protected static Context getContext()
	{
		return Abase.getContext() ;
	}
	
	

}
