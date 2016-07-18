package com.jayqqaa12.abase.annotation.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 只有 在 继承  abaseActivity 的时候 才能使用
 * 
 * 特定 属性 只有 继承 特定  activity 才有用
 *  
 *  比如  textChange  只有 继承 AbaseTextActivity
 *  
 * 继承 AbaseAllActvity  可以使 所有 属性有效
 * 
* @author jayqqaa12 
* @date 2013-5-5
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FindView
{
	
	public int id();
	/**
	 * 当 当前  布局 文件 includ引用 了相同 的布局文件的 时候 使用 只寻找 pageId 的时候 设置 id=0 即可
	 */
	public int parId()  default -1;
	/**
	 * 有viewpage 的时候 使用  当只寻找 pageId 的时候 设置 id=0 即可
	 */
	public int pageId() default -1; 
	public int pageNum() default -1;
	
	public int textId() default -1;
	public int imageId() default -1;
	public String tag() default "";
	public boolean touch() default false; 
	public boolean textChanged() default  false;
	public boolean checkedChange() default  false;
	public boolean focusChange() default  false;
	public boolean gesture() default  false;
	public boolean click() default  false;
	public boolean longClick() default  false;
	public boolean itemClick() default  false;
	public boolean itemLongClick() default  false;
	public boolean contextMenu() default  false;
	public boolean itemSelect() default false;
	public boolean childClick() default false;
	public boolean pagerChange() default false;
	
	


}
