package com.itheima.redbaby.bean;

import java.io.Serializable;

/**
 * 用户信息
 * 
 * @author zl
 * 
 */
public class UserInfo implements Serializable {
	private int userId;
	private int bonus;//积分
	private String level;//等级
	private String usersession;//用户会话
	private String ordercount;//订单号
	private String favoritescount;//收藏数量
	private String username;
	private String password;

	public UserInfo() {

	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getUsersession() {
		return usersession;
	}

	public void setUsersession(String usersession) {
		this.usersession = usersession;
	}

	public String getOrdercount() {
		return ordercount;
	}

	public void setOrdercount(String ordercount) {
		this.ordercount = ordercount;
	}


	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", bonus=" + bonus + ", level="
				+ level + ", usersession=" + usersession + ", ordercount="
				+ ordercount + ", favoritescount=" + favoritescount
				+ ", username=" + username + ", password=" + password + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFavoritescount() {
		return favoritescount;
	}

	public void setFavoritescount(String favoritescount) {
		this.favoritescount = favoritescount;
	}


}
