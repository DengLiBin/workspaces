package com.shopping.redboy.domain;

import com.shopping.redboy.annotation.ColoumName;
import com.shopping.redboy.annotation.TableName;
import com.shopping.redboy.dao.DBHelper;

@TableName(DBHelper.TABLE_HELP)
public class Help {
	@ColoumName(value = DBHelper.TABLE_ID, autoincrement = true)
	private int id;
	@ColoumName(value = DBHelper.TABLE_HELP_TITLE)
	private String title;
	@ColoumName(value=DBHelper.TABLE_HELP_SUMMARY)
	private String summary;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
}
