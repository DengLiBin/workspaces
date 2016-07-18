package com.jayqqaa12.abase.annotation.db;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 当前的 domain  要继承父类 的属性时 用这个
* @author jayqqaa12 
* @date 2013-5-23
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME) 
public @interface Parent
{

}
