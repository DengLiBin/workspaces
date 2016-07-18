package com.itheima.redbaby.bean;

import java.io.Serializable;
import java.util.List;
/**
 * 收藏夹的bean
 * @author Crist
 *
 */
public class FavoriteInfo implements Serializable {
	
	private String response;
	//商品的集合
	private List<Product> FavoriteInfo;
	//商品总数
	private String list_count;
	public FavoriteInfo() {
		
	}
	
	public FavoriteInfo(String response, List<Product> favoriteInfo,
			String list_count) {
		super();
		this.response = response;
		FavoriteInfo = favoriteInfo;
		this.list_count = list_count;
	}

	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public List<Product> getFavoriteInfo() {
		return FavoriteInfo;
	}
	public void setFavoriteInfo(List<Product> favoriteInfo) {
		FavoriteInfo = favoriteInfo;
	}
	public String getList_count() {
		return list_count;
	}
	public void setList_count(String list_count) {
		this.list_count = list_count;
	}

	@Override
	public String toString() {
		return "FavoriteInfo [response=" + response + ", FavoriteInfo="
				+ FavoriteInfo + ", list_count=" + list_count + "]";
	}
	

}
