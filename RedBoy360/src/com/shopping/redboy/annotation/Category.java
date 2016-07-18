package com.shopping.redboy.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于控制标题栏显示和底部导航的对应图标的显示
 * @author Administrator
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Category {
	/**
	 * 对应底部导航的所属类别
	 * @return
	 */
	int id();
	/**
	 * 中间标题显示的内容
	 * @return
	 */
	String title();
	/**
	 * 左边按钮显示的内容，如果不想显示设置为“”
	 * @return
	 */
	String leftbutton();
	/**
	 * 右边按钮显示的内容，如果不想显示设置为“”
	 * @return
	 */
	String rightbutton();
}
