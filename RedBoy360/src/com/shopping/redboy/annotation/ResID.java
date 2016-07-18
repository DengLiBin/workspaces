package com.shopping.redboy.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResID {
	/**
	 * 对应资源的ID(R文件中的所有资源ID)
	 * @return
	 */
	int id();
}
