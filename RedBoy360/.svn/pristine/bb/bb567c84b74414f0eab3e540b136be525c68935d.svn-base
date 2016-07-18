package com.shopping.redboy.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标明bean属性对应的json数据名
 * 
 * @author Fire
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JSON {
	/**
	 * 对应的json名称
	 * 
	 * @return
	 */
	String name();

	/**
	 * 对应的数据类型
	 * 
	 * @return
	 */
	Class type();

}
