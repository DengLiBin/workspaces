package com.jayqqaa12.abase.core;


/**
 * 要用到 db 的时候 继承 它  
 * 
 * 直接 可以 用 db属性 了 不用 自己 创建 
 * 
 * 要 打开 自定义的  数据库 时 不用 继承
 * 
 * 
 * 
* @author jayqqaa12 
* @date 2013-5-20
 */
public class AbaseDaoEngine extends AbaseEngine
{
	protected AbaseDao dao = AbaseDao.create();

}
