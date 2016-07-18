package com.shopping.redboy.dao.Impl;

import java.io.Serializable;
import java.util.List;

import android.content.Context;

import com.shopping.redboy.dao.Interface.HelpDao;
import com.shopping.redboy.domain.Help;

public class HelpDaoImpl extends BaseDaoImpl<Help> implements HelpDao {
	public HelpDaoImpl(Context context) {
		super(context);
	}
}
