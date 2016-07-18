package com.jayqqaa12.abase.annotation.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 只有 在 继承 相关 abase 类里面 才能使用
 * 注意 读取 drawable 数组 是不可以的
 * @author jayqqaa12
 * @date 2013-5-5
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FindRes
{
	public int id();

	public ResType type();

	public enum ResType
	{
		BOOLEAN, COLOR, DRAWABLE, INT, INT_ARRAY, STRING, STRING_ARRAY, TEXT, TEXT_ARRAY
	}
}