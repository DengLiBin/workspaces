package com.itheima.redbaby.bean;

import java.io.Serializable;
import java.lang.ref.SoftReference;

/**
 * 一级分类的bean
 * 
 * @author Crist
 * 
 */
public class ClassifyFist implements Serializable {
	private String bigname;// 一级分类的名称
	private String pic;// 一级分类的图片
	private String id;// 一级分类的ID

	public ClassifyFist() {
		
	}

	public ClassifyFist(String bigname, String pic, String id) {
		super();
		this.bigname = bigname;
		this.pic = pic;
		this.id = id;
	}

	public String getBigname() {
		return bigname;
	}

	public void setBigname(String bigname) {
		this.bigname = bigname;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ClassifyFist [bigname=" + bigname + ", pic=" + pic + ", id=" + id + "]";
	}

}
