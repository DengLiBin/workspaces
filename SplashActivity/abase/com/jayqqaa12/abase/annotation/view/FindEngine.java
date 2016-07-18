package com.jayqqaa12.abase.annotation.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



/**
 * 只有 继承  abaseService 的service 
 * 可以通过这个 方式 找到
 * 同时 只有 在 继承 相关 abase 类里面 才能使用
 * 
* @author jayqqaa12 
* @date 2013-5-5
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FindEngine
{
	public String name() default "";
	
}
