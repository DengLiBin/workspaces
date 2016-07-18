package com.shopping.redboy.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ColoumName {
	/**
	 * 列名
	 * @return
	 */
	String value();
	/**
	 * 设定是否自增，默认不用设置
	 * @return
	 */
	boolean autoincrement() default false ;
}
